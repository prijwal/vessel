package com.dummy.vessel.controller;

import com.dummy.vessel.constants.enums;
import com.dummy.vessel.repository.ProductDao;
import com.dummy.vessel.service.impl.UserDasImpl;
import com.dummy.vessel.entities.Role;
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
import java.util.Collection;
import java.util.HashSet;


@Controller
public class HomeController {

    @Autowired
    UserDasImpl userDasImpl;

    @Autowired
    ProductDao productRepository;

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
         Collection<Role> roles = new HashSet<>();
        roles.add(enums.RoleType.USER.toRole());
        user.setRoles(roles);
        user.setEnabled(true);
        String email = user.getEmail();
        int index = email.indexOf('@');
        String username = email.substring(0, index);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDasImpl.save(user);
        m.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/all-products")
    public String getAllProducts(Model m, Principal p) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken)
        {
            String name = p.getName();
            User user = userDasImpl.getUserByUserName(name);
            m.addAttribute("user", user);
        } else {
            m.addAttribute("user", new User());
        }
        System.out.println("#### sign up page");
        m.addAttribute("title", "all products");
        return "dummy_users/user_sections/all-products";
    }

}
