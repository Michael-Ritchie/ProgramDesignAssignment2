/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Michael
 */
public class Controller implements ActionListener {

    public View view;
    public Model model;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
        this.view.populateSaveFiles(this.model.db.getSaveFiles());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == view.loadGame)
        {
            System.out.println("LOADING GAME");
            String game = (String) view.saveFiles.getSelectedItem();
            model.loadBoard(game);
            model.board.toString();
        }
    }
    
}
