package cz.dusanrychnovsky.chessendgames;

import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Result.Status.IN_PROGRESS;
import static java.util.Arrays.asList;

public class Engine {

  private final Set<Player> players;
  
  public Engine(Player whitePlayer, Player blackPlayer) {
    checkArgument(WHITE.equals(whitePlayer.getColor()));
    checkArgument(BLACK.equals(blackPlayer.getColor()));
    
    players = new HashSet<>(asList(whitePlayer, blackPlayer));
  }
  
  private List<EventListener> eventListeners = new LinkedList<>();
  
  public void addEventListener(EventListener listener) {
    eventListeners.add(listener);
  }
  
  private void notifyNewSituation(Situation situation) {
    for (EventListener listener : eventListeners) {
      listener.onNewSituation(situation);
    }
  }
  
  public Result runGame(Situation situation) {
    notifyNewSituation(situation);
    
    Result result;
    while ((result = situation.getResult()).getStatus() == IN_PROGRESS) {

      Player currPlayer = getPlayerByColor(players, situation.getCurrentColor());
      Move currMove = currPlayer.pickMove(situation);

      situation = situation.applyMove(currMove);
      notifyNewSituation(situation);
    }

    return result;
  }

  private Player getPlayerByColor(Set<Player> players, Color color) {

    for (Player player : players) {
      if (color.equals(player.getColor())) {
        return player;
      }
    }

    throw new IllegalArgumentException("No player of color [" + color + "] registered.");
  }
}
