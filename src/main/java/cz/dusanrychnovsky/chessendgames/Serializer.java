package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.proto.Movesdb;

public class Serializer {

  public Movesdb.Database toProto(Database database) {
    var builder = Movesdb.Database.newBuilder();
    for (var entry : database.moves().entrySet()) {
      builder.addValues(
        Movesdb.Database.Pair.newBuilder()
          .setSituation(toProto(entry.getKey()))
          .setMove(toProto(entry.getValue())));
    }
    return builder.build();
  }

  public Movesdb.Move toProto(Move move) {
    return Movesdb.Move.newBuilder()
      .setFrom(toProto(move.from()))
      .setTo(toProto(move.to()))
      .build();
  }

  public Movesdb.Situation toProto(Situation situation) {
    var builder = Movesdb.Situation.newBuilder();
    for (var entry : situation.board().pieces().entrySet()) {
      builder.addValues(
        Movesdb.Situation.Pair.newBuilder()
          .setPosition(toProto(entry.getKey()))
          .setPiece(toProto(entry.getValue())));
    }
    return builder.build();
  }

  public Movesdb.Piece toProto(Piece piece) {
    return Movesdb.Piece.newBuilder()
      .setColor(toProto(piece.color()))
      .setType(toProto(piece.type()))
      .build();
  }

  public Movesdb.Color toProto(Color color) {
    return switch (color) {
      case White -> Movesdb.Color.White;
      case Black -> Movesdb.Color.Black;
      default -> throw new IllegalArgumentException("Unknown color: " + color);
    };
  }

  public Movesdb.PieceType toProto(PieceType type) {
    return switch (type) {
      case King -> Movesdb.PieceType.King;
      case Rook -> Movesdb.PieceType.Rook;
      default -> throw new IllegalArgumentException(("Unknown piece type: " + type));
    };
  }
  public Movesdb.Position toProto(Position position) {
    return Movesdb.Position.newBuilder()
      .setRow(toProto(position.row()))
      .setColumn(toProto(position.column()))
      .build();
  }

  public Movesdb.Row toProto(Row row) {
    return switch (row) {
      case R1 -> Movesdb.Row.R1;
      case R2 -> Movesdb.Row.R2;
      case R3 -> Movesdb.Row.R3;
      case R4 -> Movesdb.Row.R4;
      case R5 -> Movesdb.Row.R5;
      case R6 -> Movesdb.Row.R6;
      case R7 -> Movesdb.Row.R7;
      case R8 -> Movesdb.Row.R8;
      default -> throw new IllegalArgumentException("Unknown row: " + row);
    };
  }

  public Movesdb.Column toProto(Column column) {
    return switch (column) {
      case CA -> Movesdb.Column.CA;
      case CB -> Movesdb.Column.CB;
      case CC -> Movesdb.Column.CC;
      case CD -> Movesdb.Column.CD;
      case CE -> Movesdb.Column.CE;
      case CF -> Movesdb.Column.CF;
      case CG -> Movesdb.Column.CG;
      case CH -> Movesdb.Column.CH;
      default -> throw new IllegalArgumentException("Unknown column: " + column);
    };
  }
}
