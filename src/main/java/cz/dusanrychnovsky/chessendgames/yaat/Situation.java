package cz.dusanrychnovsky.chessendgames.yaat;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

  // ==========================================================================
  // APPLY MOVE
  // ==========================================================================

  private boolean isValidMove(Move move) {
    // TODO
    return false;
  }

  public Situation applyMove(Move move) {
    // TODO
    return null;
  }

  // ==========================================================================
  // RESULT
  // ==========================================================================

  public Result getResult() {
    if (isOnlyKingsRemaining()) {
      return new Draw();
    }
    if (isCheck()) {
      Piece currKing = new Piece(currentColor, new King());
      // in a king+rook vs king end-game, check cannot be deflected
      // other than by moving king away
      if (!canMoveWithPiece(currKing)) {
        return new Win(currentColor.getOpponentColor());
      }
    }
    else {
      if (!canMove()) {
        return new Draw();
      }
    }
    return new InProgress();
  }

  private boolean isCheck() {
    return false;
  }

  private boolean canMove() {
    for (Piece piece : getCurrentPlayersPieces()) {
      if (canMoveWithPiece(piece)) {
        return true;
      }
    }
    return false;
  }

  private Iterable<Piece> getCurrentPlayersPieces() {
    List<Piece> result = new LinkedList<>();
    for (Piece piece : pieces.keySet()) {
      if (currentColor.equals(piece.getColor())) {
        result.add(piece);
      }
    }
    return result;
  }

  private boolean canMoveWithPiece(Piece piece) {
    checkArgument(pieces.containsKey(piece));

    Position fromPos = pieces.get(piece);
    Iterable<Move> moves = piece.getType().listAllMovesFromPosition(fromPos);

    for (Move move: moves) {
      if (isValidMove(move)) {
        return true;
      }
    }

    return false;
  }

  private boolean isOnlyKingsRemaining() {
    for (Piece piece : pieces.keySet()) {
      if (!(piece.getType() instanceof King)) {
        return false;
      }
    }
    return true;
  }

  // ==========================================================================
  // BUILDER
  // ==========================================================================

  public static Builder builder(Color currentColor) {
    return new Builder(currentColor);
  }

  public Position getPosition(Piece piece) {
    checkArgument(pieces.containsKey(piece), "Piece [" + piece + "] not registered.");
    return pieces.get(piece);
  }

  public Piece getPiece(Position position) {
    for (Map.Entry<Piece, Position> entry : pieces.entrySet()) {
      if (entry.getValue().equals(position)) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("Position [" + position + "] not registered.");
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
