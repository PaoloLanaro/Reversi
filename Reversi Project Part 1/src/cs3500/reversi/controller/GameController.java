package cs3500.reversi.controller;

import java.util.Objects;

import cs3500.reversi.model.ISmarterModel;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.view.IView;

/**
 * Controller class for creating a controller for the game.
 */
public class GameController implements ReversiController, ViewFeatures, ModelFeatures {

//  private final MutableReversi model;
  private final ISmarterModel model;
  private final IView view;
  private final Player player;

  public GameController(ISmarterModel model, Player player, IView view) {
    this.model = model;
    this.view = view;
    this.player = player;
    view.addFeaturesListener(this);
//    ISmarterModel smarterModel = new SmarterModel(model);
    model.addFeaturesListener(this);
//    this.model = smarterModel;
    player.addFeaturesListener(this);
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
    view.refresh();
  }
}
