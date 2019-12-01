package cz.dusanrychnovsky.chessendgames;

public class Board {

  public static Builder builder() {
    return new Builder();
  }

  public String print() {
    return "";
  }

  public static class Builder {
    public Builder add(Piece piece, Position position) {
      return this;
    }
    public Board build() {
      return new Board();
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
