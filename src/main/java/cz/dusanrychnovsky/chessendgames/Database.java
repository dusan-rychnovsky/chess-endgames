package cz.dusanrychnovsky.chessendgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.MapExtensions.get;

public class Database {

  private final Map<Situation, Move> moves;

  public Database(Map<Situation, Move> moves) {
    this.moves = moves;
  }

  public Map<Situation, Move> moves() {
    return moves;
  }

  public Optional<Move> lookUp(Situation situation) {
    return get(moves, situation);
  }

  public static class Builder {
    private final Map<Situation, Move> moves = new HashMap<>();

    public Database.Builder add(Situation situation, Move move) {
      if (this.moves.containsKey(situation)) {
        throw new IllegalArgumentException("Duplicate situation assignment. Situation: " + situation + ".");
      }
      this.moves.put(situation, move);
      return this;
    }

    public Database build() {
      return new Database(this.moves);
    }
  }
}
