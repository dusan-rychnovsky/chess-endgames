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
  
  private static final Piece WHITE_KING = new Piece(WHITE, new King());
  private static final Piece WHITE_ROOK = new Piece(WHITE, new Rook());
  private static final Piece BLACK_KING = new Piece(BLACK, new King());

  // ==========================================================================
  // BUILDER
  // ==========================================================================

  @Test
  public void givenAValidArrangementOfPiecesAndCurrPlayerColor_buildsUpCorrespondingSituation() {

    Position f4 = new Position(CF, R4);
    Position b3 = new Position(CB, R3);
    Position g2 = new Position(CG, R2);

    Situation result = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, f4)
      .addPiece(WHITE_ROOK, b3)
      .addPiece(BLACK_KING, g2)
      .build();

    assertEquals(WHITE, result.getCurrentColor());
    assertEquals(f4, result.getPosition(WHITE_KING));
    assertEquals(b3, result.getPosition(WHITE_ROOK));
    assertEquals(g2, result.getPosition(BLACK_KING));
  }

  @Test(expected = IllegalArgumentException.class)
  public void aPieceAtMultiplePositions_notAllowed() {
    Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, new Position(CF, R4))
      .addPiece(WHITE_KING, new Position(CB, R3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiplePiecesAtTheSamePosition_notAllowed() {
    Position f4 = new Position(CF, R4);
    Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, f4)
      .addPiece(BLACK_KING, f4);
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
      .addPiece(WHITE_KING, new Position(CF, R2))
      .addPiece(BLACK_KING, new Position(CH, R1))
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
      .addPiece(WHITE_KING, new Position(CF, R2))
      .addPiece(WHITE_ROOK, new Position(CG, R2))
      .addPiece(BLACK_KING, new Position(CH, R1))
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
      .addPiece(WHITE_KING, new Position(CF, R2))
      .addPiece(WHITE_ROOK, new Position(CH, R3))
      .addPiece(BLACK_KING, new Position(CH, R1))
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
      .addPiece(WHITE_KING, new Position(CF, R2))
      .addPiece(WHITE_ROOK, new Position(CG, R3))
      .addPiece(BLACK_KING, new Position(CH, R1))
      .build();

    assertEquals(IN_PROGRESS, situation.getResult().getStatus());
  }
  
  // ==========================================================================
  // (IN)VALID MOVES
  // ==========================================================================
  
  // 8 | . . . . . . . .
  // 7 | . x . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . K . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void notThatPlayersTurn_notValid() {
    
    Position b4 = new Position(CB, R4);
    Situation situation = Situation.builder(BLACK)
      .addPiece(WHITE_KING, new Position(CC, R6))
      .addPiece(WHITE_ROOK, b4)
      .addPiece(BLACK_KING, new Position(CD, R4))
      .build();
    
    assertFalse(situation.isValidMove(new Move(b4, new Position(CB, R7))));
  }
  
  // 8 | . . . . . . . .
  // 7 | x . . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . K . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void notAValidMoveForThatPiece_notValid() {
    
    Position b4 = new Position(CB, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, new Position(CC, R6))
      .addPiece(WHITE_ROOK, b4)
      .addPiece(BLACK_KING, new Position(CD, R4))
      .build();
    
    assertFalse(situation.isValidMove(new Move(b4, new Position(CA, R7))));
  }
  
  // 8 | . . . . . . . .
  // 7 | . x . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . K . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveOnAFreeSpot_valid() {
    
    Position b4 = new Position(CB, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, new Position(CC, R6))
      .addPiece(WHITE_ROOK, b4)
      .addPiece(BLACK_KING, new Position(CD, R4))
      .build();
    
    assertTrue(situation.isValidMove(new Move(b4, new Position(CB, R7))));
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . K . x . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveAcrossAPiece_notValid() {
  
    Position b4 = new Position(CB, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, new Position(CC, R6))
      .addPiece(WHITE_ROOK, b4)
      .addPiece(BLACK_KING, new Position(CD, R4))
      .build();
  
    assertFalse(situation.isValidMove(new Move(b4, new Position(CF, R4))));
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . K . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveOntoOwnPiece_notValid() {
  
    Position b4 = new Position(CB, R4);
    Position d4 = new Position(CD, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, d4)
      .addPiece(WHITE_ROOK, b4)
      .addPiece(BLACK_KING, new Position(CC, R6))
      .build();
  
    assertFalse(situation.isValidMove(new Move(b4, d4)));
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . K . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveOntoOpponentsPiece_valid() {
  
    Position b4 = new Position(CB, R4);
    Position d4 = new Position(CD, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, new Position(CC, R6))
      .addPiece(WHITE_ROOK, b4)
      .addPiece(BLACK_KING, d4)
      .build();
  
    assertTrue(situation.isValidMove(new Move(b4, d4)));
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . K x K . . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveWithKingNearOpponentsKing_notValid() {
  
    Position b4 = new Position(CB, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, b4)
      .addPiece(BLACK_KING, new Position(CD, R4))
      .build();
  
    assertFalse(situation.isValidMove(new Move(b4, new Position(CC, R4))));
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . K x . K . . .
  // 3 | . . . . . . . .
  // 2 | . . . . . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveWithKingNotNearOpponentsKing_valid() {
    
    Position b4 = new Position(CB, R4);
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, b4)
      .addPiece(BLACK_KING, new Position(CE, R4))
      .build();
    
    assertTrue(situation.isValidMove(new Move(b4, new Position(CC, R4))));
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . K . R . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . K x . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void moveWithKingIntoCheck_notValid() {
    
    Position d2 = new Position(CD, R2);
    Situation situation = Situation.builder(BLACK)
      .addPiece(BLACK_KING, d2)
      .addPiece(WHITE_KING, new Position(CC, R5))
      .addPiece(WHITE_ROOK, new Position(CE, R5))
      .build();
  
    assertFalse(situation.isValidMove(new Move(d2, new Position(CE, R2))));
  }
}
