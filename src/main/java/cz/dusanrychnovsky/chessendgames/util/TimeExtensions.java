package cz.dusanrychnovsky.chessendgames.util;

public final class TimeExtensions {

  private TimeExtensions() {
  }

  public static String printDuration(long ns) {
    var acc = ns / 1_000_000.f;

    var ms = acc % 1_000;
    acc /= 1_000;

    var sec = acc % 60;
    acc /= 60;

    var min = acc;

    return (int) min + " min, " + (int) sec + " sec, " + (int) ms + " ms";
  }
}
