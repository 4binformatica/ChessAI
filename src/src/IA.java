package src;

import java.util.ArrayList;

import Graphics.GraphicBoard;

public class IA {
    Board b;
    GraphicBoard gb;

    int color;
     //create a ai for chess game
    public IA(Board b, GraphicBoard gb) {
        this.b = b;
        this.color = ChessPiece.WHITE_COLOR;
        this.gb = gb;
    }

    

    private ArrayList<ChessPiece> getAllMovablePieces(int color){
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        for(int i = 0; i < b.getBoard().length; i++){
            for(int j = 0; j < b.getBoard()[i].length; j++){
                ChessPiece p = b.getBoard()[i][j].getPiece();
                if(p != null && p.getColor() == color && p.hasPossibleMoves()){
                    pieces.add(p);
                }
            }
        }
        return pieces;
    }

    public void doSomething(){
        System.out.println("IA is thinking...");
        ArrayList<ChessPiece> pieces = getAllMovablePieces(color);
        int max = Integer.MIN_VALUE;
        ChessPiece bestPiece = null;
        Cell bestCell = null;
        Cell startCell = null;
        for(ChessPiece p : pieces){
            ArrayList<Cell> possibleMoves = p.getPossibleMoves();
            for(Cell c : possibleMoves){
                Board copy = b.copy();
                ChessPiece piece = copy.getBoard()[p.getCell().getI()][p.getCell().getJ()].getPiece();
                Cell cell = copy.getBoard()[c.getI()][c.getJ()];
                piece.move(cell);
                int score = evaluate(copy, color);
                if(score > max){
                    max = score;
                    bestPiece = piece;
                    bestCell = cell;
                    startCell = copy.getBoard()[p.getCell().getI()][p.getCell().getJ()];
                }
            }


        }
        
        b.getCell(startCell.getI(), startCell.getJ()).getPiece().move(b.getCell(bestCell.getI(), bestCell.getJ()));
        gb.repaint();     

        color = color == ChessPiece.WHITE_COLOR ? ChessPiece.BLACK_COLOR : ChessPiece.WHITE_COLOR;
    }

    public static int evaluate(Board b, int color) {
        int score = 0;
        Cell[][] board = b.getBoard();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                ChessPiece p = board[i][j].getPiece();
                if(p != null && p.getColor() == color){
                    score += board[i][j].getPiece().getValue();
                }
            }
        }
        return score;
    }
    
    public int evaluate(int color){
        return evaluate(b, color);
    }

     

}
