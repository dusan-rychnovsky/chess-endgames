package cz.dusanrychnovsky.chessendgames.util;

public final class EnumExtensions {

  private EnumExtensions() {
  }

  /**
   * @return Value of the given enum, which corresponds to the given string
   * value, if any.
   */
  public static <T extends Enum<T>> T parseEnum(String value, Class<T> cls) {
    return Enum.valueOf(cls, value.toUpperCase());
  }
}
