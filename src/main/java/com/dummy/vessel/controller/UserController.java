package com.dummy.vessel.controller;

import com.dummy.vessel.das.UserDasImpl;
import com.dummy.vessel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDasImpl userDasImpl;

    @GetMapping("/index")
    public String dashboard(Model model, Principal p) {
        model.addAttribute("title", "User Profile");
        String name = p.getName();
        User user = userDasImpl.getUserByUserName(name);
        model.addAttribute("user", user);

        System.out.println("#### user from dashboard is "   + user);
        return "dummy_users/user_sections/dashboard";
    }
}
