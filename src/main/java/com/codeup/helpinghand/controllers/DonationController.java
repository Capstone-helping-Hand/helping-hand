package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Category;
import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.models.Role;
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
        return "Donations/donations";
    }

    @GetMapping(path = "/singledonation/{donationId}")
    public String donationsbyId(@PathVariable long donationId, Model model) {
        model.addAttribute("title", "single donation");
        model.addAttribute("donation", donateDao.getOne(donationId));
        return "Donations/singledonation";
//        check to see if we create a different html to render a single donation this is an extension of the comment.
//        Create landing page and update donations to that url
    }

    @GetMapping("/editdonation/{donationId}")
    public String editDonation(@PathVariable long donationId, Model model) {
        model.addAttribute("title", "single request");
        model.addAttribute("donation", donateDao.getOne(donationId));
        return "Donations/editdonation";
    }

    @PostMapping("/editdonation/{donationId}")
    public String updateDonation(@PathVariable long donationId, @ModelAttribute Donation donation) {
        donateDao.save(donation);
        return "redirect:/donations";
    }












    @RequestMapping("/donations/{id}/delete")
    public String deletePost(@PathVariable long id) {
        donateDao.deleteById(id);
        return "redirect:/donations";
    }

    @GetMapping("/donationform")
    public String create(Model model) {
        model.addAttribute("donation", new Donation());
        return "Donations/donationform";
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
    public String saveDonation(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("donation", donateDao.findAll());
        User user = userService.getLoggedInUser();
        Donation newDonation = donateDao.save(donation);
        newDonation.setDonator(user);
        donateDao.save(donation);

        return "redirect:/donations";
    }

}
