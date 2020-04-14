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


    public GameView() {

        super();
        setSize(1000, 800);
        setVisible(true);
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

    public void setGamePlay() throws IOException {
        getContentPane().removeAll();
        revalidate();
        repaint();

        BufferedImage backgroundImage = ImageIO.read(new File("background.jpg"));
        setContentPane(new Background(backgroundImage));
        setTitle("Hearthstone");

        setLayout(new GridLayout(2, 1));
        JPanel currentPanel = new JPanel();
        JPanel oppPanel = new JPanel();

        oppPanel.setLayout(new GridBagLayout());
        JLabel oppHero = new JLabel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(-200, 0, 0, 0);
        Image oppImage = ImageIO.read(new File("Heroes/Anduin_Wrynn.png"));
        ImageIcon oppIcon = new ImageIcon(oppImage.getScaledInstance(-200, 230, Image.SCALE_SMOOTH));
        gbc.gridx = 2;
        gbc.gridy = 0;
        oppPanel.setOpaque(false);
        oppHero.setIcon(oppIcon);
        oppPanel.add(oppHero, gbc);
        add(oppPanel);


        Image oppHandImage = ImageIO.read(new File("handBack.png"));
        ImageIcon oppHandIcon = new ImageIcon(oppHandImage.getScaledInstance(-250, 200, Image.SCALE_SMOOTH));
        JLabel opphand = new JLabel();
        gbc.insets = new Insets(-200, 0, 0, -500);
        opphand.setIcon(oppHandIcon);
        gbc.gridx = 3;
        gbc.gridy = 0;
        oppPanel.add(opphand, gbc);

        JTextArea oppHandNum = new JTextArea("Remaining\ncards\nin the hand :");
        oppHandNum.setEditable(false);
        oppHandNum.setOpaque(false);
        oppHandNum.setFont(new Font("Algerian", Font.PLAIN, 20));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(-200, 0, 0, -800);
        gbc.gridx = 4;
        gbc.gridy = 0;
        oppPanel.add(oppHandNum, gbc);

        JLabel oppManaCrystal = new JLabel();
        Image oppManaCrystalImage = ImageIO.read(new File("manaCrystal.png"));
        ImageIcon oppManaCrystalsIcon = new ImageIcon(oppManaCrystalImage.getScaledInstance(-100, 100, Image.SCALE_DEFAULT));
        gbc.insets = new Insets(-100, -1150, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        oppManaCrystal.setIcon(oppManaCrystalsIcon);
        oppPanel.add(oppManaCrystal, gbc);

        JLabel oppDeck = new JLabel();
        Image oppDeckImage = ImageIO.read(new File("FlippedCard.png"));
        ImageIcon oppDeckIcon = new ImageIcon(oppDeckImage.getScaledInstance(220, -160, Image.SCALE_DEFAULT));
        gbc.insets = new Insets(0, -680, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.ipadx = 80;
        gbc.ipady = 200;
        gbc.gridx = 0;
        gbc.gridy = 0;
        oppDeck.setIcon(oppDeckIcon);
        oppPanel.add(oppDeck, gbc);
        gbc.anchor = GridBagConstraints.CENTER;

        JTextArea oppDeckNum = new JTextArea("Remaining\ncards\n in the deck :");
        oppDeckNum.setEditable(false);
        oppDeckNum.setOpaque(false);
        oppDeckNum.setFont(new Font("Algerian", Font.PLAIN, 20));
        gbc.insets = new Insets(0, -550, -200, 0);
        gbc.gridx = 1;
        gbc.gridy = 0;
        oppPanel.add(oppDeckNum, gbc);


        JTextArea manaCrystalsNum = new JTextArea("Remaining\nmana crystals :");
        manaCrystalsNum.setEditable(false);
        manaCrystalsNum.setOpaque(false);
        manaCrystalsNum.setFont(new Font("Fonts/Algerian", Font.PLAIN, 20));
        gbc.insets.left -= 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        oppPanel.add(manaCrystalsNum, gbc);

        JPanel oppField = new JPanel();
        oppField.setLayout(new GridLayout(1, 7));
        oppField.setOpaque(false);
        for (int i = 0; i < 7; i++) {
            JButton b = new JButton("" + i);
            b.setPreferredSize(new Dimension(100, 100));
            oppField.add(b);
        }
        gbc = new GridBagConstraints();
        gbc.insets.top -= 270;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipady = 150;
        gbc.ipadx = 600;
        gbc.insets.right -= 600;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        oppPanel.add(oppField, gbc);


        revalidate();
        repaint();
    }


    public static void main(String[] args) throws IOException {
        new GameView().setGamePlay();
    }

}
