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
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
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
        gameView.setInitial0();
        playMusic("Sound/Hearthstone_Music.wav");
        gameView.getExitButton0().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameView.getStartButton0().addMouseListener(this);
    }

    public void onGameOver() {
        Hero winner, loser;
        if (game.getCurrentHero().getCurrentHP() <= 0) {
            winner = game.getOpponent();
            loser = game.getCurrentHero();
        } else {
            winner = game.getCurrentHero();
            loser = game.getOpponent();
        }
        try {
            Thread.sleep(playSound("Sound/Heroes/" + loser.getName() + "/death.wav") / 1000);
        } catch (InterruptedException e) {

        } catch (IOException e) {
            try {
                Thread.sleep(playSound("Sound/Heroes/Rexxar/death.wav") / 1000);
            } catch (IOException | InterruptedException e1) {

            }
        }
        JOptionPane.showMessageDialog(gameView,
                "Winner is: " + winner.getName(),
                "Hearthstone",
                JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }

    private void playMusic(String path) {

        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(a);
            c.start();
            c.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }

    private long playSound(String path) throws IOException {

        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip c = AudioSystem.getClip();
            c.open(a);
            c.start();
            return c.getMicrosecondLength();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() instanceof JRadioButton) {
            JRadioButton clickedButton = (JRadioButton) mouseEvent.getComponent();
            int choosen = gameView.getChooseFirstHero().indexOf(clickedButton);
            if (gameView.getChooseFirstHero().contains(clickedButton)) {
                TitledBorder border = BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN),
                        gameView.getNames()[choosen], TitledBorder.CENTER,
                        TitledBorder.BOTTOM, gameView.getFont());
                border.setTitleColor(Color.WHITE);
                clickedButton.setBorder(border);
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
                TitledBorder border = BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.GREEN, Color.GREEN),
                        gameView.getNames()[choosen], TitledBorder.CENTER,
                        TitledBorder.TOP, gameView.getFont());
                border.setTitleColor(Color.white);
                clickedButton.setBorder(border);
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
            if (gameView.getStartButton0() == clickedButton) {
                try {
                    gameView.setInitial();
                    for (JRadioButton b : gameView.getChooseFirstHero()) {
                        b.addMouseListener(this);
                        b.addItemListener(this);
                    }
                    for (JRadioButton b : gameView.getChooseSecondHero()) {
                        b.addMouseListener(this);
                        b.addItemListener(this);
                    }

                    gameView.getStartGame().addMouseListener(this);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            } else if (clickedButton.equals(gameView.getStartGame())) {
                try {
                    game = new Game(firstHero, secondHero);
                    game.setListener(this);
                    gameView.setGamePlay(game);
                    setCurHandListeners();
                    setCurFieldListeners();
                    setOppFieldListeners();
                    gameView.getEndTurnButton().addMouseListener(this);
                    gameView.getHeroPowerButton().addMouseListener(this);
                    Thread.sleep(playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/start.wav") / 1000);
                    Thread.sleep(playSound("Sound/Heroes/" + game.getOpponent().getName() + "/start.wav") / 1000);
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
                } catch (InterruptedException e) {

                }

                /////// setInitial state ends here

            } else if (gameView.getCurHand().contains(clickedButton) &&
                    game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof Minion) {
                try {
                    Minion played = (Minion) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton));
                    game.getCurrentHero().playMinion(played);
                    updateUI();
                    playSound("Sound/Minions/" + played.getName() + "/play.wav");
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                } catch (NotYourTurnException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    updateUI();
                }
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof AOESpell)) {
                try {
                    game.getCurrentHero().castSpell((AOESpell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)),
                            game.getOpponent().getField());
                    updateUI();
                } catch (NotYourTurnException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                }
            } else if (gameView.getCurHand().contains(clickedButton) &&
                    (game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)) instanceof FieldSpell)) {
                try {
                    game.getCurrentHero().castSpell((FieldSpell) game.getCurrentHero().getHand().get(gameView.getCurHand().indexOf(clickedButton)));
                    updateUI();
                } catch (NotYourTurnException e) {
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
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

            } else if (gameView.getCurFieldMinions().contains(clickedButton) && heroPowerUser == null) {
                if (attackerSpell != null && attackerSpell instanceof MinionTargetSpell) {
                    targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((MinionTargetSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (InvalidTargetException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/invalidtarget.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/invalidtarget.wav");
                            } catch (IOException exc) {

                            }
                        }
                    }
                } else if (attackerSpell != null && attackerSpell instanceof LeechingSpell) {
                    targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((LeechingSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    }
                } else if (attackerMinion != null) {
                    targetMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
                        updateUI();
                        attackerMinion = null;
                        targetMinion = null;
                    } catch (NotYourTurnException | NotSummonedException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (TauntBypassException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/taunt.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/taunt.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (CannotAttackException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        if (e.getMessage().equals("You can't attack with a sleeping minion")) {
                            try {
                                playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/sleeping.wav");
                            } catch (IOException ex) {
                                try {
                                    playSound("Sound/Heroes/Uther Lightbringer/sleeping.wav");
                                } catch (IOException exc) {

                                }
                            }
                        } else if (e.getMessage().equals("Your minion has already attacked")) {
                            try {
                                playSound("Sound/Heroes/" + game.getCurrentHero() + "/already_attacked.wav");
                            } catch (IOException ex) {
                                try {
                                    playSound("Sound/Heroes/Rexxar/already_attacked.wav");
                                } catch (IOException exc) {

                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(gameView,
                                    e.getMessage(),
                                    "Hearthstone",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (InvalidTargetException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/invalidtarget.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/invalidtarget.wav");
                            } catch (IOException exc) {

                            }
                        }
                    }
                } else if (heroPowerUser == null) {
                    attackerMinion = game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton));
                    JOptionPane.showMessageDialog(gameView,
                            "Choose your target",
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else if (gameView.getOppFieldMinions().contains(clickedButton) && heroPowerUser == null) {
                if (attackerMinion != null) {
                    targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
                        Thread.sleep(playSound("Sound/Minions/" + attackerMinion.getName() + "/attack.wav") / 1000);
                        if (targetMinion.getCurrentHP() <= 0) {
                            playSound("Sound/Minions/" + targetMinion.getName() + "/death.wav");
                        }
                        if (attackerMinion.getCurrentHP() <= 0) {
                            playSound("Sound/Minions/" + attackerMinion.getName() + "/death.wav");
                        }
                        attackerMinion = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException | NotSummonedException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        updateUI();
                    } catch (TauntBypassException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/taunt.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/taunt.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (CannotAttackException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        if (e.getMessage().equals("You can't attack with a sleeping minion")) {
                            try {
                                playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/sleeping.wav");
                            } catch (IOException ex) {
                                try {
                                    playSound("Sound/Heroes/Uther Lightbringer/sleeping.wav");
                                } catch (IOException exc) {

                                }
                            }
                        } else if (e.getMessage().equals("Your minion has already attacked")) {
                            try {
                                playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/already_attacked.wav");
                            } catch (IOException ex) {
                                try {
                                    playSound("Sound/Heroes/Rexxar/already_attacked.wav");
                                } catch (IOException exc) {
                                    exc.printStackTrace();
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(gameView,
                                    e.getMessage(),
                                    "Hearthstone",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (InvalidTargetException e) {
                        attackerMinion = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/invalidtarget.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/invalidtarget.wav");
                            } catch (IOException exc) {

                            }
                        }
                    }
                } else if (attackerSpell != null && attackerSpell instanceof MinionTargetSpell) {
                    targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((MinionTargetSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException | InvalidTargetException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    }
                } else if (attackerSpell != null && attackerSpell instanceof LeechingSpell) {
                    targetMinion = game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton));
                    try {
                        game.getCurrentHero().castSpell((LeechingSpell) attackerSpell, targetMinion);
                        attackerSpell = null;
                        targetMinion = null;
                        updateUI();
                    } catch (NotYourTurnException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        attackerSpell = null;
                        targetMinion = null;
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    }
                }
            } else if (gameView.getCurFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton)));
                    heroPowerUser = null;
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException ex) {

                        }
                    }
                    updateUI();
                } catch (CloneNotSupportedException | NotYourTurnException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getOppFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton)));
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException ex) {

                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (CloneNotSupportedException |
                        NotYourTurnException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getCurFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getCurrentHero().getField().get(gameView.getCurFieldMinions().indexOf(clickedButton)));
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException ex) {

                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (CloneNotSupportedException | NotYourTurnException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getOppFieldMinions().contains(clickedButton) && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getOpponent().getField().get(gameView.getOppFieldMinions().indexOf(clickedButton)));
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException e1) {
                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (CloneNotSupportedException | NotYourTurnException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {
                }
            } else if (gameView.getHeroPowerButton() == clickedButton) {
                if (game.getCurrentHero() instanceof Hunter) {
                    try {
                        game.getCurrentHero().useHeroPower();
                        try {
                            Thread.sleep(playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/attack.wav") / 1000);
                        } catch (IOException e) {
                            try {
                                Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                            } catch (IOException | InterruptedException e1) {

                            }
                        }
                        updateUI();
                    } catch (NotYourTurnException | CloneNotSupportedException e) {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (FullHandException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullhand.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/fullhand.wav");
                            } catch (IOException exc) {

                            }
                        }
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (FullFieldException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullfield.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (HeroPowerAlreadyUsedException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/heropower.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/heropower.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (InterruptedException e) {

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
                        try {
                            Thread.sleep(playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/attack.wav") / 1000);
                        } catch (IOException e) {
                            try {
                                Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                            } catch (IOException | InterruptedException e1) {

                            }
                        }
                        updateUI();
                    } catch (NotYourTurnException | CloneNotSupportedException e) {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (FullHandException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero() + "/fullhand.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/fullhand.wav");
                            } catch (IOException exc) {

                            }
                        }
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (FullFieldException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullfield.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (HeroPowerAlreadyUsedException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/heropower.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/heropower.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (InterruptedException e) {

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
                        try {
                            Thread.sleep(playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/attack.wav") / 1000);
                        } catch (IOException e) {
                            try {
                                Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                            } catch (IOException | InterruptedException e1) {

                            }
                        }
                        updateUI();
                    } catch (NotYourTurnException | CloneNotSupportedException e) {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (NotEnoughManaException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Gul'dan/mana.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (FullHandException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullhand.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/fullhand.wav");
                            } catch (IOException exc) {

                            }
                        }
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (FullFieldException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullfield.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (HeroPowerAlreadyUsedException e) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/heropower.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/heropower.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } catch (InterruptedException e) {

                    }
                }
            } else if (gameView.getEndTurnButton() == clickedButton) {
                try {
                    game.endTurn();
                    attackerMinion = null;
                    attackerSpell = null;
                    targetHero = null;
                    targetMinion = null;
                    heroPowerUser = null;
                    updateUI();
                    gameView.updateCurHeroIcon(game.getCurrentHero().getName());
                    gameView.updateOppHeroIcon(game.getOpponent().getName());
                } catch (CloneNotSupportedException e) {
                    attackerMinion = null;
                    attackerSpell = null;
                    targetHero = null;
                    targetMinion = null;
                    heroPowerUser = null;
                    updateUI();
                    try {
                        gameView.updateCurHeroIcon(game.getCurrentHero().getName());
                        gameView.updateOppHeroIcon(game.getOpponent().getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FullHandException e) {
                    attackerMinion = null;
                    attackerSpell = null;
                    targetHero = null;
                    targetMinion = null;
                    heroPowerUser = null;
                    updateUI();
                    try {
                        gameView.updateCurHeroIcon(game.getCurrentHero().getName());
                        gameView.updateOppHeroIcon(game.getOpponent().getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                }
            }


        } else if (mouseEvent.getComponent() instanceof JLabel) {
            JLabel clicked = (JLabel) mouseEvent.getComponent();
            if (gameView.getCurHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getCurrentHero());
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException e1) {

                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (NotYourTurnException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getOppHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Mage) {
                try {
                    ((Mage) heroPowerUser).useHeroPower(game.getOpponent());
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException e1) {

                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (NotYourTurnException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getCurHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getCurrentHero());
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException e1) {

                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (NotYourTurnException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getOppHero() == clicked && heroPowerUser != null && heroPowerUser instanceof Priest) {
                try {
                    ((Priest) heroPowerUser).useHeroPower(game.getOpponent());
                    try {
                        Thread.sleep(playSound("Sound/Heroes/" + heroPowerUser.getName() + "/attack.wav") / 1000);
                    } catch (IOException e) {
                        try {
                            Thread.sleep(playSound("Sound/Heroes/Uther Lightbringer/attack.wav") / 1000);
                        } catch (IOException | InterruptedException e1) {

                        }
                    }
                    heroPowerUser = null;
                    updateUI();
                } catch (NotYourTurnException | CloneNotSupportedException e) {
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (FullHandException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullhand.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/fullhand.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (FullFieldException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/fullfield.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Anduin Wrynn/fullfield.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (HeroPowerAlreadyUsedException e) {
                    try {
                        playSound("Sound/Heroes/" + heroPowerUser.getName() + "/heropower.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/heropower.wav");
                        } catch (IOException exc) {

                        }
                    }
                    heroPowerUser = null;
                } catch (InterruptedException e) {

                }
            } else if (gameView.getOppHero() == clicked && attackerMinion != null) {
                targetHero = game.getOpponent();
                try {
                    game.getCurrentHero().attackWithMinion(attackerMinion, targetHero);
                    playSound("Sound/Minions/" + attackerMinion.getName() + "/attack.wav");
                    attackerMinion = null;
                    targetHero = null;
                    updateUI();
                } catch (NotYourTurnException | NotSummonedException e) {
                    attackerMinion = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (IOException e) {
                    attackerMinion = null;
                    targetHero = null;
                    updateUI();
                } catch (TauntBypassException e) {
                    attackerMinion = null;
                    targetHero = null;
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/taunt.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/taunt.wav");
                        } catch (IOException exc) {

                        }
                    }
                } catch (CannotAttackException e) {
                    attackerMinion = null;
                    targetHero = null;
                    if (e.getMessage().equals("You can't attack with a sleeping minion")) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/sleeping.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Uther Lightbringer/sleeping.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } else if (e.getMessage().equals("Your minion has already attacked")) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/already_attacked.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/already_attacked.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (InvalidTargetException e) {
                    attackerMinion = null;
                    targetHero = null;
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/invalidtarget.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/invalidtarget.wav");
                        } catch (IOException exc) {

                        }
                    }
                }
            } else if (gameView.getCurHero() == clicked && attackerMinion != null) {
                targetHero = game.getCurrentHero();
                try {
                    game.getCurrentHero().attackWithMinion(attackerMinion, targetHero);
                    attackerMinion = null;
                    targetHero = null;
                    updateUI();
                } catch (NotYourTurnException | NotSummonedException e) {
                    attackerMinion = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (TauntBypassException e) {
                    attackerMinion = null;
                    targetHero = null;
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/taunt.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Rexxar/taunt.wav");
                        } catch (IOException exc) {

                        }
                    }
                } catch (CannotAttackException e) {
                    attackerMinion = null;
                    targetHero = null;
                    if (e.getMessage().equals("You can't attack with a sleeping minion")) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/sleeping.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Uther Lightbringer/sleeping.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } else if (e.getMessage().equals("Your minion has already attacked")) {
                        try {
                            playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/already_attacked.wav");
                        } catch (IOException ex) {
                            try {
                                playSound("Sound/Heroes/Rexxar/already_attacked.wav");
                            } catch (IOException exc) {

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(gameView,
                                e.getMessage(),
                                "Hearthstone",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (InvalidTargetException e) {
                    attackerMinion = null;
                    targetHero = null;
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/invalidtarget.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/invalidtarget.wav");
                        } catch (IOException exc) {

                        }
                    }
                }
            } else if (gameView.getCurHero() == clicked && attackerSpell != null && attackerSpell instanceof HeroTargetSpell) {
                targetHero = game.getCurrentHero();
                try {
                    game.getCurrentHero().castSpell((HeroTargetSpell) attackerSpell, targetHero);
                    attackerSpell = null;
                    targetHero = null;
                    updateUI();
                } catch (NotYourTurnException e) {
                    attackerSpell = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    attackerSpell = null;
                    targetHero = null;
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
                }
            } else if (gameView.getOppHero() == clicked && attackerSpell != null && attackerSpell instanceof HeroTargetSpell) {
                targetHero = game.getOpponent();
                try {
                    game.getCurrentHero().castSpell((HeroTargetSpell) attackerSpell, targetHero);
                    attackerSpell = null;
                    targetHero = null;
                    updateUI();
                } catch (NotYourTurnException e) {
                    attackerSpell = null;
                    targetHero = null;
                    JOptionPane.showMessageDialog(gameView,
                            e.getMessage(),
                            "Hearthstone",
                            JOptionPane.WARNING_MESSAGE);
                } catch (NotEnoughManaException e) {
                    attackerSpell = null;
                    targetHero = null;
                    try {
                        playSound("Sound/Heroes/" + game.getCurrentHero().getName() + "/mana.wav");
                    } catch (IOException ex) {
                        try {
                            attackerSpell = null;
                            targetHero = null;
                            playSound("Sound/Heroes/Gul'dan/mana.wav");
                        } catch (IOException exc) {

                        }
                    }
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
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        JRadioButton b = (JRadioButton) itemEvent.getItemSelectable();
        if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
            int deselected;
            if (gameView.getChooseFirstHero().contains(b)) {
                deselected = gameView.getChooseFirstHero().indexOf(b);
                TitledBorder border = BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                        gameView.getNames()[deselected], TitledBorder.CENTER,
                        TitledBorder.TOP, gameView.getFont());
                border.setTitleColor(Color.white);
                b.setBorder(border);
            } else {
                if (gameView.getChooseSecondHero().contains(b)) {
                    deselected = gameView.getChooseSecondHero().indexOf(b);
                    TitledBorder border = BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                            gameView.getNames()[deselected], TitledBorder.CENTER,
                            TitledBorder.BOTTOM, gameView.getFont());
                    border.setTitleColor(Color.white);
                    b.setBorder(border);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() instanceof JButton) {

            JButton button = (JButton) mouseEvent.getComponent();
            if (gameView.getStartGame() != button) {
                final Point point = button.getLocation();
                final int delay = 35;
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 2; i++) {
                            try {
                                moveButton(new Point(point.x + 2, point.y), button);
                                Thread.sleep(delay);
                                moveButton(point, button);
                                Thread.sleep(delay);
                                moveButton(new Point(point.x - 2, point.y), button);
                                Thread.sleep(delay);
                                moveButton(point, button);
                                Thread.sleep(delay);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }

                    }
                };
                Thread t = new Thread(r);
                t.start();

            }
        }
    }

    private void moveButton(final Point p, JButton button) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                button.setLocation(p);
            }
        });
    }

    public static void main(String[] args) throws FullHandException, FontFormatException, CloneNotSupportedException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        Controller c = new Controller();
    }
}