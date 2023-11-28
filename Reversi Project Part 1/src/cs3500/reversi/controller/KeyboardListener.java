package cs3500.reversi.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * This class represents a keyboard listener. It is configurable by the controller that
 * instantiates it.
 * <p>
 * This listener keeps one map for key pressed actions.
 * Each map stores a key mapping. A key mapping is a pair
 * (keystroke,code to be executed with that keystroke)
 * The latter part of that pair is Runnable.
 * <p>
 * This class implements the KeyListener interface, so that its object can be used as a
 * valid keylistener for Java Swing.
 */
public class KeyboardListener implements KeyListener {
  private Map<Integer, Runnable> keyPressedMap;

  /**
   * Set the map for key pressed events. Key pressed events in Java Swing are integer codes
   *
   * @param validKeys the keys to map the presses and other keyboard style inputs to.
   */
  public void setKeyPressedMap(Map<Integer, Runnable> validKeys) {
    keyPressedMap = validKeys;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    throw new IllegalStateException(
            "If this happens check MVC GUI, otherwise change error message"
    );
  }

  @Override
  public void keyPressed(KeyEvent event) {
    if (keyPressedMap.containsKey(event.getKeyCode()))
      keyPressedMap.get(event.getKeyCode()).run();
  }

  @Override
  public void keyReleased(KeyEvent e) {
    throw new IllegalStateException(
            "If this happens check MVC GUI, otherwise change error message"
    );
  }
}
