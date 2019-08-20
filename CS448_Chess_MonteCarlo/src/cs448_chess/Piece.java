/*
   _____      _ _                                 _        ___   ___  __  ___  
  / ____|    | | |                               | |      |__ \ / _ \/_ |/ _ \ 
 | |     __ _| | |_   _ _ __ ___     ___ ___   __| | ___     ) | | | || | (_) |
 | |    / _` | | | | | | '_ ` _ \   / __/ _ \ / _` |/ _ \   / /| | | || |> _ < 
 | |___| (_| | | | |_| | | | | | | | (_| (_) | (_| |  __/  / /_| |_| || | (_) |
  \_____\__,_|_|_|\__,_|_| |_| |_|  \___\___/ \__,_|\___| |____|\___/ |_|\___/ 
 
*/

package cs448_chess;

import java.util.ArrayList;

/**
 *
 * @author callumijohnston
 */
public abstract class Piece {
    int x;
    int y;
    boolean black;
    boolean alive;
    String id;
    String idCode;
    
    public Piece(int x, int y, boolean black){
        this.x = x;
        this.y = y;
        this.black = black;
        alive = true;
    }
    
    public abstract boolean legalMove(Move m, Game g);
    
    public abstract ArrayList<Move> allLegalMoves(Game g);
}
