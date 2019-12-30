package cz.dusanrychnovsky.chessendgames;

import lombok.Value;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    var pieces = new HashMap<Position, Piece>();
    for (var entry : board.pieces().entrySet()) {
      if (entry.getKey() != from) {
        pieces.put(entry.getKey(), entry.getValue());
      }
    }

    pieces.put(move.to(), piece);

    return new Situation(color.opposite(), new Board(pieces));
  }

  public String print() {
    return color + "\n" + board.print();
  }
}
