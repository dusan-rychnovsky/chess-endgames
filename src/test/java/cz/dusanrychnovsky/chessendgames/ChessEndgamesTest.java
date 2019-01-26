package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChessEndgamesTest {

  private final Piece WHITE_KING = new Piece(WHITE, KING);
  private final Piece BLACK_KING = new Piece(BLACK, KING);
  private final Piece BLACK_ROOK = new Piece(BLACK, ROOK);

  @Test
  public void aKingVsKingAndARook() {

    Script script = new Script.Builder()
      .addSituation(situation(BLACK, D3, F5, E6))
      .addMove(BLACK, E6, E4)
      .addSituation(situation(WHITE, D3, F5, E4))
      .addMove(WHITE, D3, C3)
      .addSituation(situation(BLACK, C3, F5, E4))
      .addMove(BLACK, F5, E5)
      .addSituation(situation(WHITE, C3, E5, E4))
      .addMove(WHITE, C3, D3)
      .addSituation(situation(BLACK, D3, E5, E4))
      .addMove(BLACK, E5, D5)
      .addSituation(situation(WHITE, D3, D5, E4))
      .addMove(WHITE, D3, D2)
      .addSituation(situation(BLACK, D2, D5, E4))
      .addMove(BLACK, D5, D4)
      .addSituation(situation(WHITE, D2, D4, E4))
      .addMove(WHITE, D2, C2)
      .addSituation(situation(BLACK, C2, D4, E4))
      .addMove(BLACK, E4, E3)
      .addSituation(situation(WHITE, C2, D4, E3))
      .addMove(WHITE, C2, C1)
      .addSituation(situation(BLACK, C1, D4, E3))
      .addMove(BLACK, E3, E2)
      .addSituation(situation(WHITE, C1, D4, E2))
      .addMove(WHITE, C1, D1)
      .addSituation(situation(BLACK, D1, D4, E2))
      .addMove(BLACK, D4, D3)
      .addSituation(situation(WHITE, D1, D3, E2))
      .addMove(WHITE, D1, C1)
      .addSituation(situation(BLACK, C1, D3, E2))
      .addMove(BLACK, E2, D2)
      .addSituation(situation(WHITE, C1, D3, D2))
      .addMove(WHITE, C1, B1)
      .addSituation(situation(BLACK, B1, D3, D2))
      .addMove(BLACK, D2, C2)
      .addSituation(situation(WHITE, B1, D3, C2))
      .addMove(WHITE, B1, A1)
      .addSituation(situation(BLACK, A1, D3, C2))
      .addMove(BLACK, D3, C3)
      .addSituation(situation(WHITE, A1, C3, C2))
      .addMove(WHITE, A1, B1)
      .addSituation(situation(BLACK, B1, C3, C2))
      .addMove(BLACK, C3, B3)
      .addSituation(situation(WHITE, B1, B3, C2))
      .addMove(WHITE, B1, A1)
      .addSituation(situation(BLACK, A1, B3, C2))
      .addMove(BLACK, C2, C1)
      .setResult(new Win(BLACK))
      .build();

    TestScriptListener listener = new TestScriptListener(script.getSituations(), script.getResult());
    ScriptedPlayer whitePlayer = new ScriptedPlayer(script.getMoves(WHITE));
    ScriptedPlayer blackPlayer = new ScriptedPlayer(script.getMoves(BLACK));
    new Engine(
      Map.of(
        WHITE, whitePlayer,
        BLACK, blackPlayer
      )
    ).addListener(listener)
     .start(script.getSituations().iterator().next());

    listener.assertAllVerified();
    assertTrue("White player did not finish moves.", whitePlayer.isFinished());
    assertTrue("Black player did not finish moves.", blackPlayer.isFinished());
  }

  private Situation situation(Color player, Position whiteKingPos, Position blackKingPos, Position blackRookPos) {
    return new Situation(
      player,
      Map.of(
        whiteKingPos, WHITE_KING,
        blackKingPos, BLACK_KING,
        blackRookPos, BLACK_ROOK
      )
    );
  }

  private static class TestScriptListener implements EventListener {
    private final Iterator<Situation> situationsIt;
    private final Status result;
    private boolean resultVerified = false;

    public TestScriptListener(Iterable<Situation> situations, Status result) {
      this.situationsIt = situations.iterator();
      this.result = result;
    }

    @Override
    public EventListener onSituationChanged(Situation situation) {
      assertEquals("Observed a wrong situation.", situation, situationsIt.next());
      return this;
    }

    @Override
    public EventListener onStatusChanged(Status status) {
      assertFalse("Should not observe result yet.", situationsIt.hasNext());
      assertEquals("Observed wrong result.", result, status);
      this.resultVerified = true;
      return this;
    }

    public void assertAllVerified() {
      Assert.assertFalse("Did not observe all situations.", situationsIt.hasNext());
      Assert.assertTrue("Did not observe result.", resultVerified);
    }
  }

  private static class Script {

    private Status result;
    private final List<Situation> situations = new ArrayList<>();
    private final Map<Color, List<Move>> moves = Map.of(
      WHITE, new ArrayList<>(),
      BLACK, new ArrayList<>()
    );

    public Iterable<Move> getMoves(Color player) {
      return moves.get(player);
    }

    public Iterable<Situation> getSituations() {
      return situations;
    }

    public Status getResult() {
      return result;
    }

    private static class Builder {
      private Script script = new Script();

      public Builder addMove(Color player, Move move) {
        script.moves.get(player).add(move);
        return this;
      }

      public Builder addMove(Color player, Position from, Position to) {
        return addMove(player, new Move(from, to));
      }

      public Builder addSituation(Situation situation) {
        script.situations.add(situation);
        return this;
      }

      public Builder setResult(Status result) {
        script.result = result;
        return this;
      }

      public Script build() {
        return script;
      }
    }
  }
}
