import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
public class Player extends JPanel implements Runnable{
    private int xPos;
    private int yPos;
    private Image img;
    private int dir; //direction
    
    /*LEGEND
    dir = direction
    if dir = 1, papunta sa right
    if 2, papunta sa left
    if 3, papunta sa up
    if 4, papunta sa down
    */
    public Player(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.dir=4;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try{
            img = ImageIO.read(new File("BobDown0.png"));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        Graphics2D g2d = (Graphics2D)g;
        g.drawImage(img, xPos*80, yPos*80, 80,80, null);
    }
    public void run(){
        while(true){
            try{
                
            }
            catch(Exception e){}
        }
    }
}