package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;

public enum Column implements Navigable<Column>, Serializable {
  CA, CB, CC, CD, CE, CF, CG, CH;
  
  @Override
  public int getOrdinal() {
    return ordinal();
  }
  
  @Override
  public Column[] getValues() {
    return values();
  }
  
  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
