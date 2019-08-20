/*
   _____      _ _                                 _        ___   ___  __  ___  
  / ____|    | | |                               | |      |__ \ / _ \/_ |/ _ \ 
 | |     __ _| | |_   _ _ __ ___     ___ ___   __| | ___     ) | | | || | (_) |
 | |    / _` | | | | | | '_ ` _ \   / __/ _ \ / _` |/ _ \   / /| | | || |> _ < 
 | |___| (_| | | | |_| | | | | | | | (_| (_) | (_| |  __/  / /_| |_| || | (_) |
  \_____\__,_|_|_|\__,_|_| |_| |_|  \___\___/ \__,_|\___| |____|\___/ |_|\___/ 
 
*/

package cs448_chess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author callumijohnston
 */
public class MouseMovement extends MouseAdapter {
    
    private BoardPanel board;
    private ViewFrame frame;
    
    MouseMovement(BoardPanel board, ViewFrame frame){
        this.frame = frame;
        this.board = board;
    }

    public void mousePressed(MouseEvent evt) {
        board.squareSelected(evt.getX(), evt.getY()-24);
    }

    public void mouseReleased(MouseEvent evt) {
        board.resetSquares();
        frame.winnerLabel.setText(board.checkWin());
    }
}
