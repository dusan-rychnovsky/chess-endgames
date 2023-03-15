package cz.dusanrychnovsky.chessendgames.util;

public final class EnumExtensions {

  private EnumExtensions() {
  }

  public static <T extends Enum<T>> T parseEnum(String value, Class<T> cls) {
    return Enum.valueOf(cls, value.toUpperCase());
  }
}
