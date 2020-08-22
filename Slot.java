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
}