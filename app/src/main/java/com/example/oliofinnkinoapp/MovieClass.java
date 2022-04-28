package com.example.oliofinnkinoapp;

//MovieClass objects containing information about saved movies

public class MovieClass {

        private String name = "";
        private Float rating = 0F;
        private int length = 0; //length in minutes
        private String airTime = ""; //when the movie is airing (maybe useless)

        public MovieClass(String n, String aT, Float r, int a) {
            name = n;
            airTime = aT;
            rating = r;
            length = a;
        }

        public String getName() {
            return name;
        }

        public Float getRating() { return rating; }

        public int getLength() { return length; }

        public String getAirTime() { return airTime; }

}

