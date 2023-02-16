package com.milovanjakovljevic.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.milovanjakovljevic.movieapp.adapters.MovieRecyclerView;
import com.milovanjakovljevic.movieapp.adapters.OnMovieListener;
import com.milovanjakovljevic.movieapp.modules.MovieModel;
import com.milovanjakovljevic.movieapp.viewmodels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {


    //RecyclerView
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;


    //ViewModel
    private MovieListViewModel movieListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);

        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        searchMovieApi("fast",1);



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


                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }


    // 4- Calling method in main activity
    private void searchMovieApi(String query,int pageNumber){
        movieListViewModel.searchMovieApi(query,pageNumber);
    }

    // 5 -Initializing recyclerView and adding data to it

    private void ConfigureRecyclerView(){
        movieRecyclerAdapter=new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onMovieClick(int position) {

        Toast.makeText(this,"The position"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(String category) {

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

