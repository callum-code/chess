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
public class Game {

    Square[][] squares;
    Piece[] blackPieces;
    Piece[] whitePieces;
    boolean whiteTurn;
    int boardSize = 8;
    int winner;

    public Game() {
        squares = new Square[boardSize][boardSize];
        blackPieces = new Piece[boardSize * 2];
        whitePieces = new Piece[boardSize * 2];
        whiteTurn = true;
        winner = 0;
        initPieces();
    }

    public void playTurn(Move m) {

        if (findPiece(m.x2, m.y2, false) != null) {
            findPiece(m.x2, m.y2, false).alive = false;
        }
        Piece p = findPiece(m.x1, m.y1, true);
        try {
            p.x = m.x2;
            p.y = m.y2;
            if (p.id.equals("king") && m.x2 == m.x1-2){
                findPiece(m.x1-3,m.y1).x+=2;
            }
            if (p.id.equals("king") && m.x2 == m.x1+2){
                findPiece(m.x1+4,m.y1).x-=3;
            }
        } catch (NullPointerException exc) {
            System.out.println("nullpt!!!!!!!!!:" + m.x1 + ", " + m.y1 + "=>"
                    + m.x2 + ", " + m.y2 + " " + p);
        }
        switchTurn();
        winner = checkWinner();

    }

    public void randomMove() {
        ArrayList<Move> moves = findAllLegalMoves();
        if (moves.isEmpty()) {
            winner = checkWinner();
            return;
        }
        for (Move m : moves) {
            if (findPiece(m.x2, m.y2, m.p1!=whiteTurn) != null
                    && findPiece(m.x2, m.y2, m.p1!=whiteTurn).id.equals("king")) {
                playTurn(m);
                return;
            }
        }
        int randomInt = (int) (moves.size() * Math.random());
        playTurn(moves.get(randomInt));
    }

    public void switchTurn() {
        if (whiteTurn) {
            whiteTurn = false;
        } else {
            whiteTurn = true;
        }
    }

    public Piece findPiece(int x, int y) {
        Piece p = null;
        for (int i = 0; i < whitePieces.length; i++) {
            if (whitePieces[i].x == x && whitePieces[i].y == y && whitePieces[i].alive) {
                p = whitePieces[i];
            }
            if (blackPieces[i].x == x && blackPieces[i].y == y && blackPieces[i].alive) {
                p = blackPieces[i];
            }
        }
        return p;
    }

    public Piece findPiece(int x, int y, boolean ofTurn) {
        Piece p = null;
        for (int i = 0; i < whitePieces.length; i++) {
            if (whiteTurn == ofTurn) {
                if (whitePieces[i].x == x && whitePieces[i].y == y && whitePieces[i].alive) {
                    p = whitePieces[i];
                }
            } else {
                if (blackPieces[i].x == x && blackPieces[i].y == y && blackPieces[i].alive) {
                    p = blackPieces[i];
                }
            }
        }
        return p;
    }

    public ArrayList<Move> findAllLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<>();
        Piece[] pieces;
        if (whiteTurn) {
            pieces = whitePieces;
        } else {
            pieces = blackPieces;
        }
        for (Piece p : pieces) {
            if (p.alive) {
                legalMoves.addAll(p.allLegalMoves(this));
            }
        }
        return legalMoves;
    }

    public int checkWinner() {
        if (!blackPieces[blackPieces.length - 1].alive) {
            return 1;
        }
        if (!whitePieces[whitePieces.length - 1].alive) {
            return -1;
        }
        return 0;
    }

    public Game getGame() {
        Game newGame = new Game();
        for (int i = 0; i < blackPieces.length; i++) {
            newGame.blackPieces[i] = new Pawn(blackPieces[i].x, blackPieces[i].y, true);
            newGame.whitePieces[i] = new Pawn(whitePieces[i].x, whitePieces[i].y, false);
        }
        newGame.whiteTurn = whiteTurn;
        newGame.winner = winner;
        return newGame;
    }

    public void initPieces() {
        initPawns();
        initRooks();
        initKnights();
        initBishops();
        initQueens();
        initKings();
    }

    public void initPawns() {
        for (int i = 0; i < boardSize; i++) {
            blackPieces[i] = new Pawn(i, 1, true);
            whitePieces[i] = new Pawn(i, boardSize - 2, false);
        }
    }

    public void initRooks() {
        blackPieces[8] = new Rook(0, 0, true);
        blackPieces[9] = new Rook(7, 0, true);
        whitePieces[8] = new Rook(0, 7, false);
        whitePieces[9] = new Rook(7, 7, false);
    }

    public void initKnights() {
        blackPieces[10] = new Knight(1, 0, true);
        blackPieces[11] = new Knight(6, 0, true);
        whitePieces[10] = new Knight(1, 7, false);
        whitePieces[11] = new Knight(6, 7, false);
    }

    public void initBishops() {
        blackPieces[12] = new Bishop(2, 0, true);
        blackPieces[13] = new Bishop(5, 0, true);
        whitePieces[12] = new Bishop(2, 7, false);
        whitePieces[13] = new Bishop(5, 7, false);
    }

    public void initQueens() {
        blackPieces[14] = new Queen(4, 0, true);
        whitePieces[14] = new Queen(4, 7, false);
    }

    public void initKings() {
        blackPieces[15] = new King(3, 0, true);
        whitePieces[15] = new King(3, 7, false);
    }
    
    public String toString() {
        String returnMe = "Board: \n";
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                Piece p = findPiece(j,i);
                if (p==null){
                    returnMe+="[  ]";
                } else{
                    returnMe+="["+p.idCode+"]";
                }
            }
            returnMe+="\n";
        }
        return returnMe;
    }

}
