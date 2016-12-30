package cz.dusanrychnovsky.chessendgames.core;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Iterables.contains;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.KING;
import static cz.dusanrychnovsky.chessendgames.core.Streams.stream;
import static java.util.stream.Collectors.toList;

public class Situation {

  // TODO: assert that there always are two kings

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

  /**
   * Returns current player's color.
   */
  public Color getCurrentColor() {
    return currentColor;
  }

  /**
   * Returns current opponent's color.
   */
  public Color getOpponentsColor() {
    return currentColor.getOpponentColor();
  }

  /**
   * Returns current position of the given piece, if that piece is active in
   * the represented situation, or otherwise an empty result.
   */
  public Optional<Position> getPosition(Piece piece) {
    Position position = pieces.get(piece);
    if (position != null) {
      return Optional.of(position);
    }
    else {
      return Optional.empty();
    }
  }

  /**
   * Returns the piece currently occupying the given position, if any, or
   * otherwise an empty result.
   */
  public Optional<Piece> getPiece(Position position) {
    return pieces.entrySet().stream()
      .filter(entry -> entry.getValue().equals(position))
      .map(Map.Entry::getKey)
      .findFirst();
  }

  /**
   * Returns all pieces currently active in the represented situation.
   */
  public Iterable<Piece> getPieces() {
    return pieces.keySet();
  }

  /**
   * Returns all pieces of the given color which are currently active in the
   * represented situation.
   */
  public Iterable<Piece> getPiecesOfColor(Color color) {
    return stream(getPieces())
      .filter(piece -> color.equals(piece.getColor()))
      .collect(toList());
  }

  /**
   * Returns all pieces which belong to the current player.
   */
  public Iterable<Piece> getCurrentPlayersPieces() {
    return getPiecesOfColor(getCurrentColor());
  }

  /**
   * Returns all pieces which belong to the current opponent.
   */
  public Iterable<Piece> getOpponentsPieces() {
    return getPiecesOfColor(getOpponentsColor());
  }

  public Piece getCurrentPlayersKing() {
    return Piece.get(getCurrentColor(), KING);
  }

  public Position getCurrentPlayersKingPosition() {
    return getPosition(getCurrentPlayersKing()).get();
  }

  public Piece getOpponentsKing() {
    return Piece.get(getOpponentsColor(), KING);
  }

  public Position getOpponentsKingPosition() {
    return getPosition(getOpponentsKing()).get();
  }
  
  // ==========================================================================
  // IS VALID MOVE
  // ==========================================================================

  /**
   * Returns true if and only if the given move is valid in the represented
   * situation.
   */
  public boolean isValidMove(Move move) {
    
    Position fromPos = move.getFrom();
    Position toPos = move.getTo();
   
    if (!getPiece(fromPos).isPresent()) {
      // unoccupied from position
      return false;
    }
    
    Piece piece = getPiece(fromPos).get();

    if (!getCurrentColor().equals(piece.getColor())) {
      // not that player's turn
      return false;
    }
    
    if (!contains(piece.getType().listAllMovesFromPosition(fromPos), move)) {
      // invalid move for that piece type
      return false;
    }
    
    if (pieces.containsValue(toPos)) {
      // can capture only opponent's pieces
      if (getCurrentColor().equals(getPiece(toPos).get().getColor())) {
        return false;
      }
    }
    
    for (Position pos : new ExcludingRange(fromPos, toPos)) {
      if (getPiece(pos).isPresent()) {
        // cannot move across a piece
        return false;
      }
    }
    
    if (piece.getType() == KING) {
      Move moveToOtherKing = new Move(toPos, getOpponentsKingPosition());
      if (contains(KING.listAllMovesFromPosition(toPos), moveToOtherKing)) {
        // king is not allowed next to opponent's king
        return false;
      }
    }
    
    if (piece.getType() == KING) {
      // king is not allowed to step into a check
      if (relocatePiece(move).isCheck()) {
        return false;
      }
    }
    
    return true;
  }
  
