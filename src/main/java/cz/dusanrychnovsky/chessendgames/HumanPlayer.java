package cz.dusanrychnovsky.chessendgames;

import com.google.common.base.Preconditions;
import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;

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
    checkArgument(getColor() == situation.getCurrentColor());
    return ui.requestMove(situation);
  }
}
