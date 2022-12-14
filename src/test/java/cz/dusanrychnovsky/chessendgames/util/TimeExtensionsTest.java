package cz.dusanrychnovsky.chessendgames.util;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.util.TimeExtensions.printDuration;
import static org.junit.Assert.assertEquals;

public class TimeExtensionsTest {

  @Test
  public void printDurationShouldPrintTimeInHumanReadableForm() {
    assertEquals("5 min, 49 sec, 853 ms", printDuration(349853147100L));
  }
}
