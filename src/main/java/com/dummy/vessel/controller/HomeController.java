package com.dummy.vessel.controller;

import com.dummy.vessel.dao.ProductRepository;
import com.dummy.vessel.das.UserDasImpl;
import com.dummy.vessel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserDasImpl userDasImpl;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String getIndexPage(Model m) {
        m.addAttribute("title", "Home");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken)
        {
            m.addAttribute("loggedIn", true);
            m.addAttribute("username", authentication.getName());
        } else {
            m.addAttribute("loggedIn", false);
        }
        return "index";

    }

    @GetMapping("/login")
    public String getLoginPage(Model m) {
        System.out.println("#### login page");
        m.addAttribute("user", new User());
        m.addAttribute("title", "login");
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model m) {
        System.out.println("#### sign up page");
        m.addAttribute("title", "signup");
        m.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/registerUser")
    public String doRegisterUser(@ModelAttribute("user") User user, Model m) {
        System.out.println("#### registering user");
        System.out.println("######## user received is " + user.toString());
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        System.out.println("####### user password is  + " + user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDasImpl.save(user);
        m.addAttribute("user", new User());
        return "signup";
    }

}
