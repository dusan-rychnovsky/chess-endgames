package cz.dusanrychnovsky.chessendgames;

public class Board {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    public Builder add(Color color, Piece piece, Position position) {
      return this;
    }
    public Board build() {
      return new Board();
    }
  }
}
