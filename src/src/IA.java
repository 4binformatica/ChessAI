package src;

public class IA {
     //create a ai for chess game
    public IA() {

    }

    public int evaluate(Board b, int color) {
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

     

}
