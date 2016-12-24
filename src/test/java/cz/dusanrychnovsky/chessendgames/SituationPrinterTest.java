package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Position.*;

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
        Situation.builder(WHITE)
          .addPiece(new Piece(WHITE, new King()), F2)
          .addPiece(new Piece(WHITE, new Rook()), H3)
          .addPiece(new Piece(BLACK, new King()), H1)
          .build()
      )
    );
  }
}