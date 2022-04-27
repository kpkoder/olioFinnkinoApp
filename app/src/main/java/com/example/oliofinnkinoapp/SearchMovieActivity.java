package com.example.oliofinnkinoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.oliofinnkinoapp.EXTRA_TEXT";

    ListView listView;
    /*String[] AllMovies = {"movie1", "movie2", "movie3"};

    List<String> listOfMovies = Arrays.asList(AllMovies);*/
    List<String> tempList = new ArrayList<String>();

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        //movies for adapter
        FileSaver fileSaver = FileSaver.getInstance();
        this.tempList = fileSaver.returnMovieSorted();
        //REMOVING ;LENGTH FOR ADAPTER
        for (String i : tempList) {
            String[] s = i.split(";");
            String s1 = s[0];
            //System.out.println(s1);
            tempList.set(tempList.indexOf(i),s1);
        }
        String[] adapMovieList = tempList.stream().toArray(String[]::new);

        listView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, adapMovieList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                whatMovie(i);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void whatMovie (int position) {
        //System.out.println(position);
        Intent intent = new Intent(this, RateMovieActivity.class);

        String thisMovie = tempList.get(position);
        intent.putExtra(EXTRA_TEXT, thisMovie);
        startActivity(intent);

    }

}