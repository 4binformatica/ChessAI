package src;
public class ChessPiece {
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    private final int DEFAULT_COLOR = WHITE;
    private final Cell DEFAULT_CELL = null;
    private final int DEFAULT_VALUE = 0;

    private int value;
    private int color = WHITE;
    private Cell cell;
     
    
    public ChessPiece(int color, Cell cell) {
        this.color = color;
        this.cell = cell;
        this.value = DEFAULT_VALUE;
    }
    
    public ChessPiece(int color) {
        this.color = color;
        cell = DEFAULT_CELL;
        this.value = DEFAULT_VALUE;
    }

    public ChessPiece() {
        this.color = DEFAULT_COLOR;
        cell = DEFAULT_CELL;
        this.value = DEFAULT_VALUE;
    }

    public boolean isValidateMove(Cell endCell) {
        return false;
    }

    public void move(Cell startCell, Cell endCell) {
        setCell(endCell);
        endCell.setPiece(startCell.getPiece());
        startCell.setPiece(null);
    }

    public int getColor() {
        return color;
    }

    public boolean isWhite() {
        return color == WHITE;
    }

    public boolean isBlack() {
        return color == BLACK;
    }

    public String toString() {
        return "ChessPiece";
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
