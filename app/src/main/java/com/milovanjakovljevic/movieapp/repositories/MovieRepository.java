package com.milovanjakovljevic.movieapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.milovanjakovljevic.movieapp.modules.MovieModel;
import com.milovanjakovljevic.movieapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    //Class is acting as repository

    private static MovieRepository instacne;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance(){

        if(instacne==null){
            instacne=new MovieRepository();
        }
        return instacne;
    }

    private MovieRepository(){

      movieApiClient=MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){return movieApiClient.getMovies();}

    //2-calling method in repository
    public void searchMovieApi(String query,int pageNumber){
        movieApiClient.searchMoviesApi(query,pageNumber);
    }

}
