package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.util.Random;

import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.*;

public class Main {
  
  public static void main(String[] args) {
    
    DisplayMessage displayMessage = new CliAdapter.PrintMessageToConsole();
    displayMessage.displayMessage("CHESS ENDGAMES v. 0.1");
    
    Engine engine = new Engine(
      new HumanPlayer(WHITE, new CliAdapter.ParseMoveFromConsole()),
      new RandomMovePlayer(new Random(), BLACK, displayMessage)
    );
    
    engine.addEventListener(
      new ShowSituations(new CliAdapter.PrintSituationToConsole())
    );
    
    Situation situation = Situation.builder(WHITE)
      .addPiece(new Piece(WHITE, new King()), F4)
      .addPiece(new Piece(WHITE, new Rook()), B3)
      .addPiece(new Piece(BLACK, new King()), G2)
      .build();
    
    Result result = engine.runGame(situation);
    System.out.println("RESULT: " + result);
  }
}
