# Tic Tac Toe Game

This is a simple implementation of the classic game Tic Tac Toe in Java using Swing for the GUI.

## Features

- Two players: human (X) and bot (O).
- The bot makes its move 2 seconds after the human player.
- The bot's move is selected randomly from the available spots.
- The game board is a 3x3 grid.
- The game ends when one player has 3 of their symbols in a row, column, or diagonal, or when all spots are filled and there's no winner.

## How to Run

Compile and run the `MicroProject.java` file in any Java IDE or from the command line.

## Dependencies

This project uses the following Java libraries:

- `javax.swing` for the GUI.
- `java.awt` for the layout of the GUI.
- `java.awt.event` for the action listeners.
- `java.util.ArrayList` for storing the available buttons for the bot's move.
- `java.util.Random` for selecting a random move for the bot.
- `java.util.List` for storing the list of buttons.
