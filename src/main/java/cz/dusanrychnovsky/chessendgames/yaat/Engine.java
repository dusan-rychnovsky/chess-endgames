package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.HashSet;
import java.util.Set;

import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.IN_PROGRESS;
import static java.util.Arrays.asList;

public class Engine {

  public Result runGame(Situation situation, Player whitePlayer, Player blackPlayer) {

    Set<Player> players = new HashSet<>(asList(whitePlayer, blackPlayer));

    Result result;
    while ((result = situation.getResult()).getStatus() == IN_PROGRESS) {

      Player currPlayer = getPlayerByColor(players, situation.getCurrentColor());
      Move currMove = currPlayer.pickMove(situation);

      situation = situation.applyMove(currMove);
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
