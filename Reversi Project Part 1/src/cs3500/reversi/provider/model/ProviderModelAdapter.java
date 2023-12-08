package cs3500.reversi.provider.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Cell;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.SmarterModel;
import cs3500.reversi.model.DiscColor;

/**
 * The adapter class for the provider's model class to our model class.
 */
public class ProviderModelAdapter extends BasicReversi implements ReversiModel {

  private final SmarterModel delegate;
  //  private final GenericPlayer player;
  //  private final GenericPlayer player1;
  List<GenericPlayer> player;

  /**
   * This constructor creates an adapter class for the model to model adaption of the
   * provider's model to our model.
   *
   * @param initSize the size of the side length of the board.
   */
  public ProviderModelAdapter(int initSize) {
    super(initSize);
    BasicReversi constructor = new BasicReversi(initSize);
    delegate = new SmarterModel(constructor);
    player = new ArrayList<>();
    //    player.add(new ProviderPlayerAdapter())
    //    player.add(new ProviderPlayerAdapter())
  }

  @Override
  public void startGame(List<GenericPlayer> playerNames)
          throws IllegalArgumentException, IllegalStateException {
    delegate.startGame();
  }

  @Override
  public boolean validMove(IHex hex, TileType color) throws IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public boolean legalMoveAvailable(GenericPlayer player)
          throws IllegalArgumentException, IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public void addFeatureListener(ModelFeatures features) {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public boolean gameOver() throws IllegalStateException {
    return delegate.isGameOver();
  }

  @Override
  public GenericPlayer determineWinner() throws IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public void playerTurn(GenericPlayer player, boolean pass, IHex hex, TileType currentPlayer)
          throws IllegalStateException, IllegalArgumentException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public void switchTurn() throws IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public GenericPlayer getCurrentPlayer() throws IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public List<IHex> getPlayerTiles(GenericPlayer player) throws IllegalStateException,
          IllegalArgumentException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public List<IHex> getPlayerTiles(TileType color) throws IllegalStateException,
          IllegalArgumentException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public int getMaxRowLength() throws IllegalStateException {
    return delegate.getSideLength() * 2 - 1;
  }

  @Override
  public List<List<IHex>> getGridCopy() {
    int underlyingBoardSize = delegate.getBoard().size();
    List<List<Cell>> underlyingBoard = delegate.getBoard();

    List<List<IHex>> returnCopy = new ArrayList<>();

    for (int row = 0; row < underlyingBoardSize; row++) {
      returnCopy.add(new ArrayList<>());
      for (int col = 0; col < underlyingBoardSize; col++) {
        Cell currCell = underlyingBoard.get(row).get(col);
        if (currCell == null) {
          continue;
        }
        List<IHex> currentList = returnCopy.get(row);
        currentList.add(new ProviderHexToCellAdapter(currCell, new RowCol(row, col)));
      }
    }
    return returnCopy;
  }

  @Override
  public IHex getSpecificHex(int q, int r, int s) {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public HashMap<IHex, Integer> legalMoveAvailableList(GenericPlayer player)
          throws IllegalArgumentException, IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public int getPassCount() {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  protected static DiscColor tileTypeToDiscColor(TileType color) {
    return color == TileType.BLACK ? DiscColor.BLACK : DiscColor.WHITE;
  }
}
