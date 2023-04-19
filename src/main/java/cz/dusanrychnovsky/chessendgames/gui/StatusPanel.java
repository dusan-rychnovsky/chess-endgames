package cz.dusanrychnovsky.chessendgames.gui;

import javax.swing.*;
import java.awt.*;

import static cz.dusanrychnovsky.chessendgames.gui.SwingExtensions.runOnUiThread;
import static javax.swing.SwingConstants.CENTER;

public class StatusPanel {

  private static final int PANEL_HEIGHT = 25;

  private final JPanel panel;

  private final JLabel label;

  public StatusPanel(int width) {
    panel = new JPanel();
    panel.setPreferredSize(new Dimension(width, PANEL_HEIGHT));
    panel.setBackground(SystemColor.control);
    panel.setLayout(new BorderLayout());

    label = new JLabel();
    label.setFont(label.getFont().deriveFont(14f));
    panel.add(label, CENTER);
  }

  public void attach(JFrame frame, Object constraints) {
    frame.add(panel, constraints);
  }

  public int height() {
    return panel.getHeight();
  }

  public void setStatusMessage(String message) {
    runOnUiThread(() ->
      label.setText("   " + message)
    );
  }
}
