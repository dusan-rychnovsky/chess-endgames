package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.MapExtensions.*;
import static java.util.Collections.emptyMap;
import static java.util.Map.of;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

public class MapExtensionsTest {

  // ==========================================================================
  // Filtering by Key
  // ==========================================================================

  @Test
  public void filterByKeyShouldReturnSubMap() {
    assertEquals(
      of(1, "a", 3, "c"),
      filterByKey(
        of(1, "a", 2, "b", 3, "c"),
        key -> key % 2 == 1));
  }

  @Test
  public void filterByKeyShouldReturnEmptyMapWhenGivenEmptyMap() {
    assertEquals(of(), filterByKey(of(), key -> true));
  }

  // ==========================================================================
  // Filtering
  // ==========================================================================

  @Test
  public void filterShouldReturnSubMap() {
    assertEquals(
      of(1, "a", 3, "c"),
      filter(
        of(1, "a", 2, "b", 3, "c"),
        entry -> entry.getKey() == 1 || entry.getValue().equals("c")));
  }

  // ==========================================================================
  // Get
  // ==========================================================================

  @Test
  public void getShouldReturnValueWhenKeyDefined() {
    assertEquals(
      Optional.of("a"),
      get(of(1, "a", 3, "c"),1)
    );
  }

  @Test
  public void getShouldReturnEmptyResultWhenKeyUndefined() {
    assertEquals(
      Optional.empty(),
      get(of(1, "a", 3, "c"),2)
    );
  }

  // ==========================================================================
  // Map Values
  // ==========================================================================

  @Test
  public void mapValuesShouldReturnEmptyMapWhenGivenEmptyMap() {
    assertEquals(emptyMap(), mapValues(emptyMap(), value -> value));
  }

  @Test
  public void mapValuesShouldReturnNewMapWithMapFunctionAppliedPerValue() {
    assertEquals(
      of("a", 2, "b", 4, "c", 6),
      mapValues(of("a", 1, "b", 2, "c", 3),value -> value * 2)
    );
  }
}
