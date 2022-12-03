package io.exercise.countriesapi;

import io.exercise.countriesapi.domain.Country;
import io.exercise.countriesapi.dto.CountryList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CountriesService {
    private static final String EXTERNAL_COUNTRY_API_URL = "https://date.nager.at/api/v3/CountryInfo/";

    public CountriesService() {
    }

    public List<String> getNeighbours(String countryCode) {
        return getNeighboursFromCountry(
                getExternalCountryInfo(countryCode)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong country code provided")));
    }

    private List<String> getNeighboursFromCountry(Country country) {
        return country.getBorders()
                .stream()
                .map(Country::getOfficialName)
                .toList();
    }

    private Optional<Country> getExternalCountryInfo(String countryCode) {
        return WebClient
                    .create(EXTERNAL_COUNTRY_API_URL + countryCode)
                    .get()
                    .retrieve()
                    .bodyToMono(Country.class)
                    .onErrorComplete()
                    .blockOptional();
    }

    public CountryList[] getListNeighboursMultipleCountries(List<String> countryCodeList) {
        List<Country> countryObjectList = getListOfCountries(countryCodeList);

        if (countryObjectList.size() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return countryObjectList.stream()
                .map(country -> new CountryList(country.getOfficialName(), getNeighboursFromCountry(country)))
                .toArray(CountryList[]::new);
    }

    private List<Country> getListOfCountries(List<String> countryCodeList) {
        List<Country> countryList = new ArrayList<>();

        countryCodeList.stream()
                .map(this::getExternalCountryInfo)
                .forEach(country -> country
                        .ifPresent(countryList::add));

        return countryList;
    }
}
