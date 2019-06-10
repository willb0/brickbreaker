import java.awt.*;
import java.util.Random;

public class mapgen {
	public int map[][];
	public int brickw;
	public int brickh;
	//mapgen generates the brick map 
    public mapgen(int row, int col) {
    	map = new int [row][col];
    	for(int x = 0; x < map.length; x++) {
    		for(int y=0; y< map[0].length; y++) {
    			map[x][y] = 1;
    		}
    	}
    	brickw = 540/col;
    	brickh = 150/row;
    }
    //draw draws to the JPanel graphics
    public void draw(Graphics2D g) {
    	for(int x = 0; x < map.length; x++) {
    		for(int y=0; y< map[0].length; y++) {
    			//Creates a random color generator to make
    			//the bricks change colors
    			Random rand= new Random();
    	    	float r = rand.nextFloat();
    	    	float gr = rand.nextFloat();
    	    	float b = rand.nextFloat();
    	    	Color randomColor = new Color(r, gr, b);
    			if(map[x][y] > 0) {
    				g.setColor(randomColor);
    				g.fillRect(y * brickw + 80, x * brickh + 50, brickw, brickh);
    				g.setStroke(new BasicStroke(3));
    				g.setColor(Color.black);
    				g.drawRect(y * brickw + 80, x * brickh + 50, brickw, brickh);
    				}
    			}
    		}
    	}
       public void brickval(int value, int row, int col) {
    	   map[row][col] = value;
    	   }
}