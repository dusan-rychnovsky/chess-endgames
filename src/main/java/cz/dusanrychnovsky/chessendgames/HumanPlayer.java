package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;

import java.io.*;

public class HumanPlayer implements Player {
  
  private final Color color;
  
  private final BufferedReader reader;
  private final BufferedWriter writer;
  
  public HumanPlayer(Color color, InputStream in, OutputStream out) {
    this.color = color;
    this.reader = new BufferedReader(new InputStreamReader(in));
    this.writer = new BufferedWriter(new OutputStreamWriter(out));
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public Move pickMove(Situation situation) {
    
    write(color + " move: ");
    Move move = parseMove(readLn());
    
    while (move == null || !situation.isValidMove(move)) {
      writeLn("Invalid move!");
      write(color + " move: ");
      move = parseMove(readLn());
    }
    
    return move;
  }
  
  private Move parseMove(String line) {
    try {
      return Move.parseFrom(line);
    }
    catch (IllegalArgumentException ex) {
      return null;
    }
  }
  private String readLn() {
    try {
      return reader.readLine();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
  
  private void writeLn() {
    write("\n");
  }
  
  private void writeLn(String text) {
    write(text + "\n");
  }
  
  private void write(String text) {
    try {
      writer.write(text);
      writer.flush();
    }
    catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
