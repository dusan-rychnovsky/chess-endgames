package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Column.*;

public class ColumnTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(CA, Column.parse("a"));
    assertEquals(CB, Column.parse("b"));
    assertEquals(CC, Column.parse("c"));
    assertEquals(CD, Column.parse("d"));
    assertEquals(CE, Column.parse("e"));
    assertEquals(CF, Column.parse("f"));
    assertEquals(CG, Column.parse("g"));
    assertEquals(CH, Column.parse("h"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(CA, Column.parse("A"));
    assertEquals(CB, Column.parse("B"));
    assertEquals(CC, Column.parse("C"));
    assertEquals(CD, Column.parse("D"));
    assertEquals(CE, Column.parse("E"));
    assertEquals(CF, Column.parse("F"));
    assertEquals(CG, Column.parse("G"));
    assertEquals(CH, Column.parse("H"));
  }
}
