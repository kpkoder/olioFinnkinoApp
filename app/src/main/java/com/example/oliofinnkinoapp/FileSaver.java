package com.example.oliofinnkinoapp;




import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Sorts the ArrayList of different movies
//Can save and or fetch saved data

public class FileSaver {

    private List<MovieClass> movieList;
    private List<String> movieListSorted;
    private List<String> movieListSaved = new ArrayList<String>();

    //singleton principle
    private static FileSaver fileSaver = new FileSaver();
    public static FileSaver getInstance() { return fileSaver; }

    //returning sorted list
    public List<String> returnMovieSorted() {
        return movieListSorted;
    }

    //returning saved/reviewed movies
    public List<String> returnMovieSaved() {
        return movieListSaved;
    }

    public String reviewReader(String fileName, Context context) {

        String revText = "";
        InputStream reader = null;
        try {
            reader = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));
            String line = "";

            while ((line=br.readLine()) != null) {
                revText += line + "\n";
                //System.out.println(line);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return revText;
    }

    public String getMovieLength(String s) {
        String a = "";
        for (MovieClass i : movieList) {
            if (i.getName().equals(s)) {
                a = String.valueOf(i.getLength());
                //System.out.println(a);
                return a;
            }
        }
        return "";
    }

    public void searchReviews(Context context) {
        movieListSaved = this.movieListSaved;
        movieListSaved.clear(); //null after each iteration
        File folder = new File(context.getFilesDir().getPath());
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                if (file.toString().contains("REVIEW_")) {
                    //System.out.println(file.getName());
                    String tempMovieName = file.getName();
                    movieListSaved.add(tempMovieName);
                }
            }
        }
        /*for (String a : movieListSaved) {
            System.out.println(a);
        }*/
    }

    public void writeReview(String lengthText,String yourName, Float rating, String mName, String reviewText, Context context) {
        //test print
        //System.out.println(reviewText + " "+rating.toString());
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(
                    "REVIEW_"+mName+".txt", Context.MODE_PRIVATE)); //mode_private
            int rating2 = Math.round(rating);
            writer.write(reviewText);
            writer.write("\n\nLength: "+lengthText+" minutes\n");
            writer.write(String.format("Your rating: %d/5\n", rating2));
            writer.write(yourName);
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
                String tempTime = String.valueOf(i.getLength()); //get movie length in minutes
                writer.append(tempString + ";"+tempTime+"\n");

            }
                writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
