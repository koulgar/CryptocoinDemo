package com.koulgar.cryptocoindemo.Controller;

import javax.validation.Valid;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import com.koulgar.cryptocoindemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("formUser",new FormUser());
        return "/registration-form";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("formUser") @Valid FormUser formUser,
                                      BindingResult result) {

        User existing = userService.findByUsername(formUser.getUsername());
        if (existing != null) {
            result.rejectValue("username", null, "There is already an account registered with that username.");
        }

        if (result.hasErrors()) {
            return "/registration-form";
        }

        userService.save(formUser);
        return "redirect:/registration?success";
    }
}
