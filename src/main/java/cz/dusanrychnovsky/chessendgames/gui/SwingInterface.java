package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.*;

import java.util.List;

public class SwingInterface implements UserInterface {

  private final MainWindow mainWindow;

  public SwingInterface() {
    mainWindow = MainWindow.get();
  }

  @Override
  public void printTitle(String title) {
    mainWindow.showTitle(title);
  }

  @Override
  public Board queryInitialSituation(List<Piece> pieces) {
    var builder = Board.builder();
    for (var piece : pieces) {
      var pos = mainWindow.queryPosition(piece);
      builder.add(piece, pos);
      mainWindow.showSituation(builder.build());
    }
    return builder.build();
  }

  @Override
  public Move queryMove(Situation situation) {
    return mainWindow.queryMove(situation);
  }

  @Override
  public void printSituation(Situation situation) {
    mainWindow.showSituation(situation.board());
  }

  @Override
  public void printResult(String result) {
    mainWindow.showResult(result);
  }
}
