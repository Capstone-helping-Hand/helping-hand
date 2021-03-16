package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.repositories.CategoryRepository;
import com.codeup.helpinghand.repositories.DonationRepository;
import com.codeup.helpinghand.repositories.RoleRepository;
import com.codeup.helpinghand.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DonationController {
    private final DonationRepository donateDao;
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    private final CategoryRepository cateDao;




    public DonationController(DonationRepository donateDao, UserRepository userDao, RoleRepository roleDao, CategoryRepository cateDao){
        this.donateDao = donateDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.cateDao = cateDao;
    }

    @GetMapping(path = "/donations")
    public String donations(Model model){
        model.addAttribute("donations", donateDao.findAll());
        return "donations";
    }

    @GetMapping(path = "/donations/{id}")
    public String donationsbyId(@PathVariable long id, Model model) {
        model.addAttribute("title", "single post");
        model.addAttribute("donation", donateDao.getOne(id));
        return "donations";
//        check to see if we create a different html to render a single donation this is an extension of the comment
    }

    @RequestMapping("/donations/{id}/delete")
    public String deletePost(@PathVariable long id) {
        donateDao.deleteById(id);
        return "redirect:/donations";
    }





}
