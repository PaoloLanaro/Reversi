package cs3500.reversi.provider.model;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.Cell;
import cs3500.reversi.model.RowCol;
import cs3500.reversi.model.SmarterModel;
import cs3500.reversi.model.DiscColor;

public class ProviderModelAdapter implements ReversiModel {

  private final SmarterModel delegate;
  private int passCounter;
  //  private final GenericPlayer player;
//  private final GenericPlayer player1;
  List<GenericPlayer> player;
  Map<RowCol, RowCol> qrToRowCol;

  public ProviderModelAdapter(int initSize) {
    passCounter = 0;
    BasicReversi constructor = new BasicReversi(initSize);
    delegate = new SmarterModel(constructor);
    player = new ArrayList<>();
//    player.add(new ProviderPlayerAdapter())
//    player.add(new ProviderPlayerAdapter())
  }

  @Override
  public void startGame(List<GenericPlayer> playerNames) throws IllegalArgumentException, IllegalStateException {
    throw new UnsupportedOperationException("Failed the method invocation");
  }

  @Override
  public boolean validMove(IHex hex, TileType color) throws IllegalStateException {
    return false;
  }

  @Override
  public boolean legalMoveAvailable(GenericPlayer player) throws IllegalArgumentException, IllegalStateException {
    return false;
  }

  @Override
  public void addFeatureListener(ModelFeatures features) {

  }

  @Override
  public boolean gameOver() throws IllegalStateException {
    return false;
  }

  @Override
  public GenericPlayer determineWinner() throws IllegalStateException {
    return null;
  }

  @Override
  public void playerTurn(GenericPlayer player, boolean pass, IHex hex, TileType currentPlayer) throws IllegalStateException, IllegalArgumentException {

  }

  @Override
  public void switchTurn() throws IllegalStateException {

  }

  @Override
  public GenericPlayer getCurrentPlayer() throws IllegalStateException {
    return null;
  }

  @Override
  public List<IHex> getPlayerTiles(GenericPlayer player) throws IllegalStateException, IllegalArgumentException {
    return null;
  }

  @Override
  public List<IHex> getPlayerTiles(TileType color) throws IllegalStateException, IllegalArgumentException {
    return null;
  }

  @Override
  public int getMaxRowLength() throws IllegalStateException {
    return 0;
  }

  @Override
  public List<List<IHex>> getGridCopy() {
    return null;
  }

  @Override
  public IHex getSpecificHex(int q, int r, int s) {
    return null;
  }

  @Override
  public HashMap<IHex, Integer> legalMoveAvailableList(GenericPlayer player) throws IllegalArgumentException, IllegalStateException, IOException {
    return null;
  }

