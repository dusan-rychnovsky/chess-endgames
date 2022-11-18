package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.MapExtensions.filterByKey;
import static cz.dusanrychnovsky.chessendgames.PieceType.KING;
import static cz.dusanrychnovsky.chessendgames.Status.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public record Situation (Color color, Board board) {

  public Status status() {
    if (isStalemate()) {
      return DRAW;
    }
    if (isMate()) {
      return WIN(color.opposite());
    }
    return IN_PROGRESS;
  }

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

  public String print() {
    return color + "\n" + board.print();
  }

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
      if (pos != move.from() && pos != move.to()) {
        if (board.pieceAt(pos).isPresent()) {
          return true;
        }
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

  public boolean isMate() {
    return isCheck() && !kingCanMove();
  }

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
