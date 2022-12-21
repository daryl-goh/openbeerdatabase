package beerdb.application.beer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beerdb.application.beer.models.BeerStyle;
import beerdb.application.beer.models.Brewery;
import beerdb.application.beer.repositories.BreweryCache;
import beerdb.application.beer.repositories.BreweryRepository;
import beerdb.application.beer.repositories.StylesRepository;

@Service
public class BeerServices {

    @Autowired
    private StylesRepository stylesRepo;

    @Autowired
    private BreweryRepository breweryRepo;

	 @Autowired
	 private BreweryCache breweryCache;

    public List<BeerStyle> getBeerStyles() {
        return stylesRepo.getStyles();
    }

    public List<Brewery> getBreweriesByBeerStyle(Integer beerStyle) {

		  // check if the result is in the cache. If it is, then use the list from Redis
		  Optional<List<Brewery>> opt = breweryCache.getBreweriesByBeerStyle(beerStyle);
		  if (opt.isPresent())
			  return opt.get();

		  // Query the database and cache the result
		  List<Brewery> breweries = breweryRepo.getBreweriesByStyle(beerStyle);
		  breweryCache.saveBreweriesByBeerStyle(beerStyle, breweries);

		  return breweries;
    }
    
}
