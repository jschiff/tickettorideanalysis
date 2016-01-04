package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.City;
import com.jschiff.tickettoride.model.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Path {
  private List<Connection> connections;
  private City destination;
  private City origin;

  public Path(Path source) {
    this.origin = source.origin;
    this.destination = source.destination;
    this.connections = new ArrayList<>(source.connections);
  }

  public Path(City origin, Connection connection) {
    this.origin = origin;
    connections = new ArrayList<>();
    addConnection(connection);
  }

  public Path addConnection(Connection toAdd) {
    if (destination != null && !toAdd.getCities().contains(destination)) {
      throw new IllegalArgumentException();
    }

    connections.add(toAdd);
    HashSet<City> s = new HashSet<>(toAdd.getCities());
    s.remove(destination);
    destination = s.stream().findAny().get();

    return this;
  }

  public Path copy() {
    return new Path(this);
  }

  public Connection finalConnection() {
    return connections.get(connections.size() - 1);
  }

  public List<Connection> getConnections() {
    return Collections.unmodifiableList(connections);
  }

  public City getDestination() {
    return destination;
  }

  public City getOrigin() {
    return origin;
  }

  @Override
  public String toString() {
    return connections.toString() + " " + totalLength();
  }

  public int totalLength() {
    return connections.stream().mapToInt(Connection::getWeight).sum();
  }
}
