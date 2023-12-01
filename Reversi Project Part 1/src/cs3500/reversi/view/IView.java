package cs3500.reversi.view;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.players.Player;

/**
 * The {@code IView} interface defines methods for Reversi game views, encompassing
 * actions like setting listeners, managing visibility, and refreshing the display.
 * Implementing classes are designed to interact with the Reversi game model and controller.
 *
 * @see java.awt.event.ActionListener
 */
public interface IView {

  /**
   * Sets the action listener for this view. The action listener is notified of user
   * interactions, allowing the view to respond accordingly.
   *
   * @param featuresListener the ActionListener to set for this view
   */
  void addFeaturesListener(ViewFeatures featuresListener);


  /**
   * Sets the visibility of this view. If set to {@code true}, the view is made visible,
   * otherwise, it is hidden.
   *
   * @param visible {@code true} to make the view visible, {@code false} to hide it
   */
  void setVisible(boolean visible);

  /**
   * Refreshes the display of the view. This method is typically called to update the
   * view after changes in the game state.
   */
  void refresh();

  /**
   * Shows an error message for some player.
   *
   * @param player the {@link Player} for which to show the error message for.
   *               This will be formatted as follows: " for player: player color",
   *               and will attempt to capitalize the 'f' if the last character in
   *               {@code message} is punctuation.
   * @param message the message that should be put before the player identification.
   */
  void showErrorMessage(String message, Player player);
}
