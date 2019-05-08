package com.koulgar.cryptocoindemo.Controller;

import com.koulgar.cryptocoindemo.Entity.UpdateUser;
import com.koulgar.cryptocoindemo.Entity.User;
import com.koulgar.cryptocoindemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/userlist")
    public String ListUsers(HttpServletRequest request, Model model){

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 20; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("users", userService.findAll(PageRequest.of(page, size)));
        return "user-list";
    }

    @GetMapping("/userlist/update")
    public String updateUser(@RequestParam("userId") Integer userId, Model model){
        User user = userService.findById(userId);
        UpdateUser updateUser = userService.userToUpdateUser(user);
        model.addAttribute("updateUser", updateUser);
        return "user-update-form";
    }

    @PostMapping("/userlist/update")
    public String updateUserConfirm(@ModelAttribute("updateUser")@Valid UpdateUser updateUser,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "/user-updateUserFromUpdateUser-form";
        }

        userService.updateUserFromUpdateUser(updateUser);
        return "redirect:/admin/userlist?success";
    }

    @GetMapping("/userlist/delete")
    public String deleteUserAccount(@RequestParam("userId") Integer userId,Model model){
        User user = userService.findById(userId);
        model.addAttribute("user",user);
        return "user-update-form";
    }

    @PostMapping("userlist/delete")
    public String deleteUserAccountConfirm(@ModelAttribute("user") User user,
                                           BindingResult result, Principal principal) {

        User userAdmin = userService.findByUsername(principal.getName());
        if (userAdmin.getPassword()==null){
            result.rejectValue("password", null, "Password cannot be null");
        } else if (!(passwordEncoder.matches(user.getPassword(),userAdmin.getPassword()))) {
            result.rejectValue("password", null, "Password is not correct");
        }

        if (result.hasErrors()) {
            return "redirect:/admin/userlist/delete?fail&userId="+user.getId();
        }
        userService.deleteUserById(user.getId());
        return "redirect:/admin/userlist?delsuccess";
    }

    @RequestMapping("/userlist/search")
    public String searchUsers(@RequestParam("search")String search,HttpServletRequest request, Model model){
        int page = 0; //default page number is 0 (yes it is weird)
        int size = 20; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("search",search);
        model.addAttribute("users", userService.findBySearch(search,PageRequest.of(page, size)));
        return "user-list";
    }

}
