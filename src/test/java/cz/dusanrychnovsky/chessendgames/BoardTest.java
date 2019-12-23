package cz.dusanrychnovsky.chessendgames;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

public class BoardTest {

  // ==========================================================================
  // Entry Parsing
  // ==========================================================================

  @Test
  public void entry_parse_extractsColorPieceAndPosition() {
    Board.Entry entry = Board.Entry.parse("white king d3");
    assertEquals(White, entry.piece().color());
    assertEquals(King, entry.piece().type());
    assertEquals(D3, entry.position());
  }

  // ==========================================================================
  // Building
  // ==========================================================================

  @Test
  public void builder_positionsPiecesOnBoard() {
    Piece whiteKing = new Piece(White, King);
    Piece blackKing = new Piece(Black, King);
    Piece blackRook = new Piece(Black, Rook);
    Board board = Board.builder()
      .add(whiteKing, D3)
      .add(blackKing, F5)
      .add(blackRook, E6)
      .build();

    assertEquals(whiteKing, board.atPosition(D3).get());
    assertEquals(blackKing, board.atPosition(F5).get());
    assertEquals(blackRook, board.atPosition(E6).get());
    assertEquals(Optional.empty(), board.atPosition(D4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void builder_duplicatePosition_fails() {
    Board.builder()
      .add(new Piece(Black, King), D3)
      .add(new Piece(White, King), D3);
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void print_emptyBoard() {
    Board board = Board.builder().build();
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
    Piece whiteKing = new Piece(White, King);
    Piece blackKing = new Piece(Black, King);
    Piece blackRook = new Piece(Black, Rook);
    Board board = Board.builder()
      .add(whiteKing, D3)
      .add(blackKing, F5)
      .add(blackRook, E6)
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
}
