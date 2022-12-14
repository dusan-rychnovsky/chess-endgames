package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.proto.Movesdb;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Column.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Row.*;

public class ProtoDeserializer {

  public Database fromProto(Color color, Movesdb.Database database) {
    var builder = new Database.Builder();
    for (var pair : database.getValuesList()) {
      builder.add(
        fromProto(color, pair.getSituation()),
        fromProto(pair.getMove())
      );
    }
    return builder.build();
  }

  public Situation fromProto(Color color, Movesdb.Situation situation) {
    var builder = Board.builder();
    for (var pair : situation.getValuesList()) {
      builder.add(
        fromProto(pair.getPiece()),
        fromProto(pair.getPosition())
      );
    }
    return new Situation(
      color,
      builder.build()
    );
  }

  public Piece fromProto(Movesdb.Piece piece) {
    return new Piece(
      fromProto(piece.getColor()),
      fromProto(piece.getType())
    );
  }

  public PieceType fromProto(Movesdb.PieceType type) {
    return switch (type) {
      case King -> KING;
      case Rook -> ROOK;
      default -> throw new IllegalArgumentException("Unknown piece type: " + type);
    };
  }

  public Color fromProto(Movesdb.Color color) {
    return switch (color) {
      case White -> WHITE;
      case Black -> BLACK;
      default -> throw new IllegalArgumentException("Unknown color: " + color);
    };
  }

  public Move fromProto(Movesdb.Move move) {
    return new Move(
      fromProto(move.getFrom()),
      fromProto(move.getTo())
    );
  }

  public Position fromProto(Movesdb.Position position) {
    return Position.get(
      fromProto(position.getColumn()),
      fromProto(position.getRow())
    );
  }

  public Row fromProto(Movesdb.Row row) {
    return switch (row) {
      case R1 -> R1;
      case R2 -> R2;
      case R3 -> R3;
      case R4 -> R4;
      case R5 -> R5;
      case R6 -> R6;
      case R7 -> R7;
      case R8 -> R8;
      default -> throw new IllegalArgumentException("Unknown row: " + row);
    };
  }

  public Column fromProto(Movesdb.Column column) {
    return switch (column) {
      case CA -> CA;
      case CB -> CB;
      case CC -> CC;
      case CD -> CD;
      case CE -> CE;
      case CF -> CF;
      case CG -> CG;
      case CH -> CH;
      default -> throw new IllegalArgumentException("Unknown column: " + column);
    };
  }
}
