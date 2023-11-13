package cs3500.reversi.model;

import java.util.Optional;

public interface Strategy {
  //we originally had this take a readonlyreversi to prevent mutation, but if it doesn't get a
  // mutable board how will the players strategy actually make a ove on the board.
//  Optional<RowCol> chooseMove(ReadOnlyReversi model, DiscColor forWhom);
  Optional<RowCol> chooseMove(BasicReversi model, DiscColor forWhom);

}
