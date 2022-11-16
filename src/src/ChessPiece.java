package src;
public interface ChessPiece {
    final static int BLACK_COLOR = 0;
    final static int WHITE_COLOR = 1;

    final static int PAWN = 0;
    final static int BISHOP = 1;
    final static int KNIGHT = 2;
    final static int ROOK = 3;
    final static int QUEEN = 4;
    final static int KING = 5;

    final static int IMPOSSIBLE_TO_MOVE = 0;
    final static int POSSIBLE_TO_MOVE = 1;
    final static int POSSIBLE_TO_ATTACK = 2;
    final static int YOURSELF = 3;
    

    public boolean isValidateMove(Cell endCell);
    public boolean move(Cell endCell);
    public boolean isUnderAttack(Board b);
    public int[][] getPossibleMoves();
    
    public static boolean isUnderAttack(Board b, int color) {
        Cell[][] cells = b.getBoard();
        for(Cell c[] : cells){
            for(Cell cell : c){
                ChessPiece p = cell.getPiece();
                if(cell.isOccupied() && p.getColor() == color){
                    if(p.isValidateMove(cell)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

        
    


    @Override
    public String toString();
    
    public int getColor();
    public void setColor(int color);
    
    public int getPiece();
    
    public Cell getCell();
    public void setCell(Cell cell);
    public int getValue();
    public void setValue(int value);


}
