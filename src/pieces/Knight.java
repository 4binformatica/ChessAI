package pieces;

import src.Board;
import src.Cell;
import src.ChessPiece;

public class Knight implements ChessPiece{
    private final int VALUE = 3;

    private int value;
    private int color;
    private Cell cell;

    public Knight(int color, Cell cell) {
        this.color = color;
        this.cell = cell;
        setValue(VALUE);
    }

    public static boolean isValidateMove(Cell startCell, Cell endCell) {
        if (endCell.getPiece() != null && (startCell.getPiece().getColor() == endCell.getPiece().getColor())) {
            return false;
        }
        if (Math.abs(startCell.getI() - endCell.getI()) == 2 && Math.abs(startCell.getJ() - endCell.getJ()) == 1) {
            return true;
        }
        if (Math.abs(startCell.getI() - endCell.getI()) == 1 && Math.abs(startCell.getJ() - endCell.getJ()) == 2) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidateMove(Cell endCell) {
        return isValidateMove(getCell(), endCell);
    }

    @Override
    public boolean move(Cell endCell) {
        if (isValidateMove(endCell)) {
            endCell.setPiece(this);
            getCell().setPiece(null);
            setCell(endCell);
            return true;
        } 
        return false;    
    }

    @Override
    public boolean isUnderAttack(Board b) {
        Cell[][] cells = b.getBoard();
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
        return ChessPiece.KNIGHT;
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
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getColor() == ChessPiece.BLACK_COLOR ? "K" : "k";
    }

    @Override
    public int[][] getPossibleMoves() {
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
    
}
