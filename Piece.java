import java.util.*;

public class Piece{
    private String pieceName;
    private Player player;
    private boolean reachEnd;
    
    // Constructor (Chan Jun Yang, Aw Yew Lim)
    Piece(String pieceName, Player player, boolean reachEnd){
        this.pieceName = pieceName;
        this.player = player;
        this.reachEnd = reachEnd;
    }

    // Set piece name (Aw Yew Lim)
    public void setPieceName(String pieceName){
        this.pieceName = pieceName;
    }
    
    // Set player (Aw Yew Lim)
    public void setPlayer(Player player){
        this.player = player;
    }
    
    // Get piece name (Aw Yew Lim)
    public String getPieceName(){
        return this.pieceName;
    }
    
    // Get player (Aw Yew Lim)
    public Player getPlayer(){
        return this.player;
    }

    // Set true or false to reachEnd to determine the Arrow piece has reached the end of the chessboard or not (Chan Jun Yang)
    public void setReachEnd(boolean reachEnd){
        this.reachEnd = reachEnd;
    }
    
    // Get reachEnd to know whether the Arrow piece has reached the end of the chessboard or not (Chan Jun Yang)
    public boolean getReachEnd(){
        return this.reachEnd;
    }
}
