package com.koulgar.cryptocoindemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/showLoginPage")
    public String login(Model model) {
        return "userlogin";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
}