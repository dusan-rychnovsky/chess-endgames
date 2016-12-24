package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.Column.CA;
import static cz.dusanrychnovsky.chessendgames.Row.*;
import static org.junit.Assert.*;

public class RowTest {

  // ==========================================================================
  // GET PREVIOUS
  // ==========================================================================

  @Test
  public void firstRow_returnsEmptyResult() {
    assertEquals(Optional.empty(), R1.getPrevious());
  }

  @Test
  public void notFirstRow_returnsPreviousRow() {
    assertEquals(Optional.of(R1), R2.getPrevious());
    assertEquals(Optional.of(R2), R3.getPrevious());
    assertEquals(Optional.of(R3), R4.getPrevious());
    assertEquals(Optional.of(R4), R5.getPrevious());
    assertEquals(Optional.of(R5), R6.getPrevious());
    assertEquals(Optional.of(R6), R7.getPrevious());
    assertEquals(Optional.of(R7), R8.getPrevious());
  }

  // ==========================================================================
  // GET NEXT
  // ==========================================================================

  @Test
  public void lastRow_returnsEmptyResult() {
    assertEquals(Optional.empty(), R8.getNext());
  }

  @Test
  public void notLastRow_returnsNextRow() {
    assertEquals(Optional.of(R2), R1.getNext());
    assertEquals(Optional.of(R3), R2.getNext());
    assertEquals(Optional.of(R4), R3.getNext());
    assertEquals(Optional.of(R5), R4.getNext());
    assertEquals(Optional.of(R6), R5.getNext());
    assertEquals(Optional.of(R7), R6.getNext());
    assertEquals(Optional.of(R8), R7.getNext());
  }
  
  // ==========================================================================
  // TO STRING
  // ==========================================================================
  
  @Test
  public void humanReadableToString() {
    assertEquals("1", R1.toString());
  }
}