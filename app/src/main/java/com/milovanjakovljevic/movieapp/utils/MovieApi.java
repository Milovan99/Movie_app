package com.milovanjakovljevic.movieapp.utils;

import com.milovanjakovljevic.movieapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    //Search for movies

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String key,
                                          @Query("query") String query,
                                          @Query("page") int page
    );


}
