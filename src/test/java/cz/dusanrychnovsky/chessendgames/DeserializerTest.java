package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.proto.Movesdb;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeserializerTest {

  private static final Deserializer deserializer = new Deserializer();

  // ==========================================================================
  // from proto - row
  // ==========================================================================

  @Test
  public void fromProto_convertsRow() {
    assertEquals(Row.R3, deserializer.fromProto(Movesdb.Row.R3));
  }

  // ==========================================================================
  // from proto - column
  // ==========================================================================

  @Test
  public void fromProto_convertsColumn() {
    assertEquals(Column.CE, deserializer.fromProto(Movesdb.Column.CE));
  }

  // ==========================================================================
  // to proto - position
  // ==========================================================================

  @Test
  public void toProto_convertsPosition() {
    assertEquals(
      Position.H3,
      deserializer.fromProto(
        Movesdb.Position.newBuilder()
          .setColumn(Movesdb.Column.CH)
          .setRow(Movesdb.Row.R3)
          .build()));
  }

  // ==========================================================================
  // to proto - move
  // ==========================================================================

  @Test
  public void toProto_convertsMove() {
    assertEquals(
      new Move(Position.H3, Position.H1),
      deserializer.fromProto(
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
  public void fromProto_convertsColor() {
    assertEquals(Color.WHITE, deserializer.fromProto(Movesdb.Color.White));
  }

  // ==========================================================================
  // from proto - piece type
  // ==========================================================================

  @Test
  public void fromProto_convertsPieceType() {
    assertEquals(PieceType.KING, deserializer.fromProto(Movesdb.PieceType.King));
  }

  // ==========================================================================
  // to proto - piece
  // ==========================================================================

  @Test
  public void toProto_convertsPiece() {
    assertEquals(
      Piece.BlackRook,
      deserializer.fromProto(
        Movesdb.Piece.newBuilder()
          .setColor(Movesdb.Color.Black)
          .setType(Movesdb.PieceType.Rook)
          .build()));
  }

  // ==========================================================================
  // to proto - situation
  // ==========================================================================

  @Test
  public void toProto_convertsSituation() {
    var result = deserializer.fromProto(
      Color.WHITE,
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
    assertEquals(Color.WHITE, result.color());
    var pieces = result.board().pieces();
    assertEquals(2, pieces.size());
    assertEquals(Piece.WhiteKing, pieces.get(Position.A6));
    assertEquals(Piece.BlackRook, pieces.get(Position.H1));
  }

  // ==========================================================================
  // to proto - database
  // ==========================================================================

  @Test
  public void toProto_convertsDatabase_singleEntry() {
    var result = deserializer.fromProto(
      Color.WHITE,
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
      new Move(Position.A6, Position.A7),
      moves.get(
        new Situation(
          Color.WHITE,
          Board.builder()
            .add(Piece.WhiteKing, Position.A6)
            .add(Piece.BlackRook, Position.H1)
            .build())
          ));
  }
}
