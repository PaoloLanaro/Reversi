package cs3500.reversi.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.Player;

/**
 * The {@code IView} interface defines methods for Reversi game views, including setting
 * action listeners, controlling visibility, and refreshing the display.
 * Implementing classes are expected to work with the Reversi game model and controller.
 *
 * @see java.awt.event.ActionListener
 */
public interface IView {

  /**
   * Sets the action listener for this view. The action listener is notified of user
   * interactions, allowing the view to respond accordingly.
   *
   * @param actionListener the ActionListener to set for this view
   */
  void setListener(ActionListener actionListener);


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
   * Returns the hexagon coordinate that is selected by the view.
   *
   * @return the {@link RowCol} of the currently highlighted hexagon.
   * @throws IllegalStateException if no cell is currently selected.
   */
  RowCol getCurrentlySelectedHexagon() throws IllegalStateException;

  /**
   * Adds some key listener to this view in order for the controller to hook up with this view.
   *
   * @param listener a {@link KeyListener} that contains stuff.
   */
  void addKeyListener(KeyListener listener);

  /**
   * Shows an error message for some player.
   *
   * @param player the {@link Player} for which to show the error message for
   */
  void showErrorMessage(Player player);
}
