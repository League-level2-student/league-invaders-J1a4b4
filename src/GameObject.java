import java.awt.Graphics;

public class GameObject {

	int x;
	int y;
	int width;
	int height;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	GameObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	void update() {
		if(up) {
			y = y - 10;
		}else if(down) {
			y = y + 10;
		}else if(left) {
			x = x - 10;
		}else if(right) {
			x = x + 10;
		}
	}
	
	void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}
}
