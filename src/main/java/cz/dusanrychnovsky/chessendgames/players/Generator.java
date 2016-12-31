package cz.dusanrychnovsky.chessendgames.players;

import cz.dusanrychnovsky.chessendgames.core.Color;
import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Piece;
import cz.dusanrychnovsky.chessendgames.core.Position;
import cz.dusanrychnovsky.chessendgames.core.Situation;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static cz.dusanrychnovsky.chessendgames.core.Color.WHITE;
import static cz.dusanrychnovsky.utils.Assertions.check;

@Slf4j
public class Generator {

  private static final int INFINITE = Integer.MAX_VALUE;

  public static void main(String[] args) {

    if (args.length == 0) {
      System.err.println("USAGE: [FILE-PATH] - file to store generated database into");
      System.exit(-1);
    }

    Generator generator = new Generator();
    Database database = generator.generateDb(
      new Situations().generateAll(),
      WHITE
    );

    File file = new File(args[0]);
    database.writeTo(file);

    log.info("DONE");
  }

  /**
   * Analyzes the given situations from the perspective of the given player
   * and returns the most convenient moves for that player in those situations
   * as a {@link Database}.
   *
   * The analysis is done using the MINIMAX algorithm.
   */
  public Database generateDb(Set<Situation> situations, Color color) {
    log.info("Total situations: " + situations.size());

    Map<Situation, Record> mapping = new HashMap<>();

    int totalResolved = 0, iteration = 0;
    while (true) {
      log.info("Iteration no. " + (++iteration));

      int numResolved = goThroughSituations(color, situations, mapping);
      totalResolved += numResolved;

      log.info("Resolved: " + numResolved);
      log.info("Total Resolved: " + totalResolved);
      log.info("Remaining: " + situations.size());

      if (numResolved == 0) {
        break;
      }
    }

    return toDatabase(color, mapping);
  }

  /**
   * Extracts moves for the given player in the form of a {@link Database}.
   */
  private Database toDatabase(Color color, Map<Situation, Record> mapping) {
    Database.Builder builder = Database.builder();
    mapping.entrySet().forEach(entry -> {
      Situation situation = entry.getKey();
      if (color == situation.getCurrentColor()) {
        builder.addMapping(situation, entry.getValue().getMove());
      }
    });
    return builder.build();
  }

  /**
   * Implements a single wave of MINIMAX analysis. Updates the given mapping.
   * Returns number of situations that have been successfully evaluated in
   * this wave.
   */
  private int goThroughSituations(
      Color color, Set<Situation> situations, Map<Situation, Record> mapping) {

    int numResolved = 0;
    Iterator<Situation> situationIt = situations.iterator();
    while (situationIt.hasNext()) {
      Situation situation = situationIt.next();

      if (tryToResolveSituation(color, situation, mapping)) {
        situationIt.remove();
        numResolved++;
      }
    }

    return numResolved;
  }

  /**
   * Given a yet unevaluated situation and a partial mapping, if the situation
   * can be evaluated based on the mapping, updates the mapping and returns true,
   * otherwise returns false.
   */
  private boolean tryToResolveSituation(
      Color color, Situation situation, Map<Situation, Record> mapping) {

    check(!mapping.containsKey(situation));

    if (situation.isFinal()) {
      if (situation.isWonBy(color)) {
        mapping.put(situation, new Record(0, null));
      }
      else {
        mapping.put(situation, new Record(INFINITE, null));
      }
      return true;
    }

    SelectMove strategy;
    if (situation.getCurrentColor() == color) {
      strategy = new SelectMin();
    }
    else {
      strategy = new SelectMax();
    }

    for (Piece piece : situation.getCurrentPlayersPieces()) {
      Position pos = situation.getPosition(piece).get();

      for (Move move : piece.getType().listAllMovesFromPosition(pos)) {
        if (!situation.isValidMove(move)) {
          continue;
        }

        Situation nextSituation = situation.applyMove(move);

        Optional<Record> current = Optional.empty();
        Record record = mapping.get(nextSituation);
        if (record != null) {
          current = Optional.of(new Record(record.getNumMoves(), move));
        }

        strategy.consume(current);
      }
    }

    Optional<Record> result = strategy.getResult();
    if (result.isPresent()) {
      mapping.put(situation, result.get());
      return true;
    }
    else {
      return false;
    }
  }

  private interface SelectMove {
    void consume(Optional<Record> acc);
    Optional<Record> getResult();
  }

  /**
   * Strategy for the defending player - select moves to maximize the number
   * of moves required to end the match.
   */
  public static class SelectMax implements SelectMove {

    // after all neighbours are consumed, this will contain false if there
    // is at least one unmarked neighbour
    private boolean resultValid = true;

    // after all neighbours are consumed, assuming there was at least one
    // and provided resultValid is true, this will contain the neighbour with
    // the highest numMoves
    private Record acc;

    @Override
    public void consume(Optional<Record> current) {
      // neighbouring situation with the largest numMoves can only be determined
      // after all neighbours have been marked
      if (resultValid) {
        if (current.isPresent()) {
          Record record = current.get();
          if (acc == null || record.getNumMoves() > acc.getNumMoves()) {
            acc = record;
          }
        } else {
          resultValid = false;
        }
      }
    }

    @Override
    public Optional<Record> getResult() {
      if (resultValid && acc != null) {
        return Optional.of(
          new Record(inc(acc.getNumMoves()), acc.getMove())
        );
      }
      else {
        return Optional.empty();
      }
    }
  }

  /**
   * Strategy for the attacking player - select moves to minimize the number
   * of moves required to win the match.
   */
  public static class SelectMin implements SelectMove {

    // after all neighbours are consumed, this will contain the neighbour with
    // the lowest finite numMoves (or null if there is none such neighbour)
    private Record acc;

    @Override
    public void consume(Optional<Record> current) {
      // if there already is a neighbouring situation marked with a finite distance,
      // it's safe to ignore any remaining unmarked neighbours - they can't be lower
      // as all lower situations have already been marked at this point
      if (current.isPresent()) {
        Record record = current.get();
        if (record.getNumMoves() != INFINITE) {
          if (acc == null || record.getNumMoves() < acc.getNumMoves()) {
            acc = record;
          }
        }
      }
    }

    @Override
    public Optional<Record> getResult() {
      if (acc != null) {
        return Optional.of(
          new Record(inc(acc.getNumMoves()), acc.getMove())
        );
      }
      else {
        return Optional.empty();
      }
    }
  }

  private static int inc(int numMoves) {
    if (numMoves != INFINITE) {
      return numMoves + 1;
    }
    else {
      return numMoves;
    }
  }

  @Value
  private static class Record {
    int numMoves;
    Move move;
  }
}
