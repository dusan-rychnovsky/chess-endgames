package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PositionTest {

  @Test(expected = IllegalArgumentException.class)
  public void get_invalidposition_fails() {
    Position.get('I', 2);
  }

  @Test
  public void get_returnsPosition() {
    assertEquals(Position.H2, Position.get('H', 2));
  }
}
