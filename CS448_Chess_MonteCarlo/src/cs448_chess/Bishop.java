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
public class Bishop extends Piece {

    public Bishop(int x, int y, boolean black) {
        super(x, y, black);
        super.id = "bishop";
        super.idCode = (black ? "b" : "w") + id.charAt(0);
    }

    public boolean legalMove(Move m, Game g) {
        if (m.x2 < 0 || m.x2 >= g.boardSize || m.y2 < 0 || m.y2 >= g.boardSize) {
            return false;
        }
        if (g.findPiece(m.x2, m.y2, true) != null) {
            return false;
        }
        int ydir = ((m.y1 > m.y2) ? -1 : 1);
        int xdir = ((m.x1 > m.x2) ? -1 : 1);
        for (int i = 1; m.y1 + i * ydir != m.y2; i++) {
            if (g.findPiece(m.x1 + i * xdir, m.y1 + i * ydir) != null) {
                return false;
            }
        }
        if (Math.abs(m.y2-m.y1)==Math.abs(m.x2-m.x1)) return true;
        return false;
    }

    public ArrayList<Move> allLegalMoves(Game g) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 1; y + i < g.boardSize && x + i < g.boardSize; i++) {
            Move m = new Move(x,y,x+i,y+i,!black);
            if(legalMove(m,g)){
                moves.add(m);
            }
        }
        for (int i = -1; y + i >= 0 && x + i >= 0; i--) {
            Move m = new Move(x,y,x+i,y+i,!black);
            if(legalMove(m,g)){
                moves.add(m);
            }
        }
        for (int i = 1; y + i < g.boardSize && x - i >= 0; i++) {
            Move m = new Move(x,y,x-i,y+i,!black);
            if(legalMove(m,g)){
                moves.add(m);
            }
        }
        for (int i = 1; y - i >= 0 && x + i < g.boardSize; i++) {
            Move m = new Move(x,y,x+i,y-i,!black);
            if(legalMove(m,g)){
                moves.add(m);
            }
        }
        return moves;
    }
}
