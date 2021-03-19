package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final UserRepository userDao;

    public HomeController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to Helping Hands");
        return "index";
    }

    @GetMapping("/aboutus")
    public String AboutUs(Model model) {
        model.addAttribute("title", "A Little About Us");
        return "aboutus";
    }

    @GetMapping("/dashboard")
    public String Dashboard(Model model) {
        model.addAttribute("title", "Your Dashboard");
        return "User/userdashboard";
    }


}

