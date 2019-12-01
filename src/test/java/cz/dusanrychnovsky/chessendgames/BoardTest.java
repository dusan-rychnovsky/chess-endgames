package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.PieceType.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

public class BoardTest {

  // ==========================================================================
  // Entry Parsing
  // ==========================================================================

  @Test
  public void entry_parse_extractsColorPieceAndPosition() {
    Board.Entry entry = Board.Entry.parse("white king d3");
    assertEquals(White, entry.piece().color());
    assertEquals(King, entry.piece().type());
    assertEquals(D3, entry.position());
  }
}
