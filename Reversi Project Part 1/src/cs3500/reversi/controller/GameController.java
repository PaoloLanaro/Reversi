package cs3500.reversi.controller;

import java.util.Objects;
import java.util.Optional;

import cs3500.reversi.model.ISmarterModel;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.AIPlayer;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.view.IView;

/**
 * Controller class for creating a controller for the game.
 */
public class GameController implements ReversiController, ViewFeatures, ModelFeatures {

  private final ISmarterModel model;
  private final IView view;
  private final Player player;

  /**
   * The controller for the reversi game. This constructor constructs a classic game of reversi
   * based on the input params.
   *
   * @param model  the model with which the player wants to use the controller.
   * @param player the player to play on this controller.
   * @param view   the view to use to display the game.
   */
  public GameController(ISmarterModel model, Player player, IView view) {
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
        view.refresh();
      } catch (Exception e) {
        view.showErrorMessage(e.getMessage(), player);
      }
    }
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
