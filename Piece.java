import java.util.*;

public class Piece{
    private String pieceName;
    private Player player;
    
    Piece(String pieceName, Player player){
        this.pieceName = pieceName;
        this.player = player;
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
}