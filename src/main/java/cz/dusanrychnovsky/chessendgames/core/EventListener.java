package cz.dusanrychnovsky.chessendgames.core;

public interface EventListener {
  EventListener onSituationChanged(Situation situation);
  EventListener onStatusChanged(Status status);
}
