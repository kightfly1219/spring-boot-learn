package org.pactera.spring.boot.learn.controller;

import org.pactera.spring.boot.learn.dto.UserDataDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class loginController {
    /**
     * goToLoginPage
     * @param model Model
     * @return goToLoginPage
     */
    @GetMapping(value = "goToLoginPage")
    public String goToLoginPage(Model model) {
        List<String> usernames = new ArrayList<>();
        usernames.add("LiSa");
        usernames.add("Joey");
        usernames.add("Sam");
        model.addAttribute("usernames", usernames);
        model.addAttribute("pageTitle", "LoginPage");
        return "login.html";
    }

    /**
     * toLoginPage
     * @param user UserDataDTO
     * @return Login
     */
    @PostMapping(value = "login")
    public String login(@ModelAttribute UserDataDTO user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "login.html";
    }
}
