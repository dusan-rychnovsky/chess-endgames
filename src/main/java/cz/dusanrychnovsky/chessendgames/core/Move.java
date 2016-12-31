package cz.dusanrychnovsky.chessendgames.core;

import lombok.Value;

import java.io.Serializable;

@Value
public class Move implements Serializable {
  
  Position from;
  Position to;
  
  public static Move parseFrom(String line) {
    line = line.trim();
    
    String[] tokens = line.split("\\s+");
    if (tokens.length != 2) {
      throw new IllegalArgumentException("Invalid move: " + line);
    }
    
    return new Move(
      Position.parseFrom(tokens[0].trim()),
      Position.parseFrom(tokens[1].trim())
    );
  }
  
  @Override
  public String toString() {
    return from + " " + to;
  }
}
