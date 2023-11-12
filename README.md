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

# Changes for part 2
We chose to add BasicReversi.getCellColor(int, int); as the handout required us to add this. \
We also added BasicReversi.getSideLength(); for the same reason. \
We added a copy constructor to BasicReversi so that copying a BasicReversi object for any reason \
was done correctly. We could use this copy constructor to add another layer of protection against \
the view mutating the model in any way. \
We added the getColFromCell and getRowFromCell to the ReadOnlyReversi. This was added to help 
with implementation of mouse clicks on the gui.\
We added a RowCol class to the model package to help with implementing the MaxPointStrategy.\
We added and kept strategies in the same package as the model, because we did not want to expose
the protected BasicReversi constructor that takes a List<List<Cell>> otherBoard.

# GUI
Made an IView interface that has setVisible, setListener, and refresh methods. The setListener 
and refresh methods will be used in the controller which is in the next assignment.\
We made a wrapper Hexagon class around Polygon. \
We made a ReversiPanel class which extends JPanel and has Hexagon objects in it so that we could 
create the hexagon board and draw the individual hexagons. \
We made a ReversiGraphicsView which extends JFrame and implements the IView interface. It is 
constructed with a ReadOnly Reversi and utilizes the ReversiPanel class we created above.

# Key Map
ENTER - key press will confirm and make a move.\
BACK-SPACE - key press will pass a turn.\
U - key bind will direct player move upper left.\
I - Key bind will direct player move upper right.\
H - Key bind will direct player move left.\
K - Key bind will direct player move right.\
N - Key bind will direct player move bottom left.\
M - Key bind will direct player move bottom right.

# Mouse Controls
Mouse left click highlights selected hexagon cyan. 

