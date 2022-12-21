package beerdb.application.beer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import beerdb.application.beer.models.Brewery;
import beerdb.application.beer.services.BeerServices;

@Controller
@RequestMapping(path="/breweries")
public class BreweriesController {

    @Autowired
    private BeerServices beerSvc;

    @GetMapping
    public String getBreweries(@RequestParam Integer beerStyle, Model model) {
        List<Brewery> breweries = beerSvc.getBreweriesByBeerStyle(beerStyle);

        model.addAttribute("breweries", breweries);

        return "breweries";
    }
    
}