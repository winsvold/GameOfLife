package Animate.Listener;

import Animate.InitializeSimulator;
import GameOfLife.GameOfLifeBoard;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener {

    private JPanel boardPainter;
    private GameOfLifeBoard board;
    private InitializeSimulator simulator;

    public KeyBoardListener(JPanel boardPainter, GameOfLifeBoard board, InitializeSimulator simulator){
        this.board = board;
        this.boardPainter = boardPainter;
        this.simulator = simulator;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_N || e.getKeyCode() == KeyEvent.VK_R){
            board.initiateRandomCells();
            board.playTurn();
            boardPainter.repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            board.playTurn();
            boardPainter.repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            simulator.setMillisecondsPrRound((int)(simulator.getMillisecondsPrRound()*1.5));
            if(simulator.getMillisecondsPrRound() > 5000){
                simulator.setMillisecondsPrRound(5000);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            simulator.setMillisecondsPrRound((int)(simulator.getMillisecondsPrRound()*0.5));
            if(simulator.getMillisecondsPrRound() < 10){
                simulator.setMillisecondsPrRound(10);
            }
        }
        System.out.println(simulator.getMillisecondsPrRound());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
