package pieces;

import src.ChessPiece;
import src.NotifyMovement;

import java.util.ArrayList;

import src.Cell;

public class Bishop implements ChessPiece {

    private ArrayList<NotifyMovement> observers = new ArrayList<NotifyMovement>();


    private final int VALUE = 30;
    private final float[][] DEFAULT_POSITION_FACTOR = {
        {-2, -1, -1, -1, -1, -1, -1, -2},
        {-1, 0, 0, 0, 0, 0, 0, -1},
        {-1, 0, 0.5f, 1, 1, 0.5f, 0, -1},
        {-1, 0.5f, 0.5f, 1, 1, 0.5f, 0.5f, -1},
        {-1, 0, 1, 1, 1, 1, 0, -1},
        {-1, 1, 1, 1, 1, 1, 1, -1},
        {-1, 0.5f, 0, 0, 0, 0, 0.5f, -1},
        {-2, -1, -1, -1, -1, -1, -1, -2}
    };



    private int value;
    private float[][] positionFactor;
    private int color;
    private Cell cell;
    private boolean hasMoved = false;

    public Bishop(int color, Cell cell) {
        this.color = color;
        this.cell = cell;
        setValue(VALUE);
        setPositionFactor(DEFAULT_POSITION_FACTOR);
    }

    public static boolean isValidateMove(Cell startCell, Cell endCell) {
        if (endCell.getPiece() != null && (startCell.getPiece().getColor() == endCell.getPiece().getColor())) {
            return false;
        }
        if (Math.abs(startCell.getI() - endCell.getI()) == Math.abs(startCell.getJ() - endCell.getJ())) {
            //check if there is any piece in the way
            int i = startCell.getI();
            int j = startCell.getJ();
            int xIncrement = startCell.getI() == endCell.getI() ? 0 : startCell.getI() < endCell.getI() ? 1 : -1;
            int yIncrement = startCell.getJ() == endCell.getJ() ? 0 : startCell.getJ() < endCell.getJ() ? 1 : -1;
            
            while (i != endCell.getI() || j != endCell.getJ()) {
                i += xIncrement;
                j += yIncrement;
                if (startCell.getBoard().getCell(i, j).isOccupied() && (i != endCell.getI() || j != endCell.getJ())) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean isValidateMove(Cell endCell) {
        return isValidateMove(getCell(), endCell);
    }

    @Override
    public boolean move(Cell endCell, boolean simulated) {
        if (isValidateMove(endCell)) {
            endCell.setPiece(this);
            getCell().setPiece(null);
            setCell(endCell);
            hasMoved = true;
            if(!simulated)
                notifyObservers();
            return true;
        } 
        return false;    
    }

    @Override
    public boolean isUnderAttack() {
        Cell[][] cells = cell.getBoard().getBoard();
        for(Cell c[] : cells){
            for(Cell cell : c){
                ChessPiece p = cell.getPiece();
                if(cell.isOccupied() && p.getColor() != getColor()){
                    if(p.isValidateMove(getCell())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getPiece() {
        return ChessPiece.BISHOP;
    }

    
    @Override
    public Cell getCell() {
        return cell;
    }
    
    @Override
    public void setCell(Cell cell) {
        this.cell.setPiece(null);
        this.cell = cell;
        this.cell.setPiece(this);
    }
    
    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public float[][] getPositionFactor() {
        return positionFactor;
    }

    @Override
    public void setPositionFactor(float[][] positionFactor) {
        this.positionFactor = positionFactor;
    }


    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getColor() == ChessPiece.BLACK_COLOR ? "B" : "b";
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public int[][] getPossibleMovesMatrix() {
        //make a int[][] with sizes of the Board
        //fill it with ChessPiece.POSSIBLE_TO_MOVE if the move is valid
        //fill it with ChessPiece.IMPOSSIBLE_TO_MOVE if the move is invalid
        //fill it with ChessPiece.POSSIBLE_TO_ATTACK if the move is valid and capture a piece
        //fill it with ChessPiece.YOURSELF if it is your piece

        int[][] moves = new int[getCell().getBoard().getBoard().length][getCell().getBoard().getBoard()[0].length];
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                
                if(cell.getI() == i && cell.getJ() == j){
                    moves[i][j] = ChessPiece.YOURSELF;
                    continue;
                }  

                if (isValidateMove(getCell().getBoard().getCell(i, j))) {
                    if (getCell().getBoard().getCell(i, j).isOccupied()) {
                        moves[i][j] = ChessPiece.POSSIBLE_TO_ATTACK;
                    } else {
                        moves[i][j] = ChessPiece.POSSIBLE_TO_MOVE;
                    }
                } else {
                    moves[i][j] = ChessPiece.IMPOSSIBLE_TO_MOVE;
                }
            }
        }
        return moves;

    }

    @Override
    public boolean hasPossibleMoves() {
        int[][] moves = getPossibleMovesMatrix();
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                if (moves[i][j] == ChessPiece.POSSIBLE_TO_MOVE || moves[i][j] == ChessPiece.POSSIBLE_TO_ATTACK) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<Cell> getPossibleMoves() {
        int[][] moves = getPossibleMovesMatrix();
        ArrayList<Cell> possibleMoves = new ArrayList<>();
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < moves[i].length; j++) {
                if (moves[i][j] == ChessPiece.POSSIBLE_TO_MOVE || moves[i][j] == ChessPiece.POSSIBLE_TO_ATTACK) {
                    possibleMoves.add(getCell().getBoard().getCell(i, j));
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public void addObserver(NotifyMovement observer) {
        observers.add(observer);       
    }

    @Override
    public void removeObserver(NotifyMovement observer) {
        observers.remove(observer);   
    }

    @Override
    public ArrayList<NotifyMovement> getObservers() {
        return observers;
    }

    @Override
    public void notifyObservers() {
        for(NotifyMovement nm : observers) nm.notifyMovement();
    }

    

    
}
    
