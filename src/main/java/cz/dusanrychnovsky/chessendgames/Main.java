package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.gui.GraphicalUserInterface;
import cz.dusanrychnovsky.chessendgames.players.Database;
import cz.dusanrychnovsky.chessendgames.players.HumanPlayer;
import cz.dusanrychnovsky.chessendgames.players.PrecomputedMovesPlayer;

import java.io.File;
import java.io.InputStream;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;

public class Main {
  
  public static void main(String[] args) {

    // TODO: open up the UI first and show "Loading database..." message while loading
    Database db = Database.readFrom(loadFile("moves.db"));
    UserInterface ui = new GraphicalUserInterface();

    Engine engine = new Engine(
      new PrecomputedMovesPlayer(WHITE, db, ui),
      new HumanPlayer(BLACK, ui)
    );
    
    engine.addEventListener(new DisplaySituations(ui));
    
    Situation situation = ui.requestInitialSituation();
    Result result = engine.runGame(situation);
    ui.displayResult(result);
  }

  private static InputStream loadFile(String filename) {
    return Main.class.getResourceAsStream(filename);
  }
}
