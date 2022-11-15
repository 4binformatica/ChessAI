package pieces;

import src.ChessPiece;
import src.Cell;

public class Bishop extends ChessPiece {

    private final int value = 3;

    public Bishop(int color, Cell cell) {
        super(color, cell);
        setValue(value);
    }

    public Bishop(int color) {
        super(color);
        setValue(value);
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
                if (startCell.getBoard().getCell(i, j).isOccupied()) {
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
    public String toString() {
        return isBlack() ? "B" : "b";
    }
}
    
