import java.awt.Color;
import java.awt.Graphics;

public class Ship extends GameObject {

	Ship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
	}

	void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x + width / 4, y, width / 2, height);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y + height / 2, width, height / 2);
		g.setColor(Color.BLUE);
		g.fillRect(x + width * 2/5, y + height / 6, width / 5, height / 3);
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
}
