package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;
import static org.junit.Assert.assertTrue;

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
  public void builder_positionsPiecesOnBoard() {
    var board = Board.builder()
      .add(WhiteKing, D3)
      .add(BlackKing, F5)
      .add(BlackRook, E6)
      .build();

    assertEquals(WhiteKing, board.atPosition(D3).get());
    assertEquals(BlackKing, board.atPosition(F5).get());
    assertEquals(BlackRook, board.atPosition(E6).get());
    assertEquals(Optional.empty(), board.atPosition(D4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void builder_duplicatePosition_fails() {
    Board.builder()
      .add(BlackKing, D3)
      .add(WhiteKing, D3);
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
