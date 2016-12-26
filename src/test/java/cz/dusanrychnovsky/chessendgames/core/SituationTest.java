package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static cz.dusanrychnovsky.chessendgames.core.Result.Status.*;
import static org.junit.Assert.*;

public class SituationTest {

  private static final Color SOME_COLOR = WHITE;
  
  // ==========================================================================
  // BUILDER
  // ==========================================================================

  @Test
  public void givenAValidArrangementOfPiecesAndCurrPlayerColor_buildsUpCorrespondingSituation() {

    Situation result = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, F4)
      .addPiece(WHITE_ROOK, B3)
      .addPiece(BLACK_KING, G2)
      .build();

    Assert.assertEquals(WHITE, result.getCurrentColor());
    Assert.assertEquals(F4, result.getPosition(WHITE_KING).get());
    Assert.assertEquals(B3, result.getPosition(WHITE_ROOK).get());
    Assert.assertEquals(G2, result.getPosition(BLACK_KING).get());
  }

  @Test(expected = IllegalArgumentException.class)
  public void aPieceAtMultiplePositions_notAllowed() {
    Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, F4)
      .addPiece(WHITE_KING, B3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiplePiecesAtTheSamePosition_notAllowed() {
    Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, F4)
      .addPiece(BLACK_KING, F4);
  }
  
  // ==========================================================================
  // GET PIECE AT POSITION
  // ==========================================================================
  
  @Test
  public void positionOccupied_returnsOccupyingPiece() {
    
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, A3)
      .build();
    
    assertEquals(Optional.of(WHITE_KING), situation.getPiece(A3));
  }
  
  @Test
  public void positionEmpty_returnsEmptyResult() {
  
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, A3)
      .build();
  
    assertEquals(Optional.empty(), situation.getPiece(A4));
  }
  
  // ==========================================================================
  // GET PIECE'S POSITION
  // ==========================================================================
  
  @Test
  public void piecePresent_returnsItsPosition() {
  
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, A3)
      .build();
  
    assertEquals(Optional.of(A3), situation.getPosition(WHITE_KING));
  }
  
  @Test
  public void pieceNotPresent_returnsEmptyResult() {
  
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, A3)
      .build();
  
    assertEquals(
      Optional.empty(),
      situation.getPosition(BLACK_KING)
    );
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
      .addPiece(WHITE_KING, F2)
      .addPiece(BLACK_KING, H1)
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
      .addPiece(WHITE_KING, F2)
      .addPiece(WHITE_ROOK, G2)
      .addPiece(BLACK_KING, H1)
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
      .addPiece(WHITE_KING, F2)
      .addPiece(WHITE_ROOK, H3)
      .addPiece(BLACK_KING, H1)
      .build();

    Result result = situation.getResult();
    assertEquals(WIN, result.getStatus());
    Assert.assertEquals(WHITE, ((Win) result).getWinnerColor());
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
      .addPiece(WHITE_KING, F2)
      .addPiece(WHITE_ROOK, G3)
      .addPiece(BLACK_KING, H1)
      .build();

    assertEquals(IN_PROGRESS, situation.getResult().getStatus());
  }
  
  // ==========================================================================
  // (IN)VALID MOVES
  // ==========================================================================
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . K . . . . .
  // 5 | . . . . . . . .
  // 4 | . R . . . . . .
  // 3 | . . . K . . . .
  // 2 | . . x . . x . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void unoccupiedFromPosition_notValid() {
    
    Situation situation = Situation.builder(BLACK)
      .addPiece(WHITE_KING, C6)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, D3)
      .build();
    
    assertFalse(situation.isValidMove(new Move(C2, F2)));
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
  public void notThatPlayersTurn_notValid() {
    
    Situation situation = Situation.builder(BLACK)
      .addPiece(WHITE_KING, C6)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, D4)
      .build();
    
    assertFalse(situation.isValidMove(new Move(B4, B7)));
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
    
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, C6)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, D4)
      .build();
    
    assertFalse(situation.isValidMove(new Move(B4, A7)));
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
    
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, C6)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, D4)
      .build();
    
    assertTrue(situation.isValidMove(new Move(B4, B7)));
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
  
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, C6)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, D4)
      .build();
  
    assertFalse(situation.isValidMove(new Move(B4, F4)));
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
  
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, D4)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, C6)
      .build();
  
    assertFalse(situation.isValidMove(new Move(B4, D4)));
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
  
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, C6)
      .addPiece(WHITE_ROOK, B4)
      .addPiece(BLACK_KING, D4)
      .build();
  
    assertTrue(situation.isValidMove(new Move(B4, D4)));
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
  
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, B4)
      .addPiece(BLACK_KING, D4)
      .build();
  
    assertFalse(situation.isValidMove(new Move(B4, C4)));
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
    
    Situation situation = Situation.builder(WHITE)
      .addPiece(WHITE_KING, B4)
      .addPiece(BLACK_KING, E4)
      .build();
    
    assertTrue(situation.isValidMove(new Move(B4, C4)));
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
    
    Situation situation = Situation.builder(BLACK)
      .addPiece(BLACK_KING, D2)
      .addPiece(WHITE_KING, C5)
      .addPiece(WHITE_ROOK, E5)
      .build();
  
    assertFalse(situation.isValidMove(new Move(D2, E2)));
  }
  
  // ==========================================================================
  // APPLY MOVE
  // ==========================================================================
  
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
  @Test(expected = IllegalArgumentException.class)
  public void cannotApplyAnInvalidMove() {
    
    Situation situation = Situation.builder(BLACK)
      .addPiece(BLACK_KING, D2)
      .addPiece(WHITE_KING, C5)
      .addPiece(WHITE_ROOK, E5)
      .build();
  
    situation.applyMove(new Move(D2, E2));
  }
  
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . K R . . . .
  // 4 | . . . . . . . .
  // 3 | . . . . . . . .
  // 2 | . . . K x . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void canApplyAValidMove() {
    
    Situation fromSituation = Situation.builder(BLACK)
      .addPiece(BLACK_KING, D2)
      .addPiece(WHITE_KING, C5)
      .addPiece(WHITE_ROOK, D5)
      .build();
  
    Situation toSituation = fromSituation.applyMove(new Move(D2, E2));
    
    assertEquals(
      toSituation,
      Situation.builder(WHITE)
        .addPiece(BLACK_KING, E2)
        .addPiece(WHITE_KING, C5)
        .addPiece(WHITE_ROOK, D5)
        .build()
    );
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . K . . . . .
  // 4 | . . . . . . . .
  // 3 | . . R . . . . .
  // 2 | . . . K . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test
  public void canApplyAValidCapture() {
  
    Situation fromSituation = Situation.builder(BLACK)
      .addPiece(BLACK_KING, D2)
      .addPiece(WHITE_KING, C5)
      .addPiece(WHITE_ROOK, C3)
      .build();
  
    Situation toSituation = fromSituation.applyMove(new Move(D2, C3));
  
    assertEquals(
      toSituation,
      Situation.builder(WHITE)
        .addPiece(BLACK_KING, C3)
        .addPiece(WHITE_KING, C5)
        .build()
    );
  }
  
  // 8 | . . . . . . . .
  // 7 | . . . . . . . .
  // 6 | . . . . . . . .
  // 5 | . . . . . . . .
  // 4 | . . K . . . . .
  // 3 | . . R . . . . .
  // 2 | . . . K . . . .
  // 1 | . . . . . . . .
  // --|----------------
  //   | A B C D E F G H
  @Test(expected = IllegalArgumentException.class)
  public void cannotApplyAnInvalidCapture() {
  
    Situation situation = Situation.builder(BLACK)
      .addPiece(BLACK_KING, D2)
      .addPiece(WHITE_KING, C4)
      .addPiece(WHITE_ROOK, C3)
      .build();
  
    situation.applyMove(new Move(D2, C3));
  }
}
