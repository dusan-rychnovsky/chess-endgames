package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;
import static org.junit.Assert.*;

public class PositionTest {

  @Test
  public void humanReadableToString() {
    assertEquals("A1", new Position(CA, R1).toString());
  }
}