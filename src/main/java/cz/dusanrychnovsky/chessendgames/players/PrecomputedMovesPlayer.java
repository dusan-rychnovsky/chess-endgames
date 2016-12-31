package cz.dusanrychnovsky.chessendgames.players;

import cz.dusanrychnovsky.chessendgames.UserInterface;
import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import cz.dusanrychnovsky.chessendgames.players.Database;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class PrecomputedMovesPlayer implements Player {

  private static final long DISPLAY_PAUSE_MS = 300;

  private final Color color;
  private final Database moves;
  private final UserInterface ui;

  public PrecomputedMovesPlayer(Color color, Database moves, UserInterface ui) {
    this.color = color;
    this.moves = moves;
    this.ui = ui;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Move pickMove(Situation situation) {
    checkArgument(color == situation.getCurrentColor());

    sleep(DISPLAY_PAUSE_MS);

    Optional<Move> move = moves.getMove(situation);
    if (move.isPresent()) {
      ui.displayChosenMove(getColor(), move.get());
      return move.get();
    }
    else {
      throw new IllegalArgumentException("Unknown situation [" + situation + "].");
    }
  }

  private void sleep(long ms) {
    try {
      Thread.sleep(ms);
    }
    catch (InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
}
