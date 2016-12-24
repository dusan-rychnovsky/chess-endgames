package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;

import static cz.dusanrychnovsky.chessendgames.Color.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;

import static org.junit.Assert.*;

public class SituationPrinterTest {

  private final SituationPrinter printer = new SituationPrinter();
  
  @Test
  public void printsSituation() {
    assertEquals(
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
          .addPiece(new Piece(WHITE, new King()), new Position(CF, R2))
          .addPiece(new Piece(WHITE, new Rook()), new Position(CH, R3))
          .addPiece(new Piece(BLACK, new King()), new Position(CH, R1))
          .build()
      )
    );
  }
}