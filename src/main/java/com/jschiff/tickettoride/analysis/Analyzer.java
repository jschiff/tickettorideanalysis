package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.City;
import com.jschiff.tickettoride.model.Connection;
import com.jschiff.tickettoride.model.Game;
import com.jschiff.tickettoride.model.Route;

import java.util.Set;

public class Analyzer {
  public Analysis analyze(Game game) {
    Set<City> cities = game.getAllCities();
    Set<Connection> connections = game.getAllConnections();
    Set<Route> routes = game.getAllRoutes();

    return null;
  }


}
