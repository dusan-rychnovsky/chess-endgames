package cz.dusanrychnovsky.chessendgames;

import static cz.dusanrychnovsky.chessendgames.Column.*;
import static cz.dusanrychnovsky.chessendgames.Row.*;
import static cz.dusanrychnovsky.chessendgames.StringExtensions.capitalize;

public enum Position {
  F5(CF, R5), E6(CE, R6), D4(CD, R4), D3(CD, R3);

  private final Column column;
  private final Row row;

  Position(Column column, Row row) {
    this.column = column;
    this.row = row;
  }

  public static Position parse(String value) {
    return valueOf(capitalize(value));
  }

  public static Position get(Column column, Row row) {
    return valueOf(column.toString() + row.toString());
  }

  public Column column() {
    return this.column;
  }

  public Row row() {
    return this.row;
  }
}
