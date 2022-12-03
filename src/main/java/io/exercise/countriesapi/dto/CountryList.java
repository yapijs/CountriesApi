package io.exercise.countriesapi.dto;

import java.util.List;
import java.util.Objects;

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


    @Override
    public String toString() {
        return "CountryList{" +
                "name='" + name + '\'' +
                ", neighbours=" + neighbours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryList that = (CountryList) o;
        return Objects.equals(name, that.name) && Objects.equals(neighbours, that.neighbours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, neighbours);
    }
}
