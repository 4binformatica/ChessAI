package src;

import javax.swing.JFrame;

import Graphics.GraphicBoard;

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
        
        
        while(true){
            ia.doSomething();
            Thread.sleep(1000);
        }
           

        
        
       
    }
}
