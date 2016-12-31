package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.gui.GraphicalUserInterface;

import java.io.File;
import java.util.Random;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;

public class Main {
  
  public static void main(String[] args) {
    Database db = Database.readFrom(loadFile("moves.db"));
    UserInterface ui = new GraphicalUserInterface();
    
    ui.displayMessage("CHESS ENDGAMES v. 0.1");

    Engine engine = new Engine(
      new PrecomputedMovesPlayer(WHITE, db, ui),
      new HumanPlayer(BLACK, ui)
    );
    
    engine.addEventListener(new ShowSituations(ui));
    
    Situation situation = Situation.builder(WHITE)
//      .addPiece(WHITE_KING, F4)
//      .addPiece(WHITE_ROOK, B3)
//      .addPiece(BLACK_KING, G2)
      .addPiece(WHITE_KING, A8)
      .addPiece(WHITE_ROOK, A7)
      .addPiece(BLACK_KING, D4)
      .build();
    
    Result result = engine.runGame(situation);
    ui.displayMessage("RESULT: " + result);
  }

  private static File loadFile(String filename) {
    return new File(Main.class.getResource(filename).getFile());
  }
}
