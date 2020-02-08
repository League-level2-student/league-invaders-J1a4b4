import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {

	Ship ship;
	ArrayList <Projectile> projectiles = new ArrayList <Projectile>();
	ArrayList <Alien> aliens = new ArrayList <Alien>();
	Random random = new Random();
	int score = 0;
	
	ObjectManager(Ship ship){
		this.ship = ship;
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	void update(){
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			if (projectiles.get(i).y < 0 - projectiles.get(i).height) {
				projectiles.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}
	
	void draw(Graphics g) {
		ship.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}
	
	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(i);
			}
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
	}
	
	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			if (ship.collisionBox.intersects(aliens.get(i).collisionBox)) {
				aliens.get(i).isActive = false;
				ship.isActive = false;
			}
			for (int j = 0; j < projectiles.size(); j++) {
				if (projectiles.get(j).collisionBox.intersects(aliens.get(i).collisionBox)) {
					aliens.get(i).isActive = false;
					projectiles.get(j).isActive = false;
					score = score + 1;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addAlien();
		
	}
}
