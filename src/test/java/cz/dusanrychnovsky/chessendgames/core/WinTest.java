package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Color.BLACK;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WinTest {

  @Test
  public void print() {
    assertEquals("Mate. BLACK wins.", new Win(BLACK).print());
  }
}
