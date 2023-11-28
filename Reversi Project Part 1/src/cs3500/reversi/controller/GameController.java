package cs3500.reversi.controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.players.Player;
import cs3500.reversi.view.IView;

/**
 * Controller class for creating a controller for the game.
 */
public class GameController implements ReversiController {

  private final MutableReversi model;
  private final IView view;
  private final Player player;

  public GameController(MutableReversi model, Player player, IView view) {
    this.model = model;
    this.view = view;
    this.player = player;
  }

//  public void setView(IView view) {
//    this.view = view;
//
//    configureKeyboardListener();
//    configureMouseListener();
//
//    view.setVisible(true);
//  }

  private void configureKeyboardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();

    keyPresses.put(KeyEvent.VK_ENTER, () -> {
              RowCol highlighted = view.getCurrentlySelectedHexagon();
              model.makeMove(highlighted.getRow(), highlighted.getCol());
            }
    );

    keyPresses.put(KeyEvent.VK_BACK_SPACE, () -> {
              model.passTurn();
            }
    );

    KeyboardListener keebListener = new KeyboardListener();
    keebListener.setKeyPressedMap(keyPresses);

    view.addKeyListener(keebListener);
  }

  private void configureMouseListener() {
//    int row, col;
//    row = col = 4;

//    model.makeMove(row, col);
    // todo actually implement the mouse listener class and hook it up

    view.getCurrentlySelectedHexagon();
    view.refresh();
//    view.setListener();
  }

  @Override
  public void playGame() {
    view.refresh();
    view.setVisible(true);
  }

  @Override
  public void handleCellClick(int row, int col) {

  }
  
}
