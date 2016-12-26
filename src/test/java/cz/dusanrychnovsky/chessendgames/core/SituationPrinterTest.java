package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static org.junit.Assert.*;

public class SituationPrinterTest {

  private final SituationPrinter printer = new SituationPrinter();
  
  @Test
  public void printsSituation() {
    assertEquals(
      "WHITE:\n" +
      "8 | . . . . . . . .\n" +
      "7 | . . . . . . . .\n" +
      "6 | . . . . . . . .\n" +
      "5 | . . . . . . . .\n" +
      "4 | . . . . . . . .\n" +
      "3 | . . . . . . . R\n" +
      "2 | . . . . . K . .\n" +
      "1 | . . . . . . . K\n" +
      "--|----------------\n" +
      "  | A B C D E F G H"
      ,
      printer.printSituation(
        Situation.builder(Color.WHITE)
          .addPiece(WHITE_KING, Position.F2)
          .addPiece(WHITE_ROOK, Position.H3)
          .addPiece(BLACK_KING, Position.H1)
          .build()
      )
    );
  }
}