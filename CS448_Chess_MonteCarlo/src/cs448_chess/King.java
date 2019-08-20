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
public class King extends Piece {
    
    boolean castle = false;

    public King(int x, int y, boolean black) {
        super(x, y, black);
        id = "king";
        super.idCode = (black ? "b" : "w") + id.charAt(0);
    }

    public boolean legalMove(Move m, Game g) {
        if (m.x2 < 0 || m.x2 >= g.boardSize || m.y2 < 0 || m.y2 >= g.boardSize) {
            return false;
        }
        if (m.x1 == m.x2 && m.y1 == m.y2){
            return false;
        }
        if (Math.abs(m.x2 - m.x1) <= 1 && Math.abs(m.y2 - m.y1) <= 1) {
            if (g.findPiece(m.x2, m.y2, true) == null) {
                return true;
            }
        }
        if (m.y1 == (m.p1?7:0) && m.x1 == 3 && m.x2==m.x1-2 && g.findPiece(m.x1-2,m.y1)==null 
                && g.findPiece(m.x1-1,m.y1)==null && g.findPiece(m.x1-3,m.y1)!=null 
                && g.findPiece(m.x1-3,m.y1).id.equals("rook")){
            return true;
        }
        if (m.y1 == (m.p1?7:0) && m.x1 == 3 && m.x2==m.x1+2 && g.findPiece(m.x1+3,m.y1)==null 
                 && g.findPiece(m.x1+2,m.y1)==null  && g.findPiece(m.x1+1,m.y1)==null 
                && g.findPiece(m.x1+4,m.y1)!=null && g.findPiece(m.x1+4,m.y1).id.equals("rook")){
            return true;
        }
        return false;
    }

    public ArrayList<Move> allLegalMoves(Game g) {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move m = new Move(x,y,x+i-1,y+j-1,!black);
                if(legalMove(m,g))moves.add(m);
            }
        }
        Move m = new Move(x,y,x-2,y,!black);
        if(legalMove(m,g))moves.add(m);
        m = new Move(x,y,x+2,y,!black);
        if(legalMove(m,g))moves.add(m);
        return moves;
    }
}
