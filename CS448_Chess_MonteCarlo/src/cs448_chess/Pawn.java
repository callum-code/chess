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
public class Pawn extends Piece {

    public Pawn(int x, int y, boolean black) {
        super(x, y, black);
        super.id = "pawn";
        super.idCode = (black ? "b" : "w") + id.charAt(0);
    }

    public boolean legalMove(Move m, Game g) {
        if ((!m.p1 && m.y2 >= g.boardSize) || (m.p1 && m.y2 < 0)) {
            return false;
        }
        if (m.x2 < 0 || m.x2 >= g.boardSize) {
            return false;
        }
        if (m.x1 == m.x2 && m.y1 + (m.p1 ? -1 : 1) == m.y2 && g.findPiece(m.x2, m.y2) == null) {
            return true;
        }
        if (Math.abs(m.x2 - m.x1) == 1 && m.y1 + (m.p1 ? -1 : 1) == m.y2
                && g.findPiece(m.x2, m.y2, false) != null && g.findPiece(m.x2, m.y2, false).alive) {
            return true;
        }
        if (m.x1==m.x2 && m.y1 == 1 + 5 * (m.p1 ? 1 : 0) && m.y2 == 3 + (m.p1 ? 1 : 0) 
                && g.findPiece(m.x1, m.y1 + (m.p1 ? -1 : 1)) == null
                && g.findPiece(m.x2, m.y2) == null) {
            return true;
        }
        return false;
    }
    
    public ArrayList<Move> allLegalMoves(Game g) {
        ArrayList<Move> moves = new ArrayList<>();
        int player = black ? 1 : -1;
        int[][] potentialMoves = {{x,y+player},{x,y+2*player},{x-1,y+player},{x+1,y+player}};
        for (int[] potentialMove : potentialMoves) {
            Move m = new Move(x,y,potentialMove[0],potentialMove[1],!black);
            if(legalMove(m,g)) moves.add(m);
        }
        return moves;
    }

}
