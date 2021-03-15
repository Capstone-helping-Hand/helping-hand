package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final UserRepository userDao;

    public HomeController(UserRepository userDao){
        this.userDao = userDao;

    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/aboutus")
    public String LoginForm() {
        return "aboutus";

    }




}

