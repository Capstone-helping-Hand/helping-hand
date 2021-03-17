package com.codeup.helpinghand.controllers;

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
        this.userService = userService;
    }



    @GetMapping("/requests")
    public String request(Model model){
        model.addAttribute("requests", reqDao.findAll());
        return "requests";
    }

    @GetMapping(path = "/requests/{id}")
    public String requestById(@PathVariable Long id, Model model){
        model.addAttribute("title", "single request");
        model.addAttribute("request", reqDao.getOne(id));
        return "requests";
    }
//comment

    @GetMapping("/reqform")
    public String create(Model model) {
        model.addAttribute("request", new Request());
          return "reqform";
    }
    

@PostMapping(path = "/reqform")
public String creatRequest(@ModelAttribute Request request) {
    User user = userService.getLoggedInUser();
    request.setUser(user);

//    String filename = StringUtils.cleanPath(multipartFile.getContentType());
//    request.setPicture(filename);
     Request savereq = reqDao.save(request);
        savereq.getTitle();
        savereq.getDescription();

//     String uploadDir = "/reqform" + savereq.getRequestId();
//    Fileul.saveFile(uploadDir, filename, multipartFile);

      return "redirect:/requests";

}





}
