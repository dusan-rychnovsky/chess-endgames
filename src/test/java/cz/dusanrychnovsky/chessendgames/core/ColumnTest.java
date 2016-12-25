package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ColumnTest {

  // ==========================================================================
  // GET PREVIOUS
  // ==========================================================================

  @Test
  public void firstColumn_returnsEmptyResult() {
    assertEquals(Optional.empty(), Column.CA.getPrevious());
  }

  @Test
  public void notFirstColumn_returnsPreviousColumn() {
    assertEquals(Optional.of(Column.CA), Column.CB.getPrevious());
    assertEquals(Optional.of(Column.CB), Column.CC.getPrevious());
    assertEquals(Optional.of(Column.CC), Column.CD.getPrevious());
    assertEquals(Optional.of(Column.CD), Column.CE.getPrevious());
    assertEquals(Optional.of(Column.CE), Column.CF.getPrevious());
    assertEquals(Optional.of(Column.CF), Column.CG.getPrevious());
    assertEquals(Optional.of(Column.CG), Column.CH.getPrevious());
  }

  // ==========================================================================
  // GET NEXT
  // ==========================================================================

  @Test
  public void lastColumn_returnsEmptyResult() {
    assertEquals(Optional.empty(), Column.CH.getNext());
  }

  @Test
  public void notLastColumn_returnsNextColumn() {
    assertEquals(Optional.of(Column.CB), Column.CA.getNext());
    assertEquals(Optional.of(Column.CC), Column.CB.getNext());
    assertEquals(Optional.of(Column.CD), Column.CC.getNext());
    assertEquals(Optional.of(Column.CE), Column.CD.getNext());
    assertEquals(Optional.of(Column.CF), Column.CE.getNext());
    assertEquals(Optional.of(Column.CG), Column.CF.getNext());
    assertEquals(Optional.of(Column.CH), Column.CG.getNext());
  }
  
  // ==========================================================================
  // GET DISTANCE
  // ==========================================================================
  
  @Test
  public void sameColumn_distanceZero() {
    assertEquals(0, Column.CC.getDistance(Column.CC));
  }
  
  @Test
  public void nextColumn_distanceOne() {
    assertEquals(1, Column.CC.getDistance(Column.CD));
  }
  
  @Test
  public void acrossBoard_distanceSeven() {
    assertEquals(7, Column.CA.getDistance(Column.CH));
  }
  
  @Test
  public void distanceIsSymmetric() {
    assertEquals(Column.CC.getDistance(Column.CE), Column.CE.getDistance(Column.CC));
  }
  
  // ==========================================================================
  // TO STRING
  // ==========================================================================
  
  @Test
  public void humanReadableToString() {
    Assert.assertEquals("A", Column.CA.toString());
  }
}