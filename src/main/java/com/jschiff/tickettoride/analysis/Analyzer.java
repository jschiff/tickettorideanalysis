package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.City;
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
    Map<Route, Set<Path>> shortestPaths = new HashMap<>();

    for (Route route : routes) {
      shortestPaths.put(route, findShortestPath(route));
    }

    Analysis analysis = new Analysis();
    analysis.setShortestPaths(shortestPaths);
    shortestPaths.values().forEach(set -> set.forEach(
        path1 -> path1.getConnections().forEach(analysis::addCriticality)
    ));

    return analysis;
  }

  private Set<Path> findShortestPath(Route route) {
    Iterator<City> cities = route.getCities().iterator();
    City origin = cities.next();
    City destination = cities.next();

    HashSet<City> unvisited = new HashSet<>();
    final Map<City, Integer> distances = new HashMap<>();
    Map<City, Set<City>> previousNodes = new HashMap<>();
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
        int newDistance = distances.get(closest) + closest.getNeighborDistance(neighbor);

        if (newDistance < currentDistance) {
          // If this is closer, the closest set now only contains this one city.
          Set<City> previousCities = new HashSet<>();
          previousCities.add(closest);
          previousNodes.put(neighbor, previousCities);
          distances.put(neighbor, newDistance);
        } else if (newDistance == currentDistance) {
          // If this is the same as the current closest, add this city to that set.
          Set<City> previousCities = previousNodes.getOrDefault(neighbor, new HashSet<>());
          previousCities.add(closest);
          previousNodes.put(neighbor, previousCities);
        }
      }
    }

    final Set<Path> finishedPaths = new HashSet<>();
    final Set<Path> unfinishedPaths = new HashSet<>();
    Set<City> destinationPrevious = previousNodes.get(destination);
    destinationPrevious.forEach(destPrevCity -> {
      // Create a path for each
      Path path = new Path(destination, destPrevCity.getConnections()
          .stream()
          .filter(connection -> connection.getCities().contains(destPrevCity))
          .findAny()
          .get());
      if (path.getDestination().equals(origin)) {
        finishedPaths.add(path);
      } else {
        unfinishedPaths.add(path);
      }
    });

    while (!unfinishedPaths.isEmpty()) {
      Set<Path> pathsToAdd = new HashSet<>();
      unfinishedPaths.forEach(unfinishedPath -> {
        City dest = unfinishedPath.getDestination();
        Set<City> cities1 = previousNodes.get(dest);
        cities1.forEach(city -> {
          Path newPath = unfinishedPath.copy();
          newPath.addConnection(
              dest.getConnections()
                  .stream()
                  .filter(conn -> conn.getCities().contains(city))
                  .findFirst()
                  .get());
          pathsToAdd.add(newPath);
        });
      });

      unfinishedPaths.clear();
      pathsToAdd.forEach(path1 -> {
        if (path1.getDestination().equals(origin)) {
          finishedPaths.add(path1);
        } else {
          unfinishedPaths.add(path1);
        }
      });
    }

    return finishedPaths;
  }
}
