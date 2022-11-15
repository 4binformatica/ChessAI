package src;


import pieces.Bishop;
import pieces.Rook;

/**
 * Board
 */
public class Board {

    private final Cell[][] DEFAULT_BOARD = new Cell[8][8];

    private Cell[][] board;


    public Board() {
        board = DEFAULT_BOARD;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j, this);
            }
        }
        setup();
    }

    public Board(int rows, int columns) {
        board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(i, j, this);
            }
        }
    }

    private void setup(){
        board[0][0].setPiece(new Rook(ChessPiece.BLACK, board[0][0]));
        board[0][1].setPiece(new Bishop(ChessPiece.BLACK, board[0][1]));
        board[0][2].setPiece(new Bishop(ChessPiece.BLACK, board[0][2]));

        ChessPiece piece = board[0][0].getPiece();
        System.out.println(piece.isValidateMove(board[6][0]));
    }

    public Cell getCell(int i, int j) {
        return board[i][j];
    }

    public void setCell(int i, int j, Cell cell) {
        board[i][j] = cell;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty()) {
                    result += ".";
                } else {
                    result += board[i][j].getPiece().toString();
                }
            }
            result += "\n";
       }

        return result;
    
    }

}