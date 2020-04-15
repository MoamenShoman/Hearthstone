package view;


import org.junit.internal.builders.JUnit3Builder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView extends JFrame {


    public GameView(){
        super();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int width = d.width;
        int height = width * 9 / 16;
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setInitial() {

        getContentPane().removeAll();
        revalidate();
        repaint();

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

        player1.add(label1);
        player2.add(label2);

        String[] arr = {"Mage", "Hunter", "Paladin", "Priest", "Warlock"};

        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(arr[i]);
            player1.add(button);
        }


        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(arr[i]);
            player2.add(button);
        }

        this.add(player1);
        JButton startGame = new JButton("START GAME");
        startGame.setSize(new Dimension(getWidth() / 4, getHeight() / 6));
        JPanel startPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 100;
        gbc.ipady = 70;
        startPanel.add(startGame, gbc);
        startPanel.setSize(new Dimension(getWidth() / 4, startPanel.getHeight()));
        this.add(startPanel);
        this.add(player2);

        revalidate();
        repaint();
    }

    public void setGamePlay() throws IOException, FontFormatException {
        getContentPane().removeAll();
        revalidate();
        repaint();

        BufferedImage backgroundImage = ImageIO.read(new File("background.jpg"));
        setContentPane(new Background(backgroundImage));
        setTitle("Hearthstone");

        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/NewRocker-Regular.ttf"));
        font = font.deriveFont(Font.PLAIN, 24);

        setLayout(new GridLayout(2, 1));
        JPanel currentPanel = new JPanel();
        JPanel oppPanel = new JPanel();
        currentPanel.setOpaque(false);
        oppPanel.setOpaque(false);

        oppPanel.setLayout(null);
        Insets insets = oppPanel.getInsets();

        JLabel oppDeck = new JLabel();
        Image oppDeckImage = ImageIO.read(new File("FlippedCard.png"));
        ImageIcon oppDeckIcon = new ImageIcon(oppDeckImage.getScaledInstance((11 * getWidth() / 72), -160, Image.SCALE_DEFAULT));
        oppDeck.setIcon(oppDeckIcon);
        Dimension size = oppDeck.getPreferredSize();
        oppPanel.add(oppDeck);
        oppDeck.setBounds(insets.left, insets.top + (25 * getHeight() / 412), size.width, size.height);

        JTextArea oppDeckNum = new JTextArea("Remaining\ncards\nin the deck:");
        oppDeckNum.setEditable(false);
        oppDeckNum.setOpaque(false);
        oppDeckNum.setFont(font);
        size = oppDeckNum.getPreferredSize();
        oppPanel.add(oppDeckNum);
        oppDeckNum.setBounds(insets.left + (25 * getWidth() / 144), insets.top + (35 * getHeight() / 412), size.width, size.height);

        JLabel oppHero = new JLabel();
        Image oppImage = ImageIO.read(new File("Heroes/Anduin_Wrynn.png"));
        ImageIcon oppIcon = new ImageIcon(oppImage.getScaledInstance(-200, (115 * getHeight() / 412), Image.SCALE_DEFAULT));
        oppHero.setIcon(oppIcon);
        size = oppHero.getPreferredSize();
        oppPanel.add(oppHero);
        oppHero.setBounds(insets.left + (4 * getWidth() / 9), insets.top + (5 * getHeight() / 412), size.width, size.height);

        JLabel oppHand = new JLabel();
        Image oppHandImage = ImageIO.read(new File("handBack.png"));
        ImageIcon oppHandIcon = new ImageIcon(oppHandImage.getScaledInstance(-250, (25 * getHeight() / 103), Image.SCALE_DEFAULT));
        oppHand.setIcon(oppHandIcon);
        size = oppHand.getPreferredSize();
        oppPanel.add(oppHand);
        oppHand.setBounds(insets.left + (95 * getWidth() / 144), insets.top, size.width, size.height);

        JTextArea oppHandNum = new JTextArea("Remaining\ncards\nin the hand:");
        oppHandNum.setEditable(false);
        oppHandNum.setOpaque(false);
        oppHandNum.setFont(font);
        size = oppHandNum.getPreferredSize();
        oppPanel.add(oppHandNum);
        oppHandNum.setBounds(insets.left + (115 * getWidth() / 144), insets.top + (5 * getHeight() / 103), size.width, size.height);

        JLabel oppManaCrystal = new JLabel();
        Image oppManaCrystalImage = ImageIO.read(new File("manaCrystal.png"));
        ImageIcon oppManaCrystalsIcon = new ImageIcon(oppManaCrystalImage.getScaledInstance(-100, (25 * getHeight() / 206), Image.SCALE_DEFAULT));
        oppManaCrystal.setIcon(oppManaCrystalsIcon);
        size = oppManaCrystal.getPreferredSize();
        oppPanel.add(oppManaCrystal);
        oppManaCrystal.setBounds(insets.left + (getWidth() / 72), insets.top + (115 * getHeight() / 412), size.width, size.height);


        JTextArea oppManaCrystalsNum = new JTextArea("Remaining\nmana crystals:");
        oppManaCrystalsNum.setEditable(false);
        oppManaCrystalsNum.setOpaque(false);
        oppManaCrystalsNum.setFont(font);
        oppPanel.add(oppManaCrystalsNum);
        size = oppManaCrystalsNum.getPreferredSize();
        oppManaCrystalsNum.setBounds(insets.left + (13 * getWidth() / 144), insets.top + (255 * getHeight() / 824), size.width, size.height);

        JPanel oppField = new JPanel();
        oppField.setLayout(new GridLayout(1, 7));
        oppField.setOpaque(false);
        for (int i = 0; i < 7; i++) {
            JButton b = new JButton();
            BufferedImage image = ImageIO.read(new File("Minions/download.png"));
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(-1, 160, Image.SCALE_SMOOTH));
            b.setIcon(imageIcon);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            oppField.add(b);
        }
        size = oppField.getPreferredSize();
        oppPanel.add(oppField);
        oppField.setBounds(insets.left + (5 * getWidth() / 16), insets.top + (115 * getHeight() / 412), size.width,
                size.height);




        add(oppPanel);
        add(currentPanel);

        revalidate();
        repaint();
        setResizable(false);
        setVisible(true);
    }


    public static void main(String[] args) throws IOException, FontFormatException {
        new GameView().setGamePlay();
    }

}
