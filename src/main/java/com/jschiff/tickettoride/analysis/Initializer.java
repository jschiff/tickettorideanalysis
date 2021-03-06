package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Game;
import com.jschiff.tickettoride.model.ConnectionColor;

public class Initializer {
  public Game initializeGame(Iterable<ConnectionData> connections, Iterable<RouteData> routes) {
    Game game = new Game();

    for (ConnectionData data : connections) {
      game.addConnection(
          game.cityForName(data.getOriginCity()),
          game.cityForName(data.getDestinationCity()),
          data.getLength(),
          ConnectionColor.valueOf(data.getColor().toUpperCase()));
    }

    for (RouteData data : routes) {
      game.addRoute(
          game.cityForName(data.getOriginCity()),
          game.cityForName(data.getDestinationCity()),
          data.getReward());
    }

    return game;
  }
}
