package cz.dusanrychnovsky.chessendgames;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InProgressTest {

  @Test
  public void print() {
    assertEquals("In progress.", new InProgress().print());
  }
}
