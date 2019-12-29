package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Position.*;

public class MoveTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(new Move(D3, E5), Move.parse("d3 e5"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(new Move(D3, E5), Move.parse("D3 E5"));
  }

  @Test
  public void parse_supportsMixedCase() {
    assertEquals(new Move(D3, E5), Move.parse("d3 E5"));
    assertEquals(new Move(D3, E5), Move.parse("D3 e5"));
  }
}
