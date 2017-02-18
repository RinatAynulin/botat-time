package com.aynulin.botat.time.model;

import javax.persistence.*;
import java.util.HashSet;

/**
 * @Aynulin on 18.02.2017.
 */

@Entity
@Table(name = "city")
public class City {
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "city")
    private HashSet<Forecast> forecasts;

    public City() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashSet<Forecast> getForecasts() {
        return forecasts;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
