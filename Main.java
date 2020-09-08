import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Image.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;

public class Main extends JFrame implements ActionListener{
    private static JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private static JPanel middlePanel = new JPanel(new GridLayout(8,7));
    private static JPanel bottomPanel = new JPanel(new FlowLayout());
    
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu menu = new JMenu("Webale");
    private static JMenuItem menuItemSave = new JMenuItem("Save Game");
    private static JMenuItem menuItemLoad = new JMenuItem("Load Game");
    private static JMenuItem menuItemRestart = new JMenuItem("Restart");
    private static JFileChooser fileChooser = new JFileChooser();
    
    private static ArrayList<JButton> buttons = new ArrayList<JButton>();
    private static WebaleGame game = new WebaleGame();
    private static ChessBoard chessboard = game.chessboard;
    private static JLabel message = new JLabel("Game start! Team R first.");
    
    Main(){
        super("Chess application");
        
        setup();
        showBoard();
        
        setSize(550,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener( new WindowAdapter(){
            public void windowClosing(WindowEvent e)
            { 
                String windowName = "Exit Application";
                String windowInfo = "Do you want to exit Webale application?";
                int result = JOptionPane.showConfirmDialog(middlePanel, windowInfo, windowName, JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }
    
    private void menuSetup(){
        menu.add(menuItemSave);
        menu.add(menuItemLoad);
        menu.add(menuItemRestart);
        menuBar.add(menu);    
        topPanel.add(menuBar);
        bottomPanel.add(message);
        
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        menuItemRestart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                game.restart();
                refresh(false);
                message.setText("Game start! Team R first.");
            }
        });
    }
    
    public void iconSetup(){
        chessboard.addRedIcon("Assets/PlusR.png");
        chessboard.addRedIcon("Assets/TriangleR.png");
        chessboard.addRedIcon("Assets/ChevronR.png");
        chessboard.addRedIcon("Assets/SunR.png"); 
        chessboard.addRedIcon("Assets/ArrowR.png");
        
        chessboard.addBlueIcon("Assets/PlusB.png");
        chessboard.addBlueIcon("Assets/TriangleB.png");
        chessboard.addBlueIcon("Assets/ChevronB.png");
        chessboard.addBlueIcon("Assets/SunB.png");
        chessboard.addBlueIcon("Assets/ArrowB.png");
    }
    
    public void setup(){
        menuSetup();
        iconSetup();
        game.pieceSetup();
    }
    
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
        middlePanel.add(btn);
    }
    
    public void showBoard(){
        for(int i = 0; i < chessboard.getBoardSize(); i++){
            buttonSetup(i);
        }
    }    
    
    public void refresh(boolean endgame){
        middlePanel.removeAll();
        buttons.clear();
        showBoard();
        revalidate();
        repaint();
        if(endgame){
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).removeActionListener(this);
            }
        }
    }
    
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        Slot slot = chessboard.getSlot(buttons.indexOf(button));
        boolean hasMoved = game.move(slot);
        if(hasMoved){
            String winner = game.getWinner();
            if(winner != null){
                message.setText("Team " + winner + " wins!");
                refresh(true);
            }
            else{
                String team = game.getPlayerTurn().getColor();
                message.setText("Team " + team + ", it's your turn!");
                if(game.getPlayerTurnNum() % 4 == 0){
                    game.changeState();
                }
                chessboard.reverse();
                refresh(false);
            }
        }
        
    }
    
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
    
    public static BufferedImage resize(BufferedImage image, int newW, int newH) { 
        Image tmp = image.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    }
    
    public static void main(String[] args){
        new Main();
    }
}
