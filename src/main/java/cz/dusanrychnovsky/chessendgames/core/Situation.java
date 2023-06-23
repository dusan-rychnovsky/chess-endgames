package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.PieceType.KING;
import static cz.dusanrychnovsky.chessendgames.core.Status.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a situation on a chess board from the position of
 * the current player.
 */
public record Situation (Color color, Board board) {

  /**
   * @return Status of the represented situation. This is either
   * {@link Status#IN_PROGRESS} if the game isn't finished or
   * one of {@link Status#DRAW} or {@link Win} if it is.
   */
  public Status status() {
    if (isStalemate()) {
      return DRAW;
    }
    if (isMate()) {
      return win(color.opposite());
    }
    return IN_PROGRESS;
  }

  /**
   * @return A collection of all moves which are valid in the represented
   * situation, together with situations to which each of them lead.
   */
  public Map<Move, Situation> nextMoves() {
    var result = new HashMap<Move, Situation>();
    board.pieces(color).forEach(piece -> {
      for (var move : piece.type().movesFrom(piece.position())) {
        if (isValid(move)) {
          result.put(move, apply(move));
        }
      }
    });
    return result;
  }

  /**
   * @return Situation to which the given move leads. Note that the given move,
   * and therefore also the returned situation, don't need to be valid for the
   * represented situation.
   * @throws  IllegalArgumentException if the given move is not applicable
   * to the represented situation
   */
  public Situation apply(Move move) {
    var currPiece = board.pieceAt(move.from())
      .orElseThrow(() -> new IllegalArgumentException("No piece at position: " + move.from()));
    var otherPieces = board.pieces()
      .filter(piece -> piece.position() != move.from() && piece.position() != move.to());
    return new Situation(
      color.opposite(),
      Board.builder()
        .addAll(otherPieces)
        .add(currPiece, move.to())
        .build()
    );
  }

  /**
   * @return A human-readable text representation of the situation.
   */
  public String print() {
    return color + "\n" + board.print();
  }

  /**
   * @return True iff the given move is valid for the represented situation.
   */
  public boolean isValid(Move move) {
    return
      isCurrentPlayersPiece(move) &&
      isCompatibleWithPieceType(move) &&
      !isCaptureOfOwnPiece(move) &&
      !isMoveAcrossOtherPieces(move) &&
      !isMoveToCheck(move);
  }

  private boolean isMoveToCheck(Move move) {
    var nextSituation = apply(move);
    nextSituation = new Situation(color, nextSituation.board);
    return nextSituation.isCheck();
  }

  private boolean isMoveAcrossOtherPieces(Move move) {
    for (var pos : Range.from(move.from(), move.to())) {
      if (pos != move.from() && pos != move.to() && board.pieceAt(pos).isPresent()) {
        return true;
      }
    }
    return false;
  }

  private boolean isCaptureOfOwnPiece(Move move) {
    var targetPiece = board.pieceAt(move.to());
    return targetPiece.isPresent() && targetPiece.get().color() == color;
  }

  private boolean isCompatibleWithPieceType(Move move) {
    var piece = board.pieceAt(move.from()).get();
    var validMoves = piece.type().movesFrom(move.from());
    return validMoves.contains(move);
  }

  private boolean isCurrentPlayersPiece(Move move) {
    var piece = board.pieceAt(move.from());
    return piece.isPresent() && piece.get().color() == color;
  }

  /**
   * @return True iff the represented situation is a check. A situation
   * is considered check if current player's king is threatened by either
   * a] the opponent's king standing next to it or b] any other opponent's
   * piece.
   *
   * TODO: This functionality doesn't comply with correct definition
   * of check in chess and needs to be updated if the game engine gets
   * extended with support for multiple pieces.
   */
  public boolean isCheck() {
    return kingsAreAdjacent() || kingCanBeCaptured();
  }

  private boolean kingCanBeCaptured() {
    var kingPos = board.kingPos(color);
    if (kingPos.isPresent()) {
      var opponentsPieces = board.pieces(color.opposite());
      var opponentsView = new Situation(color.opposite(), board);
      return opponentsPieces.anyMatch(
        piece -> opponentsView.isValid(new Move(piece.position(), kingPos.get()))
      );
    }
    return false;
  }

  private boolean kingsAreAdjacent() {
    var kingPos = board.kingPos(color);
    var opponentsKingPos = board.kingPos(color.opposite());
    if (kingPos.isPresent() && opponentsKingPos.isPresent()) {
      var move = new Move(kingPos.get(), opponentsKingPos.get());
      if (KING.movesFrom(kingPos.get()).contains(move)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return True iff the represented situation is a mate. A situation
   * is considered mate if it is a check and the king can't move away.
   */
  public boolean isMate() {
    return isCheck() && !kingCanMove();
  }

  /**
   * @return True iff the represented situation is a stalemate. A situation
   * is considered stalemate if the current player's king can't move but
   * isn't in check.
   *
   * TODO: This functionality doesn't comply with correct definition
   * of check in chess and needs to be updated if the game engine gets
   * extended with support for multiple pieces.
   */
  public boolean isStalemate() {
    return !isCheck() && !kingCanMove();
  }

  private boolean kingCanMove() {
    var kingPos = board.kingPos(color);
    if (kingPos.isPresent()) {
      for (var move : KING.movesFrom(kingPos.get())) {
        if (isValid(move)) {
          return true;
        }
      }
    }
    return false;
  }
}
