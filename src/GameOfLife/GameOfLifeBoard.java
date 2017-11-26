package GameOfLife;

import java.util.Arrays;
import java.util.Random;

public class GameOfLifeBoard {
    private boolean[][] board;
    private int width;
    private int height;
    private double probabilityForEachCell;

    public GameOfLifeBoard(int width, int height, double probabilityForEachCell){
        board = new boolean[height][width];
        this.probabilityForEachCell = probabilityForEachCell;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void turnToLiving(int x, int y){
        board[y][x] = true;
    }

    public void turnToDead(int x, int y){
        board[y][x] = false;
    }

    public boolean isAlive(int x, int y){
        return board[y][x];
    }

    public void initiateRandomCells(){
        for ( boolean[] array: board ) {
            Arrays.fill(array,false);
        }
        boolean turnAlive;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if(probabilityForEachCell + new Random().nextDouble() >= 1){
                    turnAlive = true;
                } else {
                    turnAlive = false;
                }
                board[i][j] = turnAlive;
            }
        }
    }

    private boolean notOutOfBounds(int x, int y){
        return y >= 0 && x >= 0 && y < getHeight() && x < getWidth();
    }

    public int getNumberOfLivingNeighbours(int x, int y){
        int livingNeighbours = 0;
        int startX = x - 1;
        int startY = y - 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(notOutOfBounds(j + startX, i + startY)) {
                    if (board[i + startY][j + startX] && !(i == 1 && j == 1)) {
                        livingNeighbours++;
                    }
                }
            }
        }
        return livingNeighbours;
    }

    public void manageCell(int x, int y, boolean[][] boardNewState){
        int livingNeighbours = getNumberOfLivingNeighbours(x,y);
        if(livingNeighbours < 2 || livingNeighbours > 3){
            boardNewState[y][x] = false;
        } else if (board[y][x] || livingNeighbours == 3) {
            boardNewState[y][x] = true;
        }
      }

    public void playTurn(){
        boolean[][] boardNewState = new boolean[getHeight()][getWidth()];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                manageCell(j,i,boardNewState);
            }
        }
        board = boardNewState;
    }

    public void printBoardToConsole(){
        for (int i = 0; i < getHeight() ; i++) {
            for (int j = 0; j < getWidth(); j++) {
                if(getBoard()[i][j]) {
                    System.out.print("x");
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("   ");
            for (int j = 0; j < getWidth(); j++) {
                System.out.print(getNumberOfLivingNeighbours(j,i));
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
