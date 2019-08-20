/*
 *   _____      _ _                                 _        ___   ___  __  ___  
 *  / ____|    | | |                               | |      |__ \ / _ \/_ |/ _ \ 
 * | |     __ _| | |_   _ _ __ ___     ___ ___   __| | ___     ) | | | || | (_) |
 * | |    / _` | | | | | | '_ ` _ \   / __/ _ \ / _` |/ _ \   / /| | | || |> _ < 
 * | |___| (_| | | | |_| | | | | | | | (_| (_) | (_| |  __/  / /_| |_| || | (_) |
 *  \_____\__,_|_|_|\__,_|_| |_| |_|  \___\___/ \__,_|\___| |____|\___/ |_|\___/ 
 * 
 */
package cs448_chess;

/**
 *
 * @author Callum Johnston
 */
public class LearningSimulation extends Thread {

    BoardPanel bp;

    public LearningSimulation(BoardPanel bp) {
        this.bp = bp;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            //CPU p1 = new CPU(bp.game);
            while (bp.game.winner == 0) {
                //bp.game.playTurn(p1.makeMove(bp.game.findAllLegalMoves()));
                //bp.game.randomMove();
                bp.game.playTurn(bp.p1.playMovePre(bp.game.findAllLegalMoves(), bp.game));
                //bp.repaint();
                pause();
                if (bp.game.winner != 0) {
                    break;
                }
                bp.game.playTurn(bp.p2.playMovePre(bp.game.findAllLegalMoves(), bp.game));
                //System.out.println(bp.game.toString());
                //bp.repaint();
                pause();
            }
            bp.newGame();
            //System.out.println(bp.p2.data.size());
        }
    }

    private void pause() {
        try {
            Thread.sleep(0);
        } catch (Exception e) {
        }
    }

}
