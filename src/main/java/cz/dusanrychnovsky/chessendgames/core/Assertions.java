package cz.dusanrychnovsky.chessendgames.core;

public class Assertions {
  
  public static void check(boolean condition) {
    if (!condition) {
      throw new AssertionError();
    }
  }
}
