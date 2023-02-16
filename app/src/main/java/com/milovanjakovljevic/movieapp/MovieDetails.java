package com.milovanjakovljevic.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.milovanjakovljevic.movieapp.modules.MovieModel;

public class MovieDetails extends AppCompatActivity {

    private ImageView imageViewDetails;
    private TextView titleDetails,descDetails;
    private RatingBar ratingBarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        imageViewDetails=findViewById(R.id.imageView_details);
        titleDetails=findViewById(R.id.textView_title_details);
        descDetails=findViewById(R.id.textView_desc_details);
        ratingBarDetails=findViewById(R.id.ratingBar_details);
        
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        if(getIntent().hasExtra("movie")){
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            Log.v("Tagy","incoming intent :" +movieModel.getTitle());
        }
    }
}