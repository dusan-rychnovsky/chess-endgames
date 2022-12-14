package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.proto.Movesdb;
import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProtoDeserializerTest {

  private static final ProtoDeserializer DESERIALIZER = new ProtoDeserializer();

  // ==========================================================================
  // from proto - row
  // ==========================================================================

  @Test
  public void fromProtoShouldConvertRow() {
    assertEquals(Row.R3, DESERIALIZER.fromProto(Movesdb.Row.R3));
  }

  // ==========================================================================
  // from proto - column
  // ==========================================================================

  @Test
  public void fromProtoShouldConvertColumn() {
    assertEquals(Column.CE, DESERIALIZER.fromProto(Movesdb.Column.CE));
  }

  // ==========================================================================
  // to proto - position
  // ==========================================================================

  @Test
  public void toProtoShouldConvertPosition() {
    assertEquals(
      Position.H3,
      DESERIALIZER.fromProto(
        Movesdb.Position.newBuilder()
          .setColumn(Movesdb.Column.CH)
          .setRow(Movesdb.Row.R3)
          .build()));
  }

  // ==========================================================================
  // to proto - move
  // ==========================================================================

  @Test
  public void toProtoShouldConvertMove() {
    assertEquals(
      new Move(Position.H3, Position.H1),
      DESERIALIZER.fromProto(
        Movesdb.Move.newBuilder()
          .setFrom(
            Movesdb.Position.newBuilder()
              .setColumn(Movesdb.Column.CH)
              .setRow(Movesdb.Row.R3))
          .setTo(
            Movesdb.Position.newBuilder()
              .setColumn(Movesdb.Column.CH)
              .setRow(Movesdb.Row.R1))
          .build()));
  }

  // ==========================================================================
  // from proto - color
  // ==========================================================================

  @Test
  public void fromProtoShouldConvertColor() {
    assertEquals(WHITE, DESERIALIZER.fromProto(Movesdb.Color.White));
  }

  // ==========================================================================
  // from proto - piece type
  // ==========================================================================

  @Test
  public void fromProtoShouldConvertPieceType() {
    assertEquals(KING, DESERIALIZER.fromProto(Movesdb.PieceType.King));
  }

  // ==========================================================================
  // to proto - piece
  // ==========================================================================

  @Test
  public void toProtoShouldConvertPiece() {
    assertEquals(
      Piece.BLACK_ROOK,
      DESERIALIZER.fromProto(
        Movesdb.Piece.newBuilder()
          .setColor(Movesdb.Color.Black)
          .setType(Movesdb.PieceType.Rook)
          .build()));
  }

  // ==========================================================================
  // to proto - situation
  // ==========================================================================

  @Test
  public void toProtoShouldConvertSituation() {
    var result = DESERIALIZER.fromProto(
      WHITE,
      Movesdb.Situation.newBuilder()
        .addValues(
          Movesdb.Situation.Pair.newBuilder()
            .setPiece(
              Movesdb.Piece.newBuilder()
                .setColor(Movesdb.Color.White)
                .setType(Movesdb.PieceType.King))
            .setPosition(
              Movesdb.Position.newBuilder()
                .setColumn(Movesdb.Column.CA)
                .setRow(Movesdb.Row.R6))
            .build())
        .addValues(
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
        )
        .build()
    );
    assertEquals(WHITE, result.color());
    var pieces = result.board().pieces().toList();
    assertEquals(2, pieces.size());
    assertTrue(pieces.contains(new PiecePosition(WHITE, KING, A6)));
    assertTrue(pieces.contains(new PiecePosition(BLACK, ROOK, H1)));
  }

  // ==========================================================================
  // to proto - database
  // ==========================================================================

  @Test
  public void toProtoShouldConvertDatabaseWithSingleEntry() {
    var result = DESERIALIZER.fromProto(
      WHITE,
      Movesdb.Database.newBuilder()
        .addValues(
          Movesdb.Database.Pair.newBuilder()
            .setSituation(
              Movesdb.Situation.newBuilder()
                .addValues(
                  Movesdb.Situation.Pair.newBuilder()
                    .setPiece(
                      Movesdb.Piece.newBuilder()
                        .setColor(Movesdb.Color.White)
                        .setType(Movesdb.PieceType.King))
                    .setPosition(
                      Movesdb.Position.newBuilder()
                        .setColumn(Movesdb.Column.CA)
                        .setRow(Movesdb.Row.R6))
                    .build())
                .addValues(
                  Movesdb.Situation.Pair.newBuilder()
                    .setPiece(
                      Movesdb.Piece.newBuilder()
                        .setColor(Movesdb.Color.Black)
                        .setType(Movesdb.PieceType.Rook))
                    .setPosition(
                      Movesdb.Position.newBuilder()
                        .setColumn(Movesdb.Column.CH)
                        .setRow(Movesdb.Row.R1))
                    .build()))
            .setMove(
              Movesdb.Move.newBuilder()
                .setFrom(
                  Movesdb.Position.newBuilder()
                    .setColumn(Movesdb.Column.CA)
                    .setRow(Movesdb.Row.R6))
                .setTo(
                  Movesdb.Position.newBuilder()
                    .setColumn(Movesdb.Column.CA)
                    .setRow(Movesdb.Row.R7))))
        .build()
    );

    var moves = result.moves();
    assertEquals(1, moves.size());
    assertEquals(
      new Move(A6, Position.A7),
      moves.get(
        new Situation(
          WHITE,
          Board.builder()
            .add(Piece.WHITE_KING, A6)
            .add(Piece.BLACK_ROOK, Position.H1)
            .build())
          ));
  }
}
