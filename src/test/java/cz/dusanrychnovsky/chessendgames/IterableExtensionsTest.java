package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.IterableExtensions.*;

import java.util.NoSuchElementException;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

import org.junit.Test;

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

  // ==========================================================================
  // size
  // ==========================================================================

  @Test
  public void size_emptyIterable_returnsZero() {
    assertEquals(0, size(emptyList()));
  }

  @Test
  public void size_singleElementIterable_returnsOne() {
    assertEquals(1, size(singletonList(1)));
  }

  @Test
  public void size_returnsSizeOfIterable() {
    assertEquals(5, size(asList(1, 2, 3, 4, 5)));
  }

  // ==========================================================================
  // contains
  // ==========================================================================

  @Test
  public void contains_emptyIterable_doesNotContainAnyElements() {
    assertFalse(contains(emptyList(), 1));
  }

  @Test
  public void contains_existingElement_returnsTrue() {
    assertTrue((contains(asList(1, 2, 3), 3)));
  }

  @Test
  public void contains_nonExistingElement_returnsFalse() {
    assertFalse(contains(asList(1, 2, 3), 4));
  }
}
