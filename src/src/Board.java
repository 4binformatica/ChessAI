package src;


import pieces.Bishop;
import pieces.King;
import pieces.Rook;
import pieces.Knight;
import pieces.Queen;
import pieces.Pawn;
import pieces.Queen;


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
        
        //test all the pieces
        board[4][4].setPiece(new Rook(0, board[4][4]));
        int[][] moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board[4][4].setPiece(new Bishop(0, board[4][4]));
        moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board[4][4].setPiece(new Knight(0, board[4][4]));
        moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board[4][4].setPiece(new Queen(0, board[4][4]));
        moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board[4][4].setPiece(new King(0, board[4][4]));
        moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board[4][4].setPiece(new Pawn(0, board[4][4]));
        moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board[4][4].setPiece(new Pawn(1, board[4][4]));
        moves = board[4][4].getPiece().getPossibleMoves();
        for(int i = 0; i < moves.length; i++){
            for(int j = 0; j < moves[i].length; j++){
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
        
       
    }

    public Cell getCell(int i, int j) {
        return board[i][j];
    }

    public void setCell(int i, int j, Cell cell) {
        board[i][j] = cell;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
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