package cz.dusanrychnovsky.chessendgames.yaat;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.yaat.Color.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Column.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.DRAW;
import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.IN_PROGRESS;
import static cz.dusanrychnovsky.chessendgames.yaat.Result.Status.WIN;
import static cz.dusanrychnovsky.chessendgames.yaat.Row.*;
import static org.junit.Assert.*;

public class SituationTest {

  private static final Color SOME_COLOR = WHITE;

  // ==========================================================================
  // BUILDER
  // ==========================================================================

  @Test
  public void givenAValidArrangementOfPiecesAndCurrPlayerColor_buildsUpCorrespondingSituation() {

    Piece whiteKing = new Piece(WHITE, new King());
    Position f4 = new Position(CF, R4);

    Piece whiteRook = new Piece(WHITE, new Rook());
    Position b3 = new Position(CB, R3);

    Piece blackKing = new Piece(BLACK, new King());
    Position g2 = new Position(CG, R2);

    Situation result = Situation.builder(SOME_COLOR)
      .addPiece(whiteKing, f4)
      .addPiece(whiteRook, b3)
      .addPiece(blackKing, g2)
      .build();

    assertEquals(WHITE, result.getCurrentColor());
    assertEquals(f4, result.getPosition(whiteKing));
    assertEquals(b3, result.getPosition(whiteRook));
    assertEquals(g2, result.getPosition(blackKing));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aPieceAtMultiplePositions_notAllowed() {
    Piece whiteKing = new Piece(WHITE, new King());
    Situation.builder(SOME_COLOR)
      .addPiece(whiteKing, new Position(CF, R4))
      .addPiece(whiteKing, new Position(CB, R3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiplePiecesAtTheSamePosition_notAllowed() {
    Position f4 = new Position(CF, R4);
    Situation.builder(SOME_COLOR)
      .addPiece(new Piece(WHITE, new King()), f4)
      .addPiece(new Piece(BLACK, new King()), f4);
  }

  // ==========================================================================
  // GET RESULT
  // ==========================================================================

  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . K . .
  // 1 | . . . . . . . K
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void onlyKings_draw() {

    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(new Piece(WHITE, new King()), new Position(CF, R2))
      .addPiece(new Piece(BLACK, new King()), new Position(CH, R1))
      .build();

    assertEquals(DRAW, situation.getResult().getStatus());
  }

  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . K R .
  // 1 | . . . . . . . K
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void stalemate_draw() {

    Situation situation = Situation.builder(BLACK)
      .addPiece(new Piece(WHITE, new King()), new Position(CF, R2))
      .addPiece(new Piece(WHITE, new Rook()), new Position(CG, R2))
      .addPiece(new Piece(BLACK, new King()), new Position(CH, R1))
      .build();

    assertEquals(DRAW, situation.getResult().getStatus());
  }

  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . R
  // 2 | . . . . . K . .
  // 1 | . . . . . . . K
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void checkmate_win() {

    Situation situation = Situation.builder(BLACK)
      .addPiece(new Piece(WHITE, new King()), new Position(CF, R2))
      .addPiece(new Piece(WHITE, new Rook()), new Position(CH, R3))
      .addPiece(new Piece(BLACK, new King()), new Position(CH, R1))
      .build();

    Result result = situation.getResult();
    assertEquals(WIN, result.getStatus());
    assertEquals(WHITE, ((Win) result).getWinnerColor());
  }

  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . R .
  // 2 | . . . . . K . .
  // 1 | . . . . . . . K
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void inProgress() {

    Situation situation = Situation.builder(BLACK)
      .addPiece(new Piece(WHITE, new King()), new Position(CF, R2))
      .addPiece(new Piece(WHITE, new Rook()), new Position(CG, R3))
      .addPiece(new Piece(BLACK, new King()), new Position(CH, R1))
      .build();

    assertEquals(IN_PROGRESS, situation.getResult().getStatus());
  }
}
