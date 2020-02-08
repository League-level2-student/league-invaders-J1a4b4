import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Ship extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	
	Ship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 20;
		if (needImage) {
		    loadImage ("ship.png");
		}
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	
	void up() {
		if(y > 0) {
			y = y - speed;
		}
	}
	
	void down() {
		if(y < LeagueInvaders.HEIGHT - height) {
			y = y + speed;
		}
	}
	
	void left() {
		if(x > 0) {
			x = x - speed;
		}
	}
	
	void right() {
		if(x < LeagueInvaders.WIDTH - width) {
			x = x + speed;
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
	public Projectile getProjectile() {
		return new Projectile(x + width / 2, y, 10, 10);
	}
}
