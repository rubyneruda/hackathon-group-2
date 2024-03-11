package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MovieController {

//    private StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        super();
//        this.studentService = studentService;
//    }

    @GetMapping("/search")
    public String searchMovieForm() {
        System.out.println("IN  MovieController->searchMovieForm()");
        return "search_movie";

    }

    @PostMapping("/getMovie")
    // @RequestParam notation and the name of the field
    // from the form in "search_movie.html" file - which is title
    public String getMovie(@RequestParam String title) {
        System.out.println("IN  MovieController->getMovie()");
        // We can now get the "title" param from the request
        System.out.println("You searched for a movie with title: " + title);
        return "redirect:/search";
    }
}
