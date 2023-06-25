package cz.dusanrychnovsky.chessendgames.util;

public class UnexpectedException extends RuntimeException {

  public UnexpectedException(Throwable cause) {
    super(cause);
  }

  public UnexpectedException(String message, Throwable cause) {
    super(message, cause);
  }
}
