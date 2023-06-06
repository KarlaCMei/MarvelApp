package com.example.marvelapp.listsuperheroesresults.view.adapterlistsuperhero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvelapp.R;
import com.example.marvelapp.retrofit.model.Result;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CustomSuperHeroAdapter extends RecyclerView.Adapter<CustomSuperHeroAdapter.ViewHolder> {
    private List<Result> resultsSuperHeros;
    private OnClicSuperHeroListener listener;

    public CustomSuperHeroAdapter(List<Result> resultsSuperHeros, OnClicSuperHeroListener listener) {
        this.resultsSuperHeros = resultsSuperHeros;
        this.listener = listener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.super_hero_item,parent,false);

        ViewHolder vh = new ViewHolder(view);
        vh.setListener(this.listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name_character.setText(resultsSuperHeros.get(position).getName());
        holder.setSuperHeroId(resultsSuperHeros.get(position).id);
        Picasso.with(holder.img_character.getContext()).load(resultsSuperHeros.get(position).getImage() + ".jpg").into(holder.img_character);

    }

    @Override
    public int getItemCount() {
        return resultsSuperHeros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private ImageView img_character;
        private TextView txt_name_character;
        private OnClicSuperHeroListener listener;

        private int superHeroId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview);
            img_character = itemView.findViewById(R.id.imgSuperHero);
            txt_name_character = itemView.findViewById(R.id.text_name);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getListener().onClicSuperHeroListener(getSuperHeroId());
                }
            });
        }

        public int getSuperHeroId() {
            return superHeroId;
        }

        public void setSuperHeroId(int superHeroId) {
            this.superHeroId = superHeroId;
        }

        public OnClicSuperHeroListener getListener() {
            return listener;
        }

        public void setListener(OnClicSuperHeroListener listener) {
            this.listener = listener;
        }

    }

}
