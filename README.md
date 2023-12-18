# [Reversi](https://github.com/PaoloLanaro/Reversi)

by [Paolo Lanaro](https://github.com/PaoloLanaro) and [Quinn Louie](https://github.com/QuinnJL04)

A [Reversi](https://en.wikipedia.org/wiki/Reversi), otherwise known as Othello,
game application made in Java.

## Table of Contents

- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
   - [Command Line Arguments](#command-line-arguments)
- [Usage](#usage)
   - [Playing the Game](#playing-the-game)
   - [Key Map](#key-map)
   - [Mouse Controls](#mouse-controls)

## Getting Started

Clone the repo onto your local device and run command line arguments to set up various configurations of Reversi.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html): The project is built using Java, so you need to have the JDK installed. Make sure to set up your `JAVA_HOME` environment variable.

- [IntelliJ IDEA](https://www.jetbrains.com/idea/): The project is developed using IntelliJ IDEA, a powerful Java IDE. You can download it from the official website and install it on your machine.



## Installation

_Our program doesn't have any dependencies, but you'll need to run our program's jar._

1. Clone the repo
    ```sh
   git clone https://github.com/PaoloLanaro/Reversi.git
   ```

### Command Line arguments
To run our program, we require 4 command line arguments.

The first command line argument should be an integer size. 
* For Square reversi games this must be an even, positive, integer of at least 4.
* For Hexagon reversi games this must be an positive integer of at least 3.

The second command line argument will be what game you want to play.
* `square` will make a square game.
* `hex` will make a hexagon game.

The third and fourth command line arguments should be what "strategy" you would want to use.
The game will be constructed with the strategies in the order you put them. 

For example, if you put `human` `maxpointstrat` the first "player" will be a human, while the second will be a 
MaxPointStrategy "AI"
* `human` will create a human player. This means you'll be able to control this view.
* `maxpointstrat` will create a MaxPointStrategy AI.
* `cornerstrat` will create a GoForCornerStrategy AI.

## Usage

### Playing the Game

To start a game of Reversi, follow these steps:

1. Open a terminal window and navigate to the project directory:

    ```sh
   cd Reversi
   
2. Run the JAR file with the following command, specifying the game size, type, and player strategies:
   ```sh
   java -jar Reversi.jar <size> <game-type> <strategy-1> <strategy-2>


### Key Map
ENTER - key press will confirm and make a move.\
BACK-SPACE - key press will pass a turn.

### Mouse Controls
Mouse left click highlights selected hexagon cyan.

