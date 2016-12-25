package cz.dusanrychnovsky.chessendgames.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MouseClickedListener extends MouseListener {
  
  @Override
  default void mousePressed(MouseEvent mouseEvent) {}
  
  @Override
  default void mouseReleased(MouseEvent mouseEvent) {}
  
  @Override
  default void mouseEntered(MouseEvent mouseEvent) {}
  
  @Override
  default void mouseExited(MouseEvent mouseEvent) {}
}
