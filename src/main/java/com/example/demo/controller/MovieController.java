package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String getMovie() {
        System.out.println("IN  MovieController->getMovie()");
        return "redirect:/search";
    }
}
