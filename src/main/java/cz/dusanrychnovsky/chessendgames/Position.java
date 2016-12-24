package cz.dusanrychnovsky.chessendgames;

import lombok.NonNull;
import lombok.Value;

import java.util.Optional;

@Value
public class Position {

  @NonNull
  Column column;
  
  @NonNull
  Row row;
  
  @Override
  public String toString() {
    return column.toString() + row.toString();
  }
}
