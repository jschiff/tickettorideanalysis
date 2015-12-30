package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Connection;
import com.jschiff.tickettoride.model.Route;

import java.util.HashMap;
import java.util.Map;

public class Analysis {
  private final Map<Connection, Integer> criticalities = new HashMap<>();
  private Map<Route, Path> shortestPaths;

  public void addCriticality(Connection connection) {
    Integer criticality = criticalities.getOrDefault(connection, 0);
    criticalities.put(connection, criticality + 1);
  }

  public Map<Route, Path> getShortestPaths() {
    return shortestPaths;
  }

  public void setShortestPaths(Map<Route, Path> shortestPaths) {
    this.shortestPaths = shortestPaths;
  }
}
