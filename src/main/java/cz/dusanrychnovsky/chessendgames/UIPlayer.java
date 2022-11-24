package cz.dusanrychnovsky.chessendgames;

public class UIPlayer implements Player {

  private final UserInterface ui;

  public UIPlayer(UserInterface ui) {
    this.ui = ui;
  }

  @Override
  public Move getMove(Situation situation) {
    return ui.queryMove(situation);
  }
}
