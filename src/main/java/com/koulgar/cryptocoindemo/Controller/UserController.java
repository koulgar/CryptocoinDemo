package com.koulgar.cryptocoindemo.Controller;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import com.koulgar.cryptocoindemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/profile")
    public String userProfile(Principal principal,Model model) {
        String username = principal.getName();
        model.addAttribute("userDetails",userService.findByUsername(username));
        return "user-profile";
    }

    @GetMapping("/update")
    public String showFormForUpdate(Principal principal, Model model){
        FormUser formUser = new FormUser();
        User user = userService.findByUsername(principal.getName());
        formUser.setId(user.getId());
        formUser.setUsername(user.getUsername());
        formUser.setEmail(user.getEmail());
        formUser.setPassword(user.getPassword());
        formUser.setMatchingPassword(user.getPassword());
        formUser.setFirstName(user.getFirstName());
        formUser.setLastName(user.getLastName());
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

    @RequestMapping("/delete")
    public String deleteUserAccount(Principal principal){
        userService.deleteUserById(userService.findByUsername(principal.getName()).getId());
        return "redirect:/login?delete";
    }
}

    /*TODO  1-Add mapping for User Profile (/user/profile)
                a-take user data from mySQL database by searching for username
                b-write user data to /user/profile page
                c-add profile edit feature to /user/profile/edit
                d-add save button and return to /user/profile page
            2-Add mapping for Admin Panel (/admin/panel)
                a-admin should see all registrated users in a list
                    -take all user list from database and list them in /admin/panel
                b-admin should edit user information such as username,email,firstName,lastName,password
                    -upon clicking update button which belongs to user, user profile should be
                        editable in /admin/panel={userid} (by getmapping)
     */