  @Override
  public int getPassCount() {
    return 0;
  }

//  @Override
//  public void startGame(List<GenericPlayer> playerNames) throws IllegalArgumentException, IllegalStateException {
//    delegate.startGame();
//    int i = delegate.getSideLength() - 1; // x, x will be center where x is this expression
//    Cell middleCell = delegate.getBoard().get(i).get(i);
//    mapRowColToQR();
//  }
//
//  private void mapRowColToQR() {
//    // todo something similar to onion loop where we map the current pos to q and r values
//  }
//
//  @Override
//  public boolean validMove(IHex hex, TileType color) throws IllegalStateException {
//    String coords = hex.toStringQRS();
//    Position2D qrCoords = qrsToXY(coords);
//    delegate.isValidMove((int) qrCoords.getX(), (int) qrCoords.getY());
//    return false;
//  }
//
//  private Position2D qrsToXY(String qrsCoords) {
//    int q = qrsCoords.charAt(0); // q is col
//    int r = qrsCoords.charAt(1); // r
//    //qrsCoords.charAt(2);
//    // s maybe don't need
//    int tempRow = r + (q + (q & 1)) / 2;
//    // maybe add board.size/2 to row and col after translating q and r into row col (0,0)
//    // centered board.
//    int actualRow = tempRow + (delegate.getSideLength() - 1);
//    int actualCol = q + (delegate.getSideLength() - 1);
//    return new Position2D(actualRow, actualCol);
//    // todo convert to qrs
//  }
//
//  @Override
//  public boolean legalMoveAvailable(GenericPlayer player) throws IllegalArgumentException,
//          IllegalStateException {
//    return delegate.getValidMoves(DiscColor.WHITE).size() != 0
//            && (delegate.getValidMoves(DiscColor.BLACK).size() != 0);
//  }
//
//  @Override
//  public boolean gameOver() throws IllegalStateException {
//    return delegate.isGameOver();
//  }
//
//  @Override
//  public GenericPlayer determineWinner() throws IllegalStateException {
//    delegate.getWinner(); // todo convert this to a generic player?
//
//    return null;
//  }
//
//  @Override
//  public void playerTurn(GenericPlayer player, boolean pass, IHex hex, TileType currentPlayer) throws IllegalStateException, IllegalArgumentException {
//    if (pass) {
//      passCounter++;
//      delegate.passTurn();
//    }
//    hex.toStringQRS();
//
//    delegate.makeMove();
//  }
//
//  @Override
//  public void switchTurn() throws IllegalStateException {
//    delegate.passTurn();
//    passCounter++;
//  }
//
//  @Override
//  public GenericPlayer getCurrentPlayer() throws IllegalStateException {
//    delegate.getTurn(); // todo convert this to a generic player
//
//    return null;
//  }
//
//  @Override
//  public List<IHex> getPlayerTiles(GenericPlayer player) throws IllegalStateException,
//          IllegalArgumentException {
//    DiscColor colorToCheck = tileTypeToDiscColor(player.getTileColor());
//    List<IHex> listOfPlayerTiles = new ArrayList<>();
//
//    List<List<Cell>> underlyingBoard = delegate.getBoard();
//    for (int row = 0; row < underlyingBoard.size(); row++) {
//      for (int col = 0; col < underlyingBoard.size(); col++) {
//        if (underlyingBoard.get(row).get(col) == null) {
//          continue;
//        }
//        Cell cell = underlyingBoard.get(row).get(col);
//        DiscColor cellColor = underlyingBoard.get(row).get(col).getColor();
//        if (cellColor == colorToCheck) {
//          IHex playerCell = convertCellToHex(cell);
//          listOfPlayerTiles.add(playerCell);
//        }
//      }
//    }
//    return listOfPlayerTiles;
//  }
//
//  @Override
//  public List<IHex> getPlayerTiles(TileType color) throws IllegalStateException,
//          IllegalArgumentException {
//
//    return null;
//  }
//
//  @Override
//  public int getMaxRowLength() throws IllegalStateException {
//    // converts our delegate's side length to a 'max row length'
//    return delegate.getSideLength() * 2 - 1;
//  }
//
//  @Override
//  public List<List<IHex>> getGridCopy() {
//    delegate.getBoard(); // todo convert this to a list of hex
//    return null;
//  }
//
//  @Override
//  public IHex getSpecificHex(int q, int r, int s) {
//    return null;
//  }
//
//  private DiscColor tileTypeToDiscColor(TileType color) {
//    return color == TileType.BLACK ? DiscColor.BLACK : DiscColor.WHITE;
//  }
//
//  @Override
//  public HashMap<IHex, Integer> legalMoveAvailableList(GenericPlayer player) throws IllegalArgumentException, IllegalStateException, IOException {
//    delegate.getValidMoves(tileTypeToDiscColor(player.getTileColor()));
//    // todo BR.getValidMoves() linked with getting the score for that move?
//    return null;
//  }
//
//  @Override
//  public void addFeatureListener(ModelFeatures features) {
//    // todo im baffled. why use a features listener when no controller.
////    delegate.addFeaturesListener();
//  }
//
//  @Override
//  public int getPassCount() {
//    return passCounter;
//  }
}
