package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.proto.Movesdb;
import org.junit.Test;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProtoSerializerTest {

  private final static ProtoSerializer SERIALIZER = new ProtoSerializer();

  // ==========================================================================
  // to proto - row
  // ==========================================================================

  @Test
  public void toProtoShouldConvertRow() {
    assertEquals(Movesdb.Row.R3, SERIALIZER.toProto(Row.R3));
  }

  // ==========================================================================
  // to proto - column
  // ==========================================================================

  @Test
  public void toProtoShouldConvertColumn() {
    assertEquals(Movesdb.Column.CE, SERIALIZER.toProto(Column.CE));
  }

  // ==========================================================================
  // to proto - position
  // ==========================================================================

  @Test
  public void toProtoShouldConvertPosition() {
    assertEquals(
      Movesdb.Position.newBuilder()
        .setColumn(Movesdb.Column.CH)
        .setRow(Movesdb.Row.R3)
        .build(),
      SERIALIZER.toProto(
        Position.H3
      ));
  }

  // ==========================================================================
  // to proto - move
  // ==========================================================================

  @Test
  public void toProtoShouldConvertMove() {
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
      SERIALIZER.toProto(
        new Move(Position.H3, Position.H1
      )));
  }

  // ==========================================================================
  // to proto - color
  // ==========================================================================

  @Test
  public void toProtoShouldConvertColor() {
    assertEquals(Movesdb.Color.White, SERIALIZER.toProto(Color.WHITE));
  }

  // ==========================================================================
  // to proto - piece type
  // ==========================================================================

  @Test
  public void toProtoShouldConvertPieceType() {
    assertEquals(Movesdb.PieceType.King, SERIALIZER.toProto(PieceType.KING));
  }

  // ==========================================================================
  // to proto - piece
  // ==========================================================================

  @Test
  public void toProtoShouldConvertPiece() {
    assertEquals(
      Movesdb.Piece.newBuilder()
        .setColor(Movesdb.Color.Black)
        .setType(Movesdb.PieceType.Rook)
        .build(),
      SERIALIZER.toProto(Piece.BLACK_ROOK));
  }

  // ==========================================================================
  // to proto - situation
  // ==========================================================================

  @Test
  public void toProtoShouldConvertSituation() {
    var result = SERIALIZER.toProto(
      new Situation(
        Color.WHITE,
        Board.builder()
          .add(Piece.WHITE_KING, Position.A6)
          .add(Piece.BLACK_ROOK, Position.H1)
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
  public void toProtoShouldConvertDatabaseWithSingleEntry() {
    var result = SERIALIZER.toProto(
      new Database(
        ofEntries(
          entry(
            new Situation(
              Color.WHITE,
              Board.builder()
                .add(Piece.WHITE_KING, Position.A6)
                .add(Piece.BLACK_ROOK, Position.H1)
                .build()),
            new Move(Position.A6, Position.A7)))));
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
