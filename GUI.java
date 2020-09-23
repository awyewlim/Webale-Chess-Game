import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Image.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;

public class GUI extends JFrame{
    public static JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public static JPanel middlePanel = new JPanel(new GridLayout(8,7));
    public static JPanel bottomPanel = new JPanel(new FlowLayout());
    
    public static JMenuBar menuBar = new JMenuBar();
    public static JMenu menu = new JMenu("Webale");
    public static JMenuItem menuItemSave = new JMenuItem("Save Game");
    public static JMenuItem menuItemLoad = new JMenuItem("Load Game");
    public static JMenuItem menuItemRestart = new JMenuItem("Restart");
    public static JFileChooser fileChooser = new JFileChooser();
    
    private static WebaleGame game = new WebaleGame();
    private static ChessBoard chessboard = game.chessboard;
    private static JLabel message = new JLabel("Game start! Team R first.");
    
    // Default constructor. Set title and size of window and add WindowListener to GUI (Lee Wei Jie)
    GUI(){
        super("Chess application");

        setSize(550,700);
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

    // Set new message in bottomPanel (Lee Wei Jie)
    public void setMessage(String word){
        message.setText(word);
    }

    // Add ActionListener to restart item (Lee Wei Jie)
    public void menuItemRestartListener(ActionListener e){
        menuItemRestart.addActionListener(e);
    }

    // Add ActionListener to save item (Lee Wei Jie)
    public void menuItemSaveListener(ActionListener e){
        menuItemSave.addActionListener(e);
    }

    // Add ActionListener to load item (Lee Wei Jie)
    public void menuItemLoadListener(ActionListener e){
        menuItemLoad.addActionListener(e);
    }
    
    // Setup menu (Lee Wei Jie)
    public void menuSetup(){
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
        
    }
    
    // Add icons to chessboard (Lee Wei Jie)
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


}