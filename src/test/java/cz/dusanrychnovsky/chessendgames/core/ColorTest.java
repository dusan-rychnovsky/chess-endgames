package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;
import static cz.dusanrychnovsky.chessendgames.core.Color.*;
import static org.junit.Assert.assertEquals;

public class ColorTest {
  @Test
  public void getOpponent_returnsOppositeColor() {
    assertEquals(WHITE, BLACK.getOpponent());
    assertEquals(BLACK, WHITE.getOpponent());
  }
}
