package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Color.BLACK;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WinTest {

  @Test
  public void print() {
    assertEquals("Mate. BLACK wins.", new Win(BLACK).print());
  }
}
