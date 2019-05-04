package com.koulgar.cryptocoindemo.Controller;

import com.koulgar.cryptocoindemo.Entity.FormUser;
import com.koulgar.cryptocoindemo.Entity.User;
import com.koulgar.cryptocoindemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController{

    @Autowired
    private UserService userService;

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
        FormUser formUser = userService.userToFormUser(user);
        model.addAttribute("formUser", formUser);
        return "user-update-form";
    }

    @PostMapping("/userlist/update")
    public String updateUserConfirm(@ModelAttribute("formUser") FormUser formUser,
                                      BindingResult result) {

        if (formUser.getFirstName()==null){
            result.rejectValue("firstName",null,"Cannot be empty");
        }
        else if (formUser.getLastName()==null){
            result.rejectValue("lastName",null,"Cannot be empty");
        }
        else if (formUser.getEmail()==null){
            result.rejectValue("email",null,"Cannot be empty");
        }

        if (result.hasErrors()) {
            return "/user-update-form";
        }

        userService.update(formUser);
        return "redirect:/admin/userlist?success";
    }

    /*
    TODO    0-add user roles to /admin/userlist page
            1-add delete user option to admin panel (protected with admin password)
            2-save user by admin should have email validation
            3-add pageable search bar to /admin/userlist page
    */

}
