package src;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

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
        
        
        while(!b.isGameOver()){
            ia.doSomething();
            Thread.sleep(10);
        }

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        Color color = new Color(0, 0, 0);
        if(b.getWinner() == ChessPiece.WHITE_COLOR){
            label.setText("White won");
            color = Color.WHITE;
        }else{
            label.setText("Black won");
            color = Color.BLACK;
        }
        panel.setBackground(color);
        panel.setBounds(frame.getWidth()/2, frame.getHeight()/2, 100, 100);

        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
    }
}
