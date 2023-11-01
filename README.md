# Reversi-Project
Two-player Reversi game with a graphical interface. This project implements a basic version of the 
Reversi game played on a hexagonal grid. Players can make moves, pass turns, and check game state on
a hex grid with a customizable board size.

# Overview
The Reversi Game aims to provide a playable and extensive platform for the classic Reversi game. \
Players take turns making moves on a hexagonal grid. The game enforces the rules of Reversi, 
allowing players to flip their opponent's discs by placing their own discs adjacent to them. \
The game ends when there are two consecutive passes, or if there are no more valid moves. 
    
# Quick start
BasicReversi game = new BasicReversi(6); \
game.makeMove(2, 3); \
String currentPlayerTurn = game.getTurn(); 

# Key components
BasicReversi: Represents the game model class implementing the game logic. \
Cell: Represents a cell on the hexagonal grid, including its color and neighbors. \
DiscColor: Enum representing the colors of the discs: BLACK, WHITE, or EMPTY.

# Key subcomponents
makeMove(int row, int col): Allows a player to make a move on the grid. \
passTurn(): Allows a player to pass their turn. \
getValidMoves(DiscColor color): Returns a list of valid moves for a specific player. \
isValidMove(int row, int col): Checks if a move is valid at a specific row and column.

# Source Organization
src/cs3500/reversi/model/BasicReversi.java: Contains the implementation of the BasicReversi class 
                                            and related logic. \
src/cs3500/reversi/model/Cell.java: Contains the Cell class representing a hexagonal grid cell. \
src/cs3500/reversi/model/DiscColor.java: Enum defining the possible colors of the discs.
    