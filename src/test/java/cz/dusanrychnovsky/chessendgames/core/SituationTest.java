package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.core.Result.Status.DRAW;
import static cz.dusanrychnovsky.chessendgames.core.Result.Status.IN_PROGRESS;
import static cz.dusanrychnovsky.chessendgames.core.Result.Status.WIN;
import static org.junit.Assert.*;

public class SituationTest {

  private static final Color SOME_COLOR = Color.WHITE;
  
  private static final Piece WHITE_KING = new Piece(Color.WHITE, new King());
  private static final Piece WHITE_ROOK = new Piece(Color.WHITE, new Rook());
  private static final Piece BLACK_KING = new Piece(Color.BLACK, new King());

  // ==========================================================================
  // BUILDER
  // ==========================================================================

  @Test
  public void givenAValidArrangementOfPiecesAndCurrPlayerColor_buildsUpCorrespondingSituation() {

    Situation result = Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, Position.F4)
      .addPiece(WHITE_ROOK, Position.B3)
      .addPiece(BLACK_KING, Position.G2)
      .build();

    Assert.assertEquals(Color.WHITE, result.getCurrentColor());
    Assert.assertEquals(Position.F4, result.getPosition(WHITE_KING).get());
    Assert.assertEquals(Position.B3, result.getPosition(WHITE_ROOK).get());
    Assert.assertEquals(Position.G2, result.getPosition(BLACK_KING).get());
  }

  @Test(expected = IllegalArgumentException.class)
  public void aPieceAtMultiplePositions_notAllowed() {
    Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, Position.F4)
      .addPiece(WHITE_KING, Position.B3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void multiplePiecesAtTheSamePosition_notAllowed() {
    Situation.builder(SOME_COLOR)
      .addPiece(WHITE_KING, Position.F4)
      .addPiece(BLACK_KING, Position.F4);
  }
  
  // ==========================================================================
  // GET PIECE AT POSITION
  // ==========================================================================
  
  @Test
  public void positionOccupied_returnsOccupyingPiece() {
    
    Piece whiteKing = new Piece(Color.WHITE, new King());
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(whiteKing, Position.A3)
      .build();
    
    assertEquals(Optional.of(whiteKing), situation.getPiece(Position.A3));
  }
  
  @Test
  public void positionEmpty_returnsEmptyResult() {
  
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(new Piece(Color.WHITE, new King()), Position.A3)
      .build();
  
    assertEquals(Optional.empty(), situation.getPiece(Position.A4));
  }
  
  // ==========================================================================
  // GET PIECE'S POSITION
  // ==========================================================================
  
  @Test
  public void piecePresent_returnsItsPosition() {
  
    Piece whiteKing = new Piece(Color.WHITE, new King());
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(whiteKing, Position.A3)
      .build();
  
    assertEquals(Optional.of(Position.A3), situation.getPosition(whiteKing));
  }
  
  @Test
  public void pieceNotPresent_returnsEmptyResult() {
  
    Situation situation = Situation.builder(SOME_COLOR)
      .addPiece(new Piece(Color.WHITE, new King()), Position.A3)
      .build();
  
    assertEquals(
      Optional.empty(),
      situation.getPosition(new Piece(Color.BLACK, new King()))
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
      .addPiece(WHITE_KING, Position.F2)
      .addPiece(BLACK_KING, Position.H1)
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

    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(WHITE_KING, Position.F2)
      .addPiece(WHITE_ROOK, Position.G2)
      .addPiece(BLACK_KING, Position.H1)
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

    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(WHITE_KING, Position.F2)
      .addPiece(WHITE_ROOK, Position.H3)
      .addPiece(BLACK_KING, Position.H1)
      .build();

    Result result = situation.getResult();
    assertEquals(WIN, result.getStatus());
    Assert.assertEquals(Color.WHITE, ((Win) result).getWinnerColor());
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

    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(WHITE_KING, Position.F2)
      .addPiece(WHITE_ROOK, Position.G3)
      .addPiece(BLACK_KING, Position.H1)
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
    
    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(WHITE_KING, Position.C6)
      .addPiece(WHITE_ROOK, Position.B4)
      .addPiece(BLACK_KING, Position.D4)
      .build();
    
    assertFalse(situation.isValidMove(new Move(Position.B4, Position.B7)));
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
    
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.C6)
      .addPiece(WHITE_ROOK, Position.B4)
      .addPiece(BLACK_KING, Position.D4)
      .build();
    
    assertFalse(situation.isValidMove(new Move(Position.B4, Position.A7)));
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
    
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.C6)
      .addPiece(WHITE_ROOK, Position.B4)
      .addPiece(BLACK_KING, Position.D4)
      .build();
    
    assertTrue(situation.isValidMove(new Move(Position.B4, Position.B7)));
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
  
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.C6)
      .addPiece(WHITE_ROOK, Position.B4)
      .addPiece(BLACK_KING, Position.D4)
      .build();
  
    assertFalse(situation.isValidMove(new Move(Position.B4, Position.F4)));
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
  
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.D4)
      .addPiece(WHITE_ROOK, Position.B4)
      .addPiece(BLACK_KING, Position.C6)
      .build();
  
    assertFalse(situation.isValidMove(new Move(Position.B4, Position.D4)));
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
  
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.C6)
      .addPiece(WHITE_ROOK, Position.B4)
      .addPiece(BLACK_KING, Position.D4)
      .build();
  
    assertTrue(situation.isValidMove(new Move(Position.B4, Position.D4)));
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
  
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.B4)
      .addPiece(BLACK_KING, Position.D4)
      .build();
  
    assertFalse(situation.isValidMove(new Move(Position.B4, Position.C4)));
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
    
    Situation situation = Situation.builder(Color.WHITE)
      .addPiece(WHITE_KING, Position.B4)
      .addPiece(BLACK_KING, Position.E4)
      .build();
    
    assertTrue(situation.isValidMove(new Move(Position.B4, Position.C4)));
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
    
    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(BLACK_KING, Position.D2)
      .addPiece(WHITE_KING, Position.C5)
      .addPiece(WHITE_ROOK, Position.E5)
      .build();
  
    assertFalse(situation.isValidMove(new Move(Position.D2, Position.E2)));
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
    
    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(BLACK_KING, Position.D2)
      .addPiece(WHITE_KING, Position.C5)
      .addPiece(WHITE_ROOK, Position.E5)
      .build();
  
    situation.applyMove(new Move(Position.D2, Position.E2));
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
    
    Situation fromSituation = Situation.builder(Color.BLACK)
      .addPiece(BLACK_KING, Position.D2)
      .addPiece(WHITE_KING, Position.C5)
      .addPiece(WHITE_ROOK, Position.D5)
      .build();
  
    Situation toSituation = fromSituation.applyMove(new Move(Position.D2, Position.E2));
    
    assertEquals(
      toSituation,
      Situation.builder(Color.WHITE)
        .addPiece(BLACK_KING, Position.E2)
        .addPiece(WHITE_KING, Position.C5)
        .addPiece(WHITE_ROOK, Position.D5)
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
  
    Situation fromSituation = Situation.builder(Color.BLACK)
      .addPiece(BLACK_KING, Position.D2)
      .addPiece(WHITE_KING, Position.C5)
      .addPiece(WHITE_ROOK, Position.C3)
      .build();
  
    Situation toSituation = fromSituation.applyMove(new Move(Position.D2, Position.C3));
  
    assertEquals(
      toSituation,
      Situation.builder(Color.WHITE)
        .addPiece(BLACK_KING, Position.C3)
        .addPiece(WHITE_KING, Position.C5)
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
  
    Situation situation = Situation.builder(Color.BLACK)
      .addPiece(BLACK_KING, Position.D2)
      .addPiece(WHITE_KING, Position.C4)
      .addPiece(WHITE_ROOK, Position.C3)
      .build();
  
    situation.applyMove(new Move(Position.D2, Position.C3));
  }
}
