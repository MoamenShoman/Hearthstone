package Controller;

import engine.Game;
import engine.GameListener;
import exceptions.*;
import model.cards.minions.Minion;
import model.cards.spells.*;
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

public class Controller implements GameListener, MouseListener, ItemListener {
    private Game game;
    private GameView gameView;
    private Hero firstHero;
    private Hero secondHero;

    private Minion attackerMinion;
    private Minion targetMinion;
    private Spell attackerSpell;
    private Hero targetHero;

    private Hero heroPowerUser;

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
        Hero winner;
        if(game.getCurrentHero().getCurrentHP() <= 0) {
            winner = game.getOpponent();
        }else{
            winner = game.getCurrentHero();
        }
        JOptionPane.showMessageDialog(gameView,
                "Winner is: " + winner.getName(),
                "Hearthstone",
                JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }

    private void playMusic(String path) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(a);
            c.start();
            c.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.getStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() instanceof JRadioButton) {
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
        } else if (mouseEvent.getComponent() instanceof JButton) {
            JButton clickedButton = (JButton) mouseEvent.getComponent();
            if (clickedButton.equals(gameView.getStartGame())) {
                try {
                    game = new Game(firstHero, secondHero);
                    gameView.setGamePlay(game);
                    setCurHandListeners();
                    setCurFieldListeners();
                    setOppFieldListeners();
                    gameView.getEndTurnButton().addMouseListener(this);
                    gameView.getHeroPowerButton().addMouseListener(this);

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
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof Minion) {
                try {
                    game.getCurrentHero().playMinion((Minion) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)));
                    gameView.updateCurField(game.getCurrentHero().getField());
                    gameView.updateHand(game.getCurrentHero().getHand());
                    gameView.updateCurManaCrystalsNum(game.getCurrentHero().getCurrentManaCrystals(),
                            game.getCurrentHero().getTotalManaCrystals());
                    setCurHandListeners();
                    setCurFieldListeners();
                } catch (FullFieldException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotYourTurnException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof AOESpell)) {
                try {
                    game.getCurrentHero().castSpell((AOESpell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)),
                            game.getOpponent().getField());
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof FieldSpell)) {
                try {
                    game.getCurrentHero().castSpell((FieldSpell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)));
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton)));
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | CloneNotSupportedException | FullFieldException |
                        FullHandException | NotYourTurnException | HeroPowerAlreadyUsedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton)));
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | CloneNotSupportedException | FullFieldException | FullHandException |
                        NotYourTurnException | HeroPowerAlreadyUsedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton)));
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | CloneNotSupportedException | FullFieldException |
                        FullHandException | NotYourTurnException | HeroPowerAlreadyUsedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton)));
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | CloneNotSupportedException | FullFieldException | FullHandException |
                        NotYourTurnException | HeroPowerAlreadyUsedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getHeroPowerButton() == clickedButton) {
                if (game.getCurrentHero() instanceof Hunter) {
                    try {
                        game.getCurrentHero().useHeroPower();
                        updateUI();
                    } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                            FullHandException | FullFieldException | CloneNotSupportedException e) {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (game.getCurrentHero() instanceof Mage) {
                    JOptionPane.showMessageDialog(gameView,
                            "Choose a target!",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                    heroPowerUser = game.getCurrentHero();
                } else if (game.getCurrentHero() instanceof Paladin) {
                    try {
                        ((Paladin) game.getCurrentHero()).useHeroPower();
                        updateUI();
                    } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                            FullHandException | FullFieldException | CloneNotSupportedException e) {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (game.getCurrentHero() instanceof Priest) {
                    JOptionPane.showMessageDialog(gameView,
                            "Choose a target!",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                    heroPowerUser = game.getCurrentHero();
                }else if(game.getCurrentHero() instanceof Warlock){
                    try {
                        game.getCurrentHero().useHeroPower();
                        updateUI();
                    } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException |
                            FullFieldException | CloneNotSupportedException e) {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }else if(gameView.getEndTurnButton() == clickedButton){
                try {
                    game.endTurn();
                    updateUI();
                    gameView.updateCurHeroIcon(game.getCurrentHero().getName());
                    gameView.updateOppHeroIcon(game.getOpponent().getName());
                } catch (FullHandException | CloneNotSupportedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        } else if (mouseEvent.getComponent() instanceof JLabel) {
            JLabel clicked = (JLabel) mouseEvent.getComponent();
            if (gameView.getCurHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getCurrentHero());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            "Choose a target!",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                    heroPowerUser = game.getCurrentHero();
                }
            } else if (gameView.getOppHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getOpponent());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            "Choose a target!",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                    heroPowerUser = game.getCurrentHero();
                }
            }else if (gameView.getCurHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getCurrentHero());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            "Choose a target!",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                    heroPowerUser = game.getCurrentHero();
                }
            } else if (gameView.getOppHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getOpponent());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    JOptionPane.showMessageDialog(gameView,
                            "Choose a target!",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                    heroPowerUser = game.getCurrentHero();
                }
            }
        }
    }

    private void setCurHandListeners() {
        for (JButton button : gameView.getCurHand()) {
            button.addMouseListener(this);
        }
    }

    private void setCurFieldListeners() {
        for (JButton button : gameView.getCurFieldMinions()) {
            button.addMouseListener(this);
        }
    }

    private void setOppFieldListeners() {
        for (JButton button : gameView.getOppFieldMinions()) {
            button.addMouseListener(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() instanceof JButton) {
            JButton pressed = (JButton) mouseEvent.getComponent();
            if (gameView.getCurFieldMinions() != null && gameView.getCurFieldMinions().contains(pressed)) {
                attackerMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(pressed));
            }
            if (gameView.getCurHand() != null && gameView.getCurHand().contains(pressed)) {
                if (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(pressed)) instanceof MinionTargetSpell ||
                        game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(pressed)) instanceof HeroTargetSpell ||
                        game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(pressed)) instanceof LeechingSpell) {
                    attackerSpell = (Spell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(pressed));
                    System.out.println(attackerSpell.getName());
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        JComponent component = (JComponent) mouseEvent.getComponent();
        if ((gameView.getCurFieldMinions() != null && gameView.getCurFieldMinions().contains(component)) ||
                (gameView.getOppFieldMinions() != null && gameView.getOppFieldMinions().contains(component)) ||
                (gameView.getOppHero() != null && gameView.getOppHero() == component) ||
                (gameView.getCurHero() != null && gameView.getCurHero() == component)) {
            if (attackerMinion != null && targetMinion != null) {
                try {
                    game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
                    updateUI();
                    attackerMinion = null;
                    targetMinion = null;
                } catch (NotYourTurnException | TauntBypassException | CannotAttackException |
                        NotSummonedException | InvalidTargetException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (attackerMinion != null && targetHero != null) {
                try {
                    game.getCurrentHero().attackWithMinion(attackerMinion, targetHero);
                    System.out.println(attackerMinion.getName() + " " + targetHero.getName());
                    updateUI();
                    attackerMinion = null;
                    targetHero = null;
                } catch (NotYourTurnException | TauntBypassException | CannotAttackException |
                        NotSummonedException | InvalidTargetException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (attackerSpell != null && targetMinion != null && attackerSpell instanceof MinionTargetSpell) {
                try {
                    game.getCurrentHero().castSpell((MinionTargetSpell) attackerSpell, targetMinion);
                    updateUI();
                    attackerSpell = null;
                    targetMinion = null;
                } catch (InvalidTargetException | NotEnoughManaException | NotYourTurnException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (attackerSpell != null && targetHero != null && attackerSpell instanceof HeroTargetSpell) {
                try {
                    game.getCurrentHero().castSpell((HeroTargetSpell) attackerSpell, targetHero);
                    updateUI();
                    attackerSpell = null;
                    targetHero = null;
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (attackerSpell != null && targetMinion != null && attackerSpell instanceof LeechingSpell) {
                try {
                    game.getCurrentHero().castSpell((LeechingSpell) attackerSpell, targetMinion);
                    updateUI();
                    attackerSpell = null;
                    targetMinion = null;
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            attackerMinion = null;
            attackerSpell = null;
            targetMinion = null;
            targetHero = null;
        }
    }

    private void updateUI() {
        try {
            gameView.updateHand(game.getCurrentHero().getHand());
            gameView.updateCurField(game.getCurrentHero().getField());
            gameView.updateOppField(game.getOpponent().getField());
            gameView.updateCurManaCrystalsNum(game.getCurrentHero().getCurrentManaCrystals(),
                    game.getCurrentHero().getTotalManaCrystals());
            gameView.updateCurHeroHP(game.getCurrentHero().getCurrentHP());
            gameView.updateOppHeroHP(game.getOpponent().getCurrentHP());
            gameView.updateCurDeckNum(game.getCurrentHero().getDeck().size());
            gameView.updateOppDeckNum(game.getOpponent().getDeck().size());
            gameView.updateOppHandNum(game.getOpponent().getHand().size());
            gameView.updateOppManaCrystalsNum(game.getOpponent().getCurrentManaCrystals(), game.getOpponent().getTotalManaCrystals());

            setCurFieldListeners();
            setOppFieldListeners();
            setCurHandListeners();
            gameView.getCurHero().addMouseListener(this);
            gameView.getOppHero().addMouseListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        JComponent component = (JComponent) mouseEvent.getComponent();
        if (gameView.getCurFieldMinions() != null && gameView.getCurFieldMinions().contains(component)) {
            targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(component));
        } else if (gameView.getOppFieldMinions() != null && gameView.getOppFieldMinions().contains(component)) {
            targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(component));
        } else if (gameView.getCurHero() != null && gameView.getCurHero() == component) {
            targetHero = game.getCurrentHero();
        } else if (gameView.getOppHero() != null && gameView.getOppHero() == component) {
            targetHero = game.getOpponent();
        }
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
        Controller c = new Controller();
    }
}
