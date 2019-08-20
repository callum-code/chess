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
 * @author Callum Johnston
 */
public class Learner {

    ArrayList<BoardMove> data;
    ArrayList<BoardMove> gameData;
    
    public Learner(){
        data  = new ArrayList<>();
    }
    
    public Move recordRandom(ArrayList<Move> moves, Game g){
        String b = g.toString();
        Move bestMove = moves.get((int)(Math.random()*moves.size()));
        moveMade(bestMove,b);
        return bestMove;
    }
    
    public Move playMove(ArrayList<Move> moves, Game g){
        String b = g.toString();
        CPU monte= new CPU(g);
        //Move bestMove = moves.get((int)(Math.random()*(moves.size()-1)));
        Move bestMove = monte.makeMove(moves);
        int max = 0;
        try{
            max = findMove(new BoardMove(bestMove,b)).weight;
        } catch (NullPointerException np){}
        for (Move m:moves){
            int value = 0;
            try{
                value = findMove(new BoardMove(m,b)).weight;
            } catch (NullPointerException np){}
            System.out.println("v: "+value);
            if (value>max){
                max = value;
                bestMove = m;
            }
        }
//        System.out.println("moves: "+moves.size());
//        System.out.println(moves.get(0));
//        System.out.println(bestMove);
        System.out.println("Weight: "+max);
        moveMade(bestMove,b);
        return bestMove;
    }
    
    public Move playMovePre(ArrayList<Move> moves, Game g){
        String b = g.toString();
        Move bestMove = moves.get((int)(Math.random()*(moves.size()-1)));
        //Move bestMove = monte.makeMove(moves);
        int max = 0;
        try{
            max = findMove(new BoardMove(bestMove,b)).weight;
        } catch (NullPointerException np){}
        for (Move m:moves){
            int value = 0;
            try{
                value = findMove(new BoardMove(m,b)).weight;
            } catch (NullPointerException np){}
            //System.out.println("v: "+value);
            if (value>max){
                max = value;
                bestMove = m;
            }
        }
//        System.out.println("moves: "+moves.size());
//        System.out.println(moves.get(0));
//        System.out.println(bestMove);
        System.out.println("Weight: "+max);
        moveMade(bestMove,b);
        return bestMove;
    }

    public boolean goodMove(Move m, String b){
        BoardMove bm1 = new BoardMove(m,b);
        for (BoardMove bm: data){
            if (bm1.equals(bm)) return true;
        }
        return false;
    }
    
    public BoardMove findMove(BoardMove bm){
        for (BoardMove bmi: data){
            if (bm.equals(bmi)) return bmi;
        }
        return null;
    }
    
    public void newGame() {
        gameData = new ArrayList<>();
    }

    public void endGame(boolean won) {
            //System.out.println("***********************************");
            for (BoardMove bm : gameData) {
                BoardMove thisMove = findMove(bm); 
                if (thisMove==null){
                    data.add(bm);
                } else {
                    thisMove.weight+=won?1:-1;
                }
            }
        
    }

    public void moveMade(Move m, String b) {
        gameData.add(new BoardMove(m, b));
    }

    public void addMove(Move m, String b) {
        data.add(new BoardMove(m, b));
    }
}
