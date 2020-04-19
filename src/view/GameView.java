package view;


import engine.Game;
import exceptions.FullHandException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.ShadowWordDeath;
import model.heroes.Hunter;
import model.heroes.Paladin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameView extends JFrame {

    private ArrayList<JTextArea> curFieldHPs, curFieldAttacks, curFieldManaCosts, oppFieldHPs, oppFieldAttacks, oppFieldManaCosts,
            curHandHPs, curHandAttacks, curHandManaCosts;
    private ArrayList<JButton> curFieldMinions;
    private ArrayList<JButton> oppFieldMinions;
    private ArrayList<JButton> curHandLeftMinions;
    private ArrayList<JButton> curHandRightMinions;
    private ArrayList<JRadioButton> chooseFirstHero;
    private ArrayList<JRadioButton> chooseSecondHero;
    private JTextArea curHeroHP, oppHeroHP;
    private Font font;
    private JButton startGame;
    private String[] names;
    private ArrayList<JButton> curHand;
    private JPanel curHandLeft;
    private JPanel curHandRight;
    private JPanel curentPanel;
    private JPanel oppPanel;
    private JPanel curField;
    private JPanel oppField;
    private JTextArea curDeckNum;
    private JTextArea curManaCrystalsNum;
    private JTextArea oppManaCrystalsNum;
    private JTextArea oppHandNum;
    private JTextArea oppDeckNum;
    private JButton heroPowerButton;
    private JButton endTurnButton;
    private JLabel oppHero;
    private JLabel curHero;
    private JButton exitButton0;
    private JButton startButton0;

    public GameView() throws IOException, FontFormatException {
        super();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(1440, 817);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/NewRocker-Regular.ttf"));
        font = font.deriveFont(Font.PLAIN, 20);
    }

    public JButton getExitButton0() {
        return exitButton0;
    }

    public JButton getStartButton0() {
        return startButton0;
    }

    @Override
    public Font getFont() {
        return font;
    }

    public JButton getStartGame() {
        return startGame;
    }

    public String[] getNames() {
        return names;
    }

    public ArrayList<JRadioButton> getChooseFirstHero() {
        return chooseFirstHero;
    }

    public ArrayList<JRadioButton> getChooseSecondHero() {
        return chooseSecondHero;
    }

    public void setInitial0() throws IOException {
        getContentPane().removeAll();
        revalidate();
        repaint();

        BufferedImage bufferedImage = ImageIO.read(new File("initialBackground.png"));
        setContentPane(new Background(bufferedImage));
        setTitle("Hearthstone");

        JPanel start = new JPanel();
        JPanel exit = new JPanel();

        start.setOpaque(false);
        exit.setOpaque(false);

        setLayout(null);

        startButton0 = new JButton();

        Image startImage = ImageIO.read(new File("startGame.png"));
        ImageIcon startImageIcon = new ImageIcon(startImage);
        startButton0.setIcon(startImageIcon);
        startButton0.setContentAreaFilled(false);
        startButton0.setBorderPainted(false);
        Insets insets =startButton0.getInsets();
        Dimension size=startButton0.getPreferredSize();
        startButton0.setBounds(insets.left+180, insets.top-0, size.width, size.height);



        exitButton0 = new JButton();
        Image exitImage = ImageIO.read(new File("EXIT.png"));
        ImageIcon exitIcon = new ImageIcon(exitImage);
        exitButton0.setIcon(exitIcon);
        exitButton0.setContentAreaFilled(false);
        exitButton0.setBorderPainted(false);
        insets =exitButton0.getInsets();
        size=exitButton0.getPreferredSize();
        exitButton0.setBounds(insets.left+180, insets.top+400 ,size.width, size.height);


        add(startButton0);
        add(exitButton0);

        //start.add(startButton0);
        //exit.add(exitButton0);



        revalidate();
        repaint();
        setVisible(true);


    }

    public void setInitial() throws IOException, FontFormatException {

        getContentPane().removeAll();
        revalidate();
        repaint();

        BufferedImage bufferedImage = ImageIO.read(new File("Backgrounds/initial2.jpg"));
        setContentPane(new Background(bufferedImage));
        setTitle("Hearthstone");

        this.setLayout(new GridLayout(3, 1));

        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        player1.setOpaque(false);
        player2.setOpaque(false);

        player1.setLayout(new GridLayout(1, 6, 10, 0));
        player2.setLayout(new GridLayout(1, 6, 10, 0));

        player1.setSize(new Dimension(getWidth(), (int) (2 * getHeight() / 5)));
        player2.setSize(new Dimension(getWidth(), (int) (2 * getHeight() / 5)));

        JLabel label1 = new JLabel("   Choose First Hero");
        JLabel label2 = new JLabel("   Choose Second Hero");
        label1.setFont(font);
        label2.setFont(font);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);

        player1.add(label1);
        player2.add(label2);

        chooseFirstHero = new ArrayList<JRadioButton>();
        chooseSecondHero = new ArrayList<JRadioButton>();

        String[] arr = {"Jaina-full.jpg", "Rexxar_hero_art.jpg", "Uther_-_full.jpg", "Anduin-full.jpg", "Gul'dan_full.jpg"};
        names = new String[]{"MAGE", "HUNTER", "PALADIN", "PRIEST", "WARLOCK"};

        ButtonGroup group1 = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            //JButton button = new JButton();
            JRadioButton button = new JRadioButton();
            Image image = ImageIO.read(new File("HeroesIntro/" + arr[i]));
            Icon icon = new ImageIcon(image.getScaledInstance(-1, 230 * getHeight() / 810, Image.SCALE_SMOOTH));
            button.setContentAreaFilled(false);
            button.setIcon(icon);
            button.setBorderPainted(true);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            TitledBorder border = BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                    names[i], TitledBorder.CENTER,
                    TitledBorder.BOTTOM, font);
            border.setTitleColor(Color.WHITE);
            button.setBorder(border);
            player1.add(button);
            chooseFirstHero.add(button);
            group1.add(button);
        }

        ButtonGroup group2 = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            //JButton button = new JButton();
            JRadioButton button = new JRadioButton();
            Image image = ImageIO.read(new File("HeroesIntro/" + arr[i]));
            Icon icon = new ImageIcon(image.getScaledInstance(-1, 230 * getHeight() / 810, Image.SCALE_SMOOTH));
            button.setIcon(icon);
            button.setBorderPainted(true);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setContentAreaFilled(false);
            TitledBorder border = BorderFactory.createTitledBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.GRAY),
                    names[i], TitledBorder.CENTER,
                    TitledBorder.BOTTOM, font);
            border.setTitleColor(Color.WHITE);
            button.setBorder(border);
            player2.add(button);
            chooseSecondHero.add(button);
            group2.add(button);
        }

        this.add(player2);
        startGame = new JButton();
        Image startImage = ImageIO.read(new File("Play.png"));
        ImageIcon startImageIcon = new ImageIcon(startImage.getScaledInstance(300, -1, Image.SCALE_SMOOTH));
        startGame.setIcon(startImageIcon);
        startGame.setContentAreaFilled(false);
        startGame.setBorderPainted(false);


        JPanel startPanel = new JPanel(new GridBagLayout());
        startPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        startPanel.add(startGame, gbc);
        startPanel.setSize(new Dimension(getWidth() / 4, startPanel.getHeight()));
        this.add(startPanel);
        this.add(player1);

        revalidate();
        repaint();
        setVisible(true);
    }


    public JButton getHeroPowerButton() {
        return heroPowerButton;
    }

    public JButton getEndTurnButton() {
        return endTurnButton;
    }

    public JLabel getOppHero() {
        return oppHero;
    }

    public JLabel getCurHero() {
        return curHero;
    }

    public void setGamePlay(Game game) throws IOException, FontFormatException {
        getContentPane().removeAll();
        revalidate();
        repaint();

        BufferedImage backgroundImage = ImageIO.read(new File("Backgrounds/background.jpg"));
        setContentPane(new Background(backgroundImage));
        setTitle("Hearthstone");

        setLayout(new GridLayout(2, 1));
        curentPanel = new JPanel();
        oppPanel = new JPanel();
        curentPanel.setOpaque(false);
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

        oppDeckNum = new JTextArea();
        updateOppDeckNum(game.getOpponent().getDeck().size());
        oppDeckNum.setEditable(false);
        oppDeckNum.setOpaque(false);
        oppDeckNum.setFont(font);
        size = oppDeckNum.getPreferredSize();
        oppPanel.add(oppDeckNum);
        oppDeckNum.setBounds(insets.left + (11 * getWidth() / 144), insets.top + (60 * getHeight() / 412), size.width, size.height);

        oppHero = new JLabel();
        updateOppHeroIcon(game.getOpponent().getName());
        size = oppHero.getPreferredSize();
        oppPanel.add(oppHero);
        oppHero.setBounds(insets.left + 620, insets.top - 90, size.width, size.height);

        oppHeroHP = new JTextArea("");
        updateOppHeroHP(game.getOpponent().getCurrentHP());
        oppHero.add(oppHeroHP);
        oppHeroHP.setEditable(false);
        oppHeroHP.setOpaque(false);
        oppHeroHP.setForeground(Color.WHITE);
        oppHeroHP.setFont(font);
        oppHero.setLayout(null);
        Insets tmpInsets = oppHero.getInsets();
        Dimension tmpSize = oppHeroHP.getPreferredSize();
        oppHeroHP.setBounds(tmpInsets.left + 189, tmpInsets.top + 234, tmpSize.width, tmpSize.height);

        JLabel oppHand = new JLabel();
        Image oppHandImage = ImageIO.read(new File("handBack.png"));
        ImageIcon oppHandIcon = new ImageIcon(oppHandImage.getScaledInstance(-250, (25 * getHeight() / 103), Image.SCALE_DEFAULT));
        oppHand.setIcon(oppHandIcon);
        size = oppHand.getPreferredSize();
        oppPanel.add(oppHand);
        oppHand.setBounds(insets.left + (95 * getWidth() / 144), insets.top, size.width, size.height);

        oppHandNum = new JTextArea();
        updateOppHandNum(game.getOpponent().getHand().size());
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


        oppManaCrystalsNum = new JTextArea();
        updateOppManaCrystalsNum(game.getOpponent().getCurrentManaCrystals(), game.getOpponent().getTotalManaCrystals());
        oppManaCrystalsNum.setEditable(false);
        oppManaCrystalsNum.setOpaque(false);
        oppManaCrystalsNum.setFont(font);
        oppPanel.add(oppManaCrystalsNum);
        size = oppManaCrystalsNum.getPreferredSize();
        oppManaCrystalsNum.setBounds(insets.left + (9 * getWidth() / 144), insets.top + (235 * getHeight() / 824), size.width, size.height);

        updateOppField(game.getOpponent().getField());

        curentPanel.setLayout(null);

        JLabel curDeck = new JLabel();
        curDeck.setIcon(oppDeckIcon);
        curentPanel.add(curDeck);
        size = oppDeck.getPreferredSize();
        curDeck.setBounds(insets.left, insets.top + 100 * getHeight() / 864, size.width, size.height);

        JLabel curManaCrystal = new JLabel();
        curManaCrystal.setIcon(oppManaCrystalsIcon);
        curentPanel.add(curManaCrystal);
        size = oppManaCrystal.getPreferredSize();
        curManaCrystal.setBounds(insets.left + (getWidth() / 72), insets.top, size.width, size.height);

        curDeckNum = new JTextArea();
        updateCurDeckNum(game.getCurrentHero().getDeck().size());
        curentPanel.add(curDeckNum);
        curDeckNum.setFont(font);
        size = curDeckNum.getPreferredSize();
        curentPanel.add(curDeckNum);
        curDeckNum.setEditable(false);
        curDeckNum.setOpaque(false);
        curDeckNum.setBounds(insets.left + (11 * getWidth() / 144), insets.top + (50 * getHeight() / 450), size.width, size.height);

        curManaCrystalsNum = new JTextArea();
        updateCurManaCrystalsNum(game.getCurrentHero().getCurrentManaCrystals(), game.getCurrentHero().getTotalManaCrystals());
        curentPanel.add(curManaCrystalsNum);
        curManaCrystalsNum.setFont(font);
        curManaCrystalsNum.setOpaque(false);
        curManaCrystalsNum.setEditable(false);
        size = curManaCrystalsNum.getPreferredSize();
        curManaCrystalsNum.setBounds(insets.left + (9 * getWidth() / 144), insets.top + (getHeight() / 180), size.width, size.height);


        updateHand(game.getCurrentHero().getHand());

        curHero = new JLabel();
        updateCurHeroIcon(game.getCurrentHero().getName());
        size = curHero.getPreferredSize();
        curentPanel.add(curHero);
        curHero.setBounds(insets.left + 620, insets.top + 110, size.width, size.height);

        curHeroHP = new JTextArea("");
        updateCurHeroHP(game.getCurrentHero().getCurrentHP());
        curHero.add(curHeroHP);
        curHeroHP.setEditable(false);
        curHeroHP.setOpaque(false);
        curHeroHP.setForeground(Color.WHITE);
        curHeroHP.setFont(font);
        curHero.setLayout(null);
        tmpInsets = oppHero.getInsets();
        tmpSize = curHeroHP.getPreferredSize();
        curHeroHP.setBounds(tmpInsets.left + 189, tmpInsets.top + 234, tmpSize.width, tmpSize.height);


        updateCurField(game.getCurrentHero().getField());


        endTurnButton = new JButton("END TURN");
        endTurnButton.setFont(font);
        curentPanel.add(endTurnButton);
        size = endTurnButton.getPreferredSize();
        endTurnButton.setContentAreaFilled(false);
        endTurnButton.setBounds(insets.left + (685 * getWidth() / 768), insets.top, size.width, size.height);

        heroPowerButton = new JButton("HERO POWER");
        heroPowerButton.setFont(font);
        curentPanel.add(heroPowerButton);
        heroPowerButton.setContentAreaFilled(false);
        size = heroPowerButton.getPreferredSize();
        heroPowerButton.setBounds(insets.left + (675 * getWidth() / 768), insets.top + (35 * getHeight() / 432), size.width, size.height);

        add(oppPanel);
        add(curentPanel);

        revalidate();
        repaint();
        setResizable(false);
        setVisible(true);
    }

    public void updateCurHeroIcon(String name) throws IOException {
        Image currImage = ImageIO.read(new File("Heroes/" + name + ".png"));
        ImageIcon curIcon = new ImageIcon(currImage);
        curHero.setIcon(curIcon);
    }

    public void updateOppHeroIcon(String name) throws IOException {
        Image oppImage = ImageIO.read(new File("Heroes/" + name + ".png"));
        ImageIcon oppIcon = new ImageIcon(oppImage);
        oppHero.setIcon(oppIcon);
    }

    public ArrayList<JButton> getCurFieldMinions() {
        return curFieldMinions;
    }

    public ArrayList<JButton> getOppFieldMinions() {
        return oppFieldMinions;
    }

    public ArrayList<JButton> getCurHand() {
        return curHand;
    }

    public void updateCurHeroHP(int currentHP) {
        curHeroHP.setText("" + currentHP);
    }

    public void updateOppHeroHP(int currentHP) {
        oppHeroHP.setText("" + currentHP);
    }

    public void updateOppDeckNum(int size) {
        oppDeckNum.setText("Remaining\ncards\nin the deck: " + size);
    }

    public void updateOppHandNum(int size) {
        oppHandNum.setText("Remaining\ncards\nin the hand: " + size);
    }

    public void updateOppManaCrystalsNum(int currentManaCrystals, int totalManaCrystals) {
        oppManaCrystalsNum.setText("Remaining\nMana Crystals: " + currentManaCrystals + "/" +
                totalManaCrystals);
    }

    public void updateCurManaCrystalsNum(int currentManaCrystals, int total) {
        curManaCrystalsNum.setText("Remaining\nMana Crystals: " + currentManaCrystals + "/" + total);
    }

    public void updateCurDeckNum(int size) {
        curDeckNum.setText("Remaining\ncards\nin deck: " + size);
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

    public void updateOppField(ArrayList<Minion> field) throws IOException {
        if (oppField != null) {
            oppPanel.remove(oppField);
            oppPanel.revalidate();
            oppPanel.repaint();
        }

        oppField = new JPanel();
        oppField.setLayout(new GridLayout(1, 7));
        oppField.setOpaque(false);

        oppFieldMinions = new ArrayList<>();
        oppFieldHPs = new ArrayList<>();
        oppFieldAttacks = new ArrayList<>();
        oppFieldManaCosts = new ArrayList<>();

        for (int i = 0; i < field.size(); i++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File("Minions/" + field.get(i).getName() + ".png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 170 * getHeight() / 864, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            oppField.add(b);
            oppFieldMinions.add(b);
        }
        Dimension size;
        size = oppField.getPreferredSize();
        oppPanel.add(oppField);
        Insets insets = oppPanel.getInsets();
        oppField.setBounds(insets.left + (4 * getWidth() / 23), insets.top + (115 * getHeight() / 412), size.width,
                size.height);
        setOppFieldAttributes(field);
        setOppFieldAttributesLocations();
    }

    public void setOppFieldAttributes(ArrayList<Minion> field) {
        for (int i = 0; i < field.size(); i++) {
            oppFieldHPs.add(new JTextArea(field.get(i).getCurrentHP() + ""));
            oppFieldHPs.get(i).setFont(font);
            oppFieldHPs.get(i).setEditable(false);
            oppFieldHPs.get(i).setOpaque(false);
            oppFieldHPs.get(i).setForeground(Color.WHITE);

            oppFieldAttacks.add(new JTextArea(field.get(i).getAttack() + ""));
            oppFieldAttacks.get(i).setFont(font);
            oppFieldAttacks.get(i).setEditable(false);
            oppFieldAttacks.get(i).setOpaque(false);
            oppFieldAttacks.get(i).setForeground(Color.WHITE);

            oppFieldManaCosts.add(new JTextArea(field.get(i).getManaCost() + ""));
            oppFieldManaCosts.get(i).setFont(font);
            oppFieldManaCosts.get(i).setEditable(false);
            oppFieldManaCosts.get(i).setOpaque(false);
            oppFieldManaCosts.get(i).setForeground(Color.WHITE);
        }

    }

    public void setCurFieldAttributes(ArrayList<Minion> field) {
        for (int i = 0; i < field.size(); i++) {
            curFieldHPs.add(new JTextArea(field.get(i).getCurrentHP() + ""));
            curFieldHPs.get(i).setFont(font);
            curFieldHPs.get(i).setEditable(false);
            curFieldHPs.get(i).setOpaque(false);
            curFieldHPs.get(i).setForeground(Color.WHITE);

            curFieldAttacks.add(new JTextArea(field.get(i).getAttack() + ""));
            curFieldAttacks.get(i).setFont(font);
            curFieldAttacks.get(i).setEditable(false);
            curFieldAttacks.get(i).setOpaque(false);
            curFieldAttacks.get(i).setForeground(Color.WHITE);

            curFieldManaCosts.add(new JTextArea(field.get(i).getManaCost() + ""));
            curFieldManaCosts.get(i).setFont(font);
            curFieldManaCosts.get(i).setEditable(false);
            curFieldManaCosts.get(i).setOpaque(false);
            curFieldManaCosts.get(i).setForeground(Color.WHITE);
        }
    }

    public void updateCurField(ArrayList<Minion> field) throws IOException {
        if (curField != null) {
            curentPanel.remove(curField);
            curentPanel.revalidate();
            curentPanel.repaint();
        }

        curField = new JPanel();
        curentPanel.add(curField);
        curField.setLayout(new GridLayout(1, 7));
        curField.setOpaque(false);
        curFieldMinions = new ArrayList<>();
        curFieldHPs = new ArrayList<>();
        curFieldAttacks = new ArrayList<>();
        curFieldManaCosts = new ArrayList<>();

        for (int i = 0; i < field.size(); i++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File("Minions/" + field.get(i).getName() + ".png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 85 * getHeight() / 432, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            curField.add(b);
            curFieldMinions.add(b);
        }
        Dimension size;
        Insets insets = curentPanel.getInsets();
        size = curField.getPreferredSize();
        curField.setBounds(insets.left + (4 * getWidth() / 23), insets.top + (5 * getHeight() / 216), size.width,
                size.height);
        setCurFieldAttributes(field);
        setCurFieldAttributesLocations();
    }

    public void setCurHandAttributes(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {

            curHandHPs.add(new JTextArea(hand.get(i) instanceof Minion ? ((Minion) hand.get(i)).getCurrentHP() + "" : ""));
            curHandHPs.get(i).setFont(font);
            curHandHPs.get(i).setEditable(false);
            curHandHPs.get(i).setOpaque(false);
            curHandHPs.get(i).setForeground(Color.WHITE);

            curHandAttacks.add(new JTextArea(hand.get(i) instanceof Minion ?
                    ((Minion) hand.get(i)).getAttack() + "" : ""));
            curHandAttacks.get(i).setFont(font);
            curHandAttacks.get(i).setEditable(false);
            curHandAttacks.get(i).setOpaque(false);
            curHandAttacks.get(i).setForeground(Color.WHITE);

            curHandManaCosts.add(new JTextArea(hand.get(i).getManaCost() + ""));
            curHandManaCosts.get(i).setFont(font);
            curHandManaCosts.get(i).setEditable(false);
            curHandManaCosts.get(i).setOpaque(false);
            curHandManaCosts.get(i).setForeground(Color.WHITE);
        }

    }


    public void updateHand(ArrayList<Card> cards) throws IOException {
        if (curHandRight != null && curHandLeft != null) {
            curentPanel.remove(curHandLeft);
            curentPanel.remove(curHandRight);
            curentPanel.revalidate();
            curentPanel.repaint();
        }

        curHandLeft = new JPanel();
        curHandRight = new JPanel();

        curHandHPs = new ArrayList<>();
        curHandAttacks = new ArrayList<>();
        curHandManaCosts = new ArrayList<>();

        curentPanel.add(curHandLeft);
        curHandLeft.setOpaque(false);
        curHandLeft.setLayout(new GridLayout(1, 5));
        curHandLeftMinions = new ArrayList<>();
        int j = 0;
        curHand = new ArrayList<>();
        for (int i = 0; i < 5 && j < cards.size(); i++, j++) {
            JButton b = new JButton();
            String p;
            if (cards.get(j) instanceof ShadowWordDeath) {
                p = "Spells/Shadow Word Death.png";
            } else {
                if (cards.get(j) instanceof Minion) {
                    p = "Minions/" + cards.get(j).getName() + ".png";
                } else {
                    p = "Spells/" + cards.get(j).getName() + ".png";
                }
            }
            BufferedImage image = ImageIO.read(new File(p));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 170 * getHeight() / 864, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            curHandLeft.add(b);
            curHandLeftMinions.add(b);
            curHand.add(b);
        }
        Dimension size;
        Insets insets = curentPanel.getInsets();
        size = curHandLeft.getPreferredSize();
        curHandLeft.setBounds(insets.left, insets.top + 230 * getHeight() / 864, size.width - 100 * getWidth() / 1536, size.height);

        curentPanel.add(curHandRight);
        curHandRight.setLayout(new GridLayout(1, 5));
        curHandRight.setOpaque(false);
        curHandRightMinions = new ArrayList<>();
        for (int i = 0; i < 5 && j < cards.size(); i++, j++) {
            JButton b = new JButton();

            String p;
            if (cards.get(j) instanceof ShadowWordDeath) {
                p = "Spells/Shadow Word Death.png";
            } else {
                if (cards.get(j) instanceof Minion) {
                    p = "Minions/" + cards.get(j).getName() + ".png";
                } else {
                    p = "Spells/" + cards.get(j).getName() + ".png";
                }
            }
            BufferedImage image = ImageIO.read(new File(p));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 170 * getHeight() / 864, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            curHandRight.add(b);
            curHandRightMinions.add(b);
            curHand.add(b);
        }
        size = curHandRight.getPreferredSize();
        curHandRight.setBounds(insets.left + 850 * getWidth() / 1536, insets.top + 230 * getHeight() / 864, size.width - 100 * getWidth() / 1536, size.height);
        setCurHandAttributes(cards);
        setCurHandAttributesLocations();
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

    private void setCurHandAttributesLocations() {
        int j = 0;
        for (int i = 0; i < curHandLeftMinions.size(); j++, i++) {
            curHandLeftMinions.get(i).setLayout(null);
            curHandLeftMinions.get(i).add(curHandHPs.get(j));
            curHandLeftMinions.get(i).add(curHandAttacks.get(j));
            curHandLeftMinions.get(i).add(curHandManaCosts.get(j));
            Insets insets = curHandLeftMinions.get(i).getInsets();

            Dimension size = curHandHPs.get(j).getPreferredSize();
            if (!curHandHPs.get(j).getText().equals("")) {
                curHandHPs.get(j).setBounds(insets.left + (Integer.parseInt(curHandHPs.get(j).getText()) > 9 ? 77 : 81) * getWidth() / 1440,
                        insets.top + (133 * getHeight() / 810), size.width, size.height);
            }
            size = curHandAttacks.get(j).getPreferredSize();

            if (!curHandAttacks.get(j).getText().equals("")) {
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
                if (!curHandHPs.get(j).getText().equals("")) {
                    curHandHPs.get(j).setBounds(insets.left + (Integer.parseInt(curHandHPs.get(j).getText()) > 9 ? 77 : 81) * getWidth() / 1440,
                            insets.top + (133 * getHeight() / 810), size.width, size.height);
                }

                size = curHandAttacks.get(j).getPreferredSize();

                if (!curHandAttacks.get(j).getText().equals("")) {
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
        g.setGamePlay(new Game(new Hunter(), new Paladin()));

    }

}
