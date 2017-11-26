import Animate.InitializeSimulator;
import GameOfLife.GameOfLifeBoard;

import javax.swing.*;

public class Run {

    public static void main(String[] args){

        GameOfLifeBoard board = new GameOfLifeBoard(160,110, 0.08);
        InitializeSimulator initializeSimulator = new InitializeSimulator(board,10, 1);
        SwingUtilities.invokeLater(initializeSimulator);
    }
}