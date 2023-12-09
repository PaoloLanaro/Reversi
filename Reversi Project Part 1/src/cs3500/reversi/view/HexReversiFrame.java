package cs3500.reversi.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.reversi.controller.ViewFeatures;
import cs3500.reversi.model.DiscColor;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.players.Player;

/**
 * A graphical user interface representation of a Reversi game.
 * This representation is to be used with hexagonal boards and for hexagonal GUI's.
 */
public class HexReversiFrame extends JFrame implements IView {

  private final HexReversiPanel gamePanel;
  private final ReadOnlyReversi model;

  /**
   * Constructs a {@link HexReversiFrame} object with a game panel.
   *
   * @param model the {@link ReadOnlyReversi} model for the Reversi game.
   */
  public HexReversiFrame(ReadOnlyReversi model) {
    super();
    this.setTitle("Hex Reversi game!");
    this.setLocation(390, 85);
    this.setSize(new Dimension(500, 500));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    gamePanel = new HintDecorator(model);
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
