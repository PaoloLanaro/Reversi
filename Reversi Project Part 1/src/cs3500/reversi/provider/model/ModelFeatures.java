package cs3500.reversi.provider.model;

/**
 * Interface to ensure that our model features have
 * uniformity. This interface represents our model
 * status design.
 */
public interface ModelFeatures {

  void updateOnPlayerSwitch(TileType color);

}
