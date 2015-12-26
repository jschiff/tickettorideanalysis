package com.jschiff.tickettoride.analysis;

/**
 * POJO representing a connection information row.
 */
public class ConnectionData {
  private String color;
  private String destinationCity;
  private int length;
  private String originCity;

  public String getColor() {
    return color;
  }

  public String getDestinationCity() {
    return destinationCity;
  }

  public int getLength() {
    return length;
  }

  public String getOriginCity() {
    return originCity;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public void setDestinationCity(String destinationCity) {
    this.destinationCity = destinationCity;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public void setOriginCity(String originCity) {
    this.originCity = originCity;
  }
}
