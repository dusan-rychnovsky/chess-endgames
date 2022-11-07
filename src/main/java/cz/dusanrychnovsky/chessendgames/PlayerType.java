package cz.dusanrychnovsky.chessendgames;

public enum PlayerType {

  StdIn,
  Db;

  public static PlayerType parse(String value) {
    return valueOf(value);
  }
}
