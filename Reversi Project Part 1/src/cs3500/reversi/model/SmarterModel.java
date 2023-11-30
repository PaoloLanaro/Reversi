package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.reversi.controller.ModelFeatures;

public class SmarterModel implements ISmarterModel {

  MutableReversi delegate;
  List<ModelFeatures> featuresListeners;

  public SmarterModel(MutableReversi delegate) {
    this.delegate = delegate;
    featuresListeners = new ArrayList<>();
  }

  @Override
  public void addFeaturesListener(ModelFeatures featuresListener) {
    featuresListeners.add(featuresListener);
  }

  private void notifyListeners() {
    if (!featuresListeners.isEmpty()) {
      for (ModelFeatures listener : featuresListeners) {
        listener.refresh();
      }
    }
  }

  @Override
  public void passTurn() throws IllegalStateException {
    delegate.passTurn();
    notifyListeners();
  }

  @Override
  public void makeMove(int row, int col) throws IllegalStateException, IllegalArgumentException {
    delegate.makeMove(row, col);
    notifyListeners();
  }

  @Override
  public void startGame() throws IllegalStateException {
    delegate.startGame();
    notifyListeners();
  }

  @Override
  public List<List<Cell>> getBoard() {
    return delegate.getBoard();
  }

  @Override
  public boolean isGameOver() {
    return delegate.isGameOver();
  }

  @Override
  public Map<DiscColor, Integer> getScore() {
    return delegate.getScore();
  }

  @Override
  public String getWinner() {
    return delegate.getWinner();
  }

  @Override
  public List<Cell> getValidMoves(DiscColor color) {
    return delegate.getValidMoves(color);
  }

  @Override
  public String getTurn() {
    return delegate.getTurn();
  }

  @Override
  public boolean isValidMove(int row, int col) {
    return delegate.isValidMove(row, col);
  }

  @Override
  public int getSideLength() {
    return delegate.getSideLength();
  }

  @Override
  public DiscColor getCellColor(int row, int col) {
    return delegate.getCellColor(row, col);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return delegate.getCellAt(row, col);
  }

  @Override
  public int getColFromCell(Cell cell) {
    return delegate.getColFromCell(cell);
  }

  @Override
  public int getRowFromCell(Cell cell) {
    return delegate.getRowFromCell(cell);
  }
}
