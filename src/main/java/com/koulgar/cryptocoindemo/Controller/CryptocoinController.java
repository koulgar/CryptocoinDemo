package com.koulgar.cryptocoindemo.Controller;

import com.koulgar.cryptocoindemo.Entity.Cryptocoin;
import com.koulgar.cryptocoindemo.Service.CryptocoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/coins")
public class CryptocoinController {

    @Autowired
    private CryptocoinService cryptocoinService;

    @RequestMapping("/list")
    public String listCoins(HttpServletRequest request, Model model){

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 15; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("cryptocoins", cryptocoinService.findAll(PageRequest.of(page, size)));
        return "coin-list";

    }

    @GetMapping("/list/details")
    public String listCoinDetails(@RequestParam("coinRank") int rank, Model model){

        // get the employee from the service
        Cryptocoin cryptocoin = cryptocoinService.findByRank(rank);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("cryptocoin", cryptocoin);

        // send over to our form
        return "coindetails";
    }


}
