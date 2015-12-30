package com.jschiff.tickettoride.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {
  private final Set<City> cities = new HashSet<>();
  private final Map<String, City> cityMap = new HashMap<>();
  private final Set<Connection> connections = new HashSet<>();
  private final Set<Route> routes = new HashSet<>();

  public Connection addConnection(City a, City b, int weight,
      ConnectionColor connectionColor) {
    Set<City> cityKey = new HashSet<>();
    cityKey.add(a);
    cityKey.add(b);
    Connection connection = new Connection(cityKey, weight, connectionColor);
    connections.add(connection);

    a.addConnection(connection);
    b.addConnection(connection);
    return connection;
  }

  public Route addRoute(City a, City b, int reward) {
    Set<City> cityKey = new HashSet<>();
    cityKey.add(a);
    cityKey.add(b);
    Route route = new Route(cityKey, reward);
    routes.add(route);
    a.addRoute(route);
    b.addRoute(route);
    return route;
  }

  public City cityForName(String name) {
    City city = cityMap.get(name);
    if (city == null) {
      city = new City(name);
      cityMap.put(name, city);
      cities.add(city);
    }

    return city;
  }

  public Set<City> getAllCities() {
    return Collections.unmodifiableSet(cities);
  }

  public Set<Connection> getAllConnections() {
    return Collections.unmodifiableSet(connections);
  }

  public Set<Route> getAllRoutes() {
    return Collections.unmodifiableSet(routes);
  }
}
