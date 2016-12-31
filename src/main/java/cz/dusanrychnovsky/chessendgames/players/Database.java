package cz.dusanrychnovsky.chessendgames.players;

import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Situation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A storage for precomputed moves.
 */
public class Database implements Serializable {

  private final Map<Situation, Move> moves;

  private Database(Map<Situation, Move> moves) {
    this.moves = moves;
  }

  /**
   * Returns the move stored for the given situation, if any, or an empty
   * result.
   */
  public Optional<Move> getMove(Situation situation) {
    if (moves.containsKey(situation)) {
      return Optional.of(moves.get(situation));
    }
    else {
      return Optional.empty();
    }
  }

  // ==========================================================================
  // (DE)SERIALIZATION
  // ==========================================================================

  /**
   * Loads and deserializes a {@link Database} from the given {@link InputStream}.
   * Throws a {@link RuntimeException} in case of any error.
   */
  public static Database readFrom(InputStream in) {
    try (ObjectInputStream oin = new ObjectInputStream(in)) {
      return (Database) oin.readObject();
    }
    catch (IOException | ClassNotFoundException ex) {
      throw new RuntimeException("Cannot load Database.", ex);
    }
  }

  /**
   * Saves the represented {@link Database} to the given {@link OutputStream}. Throws
   * a {@link RuntimeException} in case of any error.
   */
  public void writeTo(OutputStream out) {
    try (ObjectOutputStream oout = new ObjectOutputStream(out)) {
      oout.writeObject(this);
    }
    catch (IOException ex) {
      throw new RuntimeException("Cannot save Database.", ex);
    }
  }

  // ==========================================================================
  // BUILDER
  // ==========================================================================

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final Map<Situation, Move> moves = new HashMap<>();

    public Builder addMapping(Situation situation, Move move) {
      checkArgument(!contains(situation));

      moves.put(situation, move);
      return this;
    }

    public boolean contains(Situation situation) {
      return moves.containsKey(situation);
    }

    public Database build() {
      return new Database(moves);
    }
  }
}
