package cs3500.reversi.model;

import cs3500.reversi.controller.ModelFeatures;

public interface ISmarterModel extends MutableReversi {
  void addFeaturesListener(ModelFeatures featuresListener);
}
