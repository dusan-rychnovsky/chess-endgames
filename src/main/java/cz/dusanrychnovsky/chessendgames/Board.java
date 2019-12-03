package cz.dusanrychnovsky.chessendgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

  private final Map<Position, Piece> pieces;

  public Board(Map<Position, Piece> pieces) {
    this.pieces = pieces;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Optional<Piece> atPosition(Position position) {
    return Optional.ofNullable(this.pieces.get(position));
  }

  public String print() {
    return "";
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

    public Board build() {
      return new Board(this.pieces);
    }
  }

  public static class Entry {

    private final Piece piece;
    private final Position position;

    public Entry(Piece piece, Position position) {
      this.piece = piece;
      this.position = position;
    }

    public static Entry parse(String value) {
      String[] tokens = value.split(" ");
      return new Entry(
        new Piece(
          Color.parse(tokens[0]),
          PieceType.parse(tokens[1])),
        Position.parse(tokens[2])
      );
    }

    public Piece piece() {
      return this.piece;
    }

    public Position position() {
      return this.position;
    }
  }
}
