package Graphics;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.MouseInfo;



import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;



import src.Board;
import src.Cell;
import src.ChessPiece;

public class GraphicBoard extends JPanel implements MouseListener{
    Board b;


    Color white = new Color(241, 238, 213);
    Color black = new Color(96, 56, 31);

    Cell selectedCell = null;  


    

    BufferedImage[] blackPieces = new BufferedImage[6];
    BufferedImage[] whitePieces = new BufferedImage[6];

    public GraphicBoard(Board b){
        this.b = b;
        loadImages();
        addMouseListener(this);
    }

    private void loadImages(){
        BufferedImage rootImage = null;
        try {
            rootImage = ImageIO.read(new File("src/Graphics/imgs/pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(rootImage == null) return;


        for(int i = 0; i < 6; i++){
            blackPieces[i] = rootImage.getSubimage(i * 133, 133, 133, 133);
            whitePieces[i] = rootImage.getSubimage(i * 133, 0, 133, 133);
        }
    }

    

    @Override
    public void paint(Graphics g) {
        paintSquares(g);
        paintSelectedCell(g);
        paintPieces(g);
    }

    private void paintSquares(Graphics g){
        for(int i = 0; i < b.getBoard().length; i++){
            for(int j = 0; j < b.getBoard()[i].length; j++){
                if((i + j) % 2 == 0){
                    g.setColor(white);
                }else{
                    g.setColor(black);
                }
                int width = getWidth() / b.getBoard().length;
                int height = getHeight() / b.getBoard()[i].length;
                g.fillRect(i * width, j * height, width, height);
            }
        }
    }

    private void paintPieces(Graphics g){
        int width = getWidth() / b.getBoard().length;
        int height = getHeight() / b.getBoard()[0].length;
        for(int i = 0; i < b.getBoard().length; i++){
            for(int j = 0; j < b.getBoard()[i].length; j++){
                ChessPiece piece = b.getPiece(i, j);
                if(piece != null){
                    if(piece.getColor() == ChessPiece.WHITE_COLOR){
                        g.drawImage(whitePieces[piece.getPiece()], j * width, i * height, width, height, null);
                    }else{
                        g.drawImage(blackPieces[piece.getPiece()], j * width, i * height, width, height, null);
                    }
                }
            }
        }
    }

    private void paintSelectedCell(Graphics g){
        if(selectedCell != null){
            g.setColor(new Color(255, 0, 0, 100));
            int width = getWidth() / b.getBoard().length;
            int height = getHeight() / b.getBoard()[0].length;
            int x = selectedCell.getJ() * width;
            int y = selectedCell.getI() * height;
            
            g.fillRect(x, y, width, height);

            ChessPiece piece = b.getPiece(selectedCell.getI(), selectedCell.getJ());
            if(piece == null) {
                selectedCell = null;
                return;
            }

            ArrayList<Cell> possibleMoves = piece.getPossibleMoves();
            for(Cell c : possibleMoves){
                g.setColor(new Color(0, 255, 0, 100));
                if(c.getPiece() != null)
                    g.setColor(new Color(0, 0, 255, 100));
                x = c.getJ() * width;
                y = c.getI() * height;
                g.fillRect(x, y, width, height);
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width = getWidth() / b.getBoard().length;
        int height = getHeight() / b.getBoard()[0].length;
        int i = y / height;
        int j = x / width;
        selectedCell = b.getBoard()[i][j];
        System.out.println(selectedCell);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

   

    
    

   



}
