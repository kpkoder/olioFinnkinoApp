package com.example.oliofinnkinoapp;




import android.content.Context;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Sorts the ArrayList of different movies
//Can save and or fetch saved data

public class FileSaver {

    private List<MovieClass> movieList;
    private List<String> movieListSorted;
    //private Context context;

    //singleton principle
    private static FileSaver fileSaver = new FileSaver();
    public static FileSaver getInstance() { return fileSaver; }

    //returning sorted list
    public List<String> returnMovieSorted() {
        return movieListSorted;
    }

    public void writeReview(Float rating, String mName, String reviewText, Context context) {
        //test print
        //System.out.println(reviewText + " "+rating.toString());
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(
                    "Review"+mName+".txt", Context.MODE_PRIVATE)); //mode_private
            writer.write(mName + "\n"+"Arvosana: "+rating.toString()+"\n\n"); //first lines
            writer.write(reviewText);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readAndWriteMovies(Context context) {
        InputStream reader = null;
        List<String> movieNames = new ArrayList<String>(); //new list for sorting
        this.movieListSorted = movieListSorted;

        try {
            reader = context.openFileInput("FKmovies.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));
            String line = "";

            while ((line=br.readLine()) != null) {
                movieNames.add(line); //adding movie to new list
                //System.out.println(line);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // sorting, removing dublicates
        movieListSorted = movieNames.stream().sorted().collect(Collectors.toList());
        movieListSorted = movieListSorted.stream().distinct().collect(Collectors.toList());

        //RE-WRITING FKmovies.txt with fixed list
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(
                    "FKmovies.txt", Context.MODE_PRIVATE)); //mode_private
            for (String i : movieListSorted) {
                writer.write(i + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Write(Context context) {

        //Getting movielist from WebReader
        WebReader webReader = WebReader.getInstance();
        //getting the list
        this.movieList = webReader.returnMovies();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(
                    "FKmovies.txt", Context.MODE_APPEND)); //mode_append
            for (MovieClass i : movieList) {
                String tempString = i.getName(); //get movie name
                //String tempTime = i.getAirTime(); //maybe unnecessary data
                //System.out.println(tempString);
                writer.append(tempString + "\n");

            }
                writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
