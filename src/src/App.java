package src;

import javax.swing.JFrame;

import Graphics.GraphicBoard;
import IA.IA;

public class App {
    public static void main(String[] args) throws Exception {
        Board b = new Board();
        GraphicBoard gb = new GraphicBoard(b);
        JFrame frame = new JFrame();
        frame.add(gb);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        IA ia = new IA(b, gb);
        
        
        while(!b.isGameOver()){
            System.out.println(b.getKing(0).isInCheckMate());
            System.out.println(b.getKing(1).isInCheckMate());
            ia.doSomething();
            Thread.sleep(500);
        }

        System.out.println("Game Over");
        System.out.println("Winner: " + b.getWinner());
           

        
        
       
    }
}
