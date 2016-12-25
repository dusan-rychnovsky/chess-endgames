package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.core.Position.*;
import static org.junit.Assert.*;

public class MoveTest {

  // ==========================================================================
  // TO STRING
  // ==========================================================================
  
  @Test
  public void humanReadableToString() {
    assertEquals("C3 C6", new Move(C3, C6).toString());
  }
  
  // ==========================================================================
  // PARSE MOVE
  // ==========================================================================
  
  @Test
  public void validMove_willBeParsed() {
    assertEquals(new Move(C3, F6), Move.parseFrom("C3 F6"));
  }
  
  @Test
  public void isCaseInsensitive() {
    assertEquals(new Move(C3, F6), Move.parseFrom("c3 F6"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void invalidMove_fails() {
    Move.parseFrom("C3");
  }
}