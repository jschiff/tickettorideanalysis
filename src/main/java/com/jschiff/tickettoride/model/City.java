package com.jschiff.tickettoride.model;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Tools for working with cities. The static methods of this class are not thread safe.
 */
public class City {
  private final Set<Connection> connections = new HashSet<Connection>();
  private final String name;

  City(String name) {
    Preconditions.checkNotNull(name);

    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    City city = (City) o;

    return name.equals(city.name);
  }

  public Set<Connection> getConnections() {
    return Collections.unmodifiableSet(connections);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  City addConnection(Connection connection) {
    connections.add(connection);

    return this;
  }
}