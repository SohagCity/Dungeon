package game.gameFunctionalities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Main menu.
 */
public class ControlPanel{
    private JPanel mainPanel;
    private JButton pauseButton;
    private JButton resetButton;
    private JButton exitButton;
    private JComboBox selectLevel;
    private JLabel menuIcon;
    private JComboBox saveSlots;
    private JComboBox loadSlot;
    private JLabel errors;
    private boolean flag=true;
    public ControlPanel(Game game) {
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag) {
                    game.getWorld().stop();
                    flag=false;
                    pauseButton.setText("Start");
                } else{
                    game.getWorld().start();
                    flag=true;
                    pauseButton.setText("Pause");

                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.goNextLevel(game.getLevel());
                if(game.getPlayer().getHealth()<=0){
                    //original
//                    game.getPlayer().setHealth(400);

                    //making it easier
                    game.getPlayer().setHealth(800);
                    game.goNextLevel(1);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        selectLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int level= selectLevel.getSelectedIndex()+1;
                game.getPlayer().setHealth(800);
                game.goNextLevel(level);
            }
        });

        saveSlots.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(saveSlots.getSelectedIndex()==0) {
                        Save save = new Save("resources/save1.txt", game);
                        save.writeSave();
                    }
                    if(saveSlots.getSelectedIndex()==1) {
                        Save save = new Save("resources/save2.txt", game);
                        save.writeSave();
                    }
                    if(saveSlots.getSelectedIndex()==2) {
                        Save save = new Save("resources/save3.txt", game);
                        save.writeSave();
                    }
                } catch (IOException c){
                    errors.setText("problem reading file");
                }
            }
        });
        loadSlot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loadSlot.getSelectedIndex()==0) {
                    try {
                        Load load = new Load("resources/save1.txt", game);
                        load.readSave();
                    } catch (GameLoadException ex){
                        errors.setText(ex.getReason());
                    }
                }
                if(loadSlot.getSelectedIndex()==1) {
                    try {
                        Load load = new Load("resources/save2.txt", game);
                        load.readSave();
                    } catch (GameLoadException ex){
                        errors.setText(ex.getReason());
                    }
                }
                if(loadSlot.getSelectedIndex()==2) {
                    try {
                        Load load = new Load("resources/save3.txt", game);
                        load.readSave();
                    } catch (GameLoadException ex){
                        errors.setText(ex.getReason());
                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public void updateError(String s){
        errors.setText(s);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        menuIcon=new JLabel(new ImageIcon("resources/menu.png"));

    }
}
