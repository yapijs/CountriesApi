package io.exercise.countriesapi.dto;

import java.util.List;

public class CountryList {
    private String name;
    List<String> neighbours;

    public CountryList(String name, List<String> neighbours) {
        this.name = name;
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<String> neighbours) {
        this.neighbours = neighbours;
    }
}
