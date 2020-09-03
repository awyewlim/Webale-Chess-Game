import java.util.*;

public class WebaleGame{
    ChessBoard chessboard;
    Player player1;
    Player player2;
    ArrayList<Player> playerList = new ArrayList<Player>();

    private static Piece queue = null;
    private static Slot temp = null;
    private static int playerTurn = 0;
    private static boolean hasWinner;
    private static boolean canMove = false, reachedEnd = false ;
    private static String type;
    private static  int fromX,fromY, toX, toY, x, y;
    
    WebaleGame(){
        chessboard = new ChessBoard();
        player1 = new Player("B");
        player2 = new Player("R");
        playerList.add(player2);
        playerList.add(player1);
    }
    
    public void restart(){
        chessboard.clear();
        pieceSetup();
        playerTurn = 0;
        hasWinner = false;
    }

    public void pieceSetup(){      
        String[] arrangement1 = {"Plus","Triangle","Chevron","Sun","Chevron","Triangle","Plus"};
        String arrangement2 = "Arrow";
        for(int i = 0; i < chessboard.getHeight(); i++){
            for(int j = 0; j < chessboard.getWidth(); j++){
                if (i == 0){
                    chessboard.addChessPiece(i, j, new Piece(arrangement1[j], player1));
                }
                if (i == 1){
                    chessboard.addChessPiece(i, j, new Piece(arrangement2, player1));
                    j++;
                }
                if (i == 6){
                    chessboard.addChessPiece(i, j, new Piece(arrangement2, player2));
                    j++;
                }
                if (i == 7){
                    chessboard.addChessPiece(i, j, new Piece(arrangement1[j], player2));
                }
            }
        }
    }

    public boolean move(Slot slot){
        // if clicked button has piece
        if(slot.getPiece() != null && movable(slot))
        {
            //if queue is empty
            if(queue == null)
            {
                type = slot.getPiece().getPieceName();
                fromX = slot.getX();
                fromY = slot.getY();
                queue = slot.getPiece();
                temp = slot;
            }
            //if queue is occupied
            else
            {
                if(!queue.getPlayer().equals(slot.getPiece().getPlayer()) && canMove)
                {
                    temp.setPiece(null);
                    slot.setPiece(queue);
                    queue = null;
                    temp = null;
                    playerTurn++;
                    return true;

                }
                queue = null;
                temp = null;
            }
        }
        //if clicked button has no piece
        else
        {
            if(temp != null)
            {
                toX = slot.getX();
                toY = slot.getY();
                canMove = validMove(type,fromX,fromY,toX,toY);
                if(canMove)
                {
                    slot.setPiece(queue);
                    queue = null;
                    temp.setPiece(null);
                    temp = null;
                    playerTurn++;
                    return true;
                }
            }
            queue = null;
            temp = null;
        }
        return false;
    }

    public boolean movable(Slot slot){
        if(slot.getPiece().getPlayer().equals(getPlayerTurn())){
            return true;
        }
        return false;
    }

    public boolean validMove(String type, int fromX, int fromY, int toX, int toY)
    {
        if(type.equals("Arrow"))
        {
            if(fromY == toY)
            {
                x = fromX - toX;
                y = fromY - toY;
                if(reachedEnd)
                {
                   
                }
                else
                {
                    if(x == 1 || (x == 2 && chessboard.getSlot(fromX - 1,fromY).getPiece() == null)){
                        return true;
                    }
                }
            }
        }

        else if (type.equals("Plus")){
            return true;
        }
        return false;
    }

    public String getWinner(){
        int numOfSun = 0;
        String winner = null;
        for(int i = 0; i < chessboard.getBoardSize(); i++){
            Slot slot = chessboard.getSlot(i);
            Piece piece = slot.getPiece();
            if(piece != null){
                if(piece.getPieceName() == "Sun"){
                    winner = piece.getPlayer().getColor();
                    hasWinner = true;
                    numOfSun++;
                }
            }
        }
        if(numOfSun == 2){
            winner = null;
            hasWinner = false;
        }
        return winner;        
    }

    public Player getPlayerTurn(){
        if(hasWinner){
            return playerList.get((playerTurn - 1) % 2);
        }
        else{
            return playerList.get(playerTurn % 2);
        }
    }

}
