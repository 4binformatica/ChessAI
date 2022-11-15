package pieces;

import src.ChessPiece;
import src.Cell;

public class Rook extends ChessPiece {

    private final int VALUE = 5;

    public Rook(int color, Cell cell) {
        super(color, cell);
        setValue(VALUE);
    }

    public Rook(int color) {
        super(color);
        setValue(VALUE);
    }

    public static boolean isValidateMove(Cell startCell, Cell endCell) {
        if (endCell.getPiece() != null && (startCell.getPiece().getColor() == endCell.getPiece().getColor())) {
            return false;
        }
        if (startCell.getI() == endCell.getI() || startCell.getJ() == endCell.getJ()) {
            //check if there is any piece in the way
            int i = startCell.getI();
            int j = startCell.getJ();
            int xIncrement = startCell.getI() == endCell.getI() ? 0 : startCell.getI() < endCell.getI() ? 1 : -1;
            int yIncrement = startCell.getJ() == endCell.getJ() ? 0 : startCell.getJ() < endCell.getJ() ? 1 : -1;
            
            while (i != endCell.getI() || j != endCell.getJ()) {
                i += xIncrement;
                j += yIncrement;
                if (startCell.getBoard().getCell(i, j).isOccupied()) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    public boolean isValidateMove(Cell endCell) {
        return isValidateMove(getCell(), endCell);
    }

    @Override
    public String toString() {
        return isBlack() ? "R" : "r";
    }
}
