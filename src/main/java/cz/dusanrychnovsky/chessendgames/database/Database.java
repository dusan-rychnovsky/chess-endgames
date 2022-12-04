package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.Color;
import cz.dusanrychnovsky.chessendgames.Move;
import cz.dusanrychnovsky.chessendgames.Situation;
import cz.dusanrychnovsky.chessendgames.proto.Movesdb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.MapExtensions.get;

public class Database {

  private final Map<Situation, Move> moves;

  public Database(Map<Situation, Move> moves) {
    this.moves = new HashMap<>(moves);
  }

  public static Database load(String resourcePath) {
    try {
      var deserializer = new ProtoDeserializer();
      var resource = TextSerializer.class.getResourceAsStream(resourcePath);
      return deserializer.fromProto(
        Color.WHITE,
        Movesdb.Database.parseFrom(resource)
      );
    }
    catch (IOException ex) {
      throw new RuntimeException("Can't load movesdb", ex);
    }
  }

  public Map<Situation, Move> moves() {
    return new HashMap<>(moves);
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
