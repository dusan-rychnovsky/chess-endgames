package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.IterableExtensions.single;
import static cz.dusanrychnovsky.chessendgames.MapExtensions.filterByKey;

import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class Situation {

  Color color;
  Board board;

  public Status status() {
    return Status.InProgress;
  }

  public Situation next(Move move) {
    var from = move.from();
    var piece = board.atPosition(from).orElseThrow(
      () -> new IllegalArgumentException("No piece at position: " + from));

    var pieces = filterByKey(board.pieces(), pos -> pos != from);
    pieces.put(move.to(), piece);

    return new Situation(color.opposite(), new Board(pieces));
  }

  public String print() {
    return color + "\n" + board.print();
  }

  public boolean isValid(Move move) {

    if (board.atPosition(move.from()).isEmpty()) {
      return false;
    }

    var piece = board.atPosition(move.from()).get();
    if (piece.color() != color) {
      return false;
    }
    if (!piece.type().moves(move.from()).contains(move)) {
      return false;
    }

    if (board.atPosition(move.to()).filter(target -> target.color() == color).isPresent()) {
      return false;
    }

    for (var pos : Range.from(move.from(), move.to())) {
      if (pos != move.from() && pos != move.to()) {
        if (board.atPosition(pos).isPresent()) {
          return false;
        }
      }
    }

    var nextSituation = next(move);
    nextSituation = new Situation(color, nextSituation.board);
    if (nextSituation.isCheck()) {
      return false;
    }

    return true;
  }

  public boolean isCheck() {
    var opponentsView = new Situation(color.opposite(), board);
    var kingPos = single(board.king(color).keySet());
    var opponentsPieces = board.pieces(color.opposite());
    for (var pos : opponentsPieces.keySet()) {
      if (opponentsView.isValid(new Move(pos, kingPos))) {
        return true;
      }
    }
    return false;
  }

  public boolean isMate() {
    if (!isCheck()) {
      return false;
    }

    var king = single(board.king(color).entrySet());
    var kingPos = king.getKey();
    for (var move : king.getValue().type().moves(kingPos)) {
      if (isValid(move)) {
        return false;
      }
    }

    return true;
  }
}
