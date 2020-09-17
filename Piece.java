import java.util.*;

public class Piece{
    private String pieceName;
    private Player player;
    private boolean reachEnd;
    
    Piece(String pieceName, Player player, boolean reachEnd){
        this.pieceName = pieceName;
        this.player = player;
        this.reachEnd = reachEnd;
    }

    public void setPieceName(String pieceName){
        this.pieceName = pieceName;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public String getPieceName(){
        return this.pieceName;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public void setReachEnd(boolean reachEnd){
        this.reachEnd = reachEnd;
    }
    
    public boolean getReachEnd(){
        return this.reachEnd;
    }
}
