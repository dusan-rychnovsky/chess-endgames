package cz.dusanrychnovsky.chessendgames.gui;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class SwingExtensions {

  public static void runOnUiThread(Runnable action) {
    try {
      SwingUtilities.invokeAndWait(action);
    }
    catch (InterruptedException | InvocationTargetException ex) {
      throw new RuntimeException(ex);
    }
  }
}
