package cz.dusanrychnovsky.chessendgames.gui;

import cz.dusanrychnovsky.chessendgames.util.UnexpectedException;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public final class SwingExtensions {

  private SwingExtensions() {
  }

  public static void runOnUiThread(Runnable action) {
    try {
      SwingUtilities.invokeAndWait(action);
    }
    catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    catch (InvocationTargetException ex) {
      throw new UnexpectedException(ex);
    }
  }
}
