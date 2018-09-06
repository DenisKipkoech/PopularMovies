package com.example.denis.popularmoviesstages1.Adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by denis on 06/09/18.
 */

public class PosterViewModel {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String url){
        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }
}
