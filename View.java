/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Michael
 */
public class View extends JFrame implements Observer {

    private JPanel menuPanel = new JPanel();
    private JLabel gamesLabel = new JLabel("Save Games");
    public JComboBox saveFiles = new JComboBox();
    public JButton loadGame = new JButton("Load Game");
    public JButton newGame = new JButton("New Game");
    
    private JPanel boardPanel = new JPanel();
    private JLabel turnLabel = new JLabel("Whites Turn");
    private JButton quitButton = new JButton("Quit");
    private JPanel[] cells;
    
    public JLabel message = new JLabel("Welcome to Chess!", JLabel.CENTER);
    public JTextField calcSolution = new JTextField(10);

    private boolean gameStarted;
    
    public View()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        this.setLocationRelativeTo(null); // Make the frame located at the absolute center of the screen.
        this.boardPanel.setLayout(new GridLayout(8, 8));        
        this.menuPanel.add(gamesLabel);
        this.menuPanel.add(saveFiles);
        this.menuPanel.add(loadGame);
        this.menuPanel.add(newGame);
        this.add(this.message, BorderLayout.NORTH);
        this.add(menuPanel);
        this.setVisible(true);
    }
    
    public void populateSaveFiles(String[] games)
    {
        for(String game : games)
        {
            this.saveFiles.addItem(game);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Data data = (Data) arg; // Obtain the instance of data.
        if(data.startFlag)
        {
        }
        
    }

    public void addActionListener(ActionListener listener) {
        this.loadGame.addActionListener(listener);
        this.newGame.addActionListener(listener);
        this.quitButton.addActionListener(listener);
    }
    
}
