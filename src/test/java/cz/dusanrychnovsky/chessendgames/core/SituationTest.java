package cz.dusanrychnovsky.chessendgames.core;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static org.junit.Assert.assertEquals;

public class SituationTest {

  private final Piece WHITE_KING = new Piece(WHITE, KING);
  private final Piece BLACK_ROOK = new Piece(BLACK, ROOK);

  // ==========================================================================
  // APPLY MOVE
  // ==========================================================================

  @Test(expected = IllegalArgumentException.class)
  public void applyMove_emptyFromField_fails() {
    new Situation(WHITE, new HashMap<>()).apply(new Move(E2, Position.E4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyMove_opponentsPiece_fails() {
    Situation situation = new Situation(BLACK, Map.of(E2, WHITE_KING));
    Move move = new Move(E2, E3);
    situation.apply(move);
  }

  @Test
  public void applyMove_movesPiece() {
    Situation situation = new Situation(WHITE, Map.of(E2, WHITE_KING));
    Move move = new Move(E2, E3);

    Situation result = situation.apply(move);
    Map<Position, Piece> pieces = result.getPieces();

    assertEquals(1, pieces.size());
    assertEquals(WHITE_KING, pieces.get(E3));
  }

  @Test
  public void applyMove_removesCapturedPiece() {
    Situation situation = new Situation(WHITE, Map.of(E2, WHITE_KING, F3, BLACK_ROOK));
    Move move = new Move(E2, F3);

    Situation result = situation.apply(move);
    Map<Position, Piece> pieces = result.getPieces();

    assertEquals(1, pieces.size());
    assertEquals(WHITE_KING, pieces.get(F3));
  }
}
