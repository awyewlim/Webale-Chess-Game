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
    private static JPanel mainPanel = new JPanel(new GridLayout(8,7));
    private static JPanel subPanel = new JPanel(new FlowLayout());
    private static JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    private static JLabel message = new JLabel("Game start! Red first.");
    
    private static ArrayList<JButton> buttons = new ArrayList<JButton>();
    
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenu menuItemGame = new JMenu("Webale");
    private static JMenuItem menuItemSave = new JMenuItem("Save Game");
    private static JMenuItem menuItemLoad = new JMenuItem("Load Game");
    private static JMenuItem menuItemRest = new JMenuItem("Restart");
    private static JFileChooser fileChooser = new JFileChooser();
    
    private static WebaleGame game = new WebaleGame();
    private static ChessBoard chessboard = game.chessboard;
    
    
    Main(){
        super("Chess application");
        
        setup();
        showBoard();
        
        setSize(550,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    
    private void menuSetup(){
        menuItemGame.add(menuItemSave);
        menuItemGame.add(menuItemLoad);
        menuItemGame.add(menuItemRest);
        menuBar.add(menuItemGame);
        
        subPanel.add(message);
        topPanel.add(menuBar);
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(subPanel, BorderLayout.SOUTH);
        this.add(topPanel, BorderLayout.NORTH);
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
                icon = loadImage(chessboard.getIcon(piece.getPieceName() + piece.getPlayer().getColor()), false);
            }
            else{
                icon = loadImage(chessboard.getIcon(piece.getPieceName() + piece.getPlayer().getColor()), true);
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
        mainPanel.add(btn);
    }
    
    public void showBoard(){
        for(int i = 0; i < chessboard.getBoardSize(); i++){
            buttonSetup(i);
        }
    }    
    
    public void refresh(){
        mainPanel.removeAll();
        buttons.clear();
        showBoard();
        revalidate();
        repaint();
    }
    
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        Slot slot = chessboard.getSlot(buttons.indexOf(button));
        boolean hasMoved = game.move(slot);
        if(hasMoved){
            String winner = game.getWinner();
            if(winner != null){
                message.setText(winner + " wins!");
            }
            else{
                String team = game.getPlayerTurn().getColor();
                if(team.equals("R")){
                    message.setText("Red, It's your turn!");
                }
                else{
                    message.setText("Blue, It's your turn!");
                }
                chessboard.reverse();
            }
            refresh();
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
