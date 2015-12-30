package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Path {
  private List<Connection> connections;

  public Path(Path source) {
    this.connections = new ArrayList<>(source.connections);
  }

  public Path() {
    connections = new ArrayList<>();
  }

  public Path addConnection(Connection toAdd) {
    connections.add(toAdd);
    return this;
  }

  public Path copy() {
    return new Path(this);
  }

  public List<Connection> getConnections() {
    return Collections.unmodifiableList(connections);
  }

  public int totalLength() {
    return connections.stream().mapToInt(Connection::getWeight).sum();
  }
}
