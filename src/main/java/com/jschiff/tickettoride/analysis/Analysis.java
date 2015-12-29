package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Connection;

import java.util.HashMap;
import java.util.Map;

public class Analysis {
  private final Map<Connection, Integer> criticalities = new HashMap<Connection, Integer>();

  public void addCriticality(Connection connection) {
    Integer criticality = criticalities.getOrDefault(connection, 0);
    criticalities.put(connection, criticality + 1);
  }
}
