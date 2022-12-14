package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static cz.dusanrychnovsky.chessendgames.core.Column.*;
import static cz.dusanrychnovsky.chessendgames.core.Row.*;

public class PositionTest {

  // ==========================================================================
  // Destructuring
  // ==========================================================================

  @Test
  public void column() {
    assertEquals(CD, D3.column());
  }

  @Test
  public void row() {
    assertEquals(R3, D3.row());
  }

  // ==========================================================================
  // Retrieving
  // ==========================================================================

  @Test
  public void get() {
    assertEquals(D3, Position.get(CD, R3));
    assertEquals(F5, Position.get(CF, R5));
    assertEquals(D4, Position.get(CD, R4));
    assertEquals(E6, Position.get(CE, R6));
  }
}
