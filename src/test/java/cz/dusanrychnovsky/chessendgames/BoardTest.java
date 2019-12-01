package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Piece.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

public class BoardTest {

  // ==========================================================================
  // Entry Parsing
  // ==========================================================================

  @Test
  public void entry_parse_extractsColorPieceAndPosition() {
    Board.Entry entry = Board.Entry.parse("white king d3");
    assertEquals(White, entry.color());
    assertEquals(King, entry.piece());
    assertEquals(D3, entry.position());
  }
}
