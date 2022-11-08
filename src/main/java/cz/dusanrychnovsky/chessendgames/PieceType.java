package cz.dusanrychnovsky.chessendgames;

import java.util.Set;
import java.util.stream.Stream;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

public enum PieceType {

  KING {
    @Override
    public Set<Move> moves(Position from) {
      return stream(Position.values())
        .filter(pos ->
          from.column().distanceTo(pos.column()) <= 1 &&
          from.row().distanceTo(pos.row()) <= 1)
        .filter(pos -> pos != from)
        .map(pos -> new Move(from, pos))
        .collect(toSet());
    }
  },

  ROOK {

    @Override
    public Set<Move> moves(Position from) {
      return concat(column(from), row(from))
        .filter(pos -> pos != from)
        .map(pos -> new Move(from, pos))
        .collect(toSet());
    }

    private Stream<Position> column(Position from) {
      return stream(Position.values()).filter(pos -> pos.row() == from.row());
    }

    private Stream<Position> row(Position from) {
      return stream(Position.values()).filter(pos -> pos.column() == from.column());
    }
  };

  public static PieceType parse(String value) {
    return valueOf(value.toUpperCase());
  }

  public abstract Set<Move> moves(Position from);
}
