package cz.dusanrychnovsky.chessendgames.gui;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.CENTER;

public class StatusBar extends JPanel {
  
  private static final int BAR_WIDTH = 825;
  private static final int BAR_HEIGHT = 30;
  
  private final JLabel label;
  
  public StatusBar() {
    setPreferredSize(new Dimension(BAR_WIDTH, BAR_HEIGHT));
    setBackground(SystemColor.control);
    setLayout(new BorderLayout());
    
    label = new JLabel();
    label.setFont(label.getFont().deriveFont(14f));
    add(label, CENTER);
  }
  
  public void setStatusMessage(String message) {
    label.setText("   " + message);
  }
}
