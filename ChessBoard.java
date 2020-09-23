import java.util.*;

public class ChessBoard{
    private int width;
    private int height;
    private ArrayList<Slot> chessSlot = new ArrayList<Slot>();
    private ArrayList<String> B = new ArrayList<String>();
    private ArrayList<String> R = new ArrayList<String>();
    
    // Default Constructor (Aw Yew Lim)
    ChessBoard(){
        setBoardSize(7, 8);
        addChessSlot();
    }
    
    // Custom Constructor (Aw Yew Lim)
    ChessBoard(int width, int height){
        setBoardSize(width, height);
        addChessSlot();
    }
    
    // Clear all the slots stored in chessSlot and create new empty slot (Aw Yew Lim)
    public void clear(){
        chessSlot.clear();
        addChessSlot();
    }
    
    // Set chessboard size (Aw Yew Lim)
    public void setBoardSize(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    // Create and add new slot to chessSlot (Aw Yew Lim)
    public void addChessSlot(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Slot slot = new Slot(i, j);
                chessSlot.add(slot);
            }    
        }
    }
    
    // Add piece to a slot (Aw Yew Lim)
    public void addChessPiece(int x, int y, Piece piece){
        chessSlot.get(x*width + y).setPiece(piece);
    }
    
    // Add piece icon of red team (Aw Yew Lim)
    public void addRedIcon(String iconName){
        R.add(iconName);
    }
    
    // Add piece icon of blue team (Aw Yew Lim)
    public void addBlueIcon(String iconName){
        B.add(iconName);
    }
    
    // Get slot from chessSlot (Aw Yew Lim)
    public Slot getSlot(int i){
        return chessSlot.get(i);
    }
    
    // Get slot from chessSlot (Aw Yew Lim)
    public Slot getSlot(int x, int y){
        return chessSlot.get(x*width + y);
    }
    
    // Reverse the slots in chessSlot. This function is to flip the chessboard whenever a team has made a move (Aw Yew Lim)
    public void reverse(){
        Collections.reverse(chessSlot);
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                chessSlot.get((i*width) + j).setX(i);
                chessSlot.get((i*width) + j).setY(j);
            }
        }
    }
    
    // Get icon of given piece name (Aw Yew Lim)
    public String getIcon(String icon){
        icon = "Assets/" + icon + ".png";
        return icon;
    }
    
    // Get width of chessboard (Aw Yew Lim)
    public int getWidth(){
        return this.width;
    }
    
    // Get height of chessboard (Aw Yew Lim)
    public int getHeight(){
        return this.height;
    }
    
    // Get the chessboard size/number of slot of chessboard (Aw Yew Lim)
    public int getBoardSize(){
        return this.height * this.width;
    }
}
