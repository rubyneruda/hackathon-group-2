package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class MovieController {

    @GetMapping("/search")
    public String searchMovieForm() {
        System.out.println("IN  MovieController->searchMovieForm()");
        return "search_movie";

    }

    @PostMapping("/getMovie")
    // @RequestParam notation and the name of the field
    // from the form in "search_movie.html" file - which is title
    public String getMovie(@RequestParam String title, Model model) throws JsonProcessingException {
        System.out.println("IN  MovieController->getMovie()");
        // We can now get the "title" param from the request
        System.out.println("You searched for a movie with title: " + title);
        // Create the string we are going to use to GET the movie data using the title
        String uri = "https://www.omdbapi.com/?apikey=b79fdda2&t=" + title;
        // RestTemplate is a synchronous client to perform HTTP requests.
        // Because it is synchronous, it will wait to get the response data before moving on to the next line of code.
        RestTemplate restTemplate = new RestTemplate();
        // getForObject() will perform a GET and return a JSON object, which we'll specify to be made a String
        String movieDataResponse = restTemplate.getForObject(uri,String.class);
        // We will use an ObjectMapper to deserialize the string of the JSON and create a Movie model object instance from it
        ObjectMapper mapper = new ObjectMapper();
        // First, we configure the mapper to ignore casing, since the response fields are capitalized,
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // Then, we configure the mapper to ignore unknown fields in the API response, since we just want the fields
        // we specified in our Movie model class.
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // Now we deserialize the JSON string and make a Movie object from it.
        Movie movie = mapper.readValue(movieDataResponse, Movie.class);
        // We can see we now have an Object with the data.
        System.out.println(movie.toString());
        // Next step is to create a page that displays the movie info:
        // TODO: More work.
        model.addAttribute("submittedMovie", movie); // Add submitted object
        return "movie_display"; // Name of Thymeleaf template with display
    }
}
