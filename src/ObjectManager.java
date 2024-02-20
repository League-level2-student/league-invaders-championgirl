import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;


public class ObjectManager implements ActionListener {
Rocketship rocket;
ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
Timer alienSpawn;
Random random = new Random();
int score = 0;

	public ObjectManager(Rocketship r) {
		rocket = r;
		   alienSpawn = new Timer(1000 , this);
		    alienSpawn.start();
		    alienSpawn.addActionListener(this);
	}
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien() {
		int alienX = random.nextInt(LeagueInvaders.WIDTH);
		Alien newAlien = new Alien(alienX,0,50,50);
		aliens.add(newAlien);
		
	}

	public void  update() {
		rocket.update();
		for(Alien alien : aliens){
			alien.update();
		}
		
		for(Projectile projectile : projectiles){
			projectile.update();
		}
		
		 checkCollison();
		 purgeObjects();
		
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		
		for(Alien alien : aliens){
			alien.draw(g);

		for(Projectile projectile : projectiles){
			projectile.draw(g);
		}

	}

	}

	public void purgeObjects() {
		for(int i = 0; i < aliens.size(); i++){
			 Alien a = aliens.get(i);
			if(!a.isActive) {
				aliens.remove(a);
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++){
			 Projectile p = projectiles.get(i);
			if(!p.isActive) {
				projectiles.remove(p);
			}
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAlien();
	}
	
	void stopGame() {
		alienSpawn.stop();
	}
	
	public void checkCollison() {
		for (Alien alien : aliens) {
			for (Projectile projectile : projectiles) {
				if(alien.collisionBox.intersects(projectile.collisionBox)) {
					if(alien.isActive) {
				    score++;
					alien.isActive = false;
					}
					projectile.isActive = false;
				}
			}
		}
 for (Alien alien :aliens) {
	 if(rocket.collisionBox.intersects(alien.collisionBox)) {
		 alien.isActive = false;
	     rocket.isActive = false;
	 }
 }
		

}

	public int getScore() {
		return score;
	}




}
