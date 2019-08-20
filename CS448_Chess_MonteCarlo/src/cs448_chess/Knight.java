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
public class Knight extends Piece {

    public Knight(int x, int y, boolean black) {
        super(x, y, black);
        super.id = "horse";
        super.idCode = (black ? "b" : "w") + id.charAt(0);
    }

    public boolean legalMove(Move m, Game g) {
        if (m.x2 < 0 || m.x2 >= g.boardSize || m.y2 < 0 || m.y2 >= g.boardSize) {
            return false;
        }
        if (g.findPiece(m.x2, m.y2, true) != null) {
            return false;
        }
        if (m.y1 + 2 == m.y2 || m.y1 - 2 == m.y2) {
            if (m.x2 == m.x1 + 1 || m.x2 == m.x1 - 1) {
                return true;
            }
        }
        if (m.x1 + 2 == m.x2 || m.x1 - 2 == m.x2) {
            if (m.y2 == m.y1 + 1 || m.y2 == m.y1 - 1) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Move> allLegalMoves(Game g) {
        ArrayList<Move> moves = new ArrayList<>();
        int[][] potentialMoves = {{x + 1, y + 2}, {x - 1, y + 2}, {x - 2, y + 1}, {x + 2, y + 1},
        {x + 1, y - 2}, {x - 1, y - 2}, {x - 2, y - 1}, {x + 2, y - 1}};
        for (int[] potentialMove : potentialMoves) {
            Move m = new Move(x, y, potentialMove[0], potentialMove[1], !black);
            if (m.x2 >= 0 && m.x2 < g.boardSize && m.y2 >= 0 && m.y2 < g.boardSize
                    && g.findPiece(m.x2, m.y2, true) == null) {
                moves.add(m);
            }
        }
        return moves;
    }
}
