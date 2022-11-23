package cz.dusanrychnovsky.chessendgames.gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class StatusPanel extends JPanel {

  private static final int PANEL_HEIGHT = 25;

  private final JLabel label;

  public StatusPanel(int width) {
    setPreferredSize(new Dimension(width, PANEL_HEIGHT));
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
