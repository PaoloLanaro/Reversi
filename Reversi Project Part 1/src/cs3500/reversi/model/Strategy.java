package cs3500.reversi.model;

import java.util.Optional;

public interface Strategy {
  Optional<RowCol> chooseMove(ReadOnlyReversi model, DiscColor forWhom);
}
