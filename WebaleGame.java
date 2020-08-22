import java.util.*;

public class WebaleGame{
    ChessBoard chessboard;
    Player player1;
    Player player2;
    ArrayList<Player> playerList = new ArrayList<Player>();
    
    private static Piece queue = null;
    private static Slot temp = null;
    private static int playerTurn = 0;
    
    WebaleGame(){
        chessboard = new ChessBoard();
        player1 = new Player("B");
        player2 = new Player("R");
        playerList.add(player2);
        playerList.add(player1);
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
    
    public void move(Slot slot){
        // if clicked button has piece
        if(slot.getPiece() != null && movable(slot)){
            //if queue is empty
            if(queue == null){
                queue = slot.getPiece();
                temp = slot;
            }
            //if queue is occupied
            else{
                if(!queue.getPlayer().equals(slot.getPiece().getPlayer())){
                    temp.setPiece(null);
                    slot.setPiece(queue);
                    chessboard.reverse();
                    playerTurn++;
                }
                queue = null;
                temp = null;
            }
        }
        //if clicked button has no piece
        else{
            if(temp != null){
                slot.setPiece(queue);
                queue = null;
                temp.setPiece(null);
                chessboard.reverse();
                playerTurn++;
            }
            temp = null;
        }
    }
    
    public boolean movable(Slot slot){
        if(slot.getPiece().getPlayer().equals(getPlayerTurn())){
            return true;
        }
        return false;
    }
    
    public Player getPlayerTurn(){
        return playerList.get(playerTurn%2);
    }
   
}