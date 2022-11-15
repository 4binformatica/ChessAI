package pieces;

import src.Cell;
import src.ChessPiece;

public class Queen extends ChessPiece{

    private final int VALUE = 9; 
    
    public Queen(int color, Cell cell) {
        super(color, cell);
        setValue(VALUE);
    }

    public Queen(int color) {
        super(color);
        setValue(VALUE);
    }

    public static boolean isValidateMove(Cell startCell, Cell endCell) {
        return Bishop.isValidateMove(startCell, endCell) || Rook.isValidateMove(startCell, endCell);
    }

    public boolean isValidateMove(Cell endCell) {
        return isValidateMove(getCell(), endCell);
    }

    @Override
    public String toString() {
        return isBlack() ? "Q" : "q";
    }
}
