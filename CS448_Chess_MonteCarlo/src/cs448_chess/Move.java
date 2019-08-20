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
 * @author callumijohnston
 */
public class Move {
    int x1;
    int y1;
    int x2;
    int y2;
    boolean p1;

    public Move(int x1, int y1, int x2, int y2, boolean p1) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.p1 = p1;
    }
    
    public boolean equals(Move m){
        return x1==m.x1 && y1==m.y1 && x2==m.x2 && y2==m.y2 && p1==m.p1;
    }

    @Override
    public String toString() {
        return x1 + " " + y1 + " " + x2 + " " + y2 + " " + p1;
    }
    
    
}
