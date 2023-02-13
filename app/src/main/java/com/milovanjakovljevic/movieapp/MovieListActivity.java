package com.milovanjakovljevic.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.milovanjakovljevic.movieapp.modules.MovieModel;
import com.milovanjakovljevic.movieapp.request.Servicey;
import com.milovanjakovljevic.movieapp.response.MovieSearchResponse;
import com.milovanjakovljevic.movieapp.utils.Credentials;
import com.milovanjakovljevic.movieapp.utils.MovieApi;
import com.milovanjakovljevic.movieapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    Button btn;

    //ViewModel
    private MovieListViewModel movieListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);

        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);

        //Calling observers
        ObserveAnyChange();

        //Testing Method
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovieApi("Fast",1);
            }
        });


    }

    //Observing data change
    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //Observing for any change
                if(movieModels != null){
                    for(MovieModel movieModel:movieModels){
                        //Get data in log
                        Log.v("Tag","onChanged: " + movieModel.getTitle());
                    }
                }
            }
        });
    }


    // 4- Calling method in main activity
    private void searchMovieApi(String query,int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }

   /* private void GetRetrofitResponse() {

        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall=movieApi.
                searchMovie(
                        Credentials.API_KEY,
                        "Action",
                        1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
                    Log.i("Tag","the response" + response.body().toString());

                    List<MovieModel> movies=new ArrayList<>(response.body().getMovies() );

                    for(MovieModel movie:movies){
                        Log.v("Tag","The release date "+movie.getRelease_date());

                    }
                }
                else {
                    try {
                        Log.v("Tag","Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });


    }

    private void GetRetrofitResponseAccordingToID(){
        MovieApi movieApi =Servicey.getMovieApi();
        Call<MovieModel> responseCall=movieApi
                .getMovie(550,
                        Credentials.API_KEY
                );



        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200) {
                    MovieModel movie = response.body();
                    Log.v("Tag", "The response " + movie.getTitle());
                } else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }


    */
}

