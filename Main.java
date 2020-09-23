import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Image.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;

public class Main implements ActionListener{
    
    private static GUI gui;
    
    private static ArrayList<JButton> buttons = new ArrayList<JButton>();
    private static WebaleGame game = new WebaleGame();
    private static ChessBoard chessboard = game.chessboard;
    
    // Default constructor (Leong Jean Cheong, Lee Wei Jie, Teh Jiing Joe)
    Main(){

        gui = new GUI();
        setup();
        showBoard();
        
        gui.menuItemRestartListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                game.restart();
                refresh(false);
                gui.setMessage("Game start! Team R first.");
            }
        });

        gui.menuItemSaveListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int reply = JOptionPane.showConfirmDialog(null, "Do you want to save game?");
                if(reply == JOptionPane.YES_OPTION){
                    try{
                        game.save();
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Fail to save! Please try again...");
                    }
                }
            }
        });

        gui.menuItemLoadListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int reply = JOptionPane.showConfirmDialog(null, "Do you want to load previous game?");
                if(reply == JOptionPane.YES_OPTION){
                    try{
                        game.load();                
                        refresh(false);
                        String team = game.getPlayerTurn().getColor();
                        gui.setMessage("Team " + team + ", it's your turn!");
                        JOptionPane.showMessageDialog(null, "Previous saved game loaded successfully!");
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Game failed to load! Please try again...");
                    }
                }
            }
        });
        gui.setVisible(true);

    }

    // Setup menu, icon and piece (Aw Yew Lim)
    public void setup(){
        gui.menuSetup();
        gui.iconSetup();
        game.pieceSetup();
    }
    
    // Create button with piece icon (Aw Yew Lim)
    public void buttonSetup(int i){
        Slot slot = chessboard.getSlot(i);
        Piece piece = slot.getPiece();
        Image icon;
        if(piece != null){
            if(piece.getPlayer().equals(game.getPlayerTurn())){
                if(piece.getPieceName().equals("Arrow") && piece.getReachEnd()){
                    icon = loadImage(chessboard.getIcon(piece.getPieceName() + piece.getPlayer().getColor()), true);
                }
                else{
                    icon = loadImage(chessboard.getIcon(piece.getPieceName() + piece.getPlayer().getColor()), false);
                }
            }
            else{
                if(piece.getPieceName().equals("Arrow") && piece.getReachEnd()){
                    icon = loadImage(chessboard.getIcon(piece.getPieceName() + piece.getPlayer().getColor()), false);
                }
                else{
                    icon = loadImage(chessboard.getIcon(piece.getPieceName() + piece.getPlayer().getColor()), true);
                }
            }
        }
        else{
            icon = null;
        }
        
        JButton btn = new JButton();
        if(icon != null){
            btn.setIcon(new ImageIcon(icon));
        }
        btn.addActionListener(this);
        buttons.add(btn);
        gui.middlePanel.add(btn);
    }
    
    // Do button setup for every slot of chessboard (Aw Yew Lim)
    public void showBoard(){
        for(int i = 0; i < chessboard.getBoardSize(); i++){
            buttonSetup(i);
        }
    }    
    
    // Refresh the chessboard after a player has made a move (Aw Yew Lim)
    public void refresh(boolean endgame){
        gui.middlePanel.removeAll();
        buttons.clear();
        showBoard();
        gui.revalidate();
        gui.repaint();
        if(endgame){
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).removeActionListener(this);
            }
        }
    }
    
    // Perform action after a move has been made (Aw Yew Lim)
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        Slot slot = chessboard.getSlot(buttons.indexOf(button));
        boolean hasMoved = game.move(slot);
        if(hasMoved){
            String winner = game.getWinner();
            if(winner != null){
                gui.setMessage("Team " + winner + " wins!");
                refresh(true);
            }
            else{
                String team = game.getPlayerTurn().getColor();
                gui.setMessage("Team " + team + ", it's your turn!");
                if(game.getPlayerTurnNum() % 4 == 0){
                    game.changeState();
                }
                chessboard.reverse();
                refresh(false);
            }
        }
        
    }
    
    // Load image of icon from given path (Aw Yew Lim)
    private Image loadImage(String path, boolean flip){
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(path));
        }
        catch(IOException e){}
        
        if(flip){
            AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
            tx.translate(0, -image.getHeight(null));
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = op.filter(image, null);
        }
        image = resize(image, 40, 40);
        
        return image;
    }
    
    // Resize image of icon to fit the button (Aw Yew Lim)
    public static BufferedImage resize(BufferedImage image, int newW, int newH) { 
        Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    }
    
    // Main function
    public static void main(String[] args){
        new Main();
    }
}

