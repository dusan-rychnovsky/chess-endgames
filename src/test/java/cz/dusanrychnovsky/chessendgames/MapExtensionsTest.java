package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.MapExtensions.filter;
import static cz.dusanrychnovsky.chessendgames.MapExtensions.filterByKey;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.Map;

public class MapExtensionsTest {

  // ==========================================================================
  // Filtering by Key
  // ==========================================================================

  @Test
  public void filterByKey_returnsSubMap() {
    assertEquals(
      Map.of(1, "a", 3, "c"),
      filterByKey(
        Map.of(1, "a", 2, "b", 3, "c"),
        key -> key % 2 == 1));
  }

  @Test
  public void filterByKey_emptyMap_returnsEmptySubmap() {
    assertEquals(Map.of(), filterByKey(Map.of(), key -> true));
  }

  // ==========================================================================
  // Filtering
  // ==========================================================================

  @Test
  public void filter_returnsSubMap() {
    assertEquals(
      Map.of(1, "a", 3, "c"),
      filter(
        Map.of(1, "a", 2, "b", 3, "c"),
        entry -> entry.getKey() == 1 || entry.getValue().equals("c")));
  }
}
