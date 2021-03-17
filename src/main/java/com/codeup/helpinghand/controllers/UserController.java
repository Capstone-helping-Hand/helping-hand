package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Request;
import com.codeup.helpinghand.models.Role;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.repositories.RequestRepository;
import com.codeup.helpinghand.repositories.UserRepository;
import com.codeup.helpinghand.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Scanner;


@Controller
public class UserController{

   private final UserRepository userDao;
    private final PasswordEncoder encoder;
    private final RequestRepository reqDao;
    private final UserService userService;

    public UserController(UserRepository userDao, PasswordEncoder encoder, RequestRepository reqDao,UserService userService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.reqDao = reqDao;
        this.userService = userService;

    }

    Scanner userInput = new Scanner(System.in);

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@ModelAttribute Role role, User user) {

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
    @GetMapping("/adminsignup")
    public String adminSignup(Model model){
        model.addAttribute("user", new User());
        return "adminsignup";
    }
    
    @PostMapping("/adminsignup")
    public String adminSignUp(@ModelAttribute Role role, User user){
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
    @PostMapping(path = "requests")
    public String creatRequest(@ModelAttribute Request request){
        User user = userService.getLoggedInUser();
        request.setUser(user);

        return "requests";
    }




}
