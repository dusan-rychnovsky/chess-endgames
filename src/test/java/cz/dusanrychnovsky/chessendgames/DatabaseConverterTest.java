package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.proto.Movesdb;
import org.junit.Test;

import static java.util.List.of;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatabaseConverterTest {

  private final static DatabaseConverter converter = new DatabaseConverter();

  // ==========================================================================
  // to proto - row
  // ==========================================================================

  @Test
  public void toProto_convertsRow() {
    assertEquals(Movesdb.Row.R3, converter.toProto(Row.R3));
  }

  // ==========================================================================
  // to proto - column
  // ==========================================================================

  @Test
  public void toProto_convertsColumn() {
    assertEquals(Movesdb.Column.CE, converter.toProto(Column.CE));
  }

  // ==========================================================================
  // to proto - position
  // ==========================================================================

  @Test
  public void toProto_convertsPosition() {
    assertEquals(
      Movesdb.Position.newBuilder()
        .setColumn(Movesdb.Column.CH)
        .setRow(Movesdb.Row.R3)
        .build(),
      converter.toProto(
        Position.H3
      ));
  }

  // ==========================================================================
  // to proto - move
  // ==========================================================================

  @Test
  public void toProto_convertsMove() {
    assertEquals(
      Movesdb.Move.newBuilder()
        .setFrom(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CH)
            .setRow(Movesdb.Row.R3))
        .setTo(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CH)
            .setRow(Movesdb.Row.R1))
        .build(),
      converter.toProto(
        new Move(Position.H3, Position.H1
      )));
  }

  // ==========================================================================
  // to proto - color
  // ==========================================================================

  @Test
  public void toProto_convertsColor() {
    assertEquals(Movesdb.Color.White, converter.toProto(Color.White));
  }

  // ==========================================================================
  // to proto - piece type
  // ==========================================================================

  @Test
  public void toProto_convertsPieceType() {
    assertEquals(Movesdb.PieceType.King, converter.toProto(PieceType.King));
  }

  // ==========================================================================
  // to proto - piece
  // ==========================================================================

  @Test
  public void toProto_convertsPiece() {
    assertEquals(
      Movesdb.Piece.newBuilder()
        .setColor(Movesdb.Color.Black)
        .setType(Movesdb.PieceType.Rook)
        .build(),
      converter.toProto(Piece.BlackRook));
  }

  // ==========================================================================
  // to proto - situation
  // ==========================================================================

  @Test
  public void toProto_convertsSituation() {
    var result = converter.toProto(
      new Situation(
        Color.White,
        Board.builder()
          .add(Piece.WhiteKing, Position.A6)
          .add(Piece.BlackRook, Position.H1)
          .build()));
    var values = result.getValuesList();
    assertEquals(2, values.size());
    assertTrue(values.contains(
      Movesdb.Situation.Pair.newBuilder()
        .setPiece(
          Movesdb.Piece.newBuilder()
            .setColor(Movesdb.Color.White)
            .setType(Movesdb.PieceType.King))
        .setPosition(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CA)
            .setRow(Movesdb.Row.R6))
        .build()
    ));
    assertTrue(values.contains(
      Movesdb.Situation.Pair.newBuilder()
        .setPiece(
          Movesdb.Piece.newBuilder()
            .setColor(Movesdb.Color.Black)
            .setType(Movesdb.PieceType.Rook))
        .setPosition(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CH)
            .setRow(Movesdb.Row.R1))
        .build()
    ));
  }

  // ==========================================================================
  // to proto - database
  // ==========================================================================

  @Test
  public void toProto_convertsDatabase_singleEntry() {
    var result = converter.toProto(
      ofEntries(
        entry(
          new Situation(
            Color.White,
            Board.builder()
              .add(Piece.WhiteKing, Position.A6)
              .add(Piece.BlackRook, Position.H1)
              .build()),
          new Move(Position.A6, Position.A7))));
    var dbValues = result.getValuesList();
    assertEquals(1, dbValues.size());
    var move = dbValues.get(0).getMove();
    assertEquals(
      Movesdb.Move.newBuilder()
        .setFrom(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CA)
            .setRow(Movesdb.Row.R6))
        .setTo(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CA)
            .setRow(Movesdb.Row.R7))
        .build(),
      move);
    var situation = dbValues.get(0).getSituation();
    var values = situation.getValuesList();
    assertEquals(2, values.size());
    assertTrue(values.contains(
      Movesdb.Situation.Pair.newBuilder()
        .setPiece(
          Movesdb.Piece.newBuilder()
            .setColor(Movesdb.Color.White)
            .setType(Movesdb.PieceType.King))
        .setPosition(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CA)
            .setRow(Movesdb.Row.R6))
        .build()
    ));
    assertTrue(values.contains(
      Movesdb.Situation.Pair.newBuilder()
        .setPiece(
          Movesdb.Piece.newBuilder()
            .setColor(Movesdb.Color.Black)
            .setType(Movesdb.PieceType.Rook))
        .setPosition(
          Movesdb.Position.newBuilder()
            .setColumn(Movesdb.Column.CH)
            .setRow(Movesdb.Row.R1))
        .build()
    ));
  }
}
