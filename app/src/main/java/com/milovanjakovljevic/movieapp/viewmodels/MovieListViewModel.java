package com.milovanjakovljevic.movieapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.milovanjakovljevic.movieapp.modules.MovieModel;
import com.milovanjakovljevic.movieapp.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    //Class is used for VIEWMODEL


    private MovieRepository movieRepository;


    public MovieListViewModel() {
        movieRepository=MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    };

    public LiveData<List<MovieModel>> getPop(){
        return movieRepository.getPop();
    };


    //3 Calling method in view-model
    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query,pageNumber);
    }

    public void searchMoviePop(int pageNumber){
        movieRepository.searchMoviePop(pageNumber);
    }

    public void searchNextpage(){
        movieRepository.searchNextPage();
    }
}
