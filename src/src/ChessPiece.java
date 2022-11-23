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


    
    /**
     * <h3>isValidateMove</h3>
     * <p>Checks if the move is valid</p>
     * @param endCell - the cell to which the piece is moving
     * @return whether the move in the given Cell is possible or not
     */
    public boolean isValidateMove(Cell endCell);

    /**
     * <h3>move</h3>
     * <p>Moves the piece to the given cell</p>
     * @return whether the move was successful or not
     */
    public boolean move(Cell endCell);

    /**
     * <h3>isUnderAttack</h3>
     * <p>Checks if the piece is under attack</p>
     * @return whether the piece is under attack or not
     */
    public boolean isUnderAttack();

    /**
     * <h3>getPossibleMoves</h3>
     * <p>Gets all the possible moves for the piece</p>
     * @return a 2D array of integers that represent the possible moves
     */
    public int[][] getPossibleMoves();
    
    /**
     * <h3>isUnderAttack</h3>
     * <p>Checks if the piece is under attack</p>
     * @param b - the board to check
     * @param color - the color of the piece
     * @return whether the piece is under attack or not
     */
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

    public boolean hasMoved();

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
