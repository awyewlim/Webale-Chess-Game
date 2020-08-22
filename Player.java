import java.util.*;

public class Player{
    private String color;
    
    Player(String color){
        setColor(color);
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getColor(){
        return this.color;
    }
}