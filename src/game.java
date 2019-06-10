import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class game extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0; 
	private int totalBricks = 21;
	private Timer timer;
	private int delay = 8;
	private int playerX = 310;
	private int posX = 120;
	private int posY = 350;
	private int dx = -1;
	private int dy = -2;
	private mapgen map;
	private soundfx bounce;
	private soundfx win;
	private soundfx lose;
	
	public game() {
		//Creates map and begins timer for game
		map = new mapgen(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		try {
			bounce=new soundfx("bounce.wav");
			win = new soundfx("win.wav");
			lose = new soundfx("lose.wav");
			}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();
		}
		catch (IOException e) {e.printStackTrace();
		}
		catch (LineUnavailableException e) {e.printStackTrace();
		}
		
	 }
	 public void paint(Graphics g) {
	  //Sets background
	  g.setColor(Color.black);
	  g.fillRect(1,1, 692,592);
	  //Draws to map
	  map.draw((Graphics2D)g);
	  //Borders and score
	  g.setColor(Color.yellow);
	  g.fillRect(0, 0, 3, 592);
	  g.fillRect(0, 0, 692, 3);
	  g.fillRect(691,0, 3, 592);
	  g.setColor(Color.white);
	  g.setFont(new Font("serif", Font.BOLD, 25));
	  g.drawString(""+score, 590, 30);
	  //Paddle/Ball
	  g.setColor(Color.blue);
	  g.fillRect(playerX, 550, 100, 8);
	  g.setColor(Color.red);
	  g.fillOval(posX, posY, 20, 20);
	  //Game logic check
	  if(totalBricks <= 0) {
		  win.start();
		  play = false;
		  dx = 0;
		  dy = 0;
		  g.setColor(Color.BLUE);
		  g.setFont(new Font("arial", Font.BOLD, 30));
		  g.drawString("Good Job, You Won!!", 230, 300);
		  g.setFont(new Font("arial", Font.BOLD, 30));
		  g.drawString("Press Enter to Restart", 230, 350);
	  }
	  if(posY > 570) {
		  lose.start();
		  play = false;
		  dx = 0;
		  dy = 0;
		  g.setColor(Color.green);
		  g.setFont(new Font("serif", Font.BOLD, 30));
		  g.drawString("Game Over!", 230, 300);
		  g.setFont(new Font("serif", Font.BOLD, 30));
		  g.drawString("Press Enter to Restart", 230, 350);
	  }
	  g.dispose();
	 }
	//ActionListener abstract method
	 public void actionPerformed(ActionEvent e) {
		 timer.start();
		 //Collision check to see if ball is hitting paddle
		 if(play) {
			 if(new Rectangle(posX, posY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				 dy = -dy;
				 bounce.start();
				 
				 }
			 //for loop to check for ball-brick collision
			 for(int i = 0; i<map.map.length; i++) {
				 for(int j = 0; j<map.map[0].length; j++) {
					 if(map.map[i][j] > 0) {
						 int brickX = j * map.brickw + 80;
						 int brickY = i * map.brickh + 50;
						 int brickWidth = map.brickw;
						 int brickHeight = map.brickh; 
						 Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						 Rectangle ballRect = new Rectangle(posX, posY, 20, 20);
						 Rectangle brickRect = rect;
						 if(ballRect.intersects(brickRect)) {
							 bounce.start();
							 map.brickval(0, i, j);
							 totalBricks--;
							 score += 5;
							 if(posX + 19 <= brickRect.x || posX + 1 >= brickRect.x + brickRect.width) {
								 dx = -dx;
	       
							 } else {
								 dy = -dy;
							 }
						}
					 }
				 }
			 }
	  posX += dx;
	  posY += dy;
	  if(posX < 0) {
		  dx = -dx;
	  }
	  if(posY < 0) {
		  dy = -dy;
	  }
	  if(posX > 670) {
		  dx = -dx;
	  }
	  }
		 repaint();
	 }
	 public void keyTyped(KeyEvent e) {}
	 public void keyReleased(KeyEvent e) {}
	 public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode() == KeyEvent.VK_RIGHT) {  
			 if(playerX >= 600) {
				 playerX = 600;
				 } else {
					 moveRight();
					 }
			 }
		 if(e.getKeyCode() == KeyEvent.VK_LEFT) {  
			 if(playerX < 10) {
				 playerX = 10;
				 } else {
					 moveLeft();
					 }
			 }
		 if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			 if(!play) {
				 play = true;
				 posX = 120;
				 posY = 350;
				 dx = -1;
				 dy = -2;
				 playerX = 310;
				 score = 0;
				 totalBricks = 21;
				 map = new mapgen(3, 7);repaint();
				 }
			 }
		 }
	 public void moveRight() {
		 play = true;
		 playerX+=20;
		 }
	 public void moveLeft() {
		 play = true;
		 playerX-=20;
		 }
	 }