# Review of Provider Code
Strategy implementations did not implement all methods from the interface they inherit from a 
derived class causing some errors at compile time, so we had to comment out the strategies.
(The strategies were named PlayerAiOne and PlayerAiThree). The strategies also used derived 
class player implementations to function, and because we didn't have one, and the providers 
didn't abstract the code to an interface, we couldn't get the strategies functioning or 
compiling at all. You can try and comment out the strategies, but there will be compile time 
errors, and they are related to the above comments. We still set up a factory method to 
create them within the main class, but it just makes a ProviderPlayerAdapter object because we 
can't use the strategies...

There were a few issues with the provided view implementation. To start the given view was buggy 
because it was  delayed and their board was set up incorrectly. Getting the cell coordinates 
when clicking on their view did not save the right coordinates so when it tries to interact 
with the model it does not update. This prevented us from being able to update the views 
properly after the initial game start. 

Additionally, their view had unnecessary features that prevented the code from compiling like 
switching the background based on the player and an end game pop up. We commented out 
these features as the assignment mentioned that we were ["not responsible for changing the 
provided views to implement those 
features"](https://course.ccs.neu.edu/cs3500/hw_reversi_04_assignment.html). 

Their model was tightly coupled with the GenericPlayer, which is generally bad design. The model 
should not care about players as it's out of the scope of the MVC design pattern for the model 
to know about players. This caused some problems when adapting certain methods from their model 
interface, so we headed Piazza post 1914's advice and simply threw UOE's. 

The provided model interfaces were a ReadOnlyReversiModel and a ReversiModel. The 
ReadOnlyReversiModel has a startGame method which should be in the mutable interface. Also, both 
interfaces are missing multiple functions worth of javadoc near the bottom. We had to manually 
write out this javadoc because we would lose points otherwise, so we titled it "placeholder javadoc 
because this is the provider's code."


## Features that work in our final submission

We got the providers GUI to appear, and can select, albeit very buggy, cells. The GUI accepts 
keyboard and mouse input, and although it doesn't act on those inputs because of various bugs in 
the view implementation and the coupling between classes, it can commit actions on those events. 
We wrote adapters for all the classes we needed, and adapted all the methods we needed. 

## Features that didn't work in our final submission

The whole connection between the model and the GUI doesn't work. The code they provided relies 
heavily on coupled classes, and although we tried fixing some of those issues, there's still 
many issues. We tried our very best to make the features between classes work, but some of the 
ways the classes were laid out made this very difficult. To read more about specific issues we 
had implementing the code, read the first heading of this README.

# Updated command line arguments

The updated command line arguments for this final submission are as follows:
The game must be started with three command line arguments. The first argument is assumed to be 
a good integer and will create a reversi board with side lengths as long as the integer passed in. 
The second argument will create a player or strategy from our implementations, and valid command 
line arguments for this are: `human`, `maxpointstrat`, and `cornerstrat`. The third and final 
command line argument would create a strategy of the provider's code if it actually compiled and 
didn't just throw an error. The valid arguments for this argument would be: `playeraione` and 
`playeraithree`. Read the first heading for why this isn't actually the case, and we instead 
just make a player adapter object for this.

# Quick Start for the GUI gameplay
The reversi game must be started from the command line with three command line arguments. \
The first argument should be the size of the board's 'length' as an integer. \
The next two arguments should be what type of player or strategy the views will 'use'. \
Valid command line arguments include: "human", "maxpointstrat", and "cornerstrat". \
The "human" player means that all input will come in via mouse and keyboard input. \
The "maxpointstrat" strategy will simply try and make the move that grants the 'AI' the most \
points, and breaks ties by making the move that is the most uppermost and left-most cell. \
The "cornerstrat" strategy will attempt to make a move that is closest to the corners of the \
board, and if that fails, will fall back on the "maxpointstrat". \
We expect a good user who won't run the program with spam arguments, but if they do, we throw \
different exceptions.

# Extra Credit for assignment 2
We chained our GoForCornersStrategy and our MaxPointStrategy classes so that if the corner \
strategy doesn't provide a move, it goes to any max point move.

# Reversi-Project
Two-player Reversi game with a graphical interface. This project implements a basic version of the \
Reversi game played on a hexagonal grid. Players can make moves, pass turns, and check game \
state on a hex grid with a customizable board size.

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

Images for the assignment are respectively called: \
InitImage.png \
InProgressImage.png \
SelectedHexagonImage.png \

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

# Overview
We created the JFrame and JPanel classes in the respective classes for this assignment. \
This includes the Hexagon class which represents a Hexagon, and an interface for the GUI view.

# Quick start
For the GUI:
BasicReversi model = new BasicReversi(4); Creates a game with side lengths 4. \
IView view = new ReversiGraphicsView(model); Creates a new GUI view of the model. \
view.setVisible(true); Makes the aforementioned GUI view visible. \

# Key components
ReversiPanel: Represents a JPanel of the GUI view.
ReversiGraphicsView: Represents the JFrame of the GUI view.

# Key subcomponents
Not much. making the GUI will allow you to see the GUI and everything.

# Source Organization
src/cs3500/reversi/model/BasicReversi.java: Contains the implementation of the BasicReversi class
and related logic. \
src/cs3500/reversi/model/Cell.java: Contains the Cell class representing a hexagonal grid cell. \
src/cs3500/reversi/model/DiscColor.java: Enum defining the possible colors of the discs.
src/cs3500/reversi/view/ReversiPanel.java: Contains the implementation of our extended JPanel
class. \
src/cs3500/reversi/view/ReversiGraphicsView.java: Contains the implementation of our extended
JFrame class. This includes the creation of the aforementioned class.

# Changes for part 3
We created the ModelFeatures, ViewFeatures, ReversiController, and ISmarterModel interfaces. We \
implemented the ReversiController and features interfaces in the GameController derived class, and \
the ISmarterModel interface in the SmarterModel class. This allowed us to have our code be "open \
to extension but closed to modification". The GameController class was to allow the controller \
to update the view and make moves on the model utilizing the addFeatureListener implementations \
inside the model and the view. Going off the above explanation we implemented the \
addFeaturesListener in both the model and the view to register listeners in those classes. 

