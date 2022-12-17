package IA;

import java.util.ArrayList;

import src.Board;

public class BoardNode {
    private Board board;
    private int score;
    private int color;
    private BoardNode parent;
    ArrayList<BoardNode> children;

    public BoardNode(Board board, int color, BoardNode parent, ArrayList<BoardNode> children) {
        this.board = board;
        this.color = color;
        this.score = IA.evaluate(board, color);
        this.parent = parent;
        this.children = children;
        if(this.children == null){
            this.children = new ArrayList<BoardNode>();
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public BoardNode getParent() {
        return parent;
    }

    public void setParent(BoardNode parent) {
        this.parent = parent;
    }

    public ArrayList<BoardNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<BoardNode> children) {
        this.children = children;
    }

    public ArrayList<BoardNode> getChilren() {
        return children;
    }

    public void addChild(BoardNode child) {
        this.children.add(child);
    }

    public void removeChild(BoardNode child) {
        this.children.remove(child);
    }

    public int getColor() {
        return color;
    }    
}
