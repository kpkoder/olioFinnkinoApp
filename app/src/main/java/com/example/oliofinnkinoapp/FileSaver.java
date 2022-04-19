package com.example.oliofinnkinoapp;


import android.content.Context;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

//Sorts the ArrayList of different movies
//Can save and or fetch saved data

/*FILESAVER TEST
       FileSaver fileSaver = FileSaver.getInstance();
       fileSaver.Write(Movies);
       */

public class FileSaver {


    private Context context;
    private List<MovieClass> movieList;

    //singleton principle
    private static FileSaver fileSaver = new FileSaver();
    public static FileSaver getInstance() { return fileSaver; }


    public void Write(List<MovieClass> movieList) {
        this.movieList = movieList;

        OutputStreamWriter writer = null; //responsible for writing files
        try {
            writer = new OutputStreamWriter(context.openFileOutput("movies.txt", Context.MODE_PRIVATE));

            for (MovieClass i : movieList) {
                String tempString = String.valueOf(i); //convert to string
                writer.write(tempString);
                System.out.println(i);
                }

            writer.close();
            } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
