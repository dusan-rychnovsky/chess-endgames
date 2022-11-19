package cz.dusanrychnovsky.chessendgames.database;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {

  private static final Generator GENERATOR = new Generator();

  // ==========================================================================
  // Generate database
  // ==========================================================================

  @Ignore
  @Test
  public void generateShouldGenerateDbOfCertainSize() {
    assertEquals(175_160, GENERATOR.generateDatabase().moves().size());
  }
}
