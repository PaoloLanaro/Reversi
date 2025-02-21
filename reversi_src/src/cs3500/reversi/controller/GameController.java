package cs3500.reversi.controller;

import java.util.Objects;
import java.util.Optional;

import cs3500.reversi.model.ISmarterModel;
import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.AIPlayer;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.view.IView;


/**
 * Manages the interaction between an {@link ISmarterModel} and an {@link IView} for a Reversi game.
 */
public class GameController implements ReversiController, ViewFeatures, ModelFeatures {

  private final MutableReversi model;
  private final IView view;
  private final Player player;

  /**
   * Constructs a controller for a classic game of Reversi.
   *
   * @param model  the model to be used with the controller.
   * @param player the player assigned to this controller.
   * @param view   the view for displaying the game.
   */
  public GameController(MutableReversi model, Player player, IView view) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(player);
    Objects.requireNonNull(view);
    this.model = model;
    this.view = view;
    this.player = player;
    view.addFeaturesListener(this);
    model.addFeaturesListener(this);
  }

  @Override
  public void playGame() {
    view.refresh();
    view.setVisible(true);
  }

  @Override
  public void makeMove(RowCol coordinate) {
    if (!Objects.equals(model.getTurn().toLowerCase(), player.getColor().toLowerCase())) {
      view.showErrorMessage("Cannot make move. It is not currently your turn", player);
    } else {
      try {
        model.makeMove(coordinate.getRow(), coordinate.getCol());
      } catch (Exception e) {
        view.showErrorMessage(e.getMessage(), player);
      }
    }
    playGame();
  }

  @Override
  public void passTurn() {
    if (!Objects.equals(model.getTurn().toLowerCase(), player.getColor().toLowerCase())) {
      view.showErrorMessage("Cannot pass turn. It is not currently your turn", player);
    } else {
      try {
        model.passTurn();
        view.refresh();
      } catch (Exception e) {
        view.showErrorMessage(e.getMessage(), player);
      }
    }
  }

  @Override
  public void enableHint(int row, int col) {
    System.out.println(model.getScoreFor(row, col));
  }

  @Override
  public void pushError(String errorMessage) {
    view.showErrorMessage(errorMessage, player);
  }

  @Override
  public void refresh() {
    this.playGame();
    if (player instanceof AIPlayer) {
      if (model.getTurn().equals(player.getColor())) {
        Optional<RowCol> move = player.getMove(model);
        if (move.isPresent()) {
          if (move.get().getRow() == -1) {
            model.passTurn();
            return;
          }
        }
        move.ifPresent(rowCol -> model.makeMove(rowCol.getRow(), rowCol.getCol()));
      }
    }
  }

}
