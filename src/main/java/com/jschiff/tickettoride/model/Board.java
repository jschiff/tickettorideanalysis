package com.jschiff.tickettoride.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {
  private final Map<String, City> cities = new HashMap<String, City>();
  private final Set<Connection> connections = new HashSet<Connection>();

  public Connection addConnection(City a, City b, int weight,
      ConnectionColor connectionColor) {
    Set<City> cityKey = new HashSet<City>();
    cityKey.add(a);
    cityKey.add(b);
    Connection connection = new Connection(cityKey, weight, connectionColor);
    connections.add(connection);

    a.addConnection(connection);
    b.addConnection(connection);
    return connection;
  }

  public City cityForName(String name) {
    City city = cities.get(name);
    if (city == null) {
      city = cities.put(name, new City(name));
    }

    return city;
  }

  public Set<Connection> getAllConnections() {
    return Collections.unmodifiableSet(connections);
  }
}
