package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static cz.dusanrychnovsky.chessendgames.core.Status.*;

public class SituationTest {

  // ==========================================================================
  // Next
  // ==========================================================================

  @Test
  public void nextShouldGenerateValidMoves() {
    var situation = new Situation(BLACK, Board.builder()
      .add(WHITE_KING, E5)
      .add(BLACK_KING, F3)
      .add(BLACK_ROOK, G3)
      .build());
    var situations = situation.nextMoves();
    assertEquals(13, situations.size());
    var moves = Arrays.asList(
      new Move(G3, G1),
      new Move(G3, G2),
      new Move(G3, G4),
      new Move(G3, G5),
      new Move(G3, G6),
      new Move(G3, G7),
      new Move(G3, G8),
      new Move(G3, H3),
      new Move(F3, G4),
      new Move(F3, E3),
      new Move(F3, E2),
      new Move(F3, F2),
      new Move(F3, G2));
    for (var move : moves) {
      assertEquals(situation.apply(move), situations.get(move));
    }
  }

  // ==========================================================================
  // Move
  // ==========================================================================

  @Test
  public void moveShouldSwapColor() {
    var board = Board.builder().add(BLACK_KING, D3).build();
    var move = new Move(D3, E5);
    assertEquals(BLACK, new Situation(WHITE, board).apply(move).color());
    assertEquals(WHITE, new Situation(BLACK, board).apply(move).color());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveShouldFailsWhenFromEmptyPosition() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, D3)
        .add(BLACK_KING, F5)
        .add(BLACK_ROOK, E6)
        .build());

    situation.apply(new Move(D4, D3));
  }

  @Test
  public void moveShouldMovePiece() {
    var move = new Move(D3, D4);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, D3)
        .add(BLACK_KING, F5)
        .add(BLACK_ROOK, E6)
        .build());

    situation = situation.apply(move);

    assertEquals(
      Board.builder()
        .add(WHITE_KING, D4)
        .add(BLACK_KING, F5)
        .add(BLACK_ROOK, E6)
        .build(),
      situation.board()
    );
  }

  @Test
  public void moveShouldCapturePieceAtTo() {
    var move = new Move(D3, C2);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, D3)
        .add(BLACK_KING, C2)
        .add(BLACK_ROOK, E6)
        .build());

    situation = situation.apply(move);

    assertEquals(
      Board.builder()
        .add(WHITE_KING, C2)
        .add(BLACK_ROOK, E6)
        .build(),
      situation.board()
    );
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void printShouldPrintBoardWithPieces() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, D3)
        .add(BLACK_KING, F5)
        .add(BLACK_ROOK, E6)
        .build());

    assertEquals(
      """
        WHITE
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . R . . .
        5 | . . . . . K . .
        4 | . . . . . . . .
        3 | . . . K . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        """,
      situation.print());
  }

  // ==========================================================================
  // Validating
  // ==========================================================================

  @Test
  public void moveIsNotValidWhenNoPiecePresentAtFrom() {
    var move = new Move(E7, E1);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, D3)
        .add(BLACK_KING, F5)
        .add(BLACK_ROOK, E6)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsNotValidWhenWithOpponentsPiece() {
    var move = new Move(D8, D1);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, F5)
        .add(BLACK_KING, D3)
        .add(BLACK_ROOK, D8)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsValidWhenCapturing() {
    var move = new Move(F5, E6);
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, F5)
        .add(BLACK_KING, D3)
        .add(BLACK_ROOK, E6)
        .build());

    assertTrue(situation.isValid(move));
  }

  @Test
  public void moveIsNotValidWhenCapturingOwnPiece() {
    var move = new Move(D8, D3);
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, F5)
        .add(BLACK_KING, D3)
        .add(BLACK_ROOK, D8)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsNotValidWhenAcrossAPiece() {
    var move = new Move(D8, D3);
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, D6)
        .add(BLACK_KING, D2)
        .add(BLACK_ROOK, D8)
        .build());

    assertFalse(situation.isValid(move));
  }

  @Test
  public void moveIsNotValidWhenUnavailableForThatPieceType() {
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, D6)
        .add(BLACK_KING, D2)
        .add(BLACK_ROOK, D8)
        .build());

    assertFalse(situation.isValid(new Move(D8, E7)));
    assertFalse(situation.isValid(new Move(D8, E2)));
    assertFalse(situation.isValid(new Move(D2, D4)));
  }

  @Test
  public void moveIsNotValidWhenSteppingIntoCheck() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, C3)
        .add(BLACK_KING, C5)
        .add(BLACK_ROOK, D2)
        .build());

    assertFalse(situation.isValid(new Move(C3, C4)));
    assertFalse(situation.isValid(new Move(C3, C2)));
    assertTrue(situation.isValid(new Move(C3, D2)));
  }

  @Test
  public void validMoves() {
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, D6)
        .add(BLACK_KING, D2)
        .add(BLACK_ROOK, D8)
        .build());

    assertTrue(situation.isValid(new Move(D2, D3)));
    assertTrue(situation.isValid(new Move(D2, E3)));
    assertTrue(situation.isValid(new Move(D8, G8)));
  }

  @Test
  public void moveIsNotValidWhenKingMovesNextToOpponentsKing() {
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, C3)
        .add(BLACK_ROOK, C2)
        .build());

    assertFalse(situation.isValid(new Move(C3, B2)));
  }

  // ==========================================================================
  // Check
  // ==========================================================================

  @Test
  public void isCheckWhenEnemyPieceCouldCaptureCurrentPlayersKing() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, D6)
        .add(BLACK_ROOK, D8)
        .add(BLACK_KING, C8)
        .build());

    assertTrue(situation.isCheck());
  }

  @Test
  public void isNotCheckWhenOwnPieceCouldCaptureCurrentPlayersKing() {
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, A1)
        .add(BLACK_ROOK, D8)
        .add(BLACK_KING, D2)
        .build());

    assertFalse(situation.isCheck());
  }

  @Test
  public void isNotCheckWhenCurrentPlayersPieceCouldCaptureOpponentsKing() {
    var situation = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, D6)
        .add(BLACK_ROOK, D8)
        .add(BLACK_KING, C8)
        .build());

    assertFalse(situation.isCheck());
  }

  // ==========================================================================
  // Mate
  // ==========================================================================

  @Test
  public void isMateWhenCheckAndKingCanNotMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());

    assertTrue(situation.isMate());
  }

  @Test
  public void isNotMateWhenCheckAndKingCanMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B4)
        .add(BLACK_ROOK, D1)
        .build());

    assertFalse(situation.isMate());
  }

  @Test
  public void isNotMateWhenNotCheckAndKingCanMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B4)
        .add(BLACK_ROOK, D4)
        .build());

    assertFalse(situation.isMate());
  }

  @Test
  public void isNotMateWhenNotCheckAndKingCanNotMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, A1)
        .add(BLACK_KING, A3)
        .add(BLACK_ROOK, B3)
        .build());

    assertFalse(situation.isMate());
  }

  // ==========================================================================
  // Stalemate
  // ==========================================================================

  @Test
  public void isStalemateWhenNotCheckAndKingCanNotMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, A1)
        .add(BLACK_KING, A3)
        .add(BLACK_ROOK, B3)
        .build());

    assertTrue(situation.isStalemate());
  }

  @Test
  public void isNotStalemateWhenCheckAndKingCanNotMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());

    assertFalse(situation.isStalemate());
  }

  @Test
  public void isNotStalemateWhenCheckAndKingCanMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B4)
        .add(BLACK_ROOK, D1)
        .build());

    assertFalse(situation.isStalemate());
  }

  @Test
  public void isNotStalemateWhenNotCheckAndKingCanMove() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B4)
        .add(BLACK_ROOK, D4)
        .build());

    assertFalse(situation.isStalemate());
  }

  // ==========================================================================
  // Status
  // ==========================================================================

  @Test
  public void statusInProgress() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B4)
        .add(BLACK_ROOK, D4)
        .build());

    assertEquals(IN_PROGRESS, situation.status());
  }

  @Test
  public void statusStalemateShouldBeDraw() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, A1)
        .add(BLACK_KING, A3)
        .add(BLACK_ROOK, B3)
        .build());

    assertEquals(DRAW, situation.status());
  }

  @Test
  public void statusMateShouldBeWin() {
    var situation = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());

    assertEquals(win(BLACK), situation.status());
  }

  // ==========================================================================
  // Equality
  // ==========================================================================

  @Test
  public void sameSituationsShouldBeEqual() {
    var first = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());
    var second = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void differentColorsShouldNotBeEqual() {
    var first = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());
    var second = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());

    assertNotEquals(first, second);
  }

  @Test
  public void differentPositionsShouldNotBeEqual() {
    var first = new Situation(
      WHITE,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, D1)
        .add(BLACK_ROOK, B3)
        .build());
    var second = new Situation(
      BLACK,
      Board.builder()
        .add(WHITE_KING, B1)
        .add(BLACK_KING, B3)
        .add(BLACK_ROOK, D1)
        .build());

    assertNotEquals(first, second);
  }
}
