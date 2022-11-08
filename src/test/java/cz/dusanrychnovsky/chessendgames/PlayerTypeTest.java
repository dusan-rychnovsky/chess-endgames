package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.PlayerType.*;
import static org.junit.Assert.assertEquals;

public class PlayerTypeTest {

  // ==========================================================================
  // Parsing
  // ==========================================================================

  @Test
  public void parse_supportsLowerCase() {
    assertEquals(STDIN, parse("stdin"));
    assertEquals(DB, parse("db"));
  }

  @Test
  public void parse_supportsUpperCase() {
    assertEquals(STDIN, parse("STDIN"));
    assertEquals(DB, parse("DB"));
  }

  @Test
  public void parse_supportsCamelCase() {
    assertEquals(STDIN, parse("StdIn"));
    assertEquals(DB, parse("Db"));
  }
}
