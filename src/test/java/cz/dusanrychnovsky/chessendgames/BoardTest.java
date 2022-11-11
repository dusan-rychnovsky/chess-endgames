package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Optional;

public class BoardTest {

  // ==========================================================================
  // Entry Parsing
  // ==========================================================================

  @Test
  public void entry_parse_extractsColorPieceAndPosition() {
    var entry = Board.Entry.parse("white king d3");
    assertEquals(WHITE, entry.piece().color());
    assertEquals(KING, entry.piece().type());
    assertEquals(D3, entry.position());
  }

  // ==========================================================================
  // Building
  // ==========================================================================

  @Test
  public void add_positionsPiecesOnBoard() {
    var board = Board.builder()
      .add(WhiteKing, D3)
      .add(BlackKing, F5)
      .add(BlackRook, E6)
      .build();

    assertEquals(WhiteKing, board.pieceAt(D3).get());
    assertEquals(BlackKing, board.pieceAt(F5).get());
    assertEquals(BlackRook, board.pieceAt(E6).get());
    assertEquals(Optional.empty(), board.pieceAt(D4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void add_duplicatePosition_fails() {
    Board.builder()
      .add(BlackKing, D3)
      .add(WhiteKing, D3);
  }

  @Test
  public void addAll_positionsAllGivenPiecesOnBoard() {
    var board = Board.builder()
      .addAll(
        Map.of(
          D3, WhiteKing,
          F5, BlackKing,
          E6, BlackRook
        )
      )
      .build();

    assertEquals(WhiteKing, board.pieceAt(D3).get());
    assertEquals(BlackKing, board.pieceAt(F5).get());
    assertEquals(BlackRook, board.pieceAt(E6).get());
    assertEquals(Optional.empty(), board.pieceAt(D4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addAll_duplicatePosition_fails() {
    Board.builder()
      .add(BlackKing, D3)
      .addAll(
        Map.of(
          D3, WhiteKing
        )
      );
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void print_emptyBoard() {
    var board = Board.builder().build();
    assertEquals(
      "8 | . . . . . . . .\n" +
        "7 | . . . . . . . .\n" +
        "6 | . . . . . . . .\n" +
        "5 | . . . . . . . .\n" +
        "4 | . . . . . . . .\n" +
        "3 | . . . . . . . .\n" +
        "2 | . . . . . . . .\n" +
        "1 | . . . . . . . .\n" +
        "--|----------------\n" +
        "  | A B C D E F G H\n",
      board.print());
  }

  @Test
  public void print_boardWithPieces() {
    var board = Board.builder()
      .add(WhiteKing, D3)
      .add(BlackKing, F5)
      .add(BlackRook, E6)
      .build();
    assertEquals(
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
      board.print());
  }

  // ==========================================================================
  // Filtering
  // ==========================================================================

  @Test
  public void pieces_ofColor_returnsAllPiecesOfTheGivenColor() {
    var board = Board.builder()
      .add(WhiteKing, D3)
      .add(BlackKing, F5)
      .add(BlackRook, E6)
      .build();

    var pieces = board.pieces(BLACK);

    assertEquals(2, pieces.size());
    assertEquals(BlackKing, pieces.get(F5));
    assertEquals(BlackRook, pieces.get(E6));
  }

  // ==========================================================================
  // King position
  // ==========================================================================

  @Test
  public void kingPos_returnsPositionOfKingOfTheGivenColor() {
    var board = Board.builder()
      .add(WhiteKing, D3)
      .add(BlackKing, F5)
      .add(BlackRook, E6)
      .build();

    var pos = board.kingPos(BLACK);
    assertEquals(F5, pos.get());
  }

  @Test
  public void kingPos_kingNotPresent_returnsEmptyOptional() {
    var board = Board.builder().build();
    assertEquals(Optional.empty(), board.kingPos(WHITE));
  }
}
