package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.core.Player;
import cz.dusanrychnovsky.chessendgames.core.Situation;

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
