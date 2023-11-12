package cs3500.reversi.view;

import java.awt.event.ActionListener;

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
  public void setListener(ActionListener actionListener);


  /**
   * Sets the visibility of this view. If set to {@code true}, the view is made visible,
   * otherwise, it is hidden.
   *
   * @param visible {@code true} to make the view visible, {@code false} to hide it
   */
  public void setVisible(boolean visible);

  /**
   * Refreshes the display of the view. This method is typically called to update the
   * view after changes in the game state.
   */
  public void refresh();

}
