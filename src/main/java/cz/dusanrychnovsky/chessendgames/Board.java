package cz.dusanrychnovsky.chessendgames;

public class Board {

  public static Builder builder() {
    return new Builder();
  }

  public String print() {
    return "";
  }

  public static class Builder {
    public Builder add(Color color, Piece piece, Position position) {
      return this;
    }
    public Board build() {
      return new Board();
    }
  }

  public static class Entry {

    private final Color color;
    private final Piece piece;
    private final Position position;

    public Entry(Color color, Piece piece, Position position) {
      this.color = color;
      this.piece = piece;
      this.position = position;
    }

    public static Entry parse(String value) {
      String[] tokens = value.split(" ");
      return new Entry(
        Color.parse(tokens[0]),
        Piece.parse(tokens[1]),
        Position.parse(tokens[2])
      );
    }

    public Color color() {
      return this.color;
    }

    public Piece piece() {
      return this.piece;
    }

    public Position position() {
      return this.position;
    }
  }
}
