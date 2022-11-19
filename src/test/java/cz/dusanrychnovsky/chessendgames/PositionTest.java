package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;

public class PositionTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parseShouldSupportLowerCase() {
    assertEquals(D3, Position.parse("d3"));
  }

  @Test
  public void parseShouldSupportUpperCase() {
    assertEquals(D3, Position.parse("D3"));
  }

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
