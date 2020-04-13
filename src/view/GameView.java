package view;


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
        setSize(getMaximumSize());
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
        label1.setFont(new Font("Algerian", Font.PLAIN, 25));
        JLabel label2 = new JLabel("   Choose Second Hero");
        label2.setFont(new Font("Algerian", Font.PLAIN, 23));

        player1.add(label1);
        player2.add(label2);

        String[] arr = {"Mage", "Hunter", "Paladin", "Priest", "Warlock"};

        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(arr[i]);
            button.setFont(new Font("Algerian", Font.PLAIN, 30));
            player1.add(button);
        }


        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(arr[i]);
            button.setFont(new Font("Algerian", Font.PLAIN, 30));
            player2.add(button);
        }

        this.add(player1);
        JButton startGame = new JButton("START GAME");
        startGame.setFont(new Font("Algerian", Font.PLAIN, 24));
        startGame.setSize(new Dimension(getWidth() / 4, getHeight() / 6));
        JPanel startPanel = new JPanel(new GridLayout(1, 6));
        startPanel.add(new JLabel());
        startPanel.add(new JLabel());
        startPanel.add(new JLabel());
        startPanel.add(startGame);
        startPanel.add(new JPanel());
        startPanel.add(new JPanel());
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

        revalidate();
        repaint();
    }


    public static void main(String[] args) throws IOException {
        new GameView().setGamePlay();
    }

}
