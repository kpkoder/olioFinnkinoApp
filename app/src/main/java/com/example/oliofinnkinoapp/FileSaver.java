package com.example.oliofinnkinoapp;


import java.util.List;

//Sorts the ArrayList of different movies
//Can save and or fetch saved data

/*FILESAVER TEST
       FileSaver fileSaver = FileSaver.getInstance();
       fileSaver.Write(Movies);
       */

public class FileSaver {

    private List<MovieClass> movieList;

    //singleton principle
    private static FileSaver fileSaver = new FileSaver();
    public static FileSaver getInstance() { return fileSaver; }


    public void Write(List<MovieClass> movieList) {
        this.movieList = movieList;

        for (MovieClass i : movieList) {
            System.out.println(i.getName());
        }
    }



}
