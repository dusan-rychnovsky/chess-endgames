package cz.dusanrychnovsky.chessendgames;

public enum Column implements Navigable<Column> {
  CA, CB, CC, CD, CE, CF, CG, CH;

  public static Column parse(String value) {
    return valueOf("C" + value.toUpperCase());
  }

  @Override
  public int ord() {
    return ordinal();
  }

  @Override
  public Column[] all() {
    return values();
  }

  @Override
  public String toString() {
    return super.toString().substring(1, 2);
  }
}
