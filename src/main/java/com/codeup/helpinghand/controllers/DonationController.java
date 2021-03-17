package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.repositories.CategoryRepository;
import com.codeup.helpinghand.repositories.DonationRepository;
import com.codeup.helpinghand.repositories.RoleRepository;
import com.codeup.helpinghand.repositories.UserRepository;
import com.codeup.helpinghand.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DonationController {
    private final DonationRepository donateDao;
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    private final CategoryRepository cateDao;
    private final UserService userService;




    public DonationController(DonationRepository donateDao, UserRepository userDao, RoleRepository roleDao, CategoryRepository cateDao, UserService userService){
        this.donateDao = donateDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.cateDao = cateDao;
        this.userService = userService;
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

    @GetMapping("/donationform")
    public String create(Model model) {
        model.addAttribute("donation", new Donation());
        return "donationform";
    }


//    @PostMapping(path = "/donationform")
//    public String createDonation(@ModelAttribute Donation donation) {
//        User user = userService.getLoggedInUser();
//        donation.setDonator(user);
//
//
//
//        Donation savedonation = donateDao.save(donation);
//        savedonation.getTitle();
//        savedonation.getDescription();
//        savedonation.getPicture();
////        Add a slot for category type on form and implement here once done to store the chosen data. We need this to render on the post as well
//
//
//        return "redirect:/donations";
//    }

    @RequestMapping(value = "/donationform", method = RequestMethod.POST)
    public String saveDonation(@ModelAttribute("donation") Donation donation) {
        donateDao.save(donation);

        return "redirect:/donations";
    }

}
