package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.TimeExtensions.printDuration;
import static org.junit.Assert.assertEquals;

public class TimeExtensionsTest {

  @Test
  public void printDuration_printsTimeInHumanReadableForm() {
    assertEquals("5 min, 49 sec, 853 ms", printDuration(349853147100L));
  }
}