  private Situation relocatePiece(Move move) {
    Builder builder = Situation.builder(getCurrentColor());
    getUpdatedPieces(move).entrySet().forEach(entry ->
      builder.addPiece(entry.getKey(), entry.getValue())
    );
    return builder.build();
  }

  // ==========================================================================
  // APPLY MOVE
  // ==========================================================================

  /**
   * Applies the given move on the given situation and returns the result as
   * a new situation.
   *
   * Applying a move means:
   *
   * <ul>
   *  <li>
   *    updating the location of the given piece (and, potentially, capturing
   *    the piece previously located there),
   *  </li>
   *  <li>
   *    switching the current player's color to indicate a change of turn.
   *  </li>
   * </ul>
   *
   * Throws an {@link IllegalArgumentException} if given an invalid move.
   */
  public Situation applyMove(Move move) {
    checkArgument(isValidMove(move));

    Builder builder = Situation.builder(getOpponentsColor());
    getUpdatedPieces(move).entrySet().forEach(entry ->
      builder.addPiece(entry.getKey(), entry.getValue())
    );
    return builder.build();
  }

  private Map<Piece, Position> getUpdatedPieces(Move move) {
    Position fromPos = move.getFrom();
    Position toPos = move.getTo();

    Map<Piece, Position> result = new HashMap<>();
    pieces.entrySet().forEach(entry -> {
      if (toPos.equals(entry.getValue())) {
        // remove captured piece
        return;
      }
      if (fromPos.equals(entry.getValue())) {
        // move the moved piece
        result.put(entry.getKey(), toPos);
      }
      else {
        // retain other pieces
        result.put(entry.getKey(), entry.getValue());
      }
    });

    return result;
  }

  // ==========================================================================
  // RESULT
  // ==========================================================================

  public Result getResult() {
    if (isOnlyKingsRemaining()) {
      return new Draw();
    }
    if (isCheck()) {
      // in a king+rook vs king end-game, check cannot be deflected
      // other than by moving the king away
      if (!canMoveWithPiece(getCurrentPlayersKing())) {
        return new Win(getOpponentsColor());
      }
    }
    else {
      if (!canMove()) {
        return new Draw();
      }
    }
    return new InProgress();
  }

  /**
   * Returns true if and only if the represented situation is a check against
   * the current player's king.
   *
   * A situation is a check against a player's king, if and only if there exists
   * a piece among their opponent's pieces that could capture that king as a valid
   * move, was it currently their turn.
   */
  private boolean isCheck() {
    Position kingsPos = getPosition(getCurrentPlayersKing()).get();
    Situation opponentsView = getOpponentsView();

    return stream(getOpponentsPieces())
      .map(piece -> new Move(getPosition(piece).get(), kingsPos))
      .anyMatch(opponentsView::isValidMove);
  }

  private Situation getOpponentsView() {
    return new Situation(this, getOpponentsColor());
  }

  /**
   * Returns true if and only if there is at least one valid move for the current
   * player in the represented situation (with any of their remaining pieces).
   */
  private boolean canMove() {
    return stream(getCurrentPlayersPieces()).anyMatch(this::canMoveWithPiece);
  }

  /**
   * Returns true if and only if there exists a valid move with the given piece
   * in the represented situation.
   */
  private boolean canMoveWithPiece(Piece piece) {
    checkArgument(pieces.containsKey(piece));

    Position fromPos = pieces.get(piece);
    Iterable<Move> allMoves = piece.getType().listAllMovesFromPosition(fromPos);

    return stream(allMoves).anyMatch(this::isValidMove);
  }

  /**
   * Returns true if and only if the only active pieces are kings.
   */
  private boolean isOnlyKingsRemaining() {
    return stream(getPieces()).allMatch(piece -> piece.getType() == KING);
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
