package com.example.oliofinnkinoapp;




import android.content.Context;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    public void readMovies(Context context) {
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



    }



    public void Write(Context context) {

        //Getting movielist from WebReader
        WebReader webReader = WebReader.getInstance();
        //getting the list
        this.movieList = webReader.returnMovies();
        try {
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(
                    "FKmovies.txt", Context.MODE_PRIVATE));
            for (MovieClass i : movieList) {
                String tempString = i.getName(); //get movie name
                //String tempTime = i.getAirTime(); //maybe unnecessary data
                //System.out.println(tempString);
                writer.write(tempString + "\n");

            }
                writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, "FKmovies.txt"), true);
            writer.write(tempTest.getBytes());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        /* DONT CARE ABOUT THIS SHITE, none of these worked
        OutputStreamWriter writer = null; //responsible for writing files
        writer = new OutputStreamWriter(context.openFileOutput("movies.txt", Context.MODE_PRIVATE));

        //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "movies.txt");
        //FileOutputStream fos = null;
        //THIS WORKS FOR SOME REASON
        this.movieList = webReader.returnMovies();
        for (MovieClass i : movieList) {
            String tempString = i.getName(); //get movie name
            System.out.println(tempString);
        }*/

            /*
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("FKmovies.txt"), "utf-8"))) {
                    for (MovieClass i : movieList) {
                        String tempString = i.getName(); //get movie name
                        System.out.println(tempString);
                        writer.write(tempString);        //writing movie data
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                /*
                try {
                    fos.write(tempString.getBytes()); //write
                    //Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
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
            //after try catch
             */
    }



}
