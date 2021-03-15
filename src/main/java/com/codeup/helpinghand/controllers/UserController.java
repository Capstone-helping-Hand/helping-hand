package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Role;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.repositories.UserRepository;
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

    public UserController(UserRepository userDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
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

    @GetMapping("/requests")
    public String requests(){
        return "requests";
    }


}
