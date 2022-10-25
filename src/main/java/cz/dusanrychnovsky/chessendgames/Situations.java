package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Piece.*;

import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static java.util.Optional.empty;

public class Situations {

  public static Iterable<Situation> all() {
    var result = new HashSet<Situation>();
    for (var color : Color.values()) {
      for (var blackKingEntry : entries(BlackKing)) {
        for (var whiteKingEntry : entries(WhiteKing)) {
          situation(color, whiteKingEntry, blackKingEntry)
            .ifPresent(result::add);
          for (var whiteRookEntry : entries(WhiteRook)) {
            situation(color, whiteKingEntry, whiteRookEntry, blackKingEntry)
              .ifPresent(result::add);
          }
        }
      }
    }
    return result;
  }

  private static Iterable<PositionPieceEntry> entries(Piece piece) {
    return
      Arrays.stream(Position.values())
        .map(pos -> new PositionPieceEntry(pos, piece))
        ::iterator;
  }

  private static Optional<Situation> situation(Color color, PositionPieceEntry... entries) {

    var positions = new HashSet<Position>();
    var builder = new Board.Builder();
    for (var entry : entries) {
      if (positions.contains(entry.position())) {
        return empty();
      }
      positions.add(entry.position);
      builder.add(entry.piece, entry.position);
    }
    var board = builder.build();

    var situation = new Situation(color, board);

    var opponentsView = new Situation(color.opposite(), board);
    if (opponentsView.isCheck()) {
      return empty();
    }

    return Optional.of(situation);
  }

  @Value
  @Accessors(fluent = true)
  private static class PositionPieceEntry {
    Position position;
    Piece piece;
  }
}
