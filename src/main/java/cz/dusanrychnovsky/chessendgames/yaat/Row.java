package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.Optional;

public enum Row implements Navigable<Row> {
  R1, R2, R3, R4, R5, R6, R7, R8;
  
  @Override
  public int getOrdinal() {
    return ordinal();
  }
  
  @Override
  public Row[] getValues() {
    return values();
  }
}
