package cz.dusanrychnovsky.chessendgames.database;

import cz.dusanrychnovsky.chessendgames.Move;
import cz.dusanrychnovsky.chessendgames.Player;
import cz.dusanrychnovsky.chessendgames.Situation;

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
