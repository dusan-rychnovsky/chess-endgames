package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.proto.Movesdb;

import java.io.IOException;

public class DbPlayer implements Player {

  private Database database;

  public DbPlayer(String resourceName) throws IOException {
    var proto = Movesdb.Database.parseFrom(
      getClass().getResourceAsStream(resourceName)
    );
    this.database = new Deserializer().fromProto(Color.White, proto);
  }

  @Override
  public Move getMove(Situation situation) {
    return database.lookUp(situation)
      .orElseThrow(() -> new IllegalArgumentException("Unknown situation: " + situation));
  }
}
