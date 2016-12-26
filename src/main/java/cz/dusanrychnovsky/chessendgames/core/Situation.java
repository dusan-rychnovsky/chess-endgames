package cz.dusanrychnovsky.chessendgames.core;

import com.google.common.collect.Iterables;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.contains;
import static com.google.common.collect.Iterables.filter;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.KING;

public class Situation {

  private final Map<Piece, Position> pieces;
  private final Color currentColor;

  public Situation(Situation other, Color color) {
    this.currentColor = color;
    this.pieces = new HashMap<>(other.pieces);
  }
  
  private Situation(Color currentColor, Map<Piece, Position> pieces) {
    this.currentColor = currentColor;
    this.pieces = pieces;
  }
  
  public Optional<Position> getPosition(Piece piece) {
    Position position = pieces.get(piece);
    if (position != null) {
      return Optional.of(position);
    }
    else {
      return Optional.empty();
    }
  }

  public Optional<Piece> getPiece(Position position) {
    for (Map.Entry<Piece, Position> entry : pieces.entrySet()) {
      if (entry.getValue().equals(position)) {
        return Optional.of(entry.getKey());
      }
    }
    return Optional.empty();
  }
  
  public Color getCurrentColor() {
    return currentColor;
  }
  
  public Color getOpponentColor() {
    return currentColor.getOpponentColor();
  }
  
  public Iterable<Piece> getPieces() {
    return pieces.keySet();
  }
  
  public Iterable<Piece> getPiecesOfColor(Color color) {
    return filter(
      getPieces(),
      piece -> color.equals(piece.getColor())
    );
  }
  
  // ==========================================================================
  // APPLY MOVE
  // ==========================================================================

  public boolean isValidMove(Move move) {
    
    Position fromPos = move.getFrom();
    Position toPos = move.getTo();
   
    if (!getPiece(fromPos).isPresent()) {
      // unoccupied from position
      return false;
    }
    
    Piece piece = getPiece(fromPos).get();

    if (!currentColor.equals(piece.getColor())) {
      // not that player's turn
      return false;
    }
    
    if (!contains(piece.getType().listAllMovesFromPosition(fromPos), move)) {
      // invalid move for that piece type
      return false;
    }
    
    if (pieces.containsValue(toPos)) {
      // can capture only opponent's pieces
      if (currentColor.equals(getPiece(toPos).get().getColor())) {
        return false;
      }
    }
    
    for (Position pos : new ExcludingRange(fromPos, toPos)) {
      if (pieces.containsValue(pos)) {
        // cannot move across a piece
        return false;
      }
    }
    
    if (piece.getType() == KING) {
      // TODO: refactor
      Piece otherKing = new Piece(getOpponentColor(), KING);
      Position otherKingPos = getPosition(otherKing).get();
      
      Move moveToOtherKing = new Move(toPos, otherKingPos);
      if (contains(KING.listAllMovesFromPosition(toPos), moveToOtherKing)) {
        // king is not allowed next to opponent's king
        return false;
      }
    }
    
    if (piece.getType() == KING) {
      // king is not allowed to step into a check
      if (changePosition(move).isCheck()) {
        return false;
      }
    }
    
    return true;
  }
  
  private Situation changePosition(Move move) {
    Builder builder = Situation.builder(currentColor);
    for (Map.Entry<Piece, Position> entry : pieces.entrySet()) {
      if (entry.getValue().equals(move.getTo())) {
        continue;
      }
      if (entry.getValue().equals(move.getFrom())) {
        builder.addPiece(entry.getKey(), move.getTo());
      }
      else {
        builder.addPiece(entry.getKey(), entry.getValue());
      }
    }
    return builder.build();
  }
  
  public Situation applyMove(Move move) {
    checkArgument(isValidMove(move));
    
    Builder builder = Situation.builder(getOpponentColor());
    for (Map.Entry<Piece, Position> entry : pieces.entrySet()) {
      if (move.getTo().equals(entry.getValue())) {
        // remove captured piece
        continue;
      }
      if (move.getFrom().equals(entry.getValue())) {
        // move the moved piece
        builder.addPiece(entry.getKey(), move.getTo());
      }
      else {
        // retain other pieces
        builder.addPiece(entry.getKey(), entry.getValue());
      }
    }
    
    return builder.build();
  }

  // ==========================================================================
  // RESULT
  // ==========================================================================

  public Result getResult() {
    if (isOnlyKingsRemaining()) {
      return new Draw();
    }
    if (isCheck()) {
      Piece currKing = new Piece(currentColor, KING);
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
    Piece currentKing = new Piece(currentColor, KING);
    for (Piece piece : getOpponentsPieces()) {
      if (canCapture(piece, currentKing)) {
        return true;
      }
    }
    return false;
  }
  
  private boolean canCapture(Piece first, Piece second) {
    
    if (first.getType() == KING &&
        second.getType() == KING) {
      // TODO: is this needed?
      return false;
    }
    
    Situation opponentsView = new Situation(this, getOpponentColor());
    Position fromPos = getPosition(first).get();
    Position toPos = getPosition(second).get();
    for (Move move : first.getType().listAllMovesFromPosition(fromPos)) {
      if (toPos.equals(move.getTo()) && opponentsView.isValidMove(move)) {
        return true;
      }
    }
    return false;
  }
  
  private Iterable<Piece> getOpponentsPieces() {
    List<Piece> result = new LinkedList<>();
    for (Piece piece : pieces.keySet()) {
      if (getOpponentColor().equals(piece.getColor())) {
        result.add(piece);
      }
    }
    return result;
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
      if (!(piece.getType() == KING)) {
        return false;
      }
    }
    return true;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(currentColor, pieces);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Situation)) {
      return false;
    }
    
    Situation other = (Situation) obj;
    return
      currentColor.equals(other.currentColor) &&
      pieces.equals(other.pieces);
  }
  
  // ==========================================================================
  // BUILDER
  // ==========================================================================

  public static Builder builder(Color currentColor) {
    return new Builder(currentColor);
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
