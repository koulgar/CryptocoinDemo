package com.koulgar.cryptocoindemo.Controller;

import com.koulgar.cryptocoindemo.Entity.Cryptocoin;
import com.koulgar.cryptocoindemo.Service.CryptocoinService;
import com.koulgar.cryptocoindemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/coins")
@CrossOrigin(origins = {"http://localhost:8080"})
public class CryptocoinController {

    @Autowired
    private CryptocoinService cryptocoinService;

    @Autowired
    private UserService userService;

    int page = 0; //default page number is 0 (yes it is weird)
    int size = 20; //default page size is 10

    @GetMapping("/list")
    public String listCoins(HttpServletRequest request, Model model,Principal principal){

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        addFollowListToModel(request, model, principal);
        model.addAttribute("cryptocoins", cryptocoinService.findAll(PageRequest.of(page, size)));
        return "coin-list";
    }

    @GetMapping("/list/details")
    public String listCoinDetails(@RequestParam("coinRank") int rank,HttpServletRequest request, Model model, Principal principal){
        Cryptocoin cryptocoin = cryptocoinService.findByRank(rank);
        addFollowListToModel(request, model, principal);
        model.addAttribute("cryptocoin", cryptocoin);
        return "coindetails";
    }

    public void addFollowListToModel(HttpServletRequest request, Model model, Principal principal) {
        if (request.getUserPrincipal() != null) {
            model.addAttribute("followList", userService.findByUsername(principal.getName()).getCryptocoinList());
        }
    }

    @RequestMapping("/search")
    public String searchCoins(@RequestParam("search")String search,HttpServletRequest request, Model model,Principal principal){

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        addFollowListToModel(request, model, principal);
        model.addAttribute("search",search);
        model.addAttribute("cryptocoins", cryptocoinService.findBySearch(search,PageRequest.of(page, size)));
        return "coin-list";
    }



}
