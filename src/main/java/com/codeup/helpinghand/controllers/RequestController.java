package com.codeup.helpinghand.controllers;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.Request;
import com.codeup.helpinghand.models.User;
import com.codeup.helpinghand.repositories.CategoryRepository;
import com.codeup.helpinghand.repositories.RequestRepository;
import com.codeup.helpinghand.repositories.RoleRepository;
import com.codeup.helpinghand.repositories.UserRepository;
import com.codeup.helpinghand.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.Multipart;
import java.io.IOException;

@Controller
public class RequestController  {
        private final RequestRepository reqDao;
        private final CategoryRepository cateDao;
        private final RoleRepository repoDao;
        private final UserRepository userDao;
        private final UserService userService;


    public RequestController(RequestRepository reqDao, CategoryRepository cateDao, RoleRepository repoDao, UserRepository userDao, UserService userService) {
        this.reqDao = reqDao;
        this.cateDao = cateDao;
        this.repoDao = repoDao;
        this.userDao = userDao;
        this.userService =  userService;
         }



    @GetMapping("/requests")
    public String request(Model model){
        model.addAttribute("requests", reqDao.findAll());
        return "Requests/requests";
    }

    @GetMapping(path = "/singlereq/{requestId}")
    public String requestById(@PathVariable long requestId, Model model){

        model.addAttribute("title", "single request");

        model.addAttribute("request", reqDao.getOne(requestId));

        return "Requests/singlereq";
    }
//comment


    @GetMapping("/reqedit/{requestId}")
    public String editReq(@PathVariable long requestId, Model model) {
        model.addAttribute("title", "single request");

        model.addAttribute("request", reqDao.getOne(requestId));

        return "Requests/reqedit";
    }
    @PostMapping("/reqedit/{requestId}")
    public String postEdit(@PathVariable long requestId, @ModelAttribute Request request){

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
        model.addAttribute("request", new Request());
          return "Requests/reqform";
    }


    @RequestMapping(value = "/reqform", method = RequestMethod.POST)
    public String saveDonation(@ModelAttribute Request request, Model model) {
        model.addAttribute("request", reqDao.findAll());
        User user = userService.getLoggedInUser();
        Request newReq = reqDao.save(request);
        newReq.setUser(user);
        reqDao.save(request);

        return "redirect:/requests";
    }


}
