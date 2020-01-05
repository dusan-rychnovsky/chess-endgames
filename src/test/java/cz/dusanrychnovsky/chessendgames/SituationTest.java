package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

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
}
