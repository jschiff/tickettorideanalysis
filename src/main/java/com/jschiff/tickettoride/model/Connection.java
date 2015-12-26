package com.jschiff.tickettoride.model;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.Set;

public class Connection {

  private final Set<City> cities;
  private final int weight;

  Connection(Set<City> cities, int weight, ConnectionColor color) {
    Preconditions.checkNotNull(color);
    Preconditions.checkNotNull(cities);
    Preconditions.checkArgument(cities.size() == 2);

    this.weight = weight;
    this.cities = Collections.unmodifiableSet(cities);
  }

  public Set<City> getCities() {
    return cities;
  }

  public int getWeight() {
    return weight;
  }
}
