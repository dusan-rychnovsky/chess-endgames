package cz.dusanrychnovsky.chessendgames.yaat;

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
    return
      column.toString().substring(1, 2) +
      row.toString().substring(1, 2);
  }
}
