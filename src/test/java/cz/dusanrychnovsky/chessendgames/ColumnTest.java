package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Column.*;

import static java.util.Optional.of;
import static java.util.Optional.empty;

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

  // ==========================================================================
  // Navigating
  // ==========================================================================

  @Test
  public void prev() {
    assertEquals(empty(), CA.prev());
    assertEquals(of(CA), CB.prev());
    assertEquals(of(CB), CC.prev());
    assertEquals(of(CC), CD.prev());
    assertEquals(of(CD), CE.prev());
    assertEquals(of(CE), CF.prev());
    assertEquals(of(CF), CG.prev());
    assertEquals(of(CG), CH.prev());
  }

  @Test
  public void next() {
    assertEquals(of(CB), CA.next());
    assertEquals(of(CC), CB.next());
    assertEquals(of(CD), CC.next());
    assertEquals(of(CE), CD.next());
    assertEquals(of(CF), CE.next());
    assertEquals(of(CG), CF.next());
    assertEquals(of(CH), CG.next());
    assertEquals(empty(), CH.next());
  }

  // ==========================================================================
  // Printing
  // ==========================================================================

  @Test
  public void toString_printsLetter() {
    assertEquals("A", CA.toString());
    assertEquals("C", CC.toString());
    assertEquals("H", CH.toString());
  }
}
