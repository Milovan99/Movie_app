package com.milovanjakovljevic.movieapp.response;

//This class is for single movie request

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.milovanjakovljevic.movieapp.modules.MovieModel;

public class MovieResponse {
    // Finding Movie Object

    @SerializedName("results")
    @Expose
    private MovieModel movie ;
    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
