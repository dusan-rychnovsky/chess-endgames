package cz.dusanrychnovsky.chessendgames;

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

    if (board.atPosition(move.to()).filter(target -> target.color() == color).isPresent()) {
      return false;
    }
    return true;
  }
}
