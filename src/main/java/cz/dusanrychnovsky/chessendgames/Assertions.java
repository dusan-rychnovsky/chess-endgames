package cz.dusanrychnovsky.chessendgames;

public class Assertions {
  
  public static void check(boolean condition) {
    if (!condition) {
      throw new AssertionError();
    }
  }
}
