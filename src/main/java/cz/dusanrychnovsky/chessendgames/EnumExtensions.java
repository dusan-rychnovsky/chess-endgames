package cz.dusanrychnovsky.chessendgames;

public class EnumExtensions {
  public static <T extends Enum<T>> T parseEnum(String value, Class<T> cls) {
    return Enum.valueOf(cls, value.toUpperCase());
  }
}
