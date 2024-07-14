package com.yourlivesound.e_book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// first commit
@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody       // all works
    public String home() {

        return "Welcome to the E-Book Application";
    }
}
