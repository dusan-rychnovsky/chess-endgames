package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

  // ==========================================================================
  // Equals & HashCode
  // ==========================================================================

  @Test
  public void sameMoves_areEqual() {
    assertEquals(new Move(D3, E5), new Move(D3, E5));
    assertEquals(new Move(D3, E5).hashCode(), new Move(D3, E5).hashCode());
  }

  @Test
  public void differentFrom_areNotEqual() {
    assertNotEquals(new Move(D3, E5), new Move(D2, E5));
    assertNotEquals(new Move(D3, E5).hashCode(), new Move(D2, E5).hashCode());
  }

  @Test
  public void differentTo_areNotEqual() {
    assertNotEquals(new Move(D3, E5), new Move(D3, E4));
    assertNotEquals(new Move(D3, E5).hashCode(), new Move(D3, E4).hashCode());
  }
}
