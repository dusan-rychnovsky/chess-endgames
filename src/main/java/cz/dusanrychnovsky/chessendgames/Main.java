package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;
import cz.dusanrychnovsky.chessendgames.gui.GraphicalUserInterface;

import java.util.Random;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;

public class Main {
  
  public static void main(String[] args) {
    UserInterface ui = new GraphicalUserInterface();
    
    ui.displayMessage("CHESS ENDGAMES v. 0.1");
    
    Engine engine = new Engine(
      new HumanPlayer(WHITE, ui),
      new RandomMovePlayer(new Random(), BLACK, ui)
    );
    
    engine.addEventListener(new ShowSituations(ui));
    
    Situation situation = Situation.builder(WHITE)
      .addPiece(new Piece(WHITE, KING), F4)
      .addPiece(new Piece(WHITE, ROOK), B3)
      .addPiece(new Piece(BLACK, KING), G2)
      .build();
    
    Result result = engine.runGame(situation);
    ui.displayMessage("RESULT: " + result);
  }
}
