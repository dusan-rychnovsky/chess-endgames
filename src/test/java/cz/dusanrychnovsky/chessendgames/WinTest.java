package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Color.Black;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WinTest {

  @Test
  public void print() {
    assertEquals("Mate. Black wins.", new Win(Black).print());
  }
}
