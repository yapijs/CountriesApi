package io.exercise.countriesapi;

import io.exercise.countriesapi.dto.CountryList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountriesController {

    private final CountriesService service;

    public CountriesController(CountriesService service) {
        this.service = service;
    }

    @GetMapping("/country/{countryCode}/neighbours")
    public List<String> getNeighbours(@PathVariable("countryCode") String countryCode) {
        return service.getNeighbours(countryCode);
    }

    @GetMapping("/countries/neighbours")
    public CountryList[] getListNeighboursMultipleCountries(@RequestBody List<String> countryList) {
        return service.getListNeighboursMultipleCountries(countryList);
    }

}
