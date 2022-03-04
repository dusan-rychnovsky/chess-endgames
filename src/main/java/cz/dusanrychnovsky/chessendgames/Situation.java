package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.IterableExtensions.single;
import static cz.dusanrychnovsky.chessendgames.MapExtensions.filterByKey;
import static cz.dusanrychnovsky.chessendgames.Status.*;

import lombok.Value;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Value
@Accessors(fluent = true)
public class Situation {

  Color color;
  Board board;

  public Status status() {
    if (isStalemate()) {
      return Draw;
    }
    if (isMate()) {
      return win(color.opposite());
    }
    return InProgress;
  }

  public Iterable<Situation> next() {
    var result = new ArrayList<Situation>();
    for (var entry : board().pieces(color).entrySet()) {
      var position = entry.getKey();
      var piece = entry.getValue();

      for (var move : piece.type().moves(position)) {
        if (isValid(move)) {
          result.add(move(move));
        }
      }
    }
    return result;
  }

  public Situation move(Move move) {
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

    var nextSituation = move(move);
    nextSituation = new Situation(color, nextSituation.board);
    if (nextSituation.isCheck()) {
      return false;
    }

    return true;
  }

  public boolean isCheck() {
    var opponentsPieces = board.pieces(color.opposite());
    var kingPos = board.king(color).keySet();
    if (kingPos.isEmpty()) {
      return false;
    }
    var opponentsView = new Situation(color.opposite(), board);
    for (var pos : opponentsPieces.keySet()) {
      if (opponentsView.isValid(new Move(pos, single(kingPos)))) {
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
    var king = single(board.king(color).entrySet());
    var kingPos = king.getKey();
    for (var move : king.getValue().type().moves(kingPos)) {
      if (isValid(move)) {
        return true;
      }
    }
    return false;
  }
}
