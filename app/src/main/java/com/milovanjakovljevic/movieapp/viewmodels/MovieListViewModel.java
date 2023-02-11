package com.milovanjakovljevic.movieapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.milovanjakovljevic.movieapp.modules.MovieModel;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    //Class is used for VIEWMODEL

    //LiveDate
    private MutableLiveData<List<MovieModel>> mMovies = new MutableLiveData<>();

    public MovieListViewModel() {

    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    };
}
