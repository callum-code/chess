/*
   _____      _ _                                 _        ___   ___  __  ___  
  / ____|    | | |                               | |      |__ \ / _ \/_ |/ _ \ 
 | |     __ _| | |_   _ _ __ ___     ___ ___   __| | ___     ) | | | || | (_) |
 | |    / _` | | | | | | '_ ` _ \   / __/ _ \ / _` |/ _ \   / /| | | || |> _ < 
 | |___| (_| | | | |_| | | | | | | | (_| (_) | (_| |  __/  / /_| |_| || | (_) |
  \_____\__,_|_|_|\__,_|_| |_| |_|  \___\___/ \__,_|\___| |____|\___/ |_|\___/ 
 
*/

package cs448_chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author callumijohnston
 */
public class BoardPanel extends JPanel {

    int sqLength;
    Piece selected;
    Game game;
    //CPU p2;
    Learner p2;
    Learner p1;

    public BoardPanel() {
        game = new Game();
        int panelSize = Math.min(100 * game.boardSize, 700);
        setSize(panelSize, panelSize);
        sqLength = this.getWidth() / game.squares.length;
        selected = null;
        initSquares();
        //p2 = new CPU(game);
        System.out.println(game.toString());
        p2 = new Learner();
        p1 = new Learner();
        p2.newGame();
        p1.newGame();
    }

    public void newGame() {
        game = new Game();
        game.blackPieces = new Piece[game.squares.length * 2];
        game.whitePieces = new Piece[game.squares.length * 2];
        selected = null;
        game.whiteTurn = true;
        game.winner = 0;
        //p2 = new CPU(game);
        initSquares();
        game.initPieces();
        p2 = new Learner();
        p1 = new Learner();
        p2.newGame();
        p1.newGame();
        repaint();
    }
    
    public void learn(){
        LearningSimulation simulation = new LearningSimulation(this);
        simulation.run();
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        initBoard(g2D);
        placePieces(g2D);
    }

    public void squareSelected(int x, int y) {
        if (game.winner != 0) {
            return;
        }
        for (int i = 0; i < game.squares.length; i++) {
            for (int j = 0; j < game.squares.length; j++) {
                if (game.squares[i][j].containsPt(x, y)) {
                    if (game.findPiece(i, j, true) != null) {
                        selected = game.findPiece(i, j, true);
                        ArrayList<Move> moves = game.findPiece(selected.x, selected.y).allLegalMoves(game);
                        for(Move m:moves) {
                            game.squares[m.x2][m.y2].setColor(
                                    new Color(game.squares[m.x2][m.y2].getColor().getRed() / 2 + 20,
                                            game.squares[m.x2][m.y2].getColor().getRed() / 2 + 70,
                                            game.squares[m.x2][m.y2].getColor().getBlue() / 2 + 40)
                            );
                        }

                    } else {
                        game.squares[i][j].setColor(new Color(100, 200, 150));
                        game.squares[i][j].setSelected(true);
                    }
                }
            }
        }
        repaint();
    }

    public void resetSquares() {
        for (int i = 0; i < game.squares.length; i++) {
            for (int j = 0; j < game.squares.length; j++) {
                if (game.squares[i][j].isSelected() && selected != null) {
                    Move m = new Move(selected.x, selected.y, i, j, true);
                    if (selected.legalMove(m, game)) {
                        game.playTurn(m);
                        selected = null;
                        repaint();
                        if (game.checkWinner() == 0) {
                            //game.playTurn(p2.makeMove(game.findAllLegalMoves()));
                            game.randomMove();
                            //if (game.checkWinner()==0)game.playTurn(p2.playMove(game.findAllLegalMoves(), game));
                        }
                        repaint();
                        System.out.println(game.toString());
                    }
                }
                game.squares[i][j].setColor(game.squares[i][j].getMainColor());
                game.squares[i][j].setSelected(false);
            }
        }
        repaint();
    }

    public String checkWin() {
        if (game.checkWinner() == 1) {
            return "Player won";
        }
        if (game.checkWinner() == -1) {
            return "Computer won";
        }
        return "Playing Game";
    }

    /**
     * Creates new Image from file at a location
     *
     * @param file file link (ex: "src/mypackage/myimage.png")
     * @param g Graphics 2D
     * @param x x position (left)
     * @param y y position (top)
     * @param w width
     * @param h height
     */
    private void drawImage(String file, Graphics g, int x, int y, int w, int h) {
        Graphics2D g1 = (Graphics2D) g;
        BufferedImage preImg = null;
        try {
            preImg = ImageIO.read(new File(file));
        } catch (IOException e) {
            System.out.println("image not read");
        }
        Image img = preImg.getScaledInstance(w, h, 1);
        g1.drawImage(img, x, y, this);
    }

    private void drawPiece(Graphics2D g, Piece p) {
        if (!p.alive) {
            return;
        }
        String[][] paths = {{"src/images/whitePawn.png", "src/images/blackPawn.png"},
        {"src/images/whiteRook.png", "src/images/blackRook.png"},
        {"src/images/whiteKnight.png", "src/images/blackKnight.png"},
        {"src/images/whiteBishop.png", "src/images/blackBishop.png"},
        {"src/images/whiteQueen.png", "src/images/blackQueen.png"},
        {"src/images/whiteKing.png", "src/images/blackKing.png"}};
        String[][] selectedPaths = {{"src/images/whitePawnSelected.png", "src/images/blackPawnSelected.png"},
        {"src/images/whiteRookSelected.png", "src/images/blackRookSelected.png"},
        {"src/images/whiteKnightSelected.png", "src/images/blackKnightSelected.png"},
        {"src/images/whiteBishopSelected.png", "src/images/blackBishopSelected.png"},
        {"src/images/whiteQueenSelected.png", "src/images/blackQueenSelected.png"},
        {"src/images/whiteKingSelected.png", "src/images/blackKingSelected.png"}};
        if (selected == p) {
            paths = selectedPaths;
        }
        int i = "prhbqk".indexOf(p.id.charAt(0));
        Square sq = game.squares[p.x][p.y];
        drawImage(paths[i][p.black ? 1 : 0], g, sq.x, sq.y, sqLength, sqLength);
    }

    private void placePieces(Graphics2D g) {
        for (Piece p : game.blackPieces) {
            drawPiece(g, p);
        }
        for (Piece p : game.whitePieces) {
            drawPiece(g, p);
        }
    }

    private void initBoard(Graphics2D g) {
        for (Square[] row : game.squares) {
            for (Square square : row) {
                g.setColor(square.getColor());
                g.fill(square);
            }
        }
    }

    private void initSquares() {
        for (int i = 0; i < game.squares.length; i++) {
            for (int j = 0; j < game.squares.length; j++) {
                Color c = new Color((((j + game.squares.length * i) + ((game.squares.length % 2 == 0 && i % 2 == 1) ? 1 : 0)) % 2) * 50 + 105,
                        (((j + game.squares.length * i) + ((game.squares.length % 2 == 0 && i % 2 == 1) ? 1 : 0)) % 2) * 40 + 60,
                        (((j + game.squares.length * i) + ((game.squares.length % 2 == 0 && i % 2 == 1) ? 1 : 0)) % 2) * 60);
                game.squares[i][j] = new Square(c, i * sqLength, j * sqLength, sqLength, sqLength);
            }
        }
    }

}
