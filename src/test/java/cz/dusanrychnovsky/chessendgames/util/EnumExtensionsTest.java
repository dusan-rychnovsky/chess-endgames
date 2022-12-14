package cz.dusanrychnovsky.chessendgames.util;

import org.junit.Test;
import cz.dusanrychnovsky.chessendgames.core.*;

import static cz.dusanrychnovsky.chessendgames.core.Color.WHITE;
import static cz.dusanrychnovsky.chessendgames.util.EnumExtensions.*;
import static cz.dusanrychnovsky.chessendgames.core.PieceType.KING;
import static cz.dusanrychnovsky.chessendgames.core.Position.C3;
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
