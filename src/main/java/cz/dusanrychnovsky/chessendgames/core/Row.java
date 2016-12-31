package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;

public enum Row implements Navigable<Row>, Serializable {
  R1, R2, R3, R4, R5, R6, R7, R8;
  
  @Override
  public int getOrdinal() {
    return ordinal();
  }
  
  @Override
  public Row[] getValues() {
    return values();
  }
  
  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
