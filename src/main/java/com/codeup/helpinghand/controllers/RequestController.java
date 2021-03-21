package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Request;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.repositories.CategoryRepository;
import com.codeup.helpinghand.repositories.RequestRepository;
import com.codeup.helpinghand.repositories.RoleRepository;
import com.codeup.helpinghand.repositories.UserRepository;
import com.codeup.helpinghand.services.EmailService;
import com.codeup.helpinghand.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestController {
    private final RequestRepository reqDao;
    private final CategoryRepository cateDao;
    private final RoleRepository roleDao;
    private final UserRepository userDao;
    private final UserService userService;
    private final EmailService emailService;

    public RequestController(RequestRepository reqDao, CategoryRepository cateDao, RoleRepository roleDao, UserRepository userDao, UserService userService, EmailService emailService) {
        this.reqDao = reqDao;
        this.cateDao = cateDao;
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/requests")
    public String request(Model model) {
        model.addAttribute("title", "Helping Hands Requests");
        model.addAttribute("requests", reqDao.findAll());
        return "Requests/requests";
    }

    @GetMapping(path = "/singlereq/{requestId}")
    public String requestById(@PathVariable long requestId, Model model) {
        model.addAttribute("title", "Single Request");
        model.addAttribute("request", reqDao.getOne(requestId));
        return "Requests/singlereq";
    }

    @GetMapping("/reqedit/{requestId}")
    public String editReq(@PathVariable long requestId, Model model) {
        model.addAttribute("title", "Edit a Request");
        model.addAttribute("request", reqDao.getOne(requestId));
        return "Requests/reqedit";
    }

    @PostMapping("/reqedit/{requestId}")
    public String postEdit(Model model, @ModelAttribute Request request) {
        model.addAttribute("title", "Edit a Request");
        reqDao.save(request);
        return "redirect:/requests";
    }

    @RequestMapping("/requests/{requestId}/delete")
    public String deletePost(@PathVariable long requestId) {
        reqDao.deleteById(requestId);
        return "redirect:/requests";
    }

    @GetMapping("/reqform")
    public String create(Model model) {
        model.addAttribute("title", "Create a Request");
        model.addAttribute("request", new Request());
        return "Requests/reqform";
    }

    @RequestMapping(value = "/reqform", method = RequestMethod.POST)
    public String saveRequest(@ModelAttribute Request request, Model model, @RequestParam("file") String picture, @RequestParam("cat.type") String category) {
        model.addAttribute("title", "Create a Request");
        model.addAttribute("request", reqDao.findAll());
        User user = userService.getLoggedInUser();
        Request newReq = reqDao.save(request);
        newReq.setCategory(cateDao.findByType(category));
        newReq.setPicture(picture);
        newReq.setUser(user);
        reqDao.save(request);

        String subject = "New Request Created: " + reqDao.save(request).getTitle();
        String body = "Dear " + reqDao.save(request).getUser().getUsername()
                + ". Thank you for submitting a request. Your request invoice is ID#"
                + reqDao.save(request).getRequestId() + ". Your request is as follows: \n"
                + reqDao.save(request).getDescription() + ". "
                + "\nPlease contact us if you have any questions or concerns at info@sa-hh.org.";
        emailService.prepareRequestEmail(reqDao.save(request), subject, body);

        return "redirect:/requests";
    }

}
