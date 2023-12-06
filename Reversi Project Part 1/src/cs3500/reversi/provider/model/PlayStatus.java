package cs3500.reversi.provider.model;

/**
 * Represents 4 different states a player can be. It
 * will be updated based on what actions the player makes.
 * It is used in the panel to alter the background.
 */
public enum PlayStatus {
  PASS,
  MOVE,
  DEFAULT,
  FORCEDPASS;
}
