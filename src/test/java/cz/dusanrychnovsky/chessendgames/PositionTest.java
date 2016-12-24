package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Position.A1;
import static org.junit.Assert.*;

public class PositionTest {

  @Test
  public void humanReadableToString() {
    assertEquals("A1", A1.toString());
  }
}