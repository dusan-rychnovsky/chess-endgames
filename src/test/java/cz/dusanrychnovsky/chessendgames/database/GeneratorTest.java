package cz.dusanrychnovsky.chessendgames.database;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {

  private static final Generator generator = new Generator();

  // ==========================================================================
  // Generate database
  // ==========================================================================

  @Ignore
  @Test
  public void generate_generatesDbOfCertainSize() {
    assertEquals(175_160, generator.generateDatabase().moves().size());
  }
}
