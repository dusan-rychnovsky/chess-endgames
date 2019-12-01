package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.PieceType.*;

public class PieceTypeTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(King, parse("king"));
    assertEquals(Rook, parse("rook"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(King, parse("KING"));
    assertEquals(Rook, parse("ROOK"));
  }

  @Test
  public void parse_supportsCamelCase() {
    assertEquals(King, parse("King"));
    assertEquals(Rook, parse("Rook"));
  }
}
