package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Connection;

import java.util.ArrayList;
import java.util.List;

public class Path {
  private List<Connection> connections = new ArrayList<Connection>();

  public int totalLength() {
    return connections.stream().mapToInt(Connection::getWeight).sum();
  }
}
