package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.IterableExtensions.*;

import java.util.NoSuchElementException;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IterableExtensionsTest {

  // ==========================================================================
  // single
  // ==========================================================================

  @Test
  public void single_returnsTheFirstAndOnlyElement() {
    assertEquals("42", single(singletonList("42")));
  }

  @Test(expected = NoSuchElementException.class)
  public void single_failsForEmptyIterable() {
    single(emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void single_failsForIterableWithMultipleElements() {
    single(asList(1, 2, 3));
  }

  // ==========================================================================
  // dump
  // ==========================================================================

  @Test
  public void dump_emptyIterable() {
    assertEquals("[]", dump(emptyList()));
  }

  @Test
  public void dump_singleElementIterable() {
    assertEquals("[1]", dump(singletonList(1)));
  }

  @Test
  public void dump_multiElementIterable() {
    assertEquals("[1,2,3]", dump(asList(1, 2, 3)));
  }
}
