package Animate;

import GameOfLife.GameOfLifeBoard;
import Animate.Listener.KeyBoardListener;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InitializeSimulator implements Runnable {

    private JFrame frame;
    private JPanel boardPainter;
    private GameOfLifeBoard board;
    private int pixelPerPoint;
    private double millisecondsPrRound;
    private ScheduledExecutorService updater;

    public InitializeSimulator(GameOfLifeBoard board, int pixelPerPoint, double hertz){
        this.board = board;
        this.millisecondsPrRound = (int)(1000/hertz);
        this.pixelPerPoint = pixelPerPoint;
        this.boardPainter = new BoardPainter(board,pixelPerPoint);
        board.initiateRandomCells();
        updater = Executors.newScheduledThreadPool(1);
    }

    public void setMillisecondsPrRound(double millisecondsPrRound) {
        this.millisecondsPrRound = millisecondsPrRound;
    }

    public double getMillisecondsPrRound() {
        return millisecondsPrRound;
    }

    private void setNextUpdate (){
        updater.schedule(()->{
            setNextUpdate();
            board.playTurn();
            boardPainter.repaint();
            },(int)millisecondsPrRound,TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        frame = new JFrame("GameOfLife");
        frame.setPreferredSize(new Dimension((int)((board.getWidth() + 0.8 )* pixelPerPoint) ,(board.getHeight() + 2 ) * pixelPerPoint));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(boardPainter);
        frame.addKeyListener(new KeyBoardListener(boardPainter,board,this));

        frame.pack();
        frame.setVisible(true);

        setNextUpdate();
    }
}
