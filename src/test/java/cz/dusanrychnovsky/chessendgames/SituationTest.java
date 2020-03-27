package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.*;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.Status.*;

public class SituationTest {

  // ==========================================================================
  // Next
  // ==========================================================================

  @Test
  public void next_swapsColor() {
    var board = Board.builder().add(BlackKing, D3).build();
    var move = new Move(D3, E5);
    assertEquals(Black, new Situation(White, board).next(move).color());
    assertEquals(White, new Situation(Black, board).next(move).color());
  }

  @Test(expected = IllegalArgumentException.class)
  public void next_failsWhenFromEmptyPosition() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build());

    situation.next(new Move(D4, D3));
  }

  @Test
  public void next_movesPiece() {
    var move = new Move(D3, D4);
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build());

    situation = situation.next(move);

    assertEquals(
      Board.builder()
        .add(WhiteKing, D4)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build(),
      situation.board()
    );
  }

  @Test
  public void next_capturesPieceAtTo() {
    var move = new Move(D3, C2);
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, C2)
        .add(BlackRook, E6)
        .build());

    situation = situation.next(move);

    assertEquals(
      Board.builder()
        .add(WhiteKing, C2)
        .add(BlackRook, E6)
        .build(),
      situation.board()
    );
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void print_boardWithPieces() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build());

    assertEquals(
      "White\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . R . . .\n" +
      "5 | . . . . . K . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . K . . . .\n" +
      "2 | . . . . . . . .\n" +
      "1 | . . . . . . . .\n" +
      "--|----------------\n" +
      "  | A B C D E F G H\n",
      situation.print());
  }

  // ==========================================================================
  // Validating
  // ==========================================================================

  @Test
  public void moveIsNotValid_whenNoPiecePresentAtFrom() {
    var move = new Move(E7, E1);
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D3)
        .add(BlackKing, F5)
        .add(BlackRook, E6)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsNotValid_whenWithOpponentsPiece() {
    var move = new Move(D8, D1);
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, F5)
        .add(BlackKing, D3)
        .add(BlackRook, D8)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsValid_whenCapturing() {
    var move = new Move(F5, E6);
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, F5)
        .add(BlackKing, D3)
        .add(BlackRook, E6)
        .build());

    assertTrue(situation.isValid(move));
  }

  @Test
  public void moveIsNotValid_whenCapturingOwnPiece() {
    var move = new Move(D8, D3);
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, F5)
        .add(BlackKing, D3)
        .add(BlackRook, D8)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsNotValid_whenAcrossAPiece() {
    var move = new Move(D8, D3);
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, D6)
        .add(BlackKing, D2)
        .add(BlackRook, D8)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsNotValid_whenUnavailableForThatPieceType() {
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, D6)
        .add(BlackKing, D2)
        .add(BlackRook, D8)
        .build());

    assertFalse(situation.isValid(new Move(D8, E7)));
    assertFalse(situation.isValid(new Move(D8, E2)));
    assertFalse(situation.isValid(new Move(D2, D4)));
  }

  @Test
  public void moveIsNotValid_whenSteppingIntoCheck() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, C3)
        .add(BlackKing, C5)
        .add(BlackRook, D2)
        .build());

    assertFalse(situation.isValid(new Move(C3, C4)));
    assertFalse(situation.isValid(new Move(C3, C2)));
    assertTrue(situation.isValid(new Move(C3, D2)));
  }

  @Test
  public void validMoves() {
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, D6)
        .add(BlackKing, D2)
        .add(BlackRook, D8)
        .build());

    assertTrue(situation.isValid(new Move(D2, D3)));
    assertTrue(situation.isValid(new Move(D2, E3)));
    assertTrue(situation.isValid(new Move(D8, G8)));
  }

  @Test
  public void moveIsNotValid_WhenKingMovesNextToOpponentsKing() {
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, C3)
        .add(BlackRook, C2)
        .build());

    situation.isValid(new Move(C3, B2));
  }

  // ==========================================================================
  // Check
  // ==========================================================================

  @Test
  public void isCheck_whenEnemyPieceCouldCaptureCurrentPlayersKing() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, D6)
        .add(BlackRook, D8)
        .add(BlackKing, C8)
        .build());

    assertTrue(situation.isCheck());
  }

  @Test
  public void isNotCheck_whenOwnPieceCouldCaptureCurrentPlayersKing() {
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, A1)
        .add(BlackRook, D8)
        .add(BlackKing, D2)
        .build());

    assertFalse(situation.isCheck());
  }

  @Test
  public void isNotCheck_whenCurrentPlayersPieceCouldCaptureOpponentsKing() {
    var situation = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, D6)
        .add(BlackRook, D8)
        .add(BlackKing, C8)
        .build());

    assertFalse(situation.isCheck());
  }

  // ==========================================================================
  // Mate
  // ==========================================================================

  @Test
  public void isMate_whenCheckAndKingCanNotMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());

    assertTrue(situation.isMate());
  }

  @Test
  public void isNotMate_whenCheckAndKingCanMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B4)
        .add(BlackRook, D1)
        .build());

    assertFalse(situation.isMate());
  }

  @Test
  public void isNotMate_whenNotCheckAndKingCanMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B4)
        .add(BlackRook, D4)
        .build());

    assertFalse(situation.isMate());
  }

  @Test
  public void isNotMate_whenNotCheckAndKingCanNotMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, A1)
        .add(BlackKing, A3)
        .add(BlackRook, B3)
        .build());

    assertFalse(situation.isMate());
  }

  // ==========================================================================
  // Stalemate
  // ==========================================================================

  @Test
  public void isStalemate_whenNotCheckAndKingCanNotMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, A1)
        .add(BlackKing, A3)
        .add(BlackRook, B3)
        .build());

    assertTrue(situation.isStalemate());
  }

  @Test
  public void isNotStalemate_whenCheckAndKingCanNotMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());

    assertFalse(situation.isStalemate());
  }

  @Test
  public void isNotStalemate_whenCheckAndKingCanMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B4)
        .add(BlackRook, D1)
        .build());

    assertFalse(situation.isStalemate());
  }

  @Test
  public void isNotStalemate_whenNotCheckAndKingCanMove() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B4)
        .add(BlackRook, D4)
        .build());

    assertFalse(situation.isStalemate());
  }

  // ==========================================================================
  // Status
  // ==========================================================================

  @Test
  public void status_inProgress() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B4)
        .add(BlackRook, D4)
        .build());

    assertEquals(InProgress, situation.status());
  }

  @Test
  public void status_stalemate_isDraw() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, A1)
        .add(BlackKing, A3)
        .add(BlackRook, B3)
        .build());

    assertEquals(Draw, situation.status());
  }

  @Test
  public void status_mate_isWin() {
    var situation = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());

    assertEquals(win(Black), situation.status());
  }

  // ==========================================================================
  // Equality
  // ==========================================================================

  @Test
  public void sameSituations_areEqual() {
    var first = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());
    var second = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void differentColors_areNotEqual() {
    var first = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());
    var second = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());

    assertNotEquals(first, second);
  }

  @Test
  public void differentPositions_areNotEqual() {
    var first = new Situation(
      White,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, D1)
        .add(BlackRook, B3)
        .build());
    var second = new Situation(
      Black,
      Board.builder()
        .add(WhiteKing, B1)
        .add(BlackKing, B3)
        .add(BlackRook, D1)
        .build());

    assertNotEquals(first, second);
  }
}
