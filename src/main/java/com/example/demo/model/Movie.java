package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;

@Getter
@Setter
@ToString
public class Movie {
    private String actors;
    private String awards;
    private String country;
    private String director;
    private String genre;
    private String image;
    private String language;
    private String plot;
    private String poster;
    private String rated;
    private LinkedList<HashMap<String,String>> ratings; // Ratings in the API response is a list of {Source, Rating} hashes.
    private String released;
    private String runtime;
    private String title;
    private String writer;
    private String year;

    public String getRottenTomatoesRating() {
        for (HashMap<String,String> rating : this.ratings) {
            // The data return for a movie's ratings from the API looks like this:
            /*

              "Ratings": [
                            {
                              "Source": "Internet Movie Database",
                              "Value": "6.9/10"
                            },
                            {
                              "Source": "Rotten Tomatoes",
                              "Value": "88%"
                            },
                            {
                              "Source": "Metacritic",
                              "Value": "80/100"
                            }
             */
            // So we must iterate through the list of HashMap objects,
            // then check if the Source is Rotten Tomatoes,
            // if it is, we get the Value which contains the number rating.
            for (String key: rating.keySet()) {
                if (key.equals("Source") && rating.get(key).equals("Rotten Tomatoes")) {
                    return rating.get("Value");
                }
            }
        }
        return "N/A";
    }
}
