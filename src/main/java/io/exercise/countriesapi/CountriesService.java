package io.exercise.countriesapi;

import io.exercise.countriesapi.domain.Country;
import io.exercise.countriesapi.dto.CountryList;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


@Service
public class CountriesService {
    private static final String EXTERNAL_COUNTRY_API_URL = "https://date.nager.at/api/v3/CountryInfo/";

    public CountriesService() {
    }

    public List<String> getNeighbours(String countryCode) {
        return getNeighboursFromCountry(getExternalCountryInfo(countryCode));
    }

    private List<String> getNeighboursFromCountry(Country country) {
        return country.getBorders()
                .stream()
                .map(Country::getOfficialName)
                .toList();
    }

    private Country getExternalCountryInfo(String countryCode) {
        return WebClient
                .create(EXTERNAL_COUNTRY_API_URL + countryCode)
                .get()
                .retrieve()
                .bodyToMono(Country.class)
                .block();
    }

    public CountryList[] getListNeighboursMultipleCountries(List<String> countryCodeList) {
        List<Country> countryObjectList = new ArrayList<>();
        for (String countryCode: countryCodeList) {
            countryObjectList.add(getExternalCountryInfo(countryCode));
        }
        return countryObjectList.stream()
                .map(country -> new CountryList(country.getOfficialName(), getNeighboursFromCountry(country)))
                .toArray(CountryList[]::new);
    }
}
