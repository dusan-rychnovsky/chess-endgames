package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Status.*;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusTest {

  // ==========================================================================
  // Is Final
  // ==========================================================================

  @Test
  public void winShouldBeFinal() {
    assertTrue(win(WHITE).isFinal());
  }

  @Test
  public void drawShouldBeFinal() {
    assertTrue(DRAW.isFinal());
  }

  @Test
  public void inProgressShouldNotBeFinal() {
    assertFalse(IN_PROGRESS.isFinal());
  }

  // ==========================================================================
  // Is Win
  // ==========================================================================

  @Test
  public void winShouldBeWin() {
    assertTrue(win(WHITE).isWin(WHITE));
  }

  @Test
  public void winShouldNotBeWinOfOpponent() {
    assertFalse(win(BLACK).isWin(WHITE));
  }

  @Test
  public void winShouldNotBeDraw() {
    assertFalse(DRAW.isWin(WHITE));
  }

  @Test
  public void winShouldNotBeInProgress() {
    assertFalse(IN_PROGRESS.isWin(WHITE));
  }
}
