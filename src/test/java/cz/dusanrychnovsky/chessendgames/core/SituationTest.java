package cz.dusanrychnovsky.chessendgames.core;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

  // ==========================================================================
  // HASHCODE & EQUALS
  // ==========================================================================

  @Test
  public void differentPlayers_differentHashCodeAndEquals() {
    Map<Position, Piece> pieces = Map.of(E2, WHITE_KING);
    Situation first = new Situation(WHITE, pieces);
    Situation second = new Situation(BLACK, pieces);

    assertNotEquals(first, second);
    assertNotEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void differentPieces_differentHashCodeAndEquals() {
    Situation first = new Situation(WHITE, Map.of(E2, WHITE_KING));
    Situation second = new Situation(BLACK, Map.of(E3, WHITE_KING));

    assertNotEquals(first, second);
    assertNotEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void sameSituations_sameHashCodeAndEquals() {
    Situation first = new Situation(WHITE, Map.of(E2, WHITE_KING, F3, BLACK_ROOK));
    Situation second = new Situation(WHITE, Map.of(E2, WHITE_KING, F3, BLACK_ROOK));

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
  }

  // ==========================================================================
  // GET PIECE
  // ==========================================================================

  @Test
  public void getPiece_positionOccupied_ReturnsPiece() {
    Situation situation = new Situation(WHITE, Map.of(E2, WHITE_KING));
    assertEquals(WHITE_KING, situation.getPiece(E2).get());
  }

  @Test
  public void getPiece_positionEmpty_returnsEmptyResult() {
    Situation situation = new Situation(WHITE, Map.of(E2, WHITE_KING));
    assertEquals(empty(), situation.getPiece(E1));
  }
}
