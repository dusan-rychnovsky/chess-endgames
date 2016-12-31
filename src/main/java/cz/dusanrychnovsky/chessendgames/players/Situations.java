package cz.dusanrychnovsky.chessendgames.players;

import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Situation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static cz.dusanrychnovsky.chessendgames.core.Piece.BLACK_KING;
import static cz.dusanrychnovsky.chessendgames.core.Piece.WHITE_KING;
import static cz.dusanrychnovsky.chessendgames.core.Piece.WHITE_ROOK;

public class Situations {

  public Set<Situation> generateAll() {
    Set<Situation> result = new HashSet<>();
    for (Color color : Color.values()) {
      for (Position whiteKingPos : Position.values()) {
        for (Position blackKingPos : Position.values()) {
          situation(color, whiteKingPos, blackKingPos)
            .ifPresent(result::add);
          for (Position whiteRookPos : Position.values()) {
            situation(color, whiteKingPos, blackKingPos, whiteRookPos)
              .ifPresent(result::add);
          }
        }
      }
    }
    return result;
  }

  private Optional<Situation> situation(Color color, Position whiteKingPos, Position blackKingPos) {
    try {
      return Optional.of(
        Situation.builder(color)
          .addPiece(WHITE_KING, whiteKingPos)
          .addPiece(BLACK_KING, blackKingPos)
          .build()
      );
    }
    catch (IllegalArgumentException | IllegalStateException ex) {
      return Optional.empty();
    }
  }

  private Optional<Situation> situation(
      Color color, Position whiteKingPos, Position blackKingPos, Position whiteRookPos) {
    try {
      return Optional.of(
        Situation.builder(color)
          .addPiece(WHITE_KING, whiteKingPos)
          .addPiece(WHITE_ROOK, whiteRookPos)
          .addPiece(BLACK_KING, blackKingPos)
          .build()
      );
    }
    catch (IllegalArgumentException | IllegalStateException ex) {
      return Optional.empty();
    }
  }
}
