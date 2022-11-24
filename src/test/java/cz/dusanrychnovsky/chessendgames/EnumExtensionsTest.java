package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.EnumExtensions.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.KING;
import static cz.dusanrychnovsky.chessendgames.Position.C3;
import static org.junit.Assert.assertEquals;

public class EnumExtensionsTest {

  @Test
  public void parseShouldSupportLowerCase() {
    assertEquals(WHITE, parseEnum("white", Color.class));
    assertEquals(KING, parseEnum("king", PieceType.class));
    assertEquals(C3, parseEnum("c3", Position.class));
  }

  @Test
  public void parseShouldSupportUpperCase() {
    assertEquals(WHITE, parseEnum("WHITE", Color.class));
    assertEquals(KING, parseEnum("KING", PieceType.class));
    assertEquals(C3, parseEnum("C3", Position.class));
  }

  @Test
  public void parseShouldSupportMixedCase() {
    assertEquals(WHITE, parseEnum("White", Color.class));
    assertEquals(KING, parseEnum("King", PieceType.class));
  }
}
