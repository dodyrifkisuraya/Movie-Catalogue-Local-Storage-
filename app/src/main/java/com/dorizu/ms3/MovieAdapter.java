package com.dorizu.ms3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieItem> arrMovie;
    private Context context;

    public MovieAdapter(ArrayList<MovieItem> arrMovie, Context context) {
        this.arrMovie = arrMovie;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder,final int i) {
        final MovieItem movieItem = arrMovie.get(i);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w154/"+movieItem.getThunail())
                .into(movieViewHolder.thumnail);

        movieViewHolder.title.setText(movieItem.getJudul());

        movieViewHolder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bawaKeDetail = new Intent(context, DetailActivity.class);
                MovieItem movieObj = arrMovie.get(i);
                bawaKeDetail.putExtra(DetailActivity.EXTRA_POSTER_JPG, movieObj.getThunail());
                bawaKeDetail.putExtra(DetailActivity.EXTRA_TITLE, movieObj.getJudul());
                bawaKeDetail.putExtra(DetailActivity.EXTRA_OVERVIEW, movieObj.getOverview());
                bawaKeDetail.putExtra(DetailActivity.EXTRA_RELEASE_DATE, movieObj.getTanggalRilis());
                bawaKeDetail.putExtra(DetailActivity.EXTRA_LANG, movieObj.getBahasa());
                bawaKeDetail.putExtra(DetailActivity.EXTRA_RATE, movieObj.getRate());
                bawaKeDetail.putExtra(DetailActivity.EXTRA_RATE_COUNT, movieObj.getRateCount());
                context.startActivity(bawaKeDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrMovie.size() ;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView thumnail;
        TextView title;
        CardView itemCard;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            thumnail = itemView.findViewById(R.id.img_thumnail);
            title = itemView.findViewById(R.id.tv_judul);
            itemCard = itemView.findViewById(R.id.cardview);

        }
    }
}
