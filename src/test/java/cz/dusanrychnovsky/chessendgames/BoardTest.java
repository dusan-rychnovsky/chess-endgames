package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Board.builder;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.stream.Stream;

public class BoardTest {

  // ==========================================================================
  // Entry Parsing
  // ==========================================================================

  @Test
  public void entryParseShouldExtractColorPieceAndPosition() {
    var entry = Board.Entry.parse("white king d3");
    assertEquals(WHITE, entry.piece().color());
    assertEquals(KING, entry.piece().type());
    assertEquals(D3, entry.position());
  }

  // ==========================================================================
  // Building
  // ==========================================================================

  @Test
  public void addShouldPositionPiecesOnBoard() {
    var board = builder()
      .add(WHITE_KING, D3)
      .add(BLACK_KING, F5)
      .add(BLACK_ROOK, E6)
      .build();

    assertEquals(WHITE_KING, board.pieceAt(D3).get());
    assertEquals(BLACK_KING, board.pieceAt(F5).get());
    assertEquals(BLACK_ROOK, board.pieceAt(E6).get());
    assertEquals(Optional.empty(), board.pieceAt(D4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addShouldFailWhenDuplicatePosition() {
    builder()
      .add(BLACK_KING, D3)
      .add(WHITE_KING, D3);
  }

  @Test
  public void addAllShouldPositionAllGivenPiecesOnBoard() {
    var board = builder()
      .addAll(
        Stream.of(
          new PiecePosition(WHITE, KING, D3),
          new PiecePosition(BLACK, KING, F5),
          new PiecePosition(BLACK, ROOK, E6)
        )
      )
      .build();

    assertEquals(WHITE_KING, board.pieceAt(D3).get());
    assertEquals(BLACK_KING, board.pieceAt(F5).get());
    assertEquals(BLACK_ROOK, board.pieceAt(E6).get());
    assertEquals(Optional.empty(), board.pieceAt(D4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAllShouldFailWhenDuplicatePosition() {
    builder()
      .add(BLACK_KING, D3)
      .addAll(
        Stream.of(
          new PiecePosition(WHITE, KING, D3)
        )
      );
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void printShouldPrintEmptyBoard() {
    var board = builder().build();
    assertEquals(
      """
        8 | . . . . . . . .
        7 | . . . . . . . .
        6 | . . . . . . . .
        5 | . . . . . . . .
        4 | . . . . . . . .
        3 | . . . . . . . .
        2 | . . . . . . . .
        1 | . . . . . . . .
        --|----------------
          | A B C D E F G H
        """,
      board.print());
  }

  @Test
  public void printShouldPrintBoardWithPieces() {
    var board = builder()
      .add(WHITE_KING, D3)
      .add(BLACK_KING, F5)
      .add(BLACK_ROOK, E6)
      .build();
    assertEquals(
      """
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
      board.print());
  }

  // ==========================================================================
  // Filtering
  // ==========================================================================

  @Test
  public void piecesOfColorShouldReturnAllPiecesOfTheGivenColor() {
    var board = builder()
      .add(WHITE_KING, D3)
      .add(BLACK_KING, F5)
      .add(BLACK_ROOK, E6)
      .build();

    var pieces = board.pieces(BLACK).toList();

    assertEquals(2, pieces.size());
    assertTrue(pieces.contains(new PiecePosition(BLACK, KING, F5)));
    assertTrue(pieces.contains(new PiecePosition(BLACK, ROOK, E6)));
  }

  // ==========================================================================
  // King position
  // ==========================================================================

  @Test
  public void kingPosShouldReturnPositionOfKingOfTheGivenColor() {
    var board = builder()
      .add(WHITE_KING, D3)
      .add(BLACK_KING, F5)
      .add(BLACK_ROOK, E6)
      .build();

    var pos = board.kingPos(BLACK);
    assertEquals(F5, pos.get());
  }

  @Test
  public void kingPosShouldReturnEmptyResultWhenKingNotPresent() {
    var board = builder().build();
    assertEquals(Optional.empty(), board.kingPos(WHITE));
  }
}
