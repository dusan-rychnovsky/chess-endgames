package cz.dusanrychnovsky.chessendgames;

import cz.dusanrychnovsky.chessendgames.core.Move;
import cz.dusanrychnovsky.chessendgames.core.Situation;

// TODO: rename?
public interface GatherMove {
  Move gatherMove(Situation situation);
}
