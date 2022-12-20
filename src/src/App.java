package src;

import javax.swing.JFrame;

import Graphics.GraphicBoard;
import Graphics.TaskManager;
import IA.IA;

public class App {
    public static void main(String[] args) throws Exception {
        Board b = new Board();
        GraphicBoard gb = new GraphicBoard(b);
        b.addGraphicListener(gb);
        JFrame frame = new JFrame();
        frame.add(gb);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TaskManager tm = new TaskManager();



        IA iaB = new IA(b, gb, ChessPiece.BLACK_COLOR);     
        IA iaW = new IA(b, gb, ChessPiece.WHITE_COLOR);

        //iaW.doSomething();


        
        while(!b.isGameOver()){
            System.out.println(b.getKing(0).isInCheckMate());
            System.out.println(b.getKing(1).isInCheckMate());
            iaW.iAMovingWithTree();
            Thread.sleep(500);
            iaB.iAMovingWithTree();
            Thread.sleep(500);
        }

        System.out.println("Game Over");
        System.out.println("Winner: " + b.getWinner());
           

        
        
       
    }
}
