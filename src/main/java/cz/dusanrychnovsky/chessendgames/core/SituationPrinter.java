package cz.dusanrychnovsky.chessendgames.core;

import static cz.dusanrychnovsky.chessendgames.core.Row.*;

public class SituationPrinter {
  
  public String printSituation(Situation situation) {
    StringBuilder result = new StringBuilder();
    
    result.append(situation.getCurrentColor() + ":\n");
    
    for (Row row : new Range<>(R8, R1)) {
      result.append(row + " |");
      for (Column column : new Range<>(Column.CA, Column.CH)) {
        Position position = Position.get(column, row);
        result.append(
          " " +
          situation.getPiece(position)
            .map(piece -> print(piece))
            .orElse(".")
        );
      }
      result.append("\n");
    }
    
    result.append("--|----------------\n");
    result.append("  | A B C D E F G H");
    
    return result.toString();
  }
  
  private String print(Piece piece) {
    return piece.getType().toString().substring(0, 1);
  }
}
