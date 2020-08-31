import java.util.*;

public class Slot{
    private Piece piece;
    private int x;
    private int y;
    
    Slot(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    Slot(int x, int y, Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
    
    public void setPiece(Piece piece){
        this.piece = piece;
    }
    
    public Piece getPiece(){
        return this.piece;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int y) 
    { 
        this.y = y; 
    } 
}
