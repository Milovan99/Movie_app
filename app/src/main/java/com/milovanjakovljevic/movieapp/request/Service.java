package com.milovanjakovljevic.movieapp.request;

import com.milovanjakovljevic.movieapp.utils.Credentials;
import com.milovanjakovljevic.movieapp.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {


    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public MovieApi getMovieApi(){
        return movieApi;
    }
}
