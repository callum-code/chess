/*
   _____      _ _                                 _        ___   ___  __  ___  
  / ____|    | | |                               | |      |__ \ / _ \/_ |/ _ \ 
 | |     __ _| | |_   _ _ __ ___     ___ ___   __| | ___     ) | | | || | (_) |
 | |    / _` | | | | | | '_ ` _ \   / __/ _ \ / _` |/ _ \   / /| | | || |> _ < 
 | |___| (_| | | | |_| | | | | | | | (_| (_) | (_| |  __/  / /_| |_| || | (_) |
  \_____\__,_|_|_|\__,_|_| |_| |_|  \___\___/ \__,_|\___| |____|\___/ |_|\___/ 
 
*/

package cs448_chess;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author callumijohnston
 */
public class ViewFrame extends JFrame{
    
    private BoardPanel board;
    JLabel winnerLabel;
    
    public ViewFrame(){
        board = new BoardPanel();
        initComponents();
        board.learn();
    }
    
    private void loadActionPerformed(ActionEvent evt){
        
    }
    
    private void newGameActionPerformed(ActionEvent evt){
        board.newGame();
    }
    
    private void initComponents(){
        setTitle("Chess");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250,0,board.getWidth(),board.getHeight()+39+22);
        MouseMovement mv = new MouseMovement(board, this);
        setLayout(new BorderLayout());
        addMouseListener(mv);
        
        JPanel buttons = new JPanel();
        buttons.setSize(this.getWidth(),50);
        winnerLabel = new JLabel();
        winnerLabel.setText(board.checkWin());
        JButton loadButton = new JButton();
        loadButton.setText("load");
        JButton newGameButton = new JButton();
        newGameButton.setText("new game");
        buttons.add(winnerLabel);
        buttons.add(loadButton);
        buttons.add(newGameButton);
        
        ActionListener loadAction = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadActionPerformed(evt);
            }
        };
        ActionListener newGameAction = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newGameActionPerformed(evt);
            }
        };
        
        loadButton.addActionListener(loadAction);
        newGameButton.addActionListener(newGameAction);
        
        
        add(board, BorderLayout.CENTER);
        add(buttons,BorderLayout.SOUTH);
    }
    
}
