package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import java.util.Optional;

import static cz.dusanrychnovsky.chessendgames.Column.*;
import static org.junit.Assert.*;

public class ColumnTest {

  // ==========================================================================
  // GET PREVIOUS
  // ==========================================================================

  @Test
  public void firstColumn_returnsEmptyResult() {
    assertEquals(Optional.empty(), CA.getPrevious());
  }

  @Test
  public void notFirstColumn_returnsPreviousColumn() {
    assertEquals(Optional.of(CA), CB.getPrevious());
    assertEquals(Optional.of(CB), CC.getPrevious());
    assertEquals(Optional.of(CC), CD.getPrevious());
    assertEquals(Optional.of(CD), CE.getPrevious());
    assertEquals(Optional.of(CE), CF.getPrevious());
    assertEquals(Optional.of(CF), CG.getPrevious());
    assertEquals(Optional.of(CG), CH.getPrevious());
  }

  // ==========================================================================
  // GET NEXT
  // ==========================================================================

  @Test
  public void lastColumn_returnsEmptyResult() {
    assertEquals(Optional.empty(), CH.getNext());
  }

  @Test
  public void notLastColumn_returnsNextColumn() {
    assertEquals(Optional.of(CB), CA.getNext());
    assertEquals(Optional.of(CC), CB.getNext());
    assertEquals(Optional.of(CD), CC.getNext());
    assertEquals(Optional.of(CE), CD.getNext());
    assertEquals(Optional.of(CF), CE.getNext());
    assertEquals(Optional.of(CG), CF.getNext());
    assertEquals(Optional.of(CH), CG.getNext());
  }
  
  // ==========================================================================
  // GET DISTANCE
  // ==========================================================================
  
  @Test
  public void sameColumn_distanceZero() {
    assertEquals(0, CC.getDistance(CC));
  }
  
  @Test
  public void nextColumn_distanceOne() {
    assertEquals(1, CC.getDistance(CD));
  }
  
  @Test
  public void acrossBoard_distanceSeven() {
    assertEquals(7, CA.getDistance(CH));
  }
  
  @Test
  public void distanceIsSymmetric() {
    assertEquals(CC.getDistance(CE), CE.getDistance(CC));
  }
}