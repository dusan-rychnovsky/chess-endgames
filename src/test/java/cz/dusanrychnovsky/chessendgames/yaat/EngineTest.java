package cz.dusanrychnovsky.chessendgames.yaat;

import lombok.Value;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.yaat.Color.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Column.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.WIN;
import static cz.dusanrychnovsky.chessendgames.yaat.Row.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class EngineTest {

  @Test
  public void runsGameUntilVictory() {

    Piece whiteKing = new Piece(WHITE, new King());
    Piece whiteRook = new Piece(WHITE, new Rook());
    Piece blackKing = new Piece(BLACK, new King());

    Situation initialSituation = Situation.builder(WHITE)
      .addPiece(whiteKing, new Position(CF, R4))
      .addPiece(whiteRook, new Position(CB, R3))
      .addPiece(blackKing, new Position(CG, R2))
      .build();

    List<ScriptItem> script = asList(
      new ScriptItem(WHITE, new Move(new Position(CB, R3), new Position(CF, R3))), // whiteRook
      new ScriptItem(BLACK, new Move(new Position(CG, R2), new Position(CH, R2))), // blackKing
      new ScriptItem(WHITE, new Move(new Position(CF, R3), new Position(CG, R3))), // whiteRook
      new ScriptItem(BLACK, new Move(new Position(CH, R2), new Position(CH, R1))), // blackKing
      new ScriptItem(WHITE, new Move(new Position(CF, R4), new Position(CF, R3))), // whiteKing
      new ScriptItem(BLACK, new Move(new Position(CH, R1), new Position(CH, R2))), // blackKing
      new ScriptItem(WHITE, new Move(new Position(CF, R3), new Position(CF, R2))), // whiteKing
      new ScriptItem(BLACK, new Move(new Position(CH, R2), new Position(CH, R1))), // blackKing
      new ScriptItem(WHITE, new Move(new Position(CG, R3), new Position(CH, R3))) // whiteRook
    );

    Player whitePlayer = new ScriptedPlayer(WHITE, script);
    Player blackPlayer = new ScriptedPlayer(BLACK, script);

    Engine engine = new Engine();
    Result result = engine.runGame(initialSituation, whitePlayer, blackPlayer);

    assertEquals(WIN, result.getStatus());
    assertEquals(WHITE, ((Win) result).getWinnerColor());
  }

  @Value
  private final class ScriptItem {
    Color currentColor;
    Move move;
  }
  
  private final class ScriptedPlayer implements Player {

    private final Color color;
    private final Iterator<ScriptItem> scriptIt;

    public ScriptedPlayer(Color color, Iterable<ScriptItem> script) {
      this.color = color;
      scriptIt = script.iterator();
    }

    @Override
    public Color getColor() {
      return color;
    }

    @Override
    public Move pickMove(Situation situation) {

      while (scriptIt.hasNext()) {
        ScriptItem currItem = scriptIt.next();
        if (color.equals(currItem.getCurrentColor())) {
          return currItem.getMove();
        }
      }

      throw new IllegalStateException("No more moves in script.");
    }
  }
}