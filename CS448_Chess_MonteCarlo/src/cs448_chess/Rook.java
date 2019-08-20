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
public class Rook extends Piece {

    public Rook(int x, int y, boolean black) {
        super(x, y, black);
        super.id = "rook";
        super.idCode = (black ? "b" : "w") + id.charAt(0);
    }

    public boolean legalMove(Move m, Game g) {
        if (g.findPiece(m.x2, m.y2, true) != null) return false;
        if (m.x1 == m.x2) {
            for (int i = m.y1 + ((m.y1 > m.y2) ? -1 : 1); i != m.y2; i += (m.y1 > m.y2) ? -1 : 1) {
                if (g.findPiece(m.x1, i) != null) {
                    return false;
                }
            }
            return true;
        }
        if (m.y1 == m.y2) {
            for (int i = m.x1 + ((m.x1 > m.x2) ? -1 : 1); i != m.x2; i += (m.x1 > m.x2) ? -1 : 1) {
                if (g.findPiece(i, m.y1) != null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public ArrayList<Move> allLegalMoves(Game g) {
        ArrayList<Move> moves = new ArrayList<>();
        for(int i = 0;i<g.boardSize;i++){
            Move m = new Move(x,y,x,i,!black);
            if(i!=y && legalMove(m,g)) moves.add(m);
        }
        for(int i = 0;i<g.boardSize;i++){
            Move m = new Move(x,y,i,y,!black);
            if(i!=x && legalMove(m,g)) moves.add(m);
        }
        return moves;
    }
}
