import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import  javax.swing.Timer;
public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subFont;
	Color startBackgroundColor = new Color(219, 223, 255);
	Color startFontColor = new Color(107, 115, 181);
	Color endFontColor = new Color(219, 182, 112);
	Color endBackgroundColor = new Color(234, 235, 190);
	Timer frameDraw;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Rocketship rocketship = new Rocketship(250,700,50,50);
	ObjectManager object = new ObjectManager(rocketship);

	
	
	public GamePanel() {
		titleFont = new Font("Impact", Font.PLAIN, 55);
		subFont = new Font("New Courier", Font.PLAIN,32);
		frameDraw = new Timer(1000 / 60,this);
	    frameDraw.start();
	    if (needImage) {
	        loadImage ("space.png");
	    }
	  
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}


	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {
		
	}

	void updateGameState() {
      object.update();
      if(rocketship.isActive == false) {
   	    currentState = END;
      }
	}

	void updateEndState() {
		object.stopGame();
	}

	void drawMenuState(Graphics g) {
		

		g.setColor(startBackgroundColor);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		
		g.setColor(startFontColor);
		g.drawString("LEAGUE INVADERS", 55, 150);
		g.setFont(subFont);
		g.setColor(startFontColor);
		g.drawString("Press ENTER to start", 75, 350);
		g.drawString("Press SPACE for instructions", 25, 500);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0,0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} 
		object.draw(g);
		
		
		
		
	}

	void drawEndState(Graphics g) {
		g.setColor(endBackgroundColor);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		
		g.setFont(titleFont);
		g.setColor(endFontColor);
		g.drawString("GAME OVER", 75, 150);
		g.setFont(subFont);
		g.setColor(endFontColor);
		g.drawString("You killed " + object.getScore() + " invaders", 75, 350);
		g.drawString("Press ENTER to restart", 45, 500);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	rocketship = new Rocketship(250,700,50,50);
		    	rocketship.isActive =true;
		    	object = new ObjectManager(rocketship);
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}
		
		if (arg0.getKeyCode()==KeyEvent.VK_UP) {
		  
		    if(rocketship.y > 0) {
		    rocketship.up();
		    }
		}
		
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		 
		    if(rocketship.y < 700 ){
			    rocketship.down();
			    }
		}
		if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		   
		    if(rocketship.x < 500 - rocketship.width ) {
			    rocketship.right();
			    }
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		   
		    if(rocketship.x > 0) {
			    rocketship.left();
		    }
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			object.addProjectile(rocketship.getLeftProjectile());
			object.addProjectile(rocketship.getRightProjectile());
			object.addProjectile(rocketship.getMiddleProjectile());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
