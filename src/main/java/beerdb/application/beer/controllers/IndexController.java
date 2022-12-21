package beerdb.application.beer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import beerdb.application.beer.models.BeerStyle;
import beerdb.application.beer.services.BeerServices;

@Controller
@RequestMapping(path = {"/", "/index.html"})
public class IndexController {

    @Autowired
    private BeerServices beerSvc;

    @GetMapping
    public String getIndex(Model model) {

        List<BeerStyle> styles = beerSvc.getBeerStyles();

        model.addAttribute("styles", styles);


        return "index";
    }
    
}
