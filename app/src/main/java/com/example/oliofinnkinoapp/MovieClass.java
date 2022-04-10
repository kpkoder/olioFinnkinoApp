package com.example.oliofinnkinoapp;



public class MovieClass {

        private String name = "";
        private String film = "";
        private String time = "";

        public MovieClass(String n, String f, String a) {
            name = n;
            film = f;
            time = a;
        }

        public String getName() {
            return name;
        }

        public String getFilm() { return film; }

        public String getTime() { return time; }

}

