package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Position.*;
import static cz.dusanrychnovsky.chessendgames.Column.CD;
import static cz.dusanrychnovsky.chessendgames.Row.R3;

public class PositionTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(D3, parse("d3"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(D3, parse("D3"));
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
}
