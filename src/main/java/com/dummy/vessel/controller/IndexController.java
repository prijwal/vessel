package com.dummy.vessel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndexPage(Model m){
        m.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model m){
        m.addAttribute("title", "login");
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model m){
        m.addAttribute("title", "signup");
        return "signup";
    }

    @PostMapping("/registerUser")
    public String doRegisterUser(Model m){
        m.addAttribute("title", "signup");
        return "signup";
    }
}
