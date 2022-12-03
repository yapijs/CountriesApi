package io.exercise.countriesapi;

import io.exercise.countriesapi.domain.Country;
import io.exercise.countriesapi.dto.CountryList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CountriesServiceTest {

    @Spy
    CountriesService service;

    private Optional<Country> defineSweden() {
        return Optional.of(new Country("Sweden", "Kingdom of Sweden", "SE", "Europe",
                List.of(new Country("Finland", "Republic of Finland", "FI", "Europe", null),
                        new Country("Norway", "Kingdom of Norway", "NO", "Europe", null))));
    }
    private Optional<Country> defineMexico() {
        return Optional.of(new Country("Mexico", "United Mexican States", "MX", "Americas",
                List.of(new Country("Belize", "Belize", "BZ", "Americas", null),
                        new Country("Guatemala", "Republic of Guatemala", "GT", "Americas", null),
                        new Country("United States", "United States of America", "US", "Americas", null))));
    }

    private CountryList[] defineCountryList() {
        return new CountryList[] {
                new CountryList("Kingdom of Sweden", List.of("Republic of Finland", "Kingdom of Norway")),
                new CountryList("United Mexican States", List.of("Belize", "Republic of Guatemala", "United States of America")),
        };
    }

    @Test
    public void shouldGetAllNeighbours() {
        Optional<Country> sweden = defineSweden();

        List<String> result = List.of("Republic of Finland", "Kingdom of Norway");

        Mockito.doReturn(sweden)
                .when(service).getExternalCountryInfo(Mockito.anyString());

        assertEquals(result, service.getNeighbours("SE"));
    }

    @Test
    public void shouldThrowError() {
        Optional<Country> returnedOptionalCountry = Optional.empty();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Mockito.doReturn(returnedOptionalCountry)
                .when(service).getExternalCountryInfo(Mockito.anyString());

        ResponseStatusException exception = Assertions
                .assertThrows(ResponseStatusException.class, () -> service.getNeighbours(""));
        assertEquals(exception.getReason(), "Wrong country code provided");
        assertEquals(exception.getStatusCode(), httpStatus);
    }

    @Test
    public void shouldGetNeighboursOfMultipleCountries() {
        List<String> countryCodes = List.of("SE", "MX");
        Optional<Country> sweden = defineSweden();
        Optional<Country> mexico = defineMexico();
        CountryList[] countryList = defineCountryList();


        Mockito.doReturn(sweden)
                .when(service).getExternalCountryInfo("SE");
        Mockito.doReturn(mexico)
                .when(service).getExternalCountryInfo("MX");

        CountryList[] result = service.getListNeighboursMultipleCountries(countryCodes);

        assertArrayEquals(result, countryList);
    }

    @Test
    public void shouldGetNeighboursOfMultipleCountriesWithSomeWrongCountryCodes() {
        List<String> countryCodes = List.of("SE", "MX", "LT");
        Optional<Country> sweden = defineSweden();
        Optional<Country> mexico = defineMexico();
        Optional<Country> optionalCountry = Optional.empty();
        CountryList[] countryList = defineCountryList();


        Mockito.doReturn(sweden)
                .when(service).getExternalCountryInfo("SE");
        Mockito.doReturn(mexico)
                .when(service).getExternalCountryInfo("MX");
        Mockito.doReturn(optionalCountry)
                .when(service).getExternalCountryInfo("LT");

        CountryList[] result = service.getListNeighboursMultipleCountries(countryCodes);

        assertArrayEquals(result, countryList);
    }

    @Test
    public void shouldGetNeighboursOfMultipleCountriesWithAllWrongCountryCodes() {
        Optional<Country> optionalCountry = Optional.empty();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> countryCodes = List.of("COUNTRY1", "COUNTRY2");


        Mockito.doReturn(optionalCountry)
                .when(service).getExternalCountryInfo(Mockito.anyString());

        ResponseStatusException exception = Assertions
                .assertThrows(ResponseStatusException.class, () -> service.getListNeighboursMultipleCountries(countryCodes));
        assertEquals(exception.getReason(), "Wrong list of countries provided");
        assertEquals(exception.getStatusCode(), httpStatus);
    }
}
