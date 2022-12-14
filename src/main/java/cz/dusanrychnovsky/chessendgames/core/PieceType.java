package cz.dusanrychnovsky.chessendgames.core;

import java.util.Set;
import java.util.stream.Stream;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.concat;

public enum PieceType {

  KING {
    @Override
    public Set<Move> movesFrom(Position from) {
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
    public Set<Move> movesFrom(Position from) {
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

  public abstract Set<Move> movesFrom(Position from);
}
