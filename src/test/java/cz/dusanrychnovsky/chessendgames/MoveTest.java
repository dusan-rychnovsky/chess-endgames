package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Move.parseMove;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static cz.dusanrychnovsky.chessendgames.Position.*;

public class MoveTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parseShouldSupportLowerCase() {
    assertEquals(new Move(D3, E5), parseMove("d3 e5"));
  }

  @Test
  public void parseShouldSupportUpperCase() {
    assertEquals(new Move(D3, E5), parseMove("D3 E5"));
  }

  @Test
  public void parseShouldSupportMixedCase() {
    assertEquals(new Move(D3, E5), parseMove("d3 E5"));
    assertEquals(new Move(D3, E5), parseMove("D3 e5"));
  }

  // ==========================================================================
  // Equals & HashCode
  // ==========================================================================

  @Test
  public void sameMovesShouldBeEqual() {
    assertEquals(new Move(D3, E5), new Move(D3, E5));
    assertEquals(new Move(D3, E5).hashCode(), new Move(D3, E5).hashCode());
  }

  @Test
  public void movesWithDifferentFromShouldNotNotEqual() {
    assertNotEquals(new Move(D3, E5), new Move(D2, E5));
    assertNotEquals(new Move(D3, E5).hashCode(), new Move(D2, E5).hashCode());
  }

  @Test
  public void movesWithDifferentToShouldNotBeEqual() {
    assertNotEquals(new Move(D3, E5), new Move(D3, E4));
    assertNotEquals(new Move(D3, E5).hashCode(), new Move(D3, E4).hashCode());
  }
}
