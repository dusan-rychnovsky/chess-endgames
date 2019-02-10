package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.*;
import java.io.*;
import java.util.List;

public class PrintingListener implements EventListener {

  private final Writer writer;

  public PrintingListener(OutputStream out) {
    this.writer = new BufferedWriter(new OutputStreamWriter(out));
  }

  @Override
  public EventListener onSituationChanged(Situation situation) {
    writeLine("Moves: " + situation.getPlayer());
    for (int row : List.of(8, 7, 6, 5, 4, 3, 2, 1)) {
      StringBuilder sb = new StringBuilder(row + " |");
      for (char column : List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')) {
        Position pos = Position.get(column, row);
        sb.append(
          " " +
          situation.getPiece(pos)
            .map(this::print)
            .orElse(".")
        );
      }
      writeLine(sb.toString());
    }
    writeLine("--|----------------");
    writeLine("  | A B C D E F G H");
    writeLine("");
    flush();
    return this;
  }

  private String print(Piece piece) {
    return piece.getType().toString().substring(0, 1);
  }

  @Override
  public EventListener onStatusChanged(Status status) {
    if (status instanceof Win) {
      writeLine("WIN " + ((Win) status).getPlayer());
    }
    else if (status instanceof Draw) {
      writeLine("DRAW");
    }
    else if (!(status instanceof InProgress)) {
      throw new IllegalArgumentException("Unexpected Status: " + status);
    }
    flush();
    return this;
  }

  private void writeLine(String line) {
    try {
      writer.write(line);
      writer.write("\n");
    }
    catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }

  private void flush() {
    try {
      writer.flush();
    }
    catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }
}
