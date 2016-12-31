package cz.dusanrychnovsky.chessendgames.core;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.core.Color.BLACK;
import static cz.dusanrychnovsky.chessendgames.core.Piece.*;
import static cz.dusanrychnovsky.chessendgames.core.Position.F2;
import static cz.dusanrychnovsky.chessendgames.core.Position.H1;
import static cz.dusanrychnovsky.chessendgames.core.Position.H3;
import static org.junit.Assert.*;

public class SituationPrinterTest {

  private final SituationPrinter printer = new SituationPrinter();
  
  @Test
  public void printsSituation() {
    assertEquals(
      "BLACK:\n" +
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
        Situation.builder(BLACK)
          .addPiece(WHITE_KING, F2)
          .addPiece(WHITE_ROOK, H3)
          .addPiece(BLACK_KING, H1)
          .build()
      )
    );
  }
}