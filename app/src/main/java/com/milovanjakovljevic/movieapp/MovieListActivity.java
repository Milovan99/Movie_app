package com.milovanjakovljevic.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
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

    boolean isPop=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SetupSearchView();

        recyclerView=findViewById(R.id.recyclerView);
        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopularMovies();

        movieListViewModel.searchMoviePop(1);



    }

    private void ObservePopularMovies() {
        movieListViewModel.getPop().observe(this, new Observer<List<MovieModel>>() {
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

    // 5 -Initializing recyclerView and adding data to it

    private void ConfigureRecyclerView(){
        movieRecyclerAdapter=new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        //RecyclerView Pagination

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1)){

                    movieListViewModel.searchNextpage();

                }
            }
        });



    }

    @Override
    public void onMovieClick(int position) {

     //   Toast.makeText(this,"The position"+position,Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,MovieDetails.class);
        intent.putExtra("movie",movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(String category) {

    }

    private void SetupSearchView() {
        final SearchView searchView= findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPop=false;
            }
        });



    }

}

