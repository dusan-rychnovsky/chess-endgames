package cz.dusanrychnovsky.chessendgames.core;

import java.util.Map;

public class Engine {
  public Engine(Map<Color, Player> players) {
  }

  public Engine addListener(EventListener listener) {
    return this;
  }

  public void start(Situation situation) {
  }
}
