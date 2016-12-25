package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

  @Test
  public void humanReadableToString() {
    Assert.assertEquals("A1", Position.A1.toString());
  }
}