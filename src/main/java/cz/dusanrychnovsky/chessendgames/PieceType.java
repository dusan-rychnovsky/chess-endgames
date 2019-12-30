package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.Arrays.stream;
import static java.util.stream.Stream.concat;

public enum PieceType {

  King {
    @Override
    public Set<Move> moves(Position from) {
      return null;
    }
  },

  Rook {

    @Override
    public Set<Move> moves(Position from) {
      return concat(column(from), row(from))
        .filter(pos -> pos != from)
        .map(pos -> new Move(from, pos))
        .collect(Collectors.toSet());
    }

    private Stream<Position> column(Position from) {
      return stream(Position.values()).filter(pos -> pos.row() == from.row());
    }

    private Stream<Position> row(Position from) {
      return stream(Position.values()).filter(pos -> pos.column() == from.column());
    }
  };

  public static PieceType parse(String value) {
    return valueOf(capitalize(value));
  }

  public abstract Set<Move> moves(Position from);
}
