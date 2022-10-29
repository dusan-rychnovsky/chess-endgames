package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.proto.Movesdb;

import java.io.IOException;

public class DbPlayer implements Player {

  public DbPlayer(String resourceName) throws IOException {
    Movesdb.Database.parseFrom(getClass().getResourceAsStream(resourceName));
  }

  @Override
  public Move getMove(Situation situation) {
    return null;
  }

}
