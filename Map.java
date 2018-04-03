import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
public class Map extends JPanel implements Runnable{
    static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int UP = 4;

    private Image wallImg;
    private Image boxImg;
    private Image xImg;
    private Image emptyImg;
    private Image storageImg;
    private Image bobImg;
    private Image storedBoxImg;
    private int storageLocCount=0;
    private int storedCount=0;
    String[][] map;
    String tokens;
    

    private int YPos, XPos;
    public Map(){
        createMap();   
        printMap();
        getStorageLocCount(); //get the number of storage locations

    }
    public void createMap(){
        try {
			BufferedReader reader = new BufferedReader(new FileReader("puzzle.in"));
			String line;
            String[] tokens;
            map = new String[10][10];
            int i=0;
            int j;
			while((line=reader.readLine()) != null) {
				System.out.println(line);
                tokens = line.split(" ");
                for(j=0;j<10;j++){
                    map[i][j]=tokens[j];
                }
                i++;
			}
            reader.close();
        } catch(FileNotFoundException e) {
			System.out.println("File OOP.txt not found");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public void printMap(){
        int i,j;
        System.out.println("MAP!!!");
        for(i=0;i<10;i++){
            for(j=0;j<10;j++){
                
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void getStorageLocCount(){
        int i, j;
        for(i=0;i<10;i++){
            for(j=0;j<10;j++){
               if(map[i][j].equals("s") || map[i][j].equals("K") || map[i][j].equals("B")){
                   this.storageLocCount++;     
               }
            }
        }
    }

    public int YPosGetter(){
        return this.YPos;
    }
    public int XPosGetter(){
        return this.XPos;
    }

    public void movement(int dir){
       
        if(dir == this.RIGHT && this.XPos < 9){ //if pa-right yung movement and di pa sagad sa edge
            switch(map[this.YPos][this.XPos+1]){ //check right side
                case "w": //wall
                    break; 
                case "b": //box
                    if(this.XPos < 8){ //check boundary 
                        if(map[this.YPos][this.XPos+2].equals("e") || map[this.YPos][this.XPos+2].equals("s")){ //if empty yung kasunod ng box                     
                            map[this.YPos][this.XPos+1] = "k";
                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                        
                            else map[this.YPos][this.XPos] = "e";
                            
                            this.XPos +=1; //update x position
                    
                            if(map[this.YPos][this.XPos+1].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos][this.XPos+1]="B";
                                this.storedCount++;
                            }
                            else if(map[this.YPos][this.XPos+1].equals("e")){
                                map[this.YPos][this.XPos+1]="b";
                            } 
                        }
                    }
                    break;
                case "B":
                    if(this.XPos < 8 ){
                        if(map[this.YPos][this.XPos+2].equals("e") || map[this.YPos][this.XPos+2].equals("s")){
                            map[this.YPos][this.XPos+1] = "K"; //capital K bcos u are moving to a storage location

                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s"; //if player came from a storage loc
                            else map[this.YPos][this.XPos] = "e";
                            this.XPos +=1;

                            if(map[this.YPos][this.XPos+1].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos][this.XPos+1] = "B";
                                this.storedCount++;
                            }
                            else if(map[this.YPos][this.XPos+1].equals("e")){
                                map[this.YPos][this.XPos+1] = "b";
                                this.storedCount--;
                            }
                        }
                    }
                    break;
                case "s":
                    map[this.YPos][this.XPos+1] = "K";

                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    
                    this.XPos +=1;
                    
                    break;
                case "e":
                  
                    map[this.YPos][this.XPos+1] = "k";

                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    this.XPos +=1; //update x position
                    break;
            }    
        }
        if(dir == this.LEFT && this.XPos > 1){ //if pa-right yung movement and di pa sagad sa edge
            switch(map[this.YPos][this.XPos-1]){ //check left side
                case "w": //wall
                    break; 
                case "b": //box
                    if(this.XPos > 2){ //check boundary 
                        if(map[this.YPos][this.XPos-2].equals("e") || map[this.YPos][this.XPos-2].equals("s")){ //if empty yung kasunod ng box                     
                            map[this.YPos][this.XPos-1] = "k";
                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                        
                            else map[this.YPos][this.XPos] = "e";
                            
                            this.XPos -=1; //update x position
                    
                            if(map[this.YPos][this.XPos-1].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos][this.XPos-1]="B";
                                 this.storedCount++;
                            }
                            else if(map[this.YPos][this.XPos-1].equals("e")){
                                map[this.YPos][this.XPos-1]="b";
                            } 
                        }
                    }
                    break;
                case "B":
                    if(this.XPos > 2 ){
                        if(map[this.YPos][this.XPos-2].equals("e") || map[this.YPos][this.XPos-2].equals("s")){
                            map[this.YPos][this.XPos-1] = "K"; //capital K bcos u are moving to a storage location

                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s"; //if player came from a storage loc
                            else map[this.YPos][this.XPos] = "e";
                            this.XPos -=1;

                            if(map[this.YPos][this.XPos-1].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos][this.XPos-1] = "B";
                                this.storedCount++;
                            }
                            else if(map[this.YPos][this.XPos-1].equals("e")){
                                map[this.YPos][this.XPos-1] = "b";
                                this.storedCount--;
                            }
                        }
                    }
                    break;
                case "s":
                    map[this.YPos][this.XPos-1] = "K";
                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    
                    this.XPos -=1;
                    break;
                case "e":
                    System.out.println("Entered here");
                    map[this.YPos][this.XPos-1] = "k";
                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    
                    this.XPos -=1; //update x position
                    break;
            }    
        }
        if(dir == this.UP && this.YPos > 1){ //if pa-right yung movement and di pa sagad sa edge
            switch(map[this.YPos-1][this.XPos]){ //check left side
                case "w": //wall
                    break; 
                case "b": //box
                    if(this.YPos > 2){ //check boundary 
                        if(map[this.YPos-2][this.XPos].equals("e") || map[this.YPos-2][this.XPos].equals("s")){ //if empty yung kasunod ng box                     
                            map[this.YPos-1][this.XPos] = "k";
                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                        
                            else map[this.YPos][this.XPos] = "e";
                            
                            this.YPos -=1; //update x position
                    
                            if(map[this.YPos-1][this.XPos].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos-1][this.XPos]="B";
                                 this.storedCount++;
                            }
                            else if(map[this.YPos-1][this.XPos].equals("e")){
                                map[this.YPos-1][this.XPos]="b";
                            } 
                        }
                    }
                    break;
                case "B":
                    if(this.YPos > 2 ){
                        if(map[this.YPos-2][this.XPos].equals("e") || map[this.YPos-2][this.XPos].equals("s")){
                            map[this.YPos-1][this.XPos] = "K"; //capital K bcos u are moving to a storage location

                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s"; //if player came from a storage loc
                            else map[this.YPos][this.XPos] = "e";
                            this.YPos -=1;

                            if(map[this.YPos-1][this.XPos].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos-1][this.XPos] = "B";
                                 this.storedCount++;
                            }
                            else if(map[this.YPos-1][this.XPos].equals("e")){
                                map[this.YPos-1][this.XPos] = "b";
                                 this.storedCount--;
                            }
                        }
                    }
                    break;
                case "s":
                    map[this.YPos-1][this.XPos] = "K";
                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    
                    this.YPos -=1;
                    break;
                case "e":
                    System.out.println("Entered here");
                    map[this.YPos-1][this.XPos] = "k";
                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    this.YPos -=1; //update x position
                    break;
            }    
        }
        if(dir == this.DOWN && this.YPos < 9){ //if pa-right yung movement and di pa sagad sa edge
            switch(map[this.YPos+1][this.XPos]){ //check right side
                case "w": //wall
                    break; 
                case "b": //box
                    if(this.YPos < 8){ //check boundary 
                        if(map[this.YPos+2][this.XPos].equals("e") || map[this.YPos+2][this.XPos].equals("s")){ //if empty yung kasunod ng box                     
                            map[this.YPos+1][this.XPos] = "k";
                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                        
                            else map[this.YPos][this.XPos] = "e";
                            
                            this.YPos +=1; //update x position
                    
                            if(map[this.YPos+1][this.XPos].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos+1][this.XPos]="B";
                                this.storedCount++;
                            }
                            else if(map[this.YPos+1][this.XPos].equals("e")){
                                map[this.YPos+1][this.XPos]="b";
                            } 
                        }
                    }
                    break;
                case "B":
                    if(this.YPos < 8 ){
                        if(map[this.YPos+2][this.XPos].equals("e") || map[this.YPos+2][this.XPos].equals("s")){
                            map[this.YPos+1][this.XPos] = "K"; //capital K bcos u are moving to a storage location

                            if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s"; //if player came from a storage loc
                            else map[this.YPos][this.XPos] = "e";
                            this.YPos +=1;

                            if(map[this.YPos+1][this.XPos].equals("s")){ //if the box is being moved to storage loc
                                map[this.YPos+1][this.XPos] = "B";
                                this.storedCount++;
                            }
                            else if(map[this.YPos+1][this.XPos].equals("e")){
                                map[this.YPos+1][this.XPos] = "b";
                                this.storedCount--;
                            }
                        }
                    }
                    break;
                case "s":
                    map[this.YPos+1][this.XPos] = "K";
                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";

                    this.YPos +=1;
                    break;
                case "e":
                    System.out.println("Entered here");
                    map[this.YPos+1][this.XPos] = "k";
                    if(map[this.YPos][this.XPos].equals("K")) map[this.YPos][this.XPos] = "s";
                    else map[this.YPos][this.XPos] = "e";
                    this.YPos +=1; //update x position
                    break;
            }    
        }
        printMap();
    }
    public void paintComponent(Graphics g){
        int i,j;
        super.paintComponent(g);

        try{
            wallImg = ImageIO.read(new File("wall.jpg"));
            emptyImg = ImageIO.read(new File("rsz_empty.png"));
            boxImg = ImageIO.read(new File("rsz_box.png"));
            storageImg = ImageIO.read(new File("rsz_redbox.png"));
            bobImg = ImageIO.read(new File("BobDown0.png"));
            storedBoxImg = ImageIO.read(new File("storedBox.png"));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        Graphics2D g2d = (Graphics2D)g;
        System.out.println("graphics");
        for(i=0;i<10;i++){
            for(j=0;j<10;j++){
                System.out.print(map[i][j]);
                switch(map[i][j]){
                    case "w":
                        g.drawImage(wallImg, j*60,i*60, 60,60, null); //60 x 60 yung size
                        break;
                    case "e":
                        // g.drawImage(emptyImg, i*50,j*50, 100,100, null); //60 x 60 yung size
                        break;
                    case "b":
                        g.drawImage(boxImg, j*60,i*60, 60,60, null); //60 x 60 yung size
                        break;
                    case "B":
                        g.drawImage(storedBoxImg, j*60, i*60, 60,60, null);
                        break;
                    case "s": 
                        g.drawImage(storageImg, j*60,i*60, 60,60, null); //60 x 60 yung size
                        break;
                    case "k":
                        g.drawImage(bobImg, j*60, i*60, 60, 60, null);
                        this.YPos = i;
                        this.XPos = j;
                        break;
                    case "K":
                        g.drawImage(bobImg, j*60, i*60, 60, 60, null);
                        this.YPos = i;
                        this.XPos = j;
                        break;

                }
                System.out.println();
               
            }
        }
    }
    private static boolean goalTest(String[][] map){
        //counts how many boxes have been stored in the correct
        //storage locations
        
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){ 
                if(map[i][j].equals("b")){
                    return false;
                }
            }
        }
        return true;
    }
    public void run(){
        do{
            try{
                this.repaint();
                Thread.sleep(100);
                
            }
            catch(Exception e){}
        }
        while(goalTest(map)!= true);
        try{
            this.repaint();
            Thread.sleep(100);

        }
        catch(Exception e){}
        JOptionPane.showMessageDialog(null, 
                              "You Won!", 
                              "Sokoban", 
                              JOptionPane.INFORMATION_MESSAGE);
        try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("savedState.txt"));
			
            int i,j;
            for(i=0;i<10;i++){
                for(j=0;j<10;j++){
                    writer.write(map[i][j] + " ");
                }
                writer.write("\n");
            }
           
			writer.close();
		} catch(FileNotFoundException e) {
			System.out.println("File OOP.txt not found");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}             
    }
}