package com.countries.countries;

import java.io.Serializable;

public class Countries implements Serializable {
    private String name;
    private String capital;
    private String region;
    private int population;
    private String timezone;

    public Countries(String name, String capital, String region, int population, String timezone) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
