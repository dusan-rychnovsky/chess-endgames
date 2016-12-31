package cz.dusanrychnovsky.chessendgames.core;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static cz.dusanrychnovsky.chessendgames.core.Column.CA;
import static cz.dusanrychnovsky.chessendgames.core.Column.CH;
import static cz.dusanrychnovsky.chessendgames.core.Row.R1;
import static cz.dusanrychnovsky.chessendgames.core.Row.R8;

public enum PieceType implements Serializable {

  // TODO: maybe return just toPos, instead of a whole Move?
  KING() {
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
            result.add(new Move(pos, Position.get(col, row)));
          }
        }
      }
  
      return result;
    }
  },
  
  ROOK () {
    @Override
    public Iterable<Move> listAllMovesFromPosition(Position pos) {
      List<Move> result = new LinkedList<>();
  
      Column currColumn = pos.getColumn();
      Row currRow = pos.getRow();
  
      // add row
      for (Column col : new Range<>(CA, CH)) {
        if (col != currColumn) {
          result.add(new Move(pos, Position.get(col, currRow)));
        }
      }
  
      // add column
      for (Row row : new Range<>(R1, R8)) {
        if (row != currRow) {
          result.add(new Move(pos, Position.get(currColumn, row)));
        }
      }
  
      return result;
    }
  };
  
  public abstract Iterable<Move> listAllMovesFromPosition(Position pos);
}
