package Controller;

import engine.Game;
import engine.GameListener;
import exceptions.FullHandException;
import model.heroes.*;
import view.GameView;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements GameListener, MouseListener, ItemListener {
    private Game game;
    private GameView gameView;
    private Hero firstHero;
    private Hero secondHero;

    public Controller() throws FullHandException, CloneNotSupportedException, IOException, FontFormatException, LineUnavailableException, UnsupportedAudioFileException {
        gameView = new GameView();
        gameView.setInitial();
        playMusic("Sound/Hearthstone_Music.wav");
        for (JRadioButton b : gameView.getChooseFirstHero()) {
            b.addMouseListener(this);
            b.addItemListener(this);
        }
        for (JRadioButton b : gameView.getChooseSecondHero()) {
            b.addMouseListener(this);
            b.addItemListener(this);
        }
        gameView.getStartGame().addMouseListener(this);
    }

    public void onGameOver() {

    }
    private void playMusic(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(a);
            c.start();
            c.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException e){
            e.getStackTrace();
        }
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() instanceof JRadioButton){
            JRadioButton clickedButton = (JRadioButton) mouseEvent.getComponent();
        int choosen = gameView.getChooseFirstHero().indexOf(clickedButton);
        if (gameView.getChooseFirstHero().contains(clickedButton)) {
            clickedButton.setBorder(BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN),
                    gameView.getNames()[choosen], TitledBorder.CENTER,
                    TitledBorder.BOTTOM, gameView.getFont()));
            try {
                firstHero = choosen == 0 ? new Mage() :
                        choosen == 1 ? new Hunter() :
                                choosen == 2 ? new Paladin() :
                                        choosen == 3 ? new Priest() :
                                                new Warlock();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        } else if (gameView.getChooseSecondHero().contains(clickedButton)) {
            choosen = gameView.getChooseSecondHero().indexOf(clickedButton);
            clickedButton.setBorder(BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN),
                    gameView.getNames()[choosen], TitledBorder.CENTER,
                    TitledBorder.TOP, gameView.getFont()));
            try {
                secondHero = choosen == 0 ? new Mage() :
                        choosen == 1 ? new Hunter() :
                                choosen == 2 ? new Paladin() :
                                        choosen == 3 ? new Priest() :
                                                new Warlock();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        } else {
             JButton clickedButton =(JButton) mouseEvent.getComponent();
            if (clickedButton.equals(gameView.getStartGame())) {
                try {
                    game = new Game(firstHero, secondHero);
                    gameView.setGamePlay(game);
                } catch (FullHandException e) {
                    e.printStackTrace();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(gameView,
                            "Please choose your heroes first",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        JRadioButton b = (JRadioButton) itemEvent.getItemSelectable();
        if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
            int deselected;
            if (gameView.getChooseFirstHero().contains(b)) {
                deselected = gameView.getChooseFirstHero().indexOf(b);
                b.setBorder(BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                        gameView.getNames()[deselected], TitledBorder.CENTER,
                        TitledBorder.TOP, gameView.getFont()));
            } else {
                if (gameView.getChooseSecondHero().contains(b)) {
                    deselected = gameView.getChooseSecondHero().indexOf(b);
                    b.setBorder(BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                            gameView.getNames()[deselected], TitledBorder.CENTER,
                            TitledBorder.BOTTOM, gameView.getFont()));
                }
            }
        }
    }

    public static void main(String[] args) throws FullHandException, FontFormatException, CloneNotSupportedException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        Controller c= new Controller();
    }
}
