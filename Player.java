import java.util.*;

public class Player{
    private String color;
    
    // Constructor (Teh Jiing Joe)
    Player(String color){
        setColor(color);
    }
    
    // Set color of player (Teh Jiing Joe)
    public void setColor(String color){
        this.color = color;
    }
    
    // Get color of player (Teh Jiing Joe)
    public String getColor(){
        return this.color;
    }
}