package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.Request;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.repositories.CategoryRepository;
import com.codeup.helpinghand.repositories.DonationRepository;
import com.codeup.helpinghand.repositories.RoleRepository;
import com.codeup.helpinghand.repositories.UserRepository;
import com.codeup.helpinghand.services.EmailService;
import com.codeup.helpinghand.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Controller
public class DonationController {
    private final DonationRepository donateDao;
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    private final CategoryRepository cateDao;
    private final UserService userService;
    private final EmailService emailService;

    public DonationController(DonationRepository donateDao, UserRepository userDao, RoleRepository roleDao, CategoryRepository cateDao, UserService userService, EmailService emailService) {
        this.donateDao = donateDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.cateDao = cateDao;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping(path = "/donations")
    public String donations(Model model) {
        model.addAttribute("title", "Helping Hands Donations");
        model.addAttribute("donations", donateDao.allApprovedDonations());
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
        model.addAttribute("title", "Edit This Donation");
        model.addAttribute("donation", donateDao.getOne(donationId));
        return "Donations/editdonation";
    }

    @PostMapping("/editdonation/{donationId}")
    public String updateDonation(@PathVariable long donationId, @ModelAttribute Donation editdonation) {
        Donation existingDonation = donateDao.getOne(donationId);
        existingDonation.setTitle(editdonation.getTitle());
        existingDonation.setDescription(editdonation.getDescription());
        existingDonation.setPicture(editdonation.getPicture());
        donateDao.save(existingDonation);
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
    public String saveDonation(@ModelAttribute Donation donation, Model model, @RequestParam("photo") String picture, @RequestParam("don.type") String category) {
        model.addAttribute("title", "Create a Donation");
        model.addAttribute("donation", donateDao.findAll());
        User user = userService.getLoggedInUser();
        Donation newDonation = donateDao.save(donation);
        newDonation.setDate(new Date());
        newDonation.setCategory(cateDao.findByType(category));
        newDonation.setPicture(picture);
        newDonation.setDonator(user);
        newDonation.setApproved(false);
        newDonation.setFulfilled(false);
        donateDao.save(donation);

        String subject = "New Donation Created: " + donateDao.save(donation).getTitle();
        String body = "Dear " + donateDao.save(donation).getDonator().getUsername()
                + ". Thank you for submitting a donation. Your donation invoice is ID#"
                + donateDao.save(donation).getDonationId() + ". Your donation is as follows: \n"
                + donateDao.save(donation).getDescription() + ". "
                + "\nPlease contact us if you have any questions or concerns at info@sa-hh.org.";
        emailService.prepareDonationEmail(donateDao.save(donation), subject, body);

        return "redirect:/donations";
    }

    @GetMapping(path = "/pendingdonations")
    public String pendingDonations(@ModelAttribute Donation donation, Model model) {
        model.addAttribute("title", "Pending Donations");
        model.addAttribute("donations", donateDao.lastFivePending());

        return "Donations/pendingdonations";
    }

    @PostMapping("/pendingdonations/{donationId}/approve")
    public String approveDonation(@PathVariable long donationId) {

            Donation existingDonation = donateDao.getOne(donationId);
            existingDonation.setApproved(true);

            donateDao.save(existingDonation);

            return "redirect:/pendingdonations";
        }


    @PostMapping("/pendingdonations/{donationId}/deny")
    public String denyRequest(@PathVariable long donationId){

        donateDao.deleteById(donationId);


        return "redirect:/pendingdonations";
    }





}
