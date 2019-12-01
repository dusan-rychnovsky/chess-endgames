package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StringExtensionsTest {

  // ==========================================================================
  // Capitalization
  // ==========================================================================

  @Test
  public void capitalize_supportsAllLowercase() {
    assertEquals("Hello", StringExtensions.capitalize("hello"));
  }

  @Test
  public void capitalize_supportsAllUppercase() {
    assertEquals("Hello", StringExtensions.capitalize("HELLO"));
  }

  @Test
  public void capitalize_supportsAlreadyCapitalized() {
    assertEquals("Hello", StringExtensions.capitalize("Hello"));
  }

  @Test
  public void capitalize_supportsVariousCasing() {
    assertEquals("Hello", StringExtensions.capitalize("hElLo"));
  }
}
