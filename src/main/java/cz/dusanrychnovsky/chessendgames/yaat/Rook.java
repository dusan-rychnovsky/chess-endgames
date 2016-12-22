package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.LinkedList;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.yaat.Column.*;
import static cz.dusanrychnovsky.chessendgames.yaat.Row.*;

public class Rook implements PieceType {
  
  @Override
  public Iterable<Move> listAllMovesFromPosition(Position pos) {

    List<Move> result = new LinkedList<>();

    Column currColumn = pos.getColumn();
    Row currRow = pos.getRow();

    // add row
    for (Column col : new Range<>(CA, CH)) {
      if (col != currColumn) {
        result.add(new Move(pos, new Position(col, currRow)));
      }
    }

    // add column
    for (Row row : new Range<>(R1, R8)) {
      if (row != currRow) {
        result.add(new Move(pos, new Position(currColumn, row)));
      }
    }

    return result;
  }
  
  @Override
  public int hashCode() {
    return 1;
  }
  
  @Override
  public boolean equals(Object obj) {
    return obj instanceof Rook;
  }
  
  @Override
  public String toString() {
    return "ROOK";
  }
}
