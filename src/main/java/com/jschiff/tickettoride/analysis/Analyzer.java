package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.City;
import com.jschiff.tickettoride.model.Connection;
import com.jschiff.tickettoride.model.Game;
import com.jschiff.tickettoride.model.Route;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Analyzer {
  private Game game;

  public Analyzer(Game game) {
    this.game = game;
  }

  public Analysis analyze() {
    Set<Route> routes = game.getAllRoutes();
    Map<Route, Path> shortestPaths = new HashMap<>();

    for (Route route : routes) {
      shortestPaths.put(route, findShortestPath(route));
    }

    Analysis analysis = new Analysis();
    analysis.setShortestPaths(shortestPaths);
    for (Path path : shortestPaths.values()) {
      path.getConnections().forEach(analysis::addCriticality);
    }
    return analysis;
  }

  private Path findShortestPath(Route route) {
    Iterator<City> cities = route.getCities().iterator();
    City origin = cities.next();
    City destination = cities.next();

    HashSet<City> unvisited = new HashSet<>();
    final Map<City, Integer> distances = new HashMap<>();
    Map<City, City> previousNodes = new HashMap<>();
    game.getAllCities().stream().forEach(city -> {
      distances.put(city, Integer.MAX_VALUE);
      unvisited.add(city);
    });
    distances.put(origin, 0);

    while (!unvisited.isEmpty()) {
      City closest = unvisited.stream().min(
          (o1, o2) -> Integer.compare(distances.get(o1), distances.get(o2))).get();
      unvisited.remove(closest);

      for (City neighbor : closest.getNeighbors()) {
        int currentDistance = distances.get(neighbor);
        int newDistance = distances.get(closest);
        if (newDistance < currentDistance) {
          previousNodes.put(neighbor, closest);
          distances.put(neighbor, newDistance);
        }
      }
    }

    Path path = new Path();
    City c = destination;
    while (c != origin) {
      City previous = previousNodes.get(c);
      Connection conn = previous.getConnections()
          .stream()
          .filter(connection -> connection.getCities().contains(c))
          .findAny()
          .get();
      path.addConnection(conn);
    }

    return path;
  }
}
