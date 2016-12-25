package cz.dusanrychnovsky.chessendgames;

import lombok.Value;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.Result.Status.WIN;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class EngineTest {

  @Test
  public void runsGameUntilVictory() {
  
    Piece whiteKing = new Piece(WHITE, new King());
    Piece whiteRook = new Piece(WHITE, new Rook());
    Piece blackKing = new Piece(BLACK, new King());

    Situation initialSituation = Situation.builder(WHITE)
      .addPiece(whiteKing, F4)
      .addPiece(whiteRook, B3)
      .addPiece(blackKing, G2)
      .build();

    List<ScriptItem> script = asList(
      new ScriptItem(WHITE, new Move(B3, F3)), // whiteRook
      new ScriptItem(BLACK, new Move(G2, H2)), // blackKing
      new ScriptItem(WHITE, new Move(F3, G3)), // whiteRook
      new ScriptItem(BLACK, new Move(H2, H1)), // blackKing
      new ScriptItem(WHITE, new Move(F4, F3)), // whiteKing
      new ScriptItem(BLACK, new Move(H1, H2)), // blackKing
      new ScriptItem(WHITE, new Move(F3, F2)), // whiteKing
      new ScriptItem(BLACK, new Move(H2, H1)), // blackKing
      new ScriptItem(WHITE, new Move(G3, H3)) // whiteRook
    );
  
    Player whitePlayer = new ScriptedPlayer(WHITE, script);
    Player blackPlayer = new ScriptedPlayer(BLACK, script);
  
    Engine engine = new Engine(whitePlayer, blackPlayer);
    engine.addEventListener(new PrintSituations());

    CaptureLastSituation captureLastSituation = new CaptureLastSituation();
    engine.addEventListener(captureLastSituation);
    
    Result result = engine.runGame(initialSituation);

    assertEquals(WIN, result.getStatus());
    assertEquals(WHITE, ((Win) result).getWinnerColor());
    
    assertEquals(
      Situation.builder(BLACK)
        .addPiece(new Piece(WHITE, new King()), F2)
        .addPiece(new Piece(WHITE, new Rook()), H3)
        .addPiece(new Piece(BLACK, new King()), H1)
        .build(),
      captureLastSituation.getSituation()
    );
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
  
  private static class PrintSituations implements EventListener {
    private final SituationPrinter printer = new SituationPrinter();
    @Override
    public void onNewSituation(Situation situation) {
      System.out.println(printer.printSituation(situation));
      System.out.println();
    }
  }
  
  private static class CaptureLastSituation implements EventListener {
    
    private Situation situation;
    
    @Override
    public void onNewSituation(Situation situation) {
      this.situation = situation;
    }
    
    public Situation getSituation() {
      if (situation == null) {
        throw new IllegalStateException("No situation captured.");
      }
      return situation;
    }
  }
}
