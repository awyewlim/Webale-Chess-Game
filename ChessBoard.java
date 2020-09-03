import java.util.*;

public class ChessBoard{
    private int width;
    private int height;
    private ArrayList<Slot> chessSlot = new ArrayList<Slot>();
    private ArrayList<String> B = new ArrayList<String>();
    private ArrayList<String> R = new ArrayList<String>();
    
    // Default Constructor
    ChessBoard(){
        setBoardSize(7, 8);
        addChessSlot();
    }
    
    // Custom Constructor
    ChessBoard(int width, int height){
        setBoardSize(width, height);
        addChessSlot();
    }
    
    public void clear(){
        chessSlot.clear();
        addChessSlot();
    }
    
    public void setBoardSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    public void addChessSlot(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Slot slot = new Slot(i, j);
                chessSlot.add(slot);
            }    
        }
    }
    
    public void addChessPiece(int x, int y, Piece piece){
        chessSlot.get(x*width + y).setPiece(piece);
    }
    
    public void addRedIcon(String iconName){
        R.add(iconName);
    }
    
    public void addBlueIcon(String iconName){
        B.add(iconName);
    }
    
    public Slot getSlot(int i){
        return chessSlot.get(i);
    }
    
    public Slot getSlot(int x, int y){
        return chessSlot.get(x*width + y);
    }
    
    public void reverse(){
        Collections.reverse(chessSlot);
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                chessSlot.get((i*width) + j).setX(i);
                chessSlot.get((i*width) + j).setY(j);
            }
        }
    }
    
    public String getIcon(String icon){
        icon = "Assets/" + icon + ".png";
        return icon;
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public int getHeight(){
        return this.height;
    }
    
    public int getBoardSize(){
        return this.height * this.width;
    }
}
