package cz.dusanrychnovsky.chessendgames.yaat;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class Situation {

  // TODO: make immutable

  private final Map<Piece, Position> pieces;
  private final Color currentColor;

  private Situation(Color currentColor, Map<Piece, Position> pieces) {
    this.currentColor = currentColor;
    this.pieces = pieces;
  }

  public Situation addPiece(Piece piece, Position position) {

    if (pieces.containsKey(piece) || pieces.containsValue(position)) {
      throw new RuntimeException("blabla");
    }

    pieces.put(piece, position);
    return this;
  }

  public Color getCurrentColor() {
    return currentColor;
  }

  public Situation applyMove(Move move) {
    // TODO
    return null;
  }

  public Result getResult() {
    // TODO
    return null;
  }

  public static Builder builder(Color currentColor) {
    return new Builder(currentColor);
  }

  public Position getPosition(Piece piece) {
    checkArgument(pieces.containsKey(piece), "Piece [" + piece + "] not registered.");
    return pieces.get(piece);
  }

  public static class Builder {

    private final Map<Piece, Position> pieces = new HashMap<>();
    private final Color currentColor;

    public Builder(Color currentColor) {
      this.currentColor = currentColor;
    }

    public Builder addPiece(Piece piece, Position position) {
      checkArgument(!pieces.containsKey(piece), "Piece [" + piece + "] already registered.");
      checkArgument(!pieces.containsValue(position), "Position [" + position + "] already registered.");

      pieces.put(piece, position);
      return this;
    }

    public Situation build() {
      return new Situation(currentColor, pieces);
    }
  }
}
