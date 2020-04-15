package view;


import engine.Game;
import exceptions.FullHandException;
import model.cards.minions.Minion;
import model.cards.spells.ShadowWordDeath;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import org.junit.internal.builders.JUnit3Builder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GameView extends JFrame {

    private ArrayList<JTextArea> curFieldHPs, curFieldAttacks, curFieldManaCosts, oppFieldHPs, oppFieldAttacks, oppFieldManaCosts,
            curHandHPs, curHandAttacks, curHandManaCosts;
    private ArrayList<JButton> curFieldMinions, oppFieldMinions, curHandLeftMinions, curHandRightMinions;
    private JTextArea curHeroHP, oppHeroHP;
    private Font font;

    public GameView() throws IOException, FontFormatException {
        super();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = d.width;
        int height = width * 9 / 16;
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/NewRocker-Regular.ttf"));
        font = font.deriveFont(Font.PLAIN, 20);
    }

    public void setInitial() throws IOException, FontFormatException {

        getContentPane().removeAll();
        revalidate();
        repaint();
        setVisible(true);

        this.setLayout(new GridLayout(3, 1));

        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        JPanel start = new JPanel();

        player1.setLayout(new GridLayout(1, 6, 10, 0));
        player2.setLayout(new GridLayout(1, 6, 10, 0));
        start.setLayout(new BorderLayout());

        player1.setSize(new Dimension(getWidth(), (int) (2 * getHeight() / 5)));
        player2.setSize(new Dimension(getWidth(), (int) (2 * getHeight() / 5)));

        JLabel label1 = new JLabel("   Choose First Hero");
        JLabel label2 = new JLabel("   Choose Second Hero");
        label1.setFont(font);
        label2.setFont(font);

        player1.add(label1);
        player2.add(label2);

        String[] arr = {"Jaina-full.jpg", "Rexxar_hero_art.jpg", "Uther_-_full.jpg", "Anduin-full.jpg", "Gul'dan_full.jpg"};
        String[] names = {"MAGE", "HUNTER", "PALADIN", "PRIEST", "WARLOCK"};

        for (int i = 0; i < 5; i++) {
            JButton button = new JButton();
            Image image = ImageIO.read(new File("HeroesIntro/" + arr[i]));
            Icon icon = new ImageIcon(image.getScaledInstance(-1, 230 * getHeight() / 810, Image.SCALE_SMOOTH));
            button.setContentAreaFilled(false);
            button.setIcon(icon);
            button.setBorder(BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                    names[i], TitledBorder.CENTER,
                    TitledBorder.TOP, font));
            player1.add(button);
        }


        for (int i = 0; i < 5; i++) {
            JButton button = new JButton();
            Image image = ImageIO.read(new File("HeroesIntro/" + arr[i]));
            Icon icon = new ImageIcon(image.getScaledInstance(-1, 230 * getHeight() / 810, Image.SCALE_SMOOTH));
            button.setIcon(icon);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                    names[i], TitledBorder.CENTER,
                    TitledBorder.BOTTOM, font));
            player2.add(button);
        }

        this.add(player2);
        JButton startGame = new JButton("START GAME");
        startGame.setFont(font);
        startGame.setSize(new Dimension(getWidth() / 4, getHeight() / 6));
        JPanel startPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 100;
        gbc.ipady = 70;
        startPanel.add(startGame, gbc);
        startPanel.setSize(new Dimension(getWidth() / 4, startPanel.getHeight()));
        this.add(startPanel);
        this.add(player1);

        revalidate();
        repaint();
    }

    public void setGamePlay(Game game) throws IOException, FontFormatException {
        getContentPane().removeAll();
        revalidate();
        repaint();

        BufferedImage backgroundImage = ImageIO.read(new File("background.jpg"));
        setContentPane(new Background(backgroundImage));
        setTitle("Hearthstone");

        setLayout(new GridLayout(2, 1));
        JPanel currentPanel = new JPanel();
        JPanel oppPanel = new JPanel();
        currentPanel.setOpaque(false);
        oppPanel.setOpaque(false);

        oppPanel.setLayout(null);
        Insets insets = oppPanel.getInsets();

        JLabel oppDeck = new JLabel();
        Image oppDeckImage = ImageIO.read(new File("FlippedCard.png"));
        ImageIcon oppDeckIcon = new ImageIcon(oppDeckImage.getScaledInstance((5 * getWidth() / 72), -160, Image.SCALE_DEFAULT));
        oppDeck.setIcon(oppDeckIcon);
        Dimension size = oppDeck.getPreferredSize();
        oppPanel.add(oppDeck);
        oppDeck.setBounds(insets.left, insets.top + (60 * getHeight() / 412), size.width, size.height);

        JTextArea oppDeckNum = new JTextArea("Remaining\ncards\nin the deck: " + game.getOpponent().getDeck().size());
        oppDeckNum.setEditable(false);
        oppDeckNum.setOpaque(false);
        oppDeckNum.setFont(font);
        size = oppDeckNum.getPreferredSize();
        oppPanel.add(oppDeckNum);
        oppDeckNum.setBounds(insets.left + (11 * getWidth() / 144), insets.top + (60 * getHeight() / 412), size.width, size.height);

        JLabel oppHero = new JLabel();
        Image oppImage = ImageIO.read(new File("Heroes/" + game.getOpponent().getName() + ".png"));
        ImageIcon oppIcon = new ImageIcon(oppImage.getScaledInstance(-200, (115 * getHeight() / 412), Image.SCALE_DEFAULT));
        oppHero.setIcon(oppIcon);
        size = oppHero.getPreferredSize();
        oppPanel.add(oppHero);
        oppHero.setBounds(insets.left + (4 * getWidth() / 9), insets.top + (5 * getHeight() / 412), size.width, size.height);
        oppHeroHP = new JTextArea("" + game.getOpponent().getCurrentHP());
        oppHero.add(oppHeroHP);
        oppHeroHP.setEditable(false);
        oppHeroHP.setOpaque(false);
        oppHeroHP.setForeground(Color.WHITE);
        oppHeroHP.setFont(font);
        oppHero.setLayout(null);
        Insets tmpInsets = oppHero.getInsets();
        Dimension tmpSize = oppHeroHP.getPreferredSize();
        oppHeroHP.setBounds(tmpInsets.left + (130 * getWidth() / 1440), tmpInsets.top + (140 * getHeight() / 810), tmpSize.width, tmpSize.height);

        JLabel oppHand = new JLabel();
        Image oppHandImage = ImageIO.read(new File("handBack.png"));
        ImageIcon oppHandIcon = new ImageIcon(oppHandImage.getScaledInstance(-250, (25 * getHeight() / 103), Image.SCALE_DEFAULT));
        oppHand.setIcon(oppHandIcon);
        size = oppHand.getPreferredSize();
        oppPanel.add(oppHand);
        oppHand.setBounds(insets.left + (95 * getWidth() / 144), insets.top, size.width, size.height);

        JTextArea oppHandNum = new JTextArea("Remaining\ncards\nin the hand: " + game.getOpponent().getHand().size());
        oppHandNum.setEditable(false);
        oppHandNum.setOpaque(false);
        oppHandNum.setFont(font);
        size = oppHandNum.getPreferredSize();
        oppPanel.add(oppHandNum);
        oppHandNum.setBounds(insets.left + (115 * getWidth() / 144), insets.top + (5 * getHeight() / 103), size.width, size.height);

        JLabel oppManaCrystal = new JLabel();
        Image oppManaCrystalImage = ImageIO.read(new File("manaCrystal.png"));
        ImageIcon oppManaCrystalsIcon = new ImageIcon(oppManaCrystalImage.getScaledInstance(-100, (15 * getHeight() / 206), Image.SCALE_DEFAULT));
        oppManaCrystal.setIcon(oppManaCrystalsIcon);
        size = oppManaCrystal.getPreferredSize();
        oppPanel.add(oppManaCrystal);
        oppManaCrystal.setBounds(insets.left + (getWidth() / 72), insets.top + (115 * getHeight() / 412), size.width, size.height);


        JTextArea oppManaCrystalsNum = new JTextArea("Remaining\nMana Crystals: " + game.getOpponent().getCurrentManaCrystals() + "/" +
                game.getOpponent().getTotalManaCrystals());
        oppManaCrystalsNum.setEditable(false);
        oppManaCrystalsNum.setOpaque(false);
        oppManaCrystalsNum.setFont(font);
        oppPanel.add(oppManaCrystalsNum);
        size = oppManaCrystalsNum.getPreferredSize();
        oppManaCrystalsNum.setBounds(insets.left + (9 * getWidth() / 144), insets.top + (235 * getHeight() / 824), size.width, size.height);

        JPanel oppField = new JPanel();
        oppField.setLayout(new GridLayout(1, 7));
        oppField.setOpaque(false);
        oppFieldMinions = new ArrayList<>();
        for (int i = 0; i < game.getOpponent().getField().size(); i++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File("Minions/" + game.getOpponent().getField().get(i).getName() + ".png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 170 * getHeight() / 864, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            oppField.add(b);
            oppFieldMinions.add(b);
        }

        oppFieldHPs = new ArrayList<>();
        oppFieldAttacks = new ArrayList<>();
        oppFieldManaCosts = new ArrayList<>();
        for (int i = 0; i < game.getOpponent().getField().size(); i++) {
            oppFieldHPs.add(new JTextArea(game.getOpponent().getField().get(i).getCurrentHP() + ""));
            oppFieldHPs.get(i).setFont(font);
            oppFieldHPs.get(i).setEditable(false);
            oppFieldHPs.get(i).setOpaque(false);
            oppFieldHPs.get(i).setForeground(Color.WHITE);

            oppFieldAttacks.add(new JTextArea(game.getOpponent().getField().get(i).getAttack() + ""));
            oppFieldAttacks.get(i).setFont(font);
            oppFieldAttacks.get(i).setEditable(false);
            oppFieldAttacks.get(i).setOpaque(false);
            oppFieldAttacks.get(i).setForeground(Color.WHITE);

            oppFieldManaCosts.add(new JTextArea(game.getOpponent().getField().get(i).getManaCost() + ""));
            oppFieldManaCosts.get(i).setFont(font);
            oppFieldManaCosts.get(i).setEditable(false);
            oppFieldManaCosts.get(i).setOpaque(false);
            oppFieldManaCosts.get(i).setForeground(Color.WHITE);
        }

        size = oppField.getPreferredSize();
        oppPanel.add(oppField);
        oppField.setBounds(insets.left + (4 * getWidth() / 23), insets.top + (115 * getHeight() / 412), size.width,
                size.height);

        setOppFieldAttributesLocations();

        currentPanel.setLayout(null);

        JLabel currDeck = new JLabel();
        currDeck.setIcon(oppDeckIcon);
        currentPanel.add(currDeck);
        size = oppDeck.getPreferredSize();
        currDeck.setBounds(insets.left, insets.top + 100 * getHeight() / 864, size.width, size.height);

        JLabel curManaCrystal = new JLabel();
        curManaCrystal.setIcon(oppManaCrystalsIcon);
        currentPanel.add(curManaCrystal);
        size = oppManaCrystal.getPreferredSize();
        curManaCrystal.setBounds(insets.left + (getWidth() / 72), insets.top, size.width, size.height);

        JTextArea curDeckNum = new JTextArea("Remaining\ncards\nin Deck: " + game.getCurrentHero().getDeck().size());
        currentPanel.add(curDeckNum);
        curDeckNum.setFont(font);
        size = curDeckNum.getPreferredSize();
        currentPanel.add(curDeckNum);
        curDeckNum.setEditable(false);
        curDeckNum.setOpaque(false);
        curDeckNum.setBounds(insets.left + (11 * getWidth() / 144), insets.top + (50 * getHeight() / 450), size.width, size.height);

        JTextArea currManaCrystalsNum = new JTextArea("Remaining\nMana Crystals: " + game.getCurrentHero().getCurrentManaCrystals() + "/" +
                game.getCurrentHero().getTotalManaCrystals());
        currentPanel.add(currManaCrystalsNum);
        currManaCrystalsNum.setFont(font);
        currManaCrystalsNum.setOpaque(false);
        currManaCrystalsNum.setEditable(false);
        size = currManaCrystalsNum.getPreferredSize();
        currManaCrystalsNum.setBounds(insets.left + (9 * getWidth() / 144), insets.top + (getHeight() / 180), size.width, size.height);

        JPanel currHandLeft = new JPanel();
        currentPanel.add(currHandLeft);
        currHandLeft.setOpaque(false);
        currHandLeft.setLayout(new GridLayout(1, 5));
        curHandLeftMinions = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < 5 && j < game.getCurrentHero().getHand().size(); i++, j++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File(game.getCurrentHero().getHand().get(j) instanceof ShadowWordDeath ? "Spells/Shadow Word Death.png" :
                    (game.getCurrentHero().getHand().get(j) instanceof Minion ? "Minions" : "Spells")
                            + "/" + game.getCurrentHero().getHand().get(j).getName() + ".png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 170 * getHeight() / 864, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            currHandLeft.add(b);
            curHandLeftMinions.add(b);
        }
        size = currHandLeft.getPreferredSize();
        currHandLeft.setBounds(insets.left, insets.top + 230 * getHeight() / 864, size.width - 100 * getWidth() / 1536, size.height);

        JLabel curHero = new JLabel();
        Image currImage = ImageIO.read(new File("Heroes/" + game.getCurrentHero().getName() + ".png"));
        ImageIcon currIcon = new ImageIcon(currImage.getScaledInstance(-200 * getWidth() / 1536, (115 * getHeight() / 412), Image.SCALE_DEFAULT));
        curHero.setIcon(currIcon);
        size = curHero.getPreferredSize();
        currentPanel.add(curHero);
        curHero.setBounds(insets.left + (4 * getWidth() / 9), insets.top + 200 * getHeight() / 864, size.width, size.height);
        curHeroHP = new JTextArea("" + game.getCurrentHero().getCurrentHP());
        curHero.add(curHeroHP);
        curHeroHP.setEditable(false);
        curHeroHP.setOpaque(false);
        curHeroHP.setForeground(Color.WHITE);
        curHeroHP.setFont(font);
        curHero.setLayout(null);
        tmpInsets = oppHero.getInsets();
        tmpSize = curHeroHP.getPreferredSize();
        curHeroHP.setBounds(tmpInsets.left + (130 * getWidth() / 1440), tmpInsets.top + (140 * getHeight() / 810), tmpSize.width, tmpSize.height);

        JPanel currHandRight = new JPanel();
        currentPanel.add(currHandRight);
        currHandRight.setLayout(new GridLayout(1, 5));
        currHandRight.setOpaque(false);
        curHandRightMinions = new ArrayList<>();
        for (int i = 0; i < 5 && j < game.getCurrentHero().getHand().size(); i++, j++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File(game.getCurrentHero().getHand().get(j) instanceof ShadowWordDeath ? "Spells/Shadow Word Death.png" :
                    (game.getCurrentHero().getHand().get(j) instanceof Minion ? "Minions" : "Spells")
                            + "/" + game.getCurrentHero().getHand().get(j).getName() + ".png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 170 * getHeight() / 864, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            currHandRight.add(b);
            curHandRightMinions.add(b);
        }
        size = currHandRight.getPreferredSize();
        currHandRight.setBounds(insets.left + 850 * getWidth() / 1536, insets.top + 230 * getHeight() / 864, size.width - 100 * getWidth() / 1536, size.height);

        curHandHPs = new ArrayList<>();
        curHandAttacks = new ArrayList<>();
        curHandManaCosts = new ArrayList<>();
        for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
            curHandHPs.add(new JTextArea(game.getCurrentHero().getHand().get(i) instanceof Minion ?
                    ((Minion) game.getCurrentHero().getHand().get(i)).getCurrentHP() + "" : ""));
            curHandHPs.get(i).setFont(font);
            curHandHPs.get(i).setEditable(false);
            curHandHPs.get(i).setOpaque(false);
            curHandHPs.get(i).setForeground(Color.WHITE);

            curHandAttacks.add(new JTextArea(game.getCurrentHero().getHand().get(i) instanceof Minion ?
                    ((Minion) game.getCurrentHero().getHand().get(i)).getAttack() + "" : ""));
            curHandAttacks.get(i).setFont(font);
            curHandAttacks.get(i).setEditable(false);
            curHandAttacks.get(i).setOpaque(false);
            curHandAttacks.get(i).setForeground(Color.WHITE);

            curHandManaCosts.add(new JTextArea(game.getCurrentHero().getHand().get(i).getManaCost() + ""));
            curHandManaCosts.get(i).setFont(font);
            curHandManaCosts.get(i).setEditable(false);
            curHandManaCosts.get(i).setOpaque(false);
            curHandManaCosts.get(i).setForeground(Color.WHITE);
        }

        setCurHandAttributesLocations();

        JPanel curField = new JPanel();
        currentPanel.add(curField);
        curField.setLayout(new GridLayout(1, 7));
        curField.setOpaque(false);
        curFieldMinions = new ArrayList<>();
        for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File("Minions/" + game.getCurrentHero().getField().get(i).getName() + ".png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 85 * getHeight() / 432, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            curField.add(b);
            curFieldMinions.add(b);
        }
        size = curField.getPreferredSize();
        curField.setBounds(insets.left + (4 * getWidth() / 23), insets.top + (5 * getHeight() / 216), size.width,
                size.height);

        JButton endTurnButton = new JButton("END TURN");
        endTurnButton.setFont(font);
        currentPanel.add(endTurnButton);
        size = endTurnButton.getPreferredSize();
        endTurnButton.setContentAreaFilled(false);
        endTurnButton.setBounds(insets.left + (685 * getWidth() / 768), insets.top, size.width, size.height);

        JButton heroPowerButton = new JButton("HERO POWER");
        heroPowerButton.setFont(font);
        currentPanel.add(heroPowerButton);
        heroPowerButton.setContentAreaFilled(false);
        size = heroPowerButton.getPreferredSize();
        heroPowerButton.setBounds(insets.left + (675 * getWidth() / 768), insets.top + (35 * getHeight() / 432), size.width, size.height);

        curFieldHPs = new ArrayList<>(7);
        curFieldAttacks = new ArrayList<>();
        curFieldManaCosts = new ArrayList<>();

        for (int i = 0; i < game.getCurrentHero().getField().size(); i++) {
            curFieldHPs.add(new JTextArea(game.getCurrentHero().getField().get(i).getCurrentHP() + ""));
            curFieldHPs.get(i).setFont(font);
            curFieldHPs.get(i).setEditable(false);
            curFieldHPs.get(i).setOpaque(false);
            curFieldHPs.get(i).setForeground(Color.WHITE);

            curFieldAttacks.add(new JTextArea(game.getCurrentHero().getField().get(i).getAttack() + ""));
            curFieldAttacks.get(i).setFont(font);
            curFieldAttacks.get(i).setEditable(false);
            curFieldAttacks.get(i).setOpaque(false);
            curFieldAttacks.get(i).setForeground(Color.WHITE);

            curFieldManaCosts.add(new JTextArea(game.getCurrentHero().getField().get(i).getManaCost() + ""));
            curFieldManaCosts.get(i).setFont(font);
            curFieldManaCosts.get(i).setEditable(false);
            curFieldManaCosts.get(i).setOpaque(false);
            curFieldManaCosts.get(i).setForeground(Color.WHITE);
        }

        setCurFieldAttributesLocations();

        add(oppPanel);
        add(currentPanel);

        revalidate();
        repaint();
        setResizable(false);
        setVisible(true);
    }

    private void setCurFieldAttributesLocations() {
        for (int i = 0; i < curFieldMinions.size(); i++) {
            curFieldMinions.get(i).setLayout(null);
            curFieldMinions.get(i).add(curFieldHPs.get(i));
            curFieldMinions.get(i).add(curFieldAttacks.get(i));
            curFieldMinions.get(i).add(curFieldManaCosts.get(i));
            Insets insets = curFieldMinions.get(i).getInsets();

            Dimension size = curFieldHPs.get(i).getPreferredSize();
            curFieldHPs.get(i).setBounds(insets.left + (Integer.parseInt(curFieldHPs.get(i).getText()) > 9 ? 88 : 92) * getWidth() / 1440,
                    insets.top + (133 * getHeight() / 810), size.width, size.height);

            size = curFieldAttacks.get(i).getPreferredSize();
            curFieldAttacks.get(i).setBounds(insets.left + (Integer.parseInt(curFieldAttacks.get(i).getText()) > 9 ? 10 : 13) * getWidth() / 1440,
                    insets.top + (133 * getHeight() / 810), size.width, size.height);

            size = curFieldManaCosts.get(i).getPreferredSize();
            curFieldManaCosts.get(i).setBounds(insets.left + (Integer.parseInt(curFieldManaCosts.get(i).getText()) > 9 ? 8 : 13) * getWidth() / 1440,
                    insets.top + (13 * getHeight() / 810), size.width, size.height);
        }
    }

    private void setOppFieldAttributesLocations() {
        for (int i = 0; i < oppFieldMinions.size(); i++) {
            oppFieldMinions.get(i).setLayout(null);
            oppFieldMinions.get(i).add(oppFieldHPs.get(i));
            oppFieldMinions.get(i).add(oppFieldAttacks.get(i));
            oppFieldMinions.get(i).add(oppFieldManaCosts.get(i));
            Insets insets = oppFieldMinions.get(i).getInsets();

            Dimension size = oppFieldHPs.get(i).getPreferredSize();
            oppFieldHPs.get(i).setBounds(insets.left + (Integer.parseInt(oppFieldHPs.get(i).getText()) > 9 ? 88 : 92) * getWidth() / 1440,
                    insets.top + (133 * getHeight() / 810), size.width, size.height);

            size = oppFieldAttacks.get(i).getPreferredSize();
            oppFieldAttacks.get(i).setBounds(insets.left + (Integer.parseInt(oppFieldAttacks.get(i).getText()) > 9 ? 10 : 13) * getWidth() / 1440,
                    insets.top + (133 * getHeight() / 810), size.width, size.height);

            size = oppFieldManaCosts.get(i).getPreferredSize();
            oppFieldManaCosts.get(i).setBounds(insets.left + (Integer.parseInt(oppFieldManaCosts.get(i).getText()) > 9 ? 8 : 13) * getWidth() / 1440,
                    insets.top + (13 * getHeight() / 810), size.width, size.height);
        }
    }

    private void setCurHandAttributesLocations() {
        int j = 0;
        for (int i = 0; i < curHandLeftMinions.size(); j++, i++) {
            curHandLeftMinions.get(i).setLayout(null);
            curHandLeftMinions.get(i).add(curHandHPs.get(j));
            curHandLeftMinions.get(i).add(curHandAttacks.get(j));
            curHandLeftMinions.get(i).add(curHandManaCosts.get(j));
            Insets insets = curHandLeftMinions.get(i).getInsets();

            Dimension size = curHandHPs.get(j).getPreferredSize();
            if(!curHandHPs.get(j).getText().equals("")) {
                curHandHPs.get(j).setBounds(insets.left + (Integer.parseInt(curHandHPs.get(j).getText()) > 9 ? 77 : 81) * getWidth() / 1440,
                        insets.top + (133 * getHeight() / 810), size.width, size.height);
            }
            size = curHandAttacks.get(j).getPreferredSize();

            if(!curHandAttacks.get(j).getText().equals("")) {
                curHandAttacks.get(j).setBounds(insets.left + (Integer.parseInt(curHandAttacks.get(j).getText()) > 9 ? 1 : 4) * getWidth() / 1440,
                        insets.top + (133 * getHeight() / 810), size.width, size.height);
            }

            size = curHandManaCosts.get(j).getPreferredSize();
            curHandManaCosts.get(j).setBounds(insets.left + (Integer.parseInt(curHandManaCosts.get(j).getText()) > 9 ? 0 : 5) * getWidth() / 1440,
                    insets.top + (13 * getHeight() / 810), size.width, size.height);
        }

        if (curHandRightMinions.size() > 0) {
            for (int i = 0; i < curHandRightMinions.size(); i++, j++) {
                curHandRightMinions.get(i).setLayout(null);
                curHandRightMinions.get(i).add(curHandHPs.get(j));
                curHandRightMinions.get(i).add(curHandAttacks.get(j));
                curHandRightMinions.get(i).add(curHandManaCosts.get(j));
                Insets insets = curHandRightMinions.get(i).getInsets();

                Dimension size = curHandHPs.get(j).getPreferredSize();
                if(!curHandHPs.get(j).getText().equals("")) {
                    curHandHPs.get(j).setBounds(insets.left + (Integer.parseInt(curHandHPs.get(j).getText()) > 9 ? 77 : 81) * getWidth() / 1440,
                            insets.top + (133 * getHeight() / 810), size.width, size.height);
                }

                size = curHandAttacks.get(j).getPreferredSize();

                if(!curHandAttacks.get(j).getText().equals("")) {
                    curHandAttacks.get(j).setBounds(insets.left + (Integer.parseInt(curHandAttacks.get(j).getText()) > 9 ? 1 : 4) * getWidth() / 1440,
                            insets.top + (133 * getHeight() / 810), size.width, size.height);
                }

                size = curHandManaCosts.get(j).getPreferredSize();
                curHandManaCosts.get(j).setBounds(insets.left + (Integer.parseInt(curHandManaCosts.get(j).getText()) > 9 ? 0 : 5) * getWidth() / 1440,
                        insets.top + (13 * getHeight() / 810), size.width, size.height);
            }
        }
    }


    public static void main(String[] args) throws IOException, FontFormatException, CloneNotSupportedException, FullHandException {
        GameView g = new GameView();
        g.setGamePlay(new Game(new Priest(), new Paladin()));
    }

}
