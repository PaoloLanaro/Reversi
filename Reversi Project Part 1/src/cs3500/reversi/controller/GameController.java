package cs3500.reversi.controller;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import cs3500.reversi.model.MutableReversi;
import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.view.IView;

/**
 * Controller class for creating a controller for the game.
 */
public class GameController {

  private MutableReversi model;
  private IView view;

  public GameController(MutableReversi model) {
    this.model = model;
  }

  public void setView(IView view) {
    this.view = view;

    configureKeyboardListener();
    configureMouseListener();
  }

  private void configureKeyboardListener() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();

    keyPresses.put(KeyEvent.VK_ENTER, () -> {
              Point2D point = view.getSelectedHexagon();
            }
    );

    keyPresses.put(KeyEvent.VK_BACK_SPACE, () -> {
              model.passTurn();
            }
    );

    keyPresses.put(KeyEvent.VK_U, () -> {
              view.moveSelection(); // todo i got no idea if this makes sense
            }
    );

    keyPresses.put(KeyEvent.VK_I, () -> {
            }
    );

    keyPresses.put(KeyEvent.VK_H, () -> {
            }
    );

    keyPresses.put(KeyEvent.VK_K, () -> {
            }
    );

    keyPresses.put(KeyEvent.VK_N, () -> {
            }
    );

    keyPresses.put(KeyEvent.VK_M, () -> {
            }
    );

  }

  private void configureMouseListener() {
    // todo actually implement the mouse listener class and hook it up
  }
  // empty, waiting for future assignments
}
