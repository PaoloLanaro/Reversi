package cs3500.reversi.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.model.ReadOnlyReversi;

/**
 * The GUI Frame for a Square Reversi game.
 * Should be used with square-style models.
 */
public class SquareReversiFrame extends JFrame implements IView {

  private final SquareReversiPanel gamePanel;
  private final ReadOnlyReversi model;

  /**
   * This constructor creates a {@link SquareReversiFrame} object.
   * It should be used only with a {@link ReadOnlyReversi} model to avoid mutability.
   *
   * @param model the {@link ReadOnlyReversi} model for the Reversi game.
   */
  public SquareReversiFrame(ReadOnlyReversi model) {
    super();
    this.setTitle("Square Reversi game!");
    this.setLocation(390, 85);
    this.setSize(new Dimension(500, 524));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    gamePanel = new SquareReversiPanel(model);
    this.model = model;
    this.add(gamePanel);
  }

  @Override
  public void addFeaturesListener(ViewFeatures featuresListener) {
    gamePanel.addFeaturesListener(featuresListener);
    //for controller.
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
  }

  @Override
  public void refresh() {
    int blackScore = model.getScore().get(DiscColor.BLACK);
    int whiteScore = model.getScore().get(DiscColor.WHITE);
    String currentScore = (whiteScore > blackScore ? whiteScore + "-" + blackScore :
            blackScore + "-" + whiteScore);
    this.setTitle("Reversi player " + model.getTurn() + "'s turn. Current Score: " + currentScore);
    this.repaint();
    if (model.isGameOver()) {
      this.setTitle(model.getWinner() + " Final score: " + currentScore);
    }
  }

  @Override
  public void showErrorMessage(String message, Player player) {
    String playerMsg;
    String punctuationRegex = "\\p{Punct}";
    if (String.valueOf(message.charAt(message.trim().length() - 1)).matches(punctuationRegex)) {
      playerMsg = " For player: " + player.getColor();
    } else {
      playerMsg = " for player: " + player.getColor();
    }
    JOptionPane.showMessageDialog(null, message + playerMsg);
  }
}
