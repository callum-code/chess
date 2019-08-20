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
public class Node {
    Move move;
    int weight;
    Node lChild;
    Node rSibling;
    String ID = "";
    String lcID = "";
    String rsID = "";

    public Node(Move m) {
        this.move = m;
        weight = 0;
    }
    
    public Node(Move m, int weight){
        this.move = m;
        this.weight = weight;
    }
    
    public boolean equals(Node n){
        return move.x1==n.move.x1 && move.y1==n.move.y1 && move.x2==n.move.x2 && move.y2==n.move.y2 && move.p1==n.move.p1;
    }
}
