package cz.dusanrychnovsky.chessendgames.yaat;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class King implements PieceType {
  
  @Override
  public Iterable<Move> listAllMovesFromPosition(Position pos) {

    List<Move> result = new LinkedList<>();

    Column currColumn = pos.getColumn();
    Row currRow = pos.getRow();

    Column leftCol = currColumn.getPrevious().orElse(currColumn);
    Row topRow = currRow.getPrevious().orElse(currRow);
    Column rightCol = currColumn.getNext().orElse(currColumn);
    Row bottomRow = currRow.getNext().orElse(currRow);

    for (Column col : new Range<>(leftCol, rightCol)) {
      for (Row row : new Range<>(topRow, bottomRow)) {
        if (col != currColumn || row != currRow) {
          result.add(new Move(pos, new Position(col, row)));
        }
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
    return obj instanceof King;
  }

  @Override
  public String toString() {
    return "KING";
  }
}
