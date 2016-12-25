package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.*;

public class HumanPlayer implements Player {
  
  private final Color color;
  
  private final GatherMove gatherMove;
  
  public HumanPlayer(Color color, GatherMove gatherMove) {
    this.color = color;
    this.gatherMove = gatherMove;
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public Move pickMove(Situation situation) {
    return gatherMove.gatherMove(situation);
  }
}
