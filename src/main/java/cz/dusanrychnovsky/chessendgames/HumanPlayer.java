package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.*;

public class HumanPlayer implements Player {
  
  private final Color color;
  private final UserInterface ui;
  
  public HumanPlayer(Color color, UserInterface ui) {
    this.color = color;
    this.ui = ui;
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public Move pickMove(Situation situation) {
    return ui.requestMove(situation);
  }
}
