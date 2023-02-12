package com.milovanjakovljevic.movieapp.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.milovanjakovljevic.movieapp.AppExecutors;
import com.milovanjakovljevic.movieapp.modules.MovieModel;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MovieApiClient {

    //LiveDate
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    public static MovieApiClient getInstance(){
        if(instance==null){
            instance=new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient(){
        mMovies=new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }


    public void searchMoviesApi (){
        final Future myHandler= AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                //Retrieve data from api



            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Canceling Retrofit call
                    myHandler.cancel(true);

            }
        }, 5000 , TimeUnit.MICROSECONDS);
    }



}
