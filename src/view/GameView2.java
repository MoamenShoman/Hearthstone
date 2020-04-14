package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView2 extends JFrame {

    public GameView2(){
        super();
        setSize(getMaximumSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setGamePlay2() throws IOException, FontFormatException {
        getContentPane().removeAll();
        revalidate();
        repaint();
        setVisible(true);

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
            JButton b = new JButton("" + i);
            oppField.add(b);
        }
        size = oppField.getPreferredSize();
        oppPanel.add(oppField);
        oppField.setBounds(insets.left + (5 * getWidth() / 16), insets.top + (115 * getHeight() / 412), size.width + (5 * getWidth() / 48),
                size.height + (12 * getHeight() / 90));

        add(oppPanel);
        add(currentPanel);

        revalidate();
        repaint();
        setResizable(false);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        GameView2 gameView2 = new GameView2();
        gameView2.setGamePlay2();
        System.out.println(gameView2.getWidth());
        System.out.println(gameView2.getHeight());
    }
}
