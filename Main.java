import java.awt.*;
import javax.swing.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

class Main{
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    static final int UP = 4;
    
    public static void main(String[] args){
        
        //Create a window
        MainFrame frame = new MainFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(700,700));

        final Map map = new Map();
        frame.setContentPane(map);
        Thread thread = new Thread(map);
        thread.start();
        
        frame.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_RIGHT) map.movement(Main.RIGHT);			// move to the right
                if(key == KeyEvent.VK_DOWN) map.movement(Main.DOWN);			//move down
                if(key == KeyEvent.VK_LEFT) map.movement(Main.LEFT);			//move to the left
                if(key == KeyEvent.VK_UP) map.movement(Main.UP);				//move up
            }
            public void keyReleased(KeyEvent e){}
            public void keyTyped(KeyEvent e){}
        });
        
        // frame.setLocationRelativeTo(null); //window appears in the center of the screen
        frame.pack(); //looks at what the JFrame contains and automatically sets the size of the window.
        frame.setVisible(true);


    }
}