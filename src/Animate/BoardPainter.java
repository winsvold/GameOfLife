package Animate;

import GameOfLife.GameOfLifeBoard;

import javax.swing.*;
import java.awt.*;

public class BoardPainter extends JPanel{
    private GameOfLifeBoard board;
    private int pixelPerPoint;

    public BoardPainter(GameOfLifeBoard board, int pixelPerPoint) {
        super.setBackground(Color.orange);
        this.pixelPerPoint = pixelPerPoint;
        this.board = board;
    }

    private void paintCells(Graphics g){
        int offset = pixelPerPoint;
        Color myColor = new Color(0,0,0,120);
        g.setColor(myColor);
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if(board.isAlive(j,i)) {
                    g.fillOval(j * offset, i * offset, (int) (pixelPerPoint*1), (int) (pixelPerPoint*1));
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintCells(g);
    }
}
