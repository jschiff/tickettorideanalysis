package com.jschiff.tickettoride.analysis;

public class RouteData {
  private String destinationCity;
  private String originCity;
  private int reward;

  public String getDestinationCity() {
    return destinationCity;
  }

  public String getOriginCity() {
    return originCity;
  }

  public int getReward() {
    return reward;
  }

  public void setDestinationCity(String destinationCity) {
    this.destinationCity = destinationCity;
  }

  public void setOriginCity(String originCity) {
    this.originCity = originCity;
  }

  public void setReward(int reward) {
    this.reward = reward;
  }
}
