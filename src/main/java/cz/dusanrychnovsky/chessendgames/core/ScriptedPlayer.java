package cz.dusanrychnovsky.chessendgames.core;

import java.util.Iterator;

public class ScriptedPlayer implements Player {

  private final Iterator<Move> movesIt;

  public ScriptedPlayer(Iterable<Move> moves) {
    this.movesIt = moves.iterator();
  }

  @Override
  public Move move(Situation situation) {
    return movesIt.next();
  }

  public boolean isFinished() {
    return !movesIt.hasNext();
  }
}
