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
    var maybePiece = board.atPosition(move.from());
    maybePiece.orElseThrow(() -> new IllegalArgumentException("No piece at position: " + move.from()));
    var piece = maybePiece.get();

    var pieces = new HashMap<Position, Piece>();
    for (var entry : board.pieces().entrySet()) {
      if (entry.getKey() != move.from()) {
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
