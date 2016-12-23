package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.Optional;

public enum Column implements Navigable<Column> {
  CA, CB, CC, CD, CE, CF, CG, CH;
  
  @Override
  public int getOrdinal() {
    return ordinal();
  }
  
  @Override
  public Column[] getValues() {
    return values();
  }
}
