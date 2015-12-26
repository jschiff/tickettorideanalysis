package com.jschiff.tickettoride.analysis;

import com.jschiff.tickettoride.model.Board;
import com.jschiff.tickettoride.model.ConnectionColor;

public class Initializer {
  public Board initializeBoard(Iterable<ConnectionData> input) {
    Board board = new Board();

    for (ConnectionData data : input) {
      board.addConnection(
          board.cityForName(data.getOriginCity()),
          board.cityForName(data.getDestinationCity()),
          data.getLength(),
          ConnectionColor.valueOf(data.getColor()));
    }

    return board;
  }
}
