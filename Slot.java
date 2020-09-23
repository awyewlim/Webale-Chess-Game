import java.util.*;

public class Slot{
    private Piece piece;
    private int x;
    private int y;
    
    // Constructor (Aw Yew Lim)
    Slot(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Constructor (Aw Yew Lim)
    Slot(int x, int y, Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
    
    // Set piece of this slot (Aw Yew Lim)
    public void setPiece(Piece piece){
        this.piece = piece;
    }
    
    // Set vertical position of this slot (Chan Jun Yang)
    public void setX(int x){
        this.x = x;
    }
    
    // Set horizontal position of this slot (Chan Jun Yang)
    public void setY(int y){
        this.y = y;
    }
    
    // Get piece of this slot (Aw Yew Lim)
    public Piece getPiece(){
        return this.piece;
    }
    
    // Get X of this slot (Aw Yew Lim)
    public int getX(){
        return this.x;
    }
    
    // Get Y of this slot (Aw Yew Lim)
    public int getY(){
        return this.y;
    }
}
