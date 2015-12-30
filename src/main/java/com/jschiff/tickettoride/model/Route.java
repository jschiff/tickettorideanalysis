package com.jschiff.tickettoride.model;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.Set;

public class Route {
  private final Set<City> cities;
  private final int reward;

  Route(Set<City> cities, int reward) {
    Preconditions.checkNotNull(cities);
    Preconditions.checkArgument(cities.size() == 2);

    this.reward = reward;
    this.cities = Collections.unmodifiableSet(cities);
  }

  public Set<City> getCities() {
    return cities;
  }

  public int getReward() {
    return reward;
  }

  @Override
  public String toString() {
    return cities.toString() + " " + reward;
  }
}
