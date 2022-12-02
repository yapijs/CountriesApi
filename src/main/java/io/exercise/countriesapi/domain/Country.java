package io.exercise.countriesapi.domain;

import java.util.List;
import java.util.Objects;

public class Country {
    private String commonName;
    private String officialName;
    private String countryCode;
    private String region;
    List<Country> borders;

    public Country() {
    }

    public Country(String commonName, String officialName, String countryCode, String region, List<Country> borders) {
        this.commonName = commonName;
        this.officialName = officialName;
        this.countryCode = countryCode;
        this.region = region;
        this.borders = borders;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Country> getBorders() {
        return borders;
    }

    public void setBorders(List<Country> borders) {
        this.borders = borders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(commonName, country.commonName) && Objects.equals(officialName, country.officialName) && Objects.equals(countryCode, country.countryCode) && Objects.equals(region, country.region) && Objects.equals(borders, country.borders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commonName, officialName, countryCode, region, borders);
    }

    @Override
    public String toString() {
        return "Country{" +
                "commonName='" + commonName + '\'' +
                ", officialName='" + officialName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", borders=" + borders +
                '}';
    }
}
