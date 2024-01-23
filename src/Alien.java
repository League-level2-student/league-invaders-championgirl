import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {

	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.speed = 1;
	}
	
 
 void draw(Graphics g) {
	    g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
 
 }
 
 public void update() {
	 y+=speed;
 }
 
}