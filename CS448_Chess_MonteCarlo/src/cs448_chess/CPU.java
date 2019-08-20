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
public class CPU {

    Game game;

    public CPU(Game game) {
        this.game = game;
    }

    Move makeMove(ArrayList<Move> moves) {
        Move bestMove = moves.get(0);
        int max = winProbability(bestMove);
        for(Move m:moves.subList(1,moves.size())){
            int probability = winProbability(m);
            if (probability>max){
                max = probability;
                bestMove = m;
            }
        }
        System.out.println(max);
        return bestMove;
    }

    int winProbability(Move m) {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            total += playGame(m) ? 1 : 0;
        }
        return total;
    }

    boolean playGame(Move m) {
        Game simulation = game.getGame();
        simulation.playTurn(m);
        while (simulation.winner == 0) {
            simulation.randomMove();
        }
        return simulation.winner == -1;
    }

}
