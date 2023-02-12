package com.milovanjakovljevic.movieapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.milovanjakovljevic.movieapp.modules.MovieModel;

import java.util.List;

public class MovieRepository {
    //Class is acting as repository

    private static MovieRepository instacne;
    //LiveDate
    private MutableLiveData<List<MovieModel>> mMovies;

    public static MovieRepository getInstance(){

        if(instacne==null){
            instacne=new MovieRepository();
        }
        return instacne;
    }

    private MovieRepository(){

        mMovies= new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){return mMovies;}

}
