package cz.dusanrychnovsky.chessendgames.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
  private final Map<Color, Player> players = new HashMap<>();
  private final List<EventListener> listeners = new ArrayList<>();

  public Engine(Map<Color, Player> players) {
    this.players.putAll(players);
  }

  public Engine addListener(EventListener listener) {
    this.listeners.add(listener);
    return this;
  }

  public void start(Situation situation) {

    Situation currSituation = situation;
    publishSituation(currSituation);

    while (currSituation.getStatus() instanceof InProgress) {
      Player currPlayer = players.get(currSituation.getPlayer());
      Move move = currPlayer.move(currSituation);
      currSituation = currSituation.apply(move);
      publishSituation(currSituation);
    }

    Status result = currSituation.getStatus();
    publishResult(result);
  }

  private void publishSituation(Situation situation) {
    for (EventListener listener : listeners) {
      listener.onSituationChanged(situation);
    }
  }

  private void publishResult(Status result) {
    for (EventListener listener : listeners) {
      listener.onStatusChanged(result);
    }
  }
}
