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
        ImageIcon oppDeckIcon = new ImageIcon(oppDeckImage.getScaledInstance(220, -160, Image.SCALE_DEFAULT));
        oppDeck.setIcon(oppDeckIcon);
        Dimension size = oppDeck.getPreferredSize();
        oppPanel.add(oppDeck);
        oppDeck.setBounds(insets.left, insets.top + 50, size.width, size.height);

        JTextArea oppDeckNum = new JTextArea("Remaining\ncards\nin the deck:");
        oppDeckNum.setEditable(false);
        oppDeckNum.setOpaque(false);
        oppDeckNum.setFont(font);
        size = oppDeckNum.getPreferredSize();
        oppPanel.add(oppDeckNum);
        oppDeckNum.setBounds(insets.left + 250, insets.top + 70, size.width, size.height);

        JLabel oppHero = new JLabel();
        Image oppImage = ImageIO.read(new File("Heroes/Anduin_Wrynn.png"));
        ImageIcon oppIcon = new ImageIcon(oppImage.getScaledInstance(-200, 230, Image.SCALE_DEFAULT));
        oppHero.setIcon(oppIcon);
        size = oppHero.getPreferredSize();
        oppPanel.add(oppHero);
        oppHero.setBounds(insets.left + 640, insets.top + 10, size.width, size.height);

        JLabel oppHand = new JLabel();
        Image oppHandImage = ImageIO.read(new File("handBack.png"));
        ImageIcon oppHandIcon = new ImageIcon(oppHandImage.getScaledInstance(-250, 200, Image.SCALE_DEFAULT));
        oppHand.setIcon(oppHandIcon);
        size = oppHand.getPreferredSize();
        oppPanel.add(oppHand);
        oppHand.setBounds(insets.left + 950, insets.top, size.width, size.height);

        JTextArea oppHandNum = new JTextArea("Remaining\ncards\nin the hand:");
        oppHandNum.setEditable(false);
        oppHandNum.setOpaque(false);
        oppHandNum.setFont(font);
        size = oppHandNum.getPreferredSize();
        oppPanel.add(oppHandNum);
        oppHandNum.setBounds(insets.left + 1150, insets.top + 40, size.width, size.height);

        JLabel oppManaCrystal = new JLabel();
        Image oppManaCrystalImage = ImageIO.read(new File("manaCrystal.png"));
        ImageIcon oppManaCrystalsIcon = new ImageIcon(oppManaCrystalImage.getScaledInstance(-100, 100, Image.SCALE_DEFAULT));
        oppManaCrystal.setIcon(oppManaCrystalsIcon);
        size = oppManaCrystal.getPreferredSize();
        oppPanel.add(oppManaCrystal);
        oppManaCrystal.setBounds(insets.left + 20, insets.top + 230, size.width, size.height);


        JTextArea oppManaCrystalsNum = new JTextArea("Remaining\nmana crystals:");
        oppManaCrystalsNum.setEditable(false);
        oppManaCrystalsNum.setOpaque(false);
        oppManaCrystalsNum.setFont(font);
        oppPanel.add(oppManaCrystalsNum);
        size = oppManaCrystalsNum.getPreferredSize();
        oppManaCrystalsNum.setBounds(insets.left + 130, insets.top + 255, size.width, size.height);

        JPanel oppField = new JPanel();
        oppField.setLayout(new GridLayout(1, 7));
        oppField.setOpaque(false);
        for (int i = 0; i < 7; i++) {
            JButton b = new JButton("" + i);
            b.setPreferredSize(new Dimension(100, 100));
            oppField.add(b);
        }
        size = oppField.getPreferredSize();
        oppPanel.add(oppField);
        oppField.setBounds(insets.left + 450, insets.top + 230, size.width, size.height + 40);

        add(oppPanel);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        GameView2 gameView2 = new GameView2();
        gameView2.setGamePlay2();
        gameView2.setVisible(true);
        System.out.println(gameView2.getWidth());
    }
}
