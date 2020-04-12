package view;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameView extends JFrame  {


    public GameView(){

        super();
        setSize(900,700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void setInitial(){
        getContentPane().removeAll();
        revalidate();
        repaint();

        this.setLayout(new FlowLayout());

        JPanel player1 = new JPanel();
        JPanel player2 = new JPanel();
        JPanel start = new JPanel();

        player1.setLayout(new GridLayout(1,6,10,0));
        player2.setLayout(new GridLayout(1 ,6,10,0));
        start.setLayout(new BorderLayout());



        player1.setPreferredSize(new Dimension( getWidth(),(int)(2*getHeight()/5)));
        player2.setPreferredSize(new Dimension( getWidth(),(int)(2*getHeight()/5)));
        start.setPreferredSize(new Dimension(getWidth() , (int)getHeight()/5));


        JLabel label1 = new JLabel("   Choose First Hero");
        JLabel label2 = new JLabel("   Choose Second Hero");

        player1.add(label1);
        player2.add(label2);





        String [] arr = {"Mage" , "Hunter" , "Paladin" , "Priest" , "Warlock"};

        for(int i = 0 ; i < 5 ; i++)
            player1.add(new JButton(arr[i]));

        JButton startGame = new JButton("START GAME");
        start.setSize(new Dimension(getWidth()/4, getHeight()/5));
        start.add(startGame , BorderLayout.CENTER);


        for(int i = 0 ; i < 5 ; i++)
            player2.add(new JButton(arr[i]));

        this.add(player1);
        this.add(start);
        this.add(player2);

        revalidate();
        repaint();
    }


    public static void main(String[] args) {
        new GameView().setInitial();
    }

}
