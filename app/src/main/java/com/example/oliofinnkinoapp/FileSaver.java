package com.example.oliofinnkinoapp;


import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    private List<MovieClass> movieList;

    //singleton principle
    private static FileSaver fileSaver = new FileSaver();
    public static FileSaver getInstance() { return fileSaver; }

    public void Write() {
        //Getting movielist from WebReader
        WebReader webReader = WebReader.getInstance();
        this.movieList = webReader.returnMovies();

        /* didnt work
        OutputStreamWriter writer = null; //responsible for writing files
        writer = new OutputStreamWriter(context.openFileOutput("movies.txt", Context.MODE_PRIVATE));
        */
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "movies.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            for (MovieClass i : movieList) {
                String tempString = i.getName(); //convert to string
                fos.write(tempString.getBytes()); //write movies
                System.out.println(tempString);
            }
            //Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

    }



}
