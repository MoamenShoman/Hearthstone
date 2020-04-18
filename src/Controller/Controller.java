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
        if (game.getCurrentHero().getCurrentHP() <= 0) {
            winner = game.getOpponent();
        } else {
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
                    game.setListener(this);
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

                /////// setInitial state ends here

            } else if (gameView.getCurHand().contains(clickedButton) &&
                    game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof Minion) {
                try {
                    game.getCurrentHero().playMinion((Minion) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)));
                    updateUI();
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
                }
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof AOESpell)) {
                try {
                    game.getCurrentHero().castSpell((AOESpell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)),
                            game.getOpponent().getField());
                    updateUI();
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
                    updateUI();
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof MinionTargetSpell)) {
                JOptionPane.showMessageDialog(gameView,
                        "Choose your target",
                        "Hearthstone",
                        JOptionPane.WARNING_MESSAGE);
                attackerSpell = (Spell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton));

            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof HeroTargetSpell)) {
                JOptionPane.showMessageDialog(gameView,
                        "Choose your target",
                        "Hearthstone",
                        JOptionPane.WARNING_MESSAGE);
                attackerSpell = (Spell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton));

            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof LeechingSpell)) {
                JOptionPane.showMessageDialog(gameView,
                        "Choose your target",
                        "Hearthstone",
                        JOptionPane.WARNING_MESSAGE);
                attackerSpell = (Spell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton));

            } else if (gameView.getCurFieldMinions().contains(clickedButton)) {
                if (attackerSpell != null && attackerSpell instanceof MinionTargetSpell) {
                    targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((MinionTargetSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (attackerSpell != null && attackerSpell instanceof LeechingSpell) {
                    targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((LeechingSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException | NotEnoughManaException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (attackerMinion != null) {
                    targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
                        attackerMinion = null;
                        targetMinion = null;
                        updateUI();
                    } catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException | NotSummonedException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (heroPowerUser == null) {
                    attackerMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    JOptionPane.showMessageDialog(gameView,
                            "Choose your target",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppFieldMinions().contains(clickedButton)) {
                if (attackerMinion != null) {
                    targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
                        attackerMinion = null;
                        targetMinion = null;
                        updateUI();
                    } catch (CannotAttackException | NotYourTurnException | TauntBypassException | InvalidTargetException | NotSummonedException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (attackerSpell != null && attackerSpell instanceof MinionTargetSpell) {
                    targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((MinionTargetSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else if (attackerSpell != null && attackerSpell instanceof LeechingSpell) {
                    targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((LeechingSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException | NotEnoughManaException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else if (gameView.getCurFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton)));
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | CloneNotSupportedException | FullFieldException |
                        FullHandException | NotYourTurnException | HeroPowerAlreadyUsedException e) {
                    heroPowerUser = null;
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
                    heroPowerUser = null;
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
                    heroPowerUser = null;
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
                    heroPowerUser = null;
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
                } else if (game.getCurrentHero() instanceof Warlock) {
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
            } else if (gameView.getEndTurnButton() == clickedButton) {
                try {
                    game.endTurn();
                    attackerMinion=null;
                    attackerSpell=null;
                    targetHero=null;
                    targetMinion=null;
                    heroPowerUser=null;
                    updateUI();
                    gameView.updateCurHeroIcon(game.getCurrentHero().getName());
                    gameView.updateOppHeroIcon(game.getOpponent().getName());
                } catch (FullHandException | CloneNotSupportedException e) {
                    attackerMinion=null;
                    attackerSpell=null;
                    targetHero=null;
                    targetMinion=null;
                    heroPowerUser=null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
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
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getOpponent());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getCurrentHero());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getOpponent());
                    heroPowerUser = null;
                    updateUI();
                } catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException |
                        FullHandException | FullFieldException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppHero() == clicked && attackerMinion != null) {
                targetHero = game.getOpponent();
                try {
                    game.getCurrentHero().attackWithMinion(attackerMinion, targetHero);
                    attackerMinion = null;
                    targetHero = null;
                    updateUI();
                } catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException | InvalidTargetException e) {
                    attackerMinion = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurHero() == clicked && attackerMinion != null) {
                targetHero = game.getCurrentHero();
                try {
                    game.getCurrentHero().attackWithMinion(attackerMinion, targetHero);
                    attackerMinion = null;
                    targetHero = null;
                    updateUI();
                } catch (CannotAttackException | NotYourTurnException | TauntBypassException | NotSummonedException | InvalidTargetException e) {
                    attackerMinion = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getCurHero() == clicked && attackerSpell != null && attackerSpell instanceof HeroTargetSpell) {
                targetHero = game.getCurrentHero();
                try {
                    game.getCurrentHero().castSpell((HeroTargetSpell) attackerSpell, targetHero);
                    attackerSpell = null;
                    targetHero = null;
                    updateUI();
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    attackerSpell = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppHero() == clicked && attackerSpell != null && attackerSpell instanceof HeroTargetSpell) {
                targetHero = game.getOpponent();
                try {
                    game.getCurrentHero().castSpell((HeroTargetSpell) attackerSpell, targetHero);
                    attackerSpell = null;
                    targetHero = null;
                    updateUI();
                } catch (NotYourTurnException | NotEnoughManaException e) {
                    attackerSpell = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
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

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

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
