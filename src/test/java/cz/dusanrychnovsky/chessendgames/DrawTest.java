package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DrawTest {
  @Test
  public void print() {
    assertEquals("Draw.", new Draw().print());
  }
}
