package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Row.*;
import static cz.dusanrychnovsky.chessendgames.Column.*;

public class SituationPrinter {
  
  public String printSituation(Situation situation) {
    StringBuilder result = new StringBuilder();
    
    for (Row row : new Range<>(R8, R1)) { // TODO: inverted range
      result.append(row + " |");
      for (Column column : new Range<>(CA, CH)) {
        Position position = new Position(column, row);
        result.append(
          " " +
          situation.getPiece(position)  // TODO: getPosition to return Optional
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
