package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Board {

  private final Map<Position, Piece> pieces;

  public Board(Map<Position, Piece> pieces) {
    this.pieces = pieces;
  }

  public Stream<PiecePosition> pieces() {
    return pieces.entrySet().stream()
      .map(entry -> new PiecePosition(
        entry.getValue().color(),
        entry.getValue().type(),
        entry.getKey()));
  }

  public Stream<PiecePosition> pieces(Color color) {
    return pieces().filter(piece -> piece.color() == color);
  }

  public Optional<Position> kingPos(Color color) {
    for (var entry : pieces.entrySet()) {
      if (entry.getValue().equals(new Piece(color, KING))) {
        return Optional.of(entry.getKey());
      }
    }
    return Optional.empty();
  }

  public static Builder builder() {
    return new Builder();
  }

  public Optional<Piece> pieceAt(Position position) {
    return Optional.ofNullable(this.pieces.get(position));
  }

  public String print() {
    var result = new StringBuilder();

    for (var row : Range.from(R8, R1)) {
      result.append(row + " |");
      for (var column : Range.from(CA, CH)) {
        var position = Position.get(column, row);
        result.append(
          " " +
            pieceAt(position)
              .map(this::print)
              .orElse(".")
        );
      }
      result.append("\n");
    }

    result.append("--|----------------\n");
    result.append("  | A B C D E F G H\n");

    return result.toString();
  }

  private String print(Piece piece) {
    return piece.type().toString().substring(0, 1);
  }

  public static class Builder {
    private final Map<Position, Piece> pieces = new HashMap<>();

    public Builder add(Piece piece, Position position) {
      if (this.pieces.containsKey(position)) {
        throw new IllegalArgumentException("Duplicate position assignment. Position: " + position + ".");
      }
      this.pieces.put(position, piece);
      return this;
    }

    public Builder addAll(Stream<PiecePosition> pieces) {
      pieces.forEach(item -> add(item.piece(), item.position()));
      return this;
    }

    public Board build() {
      return new Board(this.pieces);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Board other) {
      return pieces.equals(other.pieces);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return pieces.hashCode();
  }

  public record Entry(Piece piece, Position position) {
    public static Entry parse(String value) {
      var tokens = value.split(" ");
      return new Entry(
        new Piece(
          Color.parse(tokens[0]),
          PieceType.parse(tokens[1])),
        Position.parse(tokens[2])
      );
    }
  }
}
