package src;
public class Cell {
    private final ChessPiece DEFAULT_PIECE = null;
    private final int DEFAULT_ROW = 0;
    private final int DEFAULT_COLUMN = 0;
    private final Board DEFAULT_BOARD = null;

    ChessPiece piece;
    Board board;
    int i;
    int j;




    public Cell(int i, int j, Board board) {
        this.i = i;
        this.j = j;
        this.board = board;
        this.piece = DEFAULT_PIECE;
    }

    public Cell(int i, int j,  Board board, ChessPiece piece) {
        this.i = i;
        this.j = j;
        this.board = board;
        this.piece = piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public boolean isOccupied() {
        return piece != null;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
