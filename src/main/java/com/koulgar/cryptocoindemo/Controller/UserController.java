package com.koulgar.cryptocoindemo.Controller;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import com.koulgar.cryptocoindemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String userProfile(Principal principal,Model model) {
        String username = principal.getName();
        model.addAttribute("userDetails",userService.findByUsername(username));
        return "user-profile";
    }

    @GetMapping("/update")
    public String showFormForUpdate(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        FormUser formUser = userService.userToFormUser(user);
        model.addAttribute("formUser",formUser);

        return "user-update-form";
    }

    @PostMapping("/update")
    public String registerUserAccount(@ModelAttribute("formUser") @Valid FormUser formUser,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "/user-update-form";
        }

        userService.save(formUser);
        return "redirect:/user/update?success";
    }

    @GetMapping("/delete")
    public String deleteUserAccount(Model model,Principal principal){
        User user = userService.findByUsername(principal.getName());
        FormUser formUser = userService.userToFormUser(user);
        formUser.setPassword(null);
        model.addAttribute("formUser",formUser);
        return "user-update-form";
    }

    @PostMapping("/delete")
    public String deleteUserAccountConfirm(@ModelAttribute("formUser") FormUser formUser,
                                           BindingResult result) {

        User user = userService.findByUsername(formUser.getUsername());
        if (formUser.getPassword()==null){
            result.rejectValue("password", null, "Password cannot be null");
        } else if (!(passwordEncoder.matches(formUser.getPassword(),user.getPassword()))) {
            result.rejectValue("password", null, "Password is not correct");
        }

        if (result.hasErrors()) {
            return "user-update-form";
        }
        userService.deleteUserById(user.getId());
        return null;
    }
}


