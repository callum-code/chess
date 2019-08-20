/*
   _____      _ _                                 _        ___   ___  __  ___  
  / ____|    | | |                               | |      |__ \ / _ \/_ |/ _ \ 
 | |     __ _| | |_   _ _ __ ___     ___ ___   __| | ___     ) | | | || | (_) |
 | |    / _` | | | | | | '_ ` _ \   / __/ _ \ / _` |/ _ \   / /| | | || |> _ < 
 | |___| (_| | | | |_| | | | | | | | (_| (_) | (_| |  __/  / /_| |_| || | (_) |
  \_____\__,_|_|_|\__,_|_| |_| |_|  \___\___/ \__,_|\___| |____|\___/ |_|\___/ 
 
*/

package cs448_chess;

/**
 *
 * @author Callum Johnston
 */

public class BoardMove{
    public Move move;
    public String board;
    public int weight;

    public BoardMove(Move move, String board) {
        this.move = move;
        this.board = board;
        this.weight = 0;
    }
    
    public boolean equals(BoardMove bm){
        return bm.move.equals(move) && bm.board.equals(board);
    }
    
}
