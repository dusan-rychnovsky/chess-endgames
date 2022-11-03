package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.HashSet;

import static cz.dusanrychnovsky.chessendgames.SetExtensions.single;
import static java.util.Set.*;
import static org.junit.Assert.assertEquals;

public class SetExtensionsTest {

  // ==========================================================================
  // Single
  // ==========================================================================

  @Test(expected = IllegalArgumentException.class)
  public void single_emptySet_Fails() {
    single(new HashSet<String>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void single_setWithMoreThanOneElement_Fails() {
    single(of("a", "b", "c"));
  }

  @Test
  public void single_setWithSingleItem_ReturnsTheItem() {
    assertEquals("a", single(of("a")));
  }
}
