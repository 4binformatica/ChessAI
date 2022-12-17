package IA;

import java.util.ArrayList;



import src.Board;
import src.Cell;
import src.ChessPiece;

public class BoardTree {
    private BoardNode root;
    private int color;
    private int depth;

    public BoardTree(Board root, int color, int depth) {
        this.root = new BoardNode(root, color, null, null);
        this.depth = depth;
        this.color = color;
    }

    public BoardTree(BoardNode root, int color, int depth) {
        this.root = root;
        this.depth = depth;
        this.color = color;
    }

   

    public BoardTree() {
        this.root = null;
        this.depth = 0;
    }

    public static void generateChildren(BoardNode node, int depth, int startColor, int color){
        if(depth == 0){
            return;
        }
        //generate children
        ArrayList<ChessPiece> pieces = node.getBoard().getPieces(startColor);
        
        for(ChessPiece p : pieces){
            ArrayList<Cell> possibleMoves = p.getPossibleMoves();
            for(Cell c : possibleMoves){
                Board clone = node.getBoard().clone();
                ChessPiece piece = clone.getCell(p.getCell().getI(), p.getCell().getJ()).getPiece();
                piece.move(clone.getCell(c.getI(), c.getJ()));
                BoardNode child = new BoardNode(clone, color, node, null);
                node.addChild(child);
            }    
        }
        
        //for each child, call generateChildren(child, depth - 1)
        for(BoardNode child : node.getChildren()){
            generateChildren(child, depth - 1, startColor == ChessPiece.WHITE_COLOR ? ChessPiece.BLACK_COLOR : ChessPiece.WHITE_COLOR, color);
        }
    }

    public void generateChildren(int startColor){
        generateChildren(root, depth, startColor, color);
    }

    public BoardNode getRoot() {
        return root;
    }

    public void setRoot(BoardNode root) {
        this.root = root;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }    

    public int alfaBetaPruning(BoardNode node, int depth, int alpha, int beta, boolean isMaximising){
        if(depth == 0){
            return node.getScore();
        }
        if(isMaximising){
            int value = Integer.MIN_VALUE;
            for(BoardNode child : node.getChildren()){
                value = Math.max(value, alfaBetaPruning(child, depth - 1, alpha, beta, false));
                alpha = Math.max(alpha, value);
                if(beta <= alpha){
                    child.getParent().removeChild(child);
                    break;
                }
            }
            return value;
        }else{
            int value = Integer.MAX_VALUE;
            for(BoardNode child : node.getChildren()){
                value = Math.min(value, alfaBetaPruning(child, depth - 1, alpha, beta, true));
                beta = Math.min(beta, value);
                if(beta <= alpha){
                    child.getParent().removeChild(child);
                    break;
                }
            }
            return value;
        }
       
    }

    private String toStringR(BoardNode n, String s){
        if(n.getChildren().size() == 0){
            return s + n.getScore() + " ";
        }
        String str = "";
        for(BoardNode child : n.getChildren()){
            str += toStringR(child, s + " ");
        }
        return str;
            
    }

    @Override
    public String toString() {
        return toStringR(root, "");
    }

    public void alfaBetaPruning() {
        alfaBetaPruning(root, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
    }
}
