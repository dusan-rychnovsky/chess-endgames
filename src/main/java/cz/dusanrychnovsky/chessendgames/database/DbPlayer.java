package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.Color;
import cz.dusanrychnovsky.chessendgames.Move;
import cz.dusanrychnovsky.chessendgames.Player;
import cz.dusanrychnovsky.chessendgames.Situation;
import cz.dusanrychnovsky.chessendgames.database.Database;
import cz.dusanrychnovsky.chessendgames.database.ProtoDeserializer;
import cz.dusanrychnovsky.chessendgames.proto.Movesdb;

import java.io.IOException;

public class DbPlayer implements Player {

  private Database database;

  public DbPlayer(String resourceName) {
    this.database = Database.load(resourceName);
  }

  @Override
  public Move getMove(Situation situation) {
    return database.lookUp(situation)
      .orElseThrow(() -> new IllegalArgumentException("Unknown situation: " + situation));
  }
}
