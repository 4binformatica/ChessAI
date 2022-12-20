package src;


import java.util.ArrayList;

import Graphics.GraphicNotifyer;
import pieces.Bishop;
import pieces.King;
import pieces.Rook;
import pieces.Knight;
import pieces.Queen;
import pieces.Pawn;



/**
 * Board
 */
public class Board implements NotifyMovement{

    
    private final Cell[][] DEFAULT_BOARD = new Cell[8][8];

    private Cell[][] board;

    ArrayList<GraphicNotifyer> graphicListeners = new ArrayList<>();

    public Board() {
        board = DEFAULT_BOARD;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j, this);
            }
        }
        setup();
    }

    //make a copy method
    @Override
    public Board clone() {
        Board copy = new Board(getBoard().length, getBoard()[0].length);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece p = board[i][j].getPiece();
                ChessPiece newPiece = null;
                if(p != null){
                    if(p instanceof Pawn){
                        newPiece = new Pawn(p.getColor(), copy.board[i][j]);
                    }else if(p instanceof Rook){
                        newPiece = new Rook(p.getColor(), copy.board[i][j]);
                    }else if(p instanceof Knight){
                        newPiece = new Knight(p.getColor(), copy.board[i][j]);
                    }else if(p instanceof Bishop){
                        newPiece = new Bishop(p.getColor(), copy.board[i][j]);
                    }else if(p instanceof Queen){
                        newPiece = new Queen(p.getColor(), copy.board[i][j]);
                    }else if(p instanceof King){
                        newPiece = new King(p.getColor(), copy.board[i][j]);
                    }
                }
                copy.board[i][j].setPiece(newPiece);
            }
        }
        return copy;
    }

    public Board(int rows, int columns) {
        board = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(i, j, this);
            }
        }
        setup();
    }

    private void setup(){        
        //setup the board
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Pawn(ChessPiece.BLACK_COLOR, board[1][i]));
            board[6][i].setPiece(new Pawn(ChessPiece.WHITE_COLOR, board[6][i]));
        }

        board[0][0].setPiece(new Rook(ChessPiece.BLACK_COLOR, board[0][0]));
        board[0][7].setPiece(new Rook(ChessPiece.BLACK_COLOR, board[0][7]));
        board[7][0].setPiece(new Rook(ChessPiece.WHITE_COLOR, board[7][0]));
        board[7][7].setPiece(new Rook(ChessPiece.WHITE_COLOR, board[7][7]));
        
        board[0][1].setPiece(new Knight(ChessPiece.BLACK_COLOR, board[0][1]));
        board[0][6].setPiece(new Knight(ChessPiece.BLACK_COLOR, board[0][6]));
        board[7][1].setPiece(new Knight(ChessPiece.WHITE_COLOR, board[7][1]));
        board[7][6].setPiece(new Knight(ChessPiece.WHITE_COLOR, board[7][6]));

        board[0][2].setPiece(new Bishop(ChessPiece.BLACK_COLOR, board[0][2]));
        board[0][5].setPiece(new Bishop(ChessPiece.BLACK_COLOR, board[0][5]));
        board[7][2].setPiece(new Bishop(ChessPiece.WHITE_COLOR, board[7][2]));
        board[7][5].setPiece(new Bishop(ChessPiece.WHITE_COLOR, board[7][5]));

        board[0][3].setPiece(new Queen(ChessPiece.BLACK_COLOR, board[0][3]));
        board[7][3].setPiece(new Queen(ChessPiece.WHITE_COLOR, board[7][3]));

        board[0][4].setPiece(new King(ChessPiece.BLACK_COLOR, board[0][4]));
        board[7][4].setPiece(new King(ChessPiece.WHITE_COLOR, board[7][4])); 

        
        
    }



    public ChessPiece getPiece(int i, int j) {
        return board[i][j].getPiece();
    }

    public ChessPiece getRandomPiece(int color){
        int i = (int)(Math.random() * 8);
        int j = (int)(Math.random() * 8);
        while(board[i][j].isEmpty() || board[i][j].getPiece().getColor() != color){
            i = (int)(Math.random() * 8);
            j = (int)(Math.random() * 8);
        }
        return board[i][j].getPiece();
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

    public King getKing(int color){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!board[i][j].isEmpty() && board[i][j].getPiece() instanceof King && board[i][j].getPiece().getColor() == color){
                    return (King)board[i][j].getPiece();
                }
            }
        }
        return null;
    }

    
    public boolean isGameOver(){
        return getKing(ChessPiece.WHITE_COLOR).isInCheckMate() || getKing(ChessPiece.BLACK_COLOR).isInCheckMate();
    }

    public int getWinner(){
        if(getKing(ChessPiece.WHITE_COLOR).isInCheckMate()){
            return ChessPiece.BLACK_COLOR;
        }else if(getKing(ChessPiece.BLACK_COLOR).isInCheckMate()){
            return ChessPiece.WHITE_COLOR;
        }
        return -1;
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

    public ArrayList<ChessPiece> getPieces(int color) {
        ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!board[i][j].isEmpty() && board[i][j].getPiece().getColor() == color){
                    pieces.add(board[i][j].getPiece());
                }
            }
        }
        return pieces;
    }

    //get width of board
    public int getWidth() {
        return board[0].length;
    }

    //get height of board
    public int getHeight() {
        return board.length;
    }

    @Override
    public void notifyMovement() {
        for (GraphicNotifyer listener : graphicListeners) {
            listener.notifyMovement();
        }
    }

    public void addGraphicListener(GraphicNotifyer listener) {
        graphicListeners.add(listener);
    }

    public void removeGraphicListener(GraphicNotifyer listener) {
        graphicListeners.remove(listener);
    }



   

}