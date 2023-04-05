import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Timer alienSpawn;
	Ship ship = new Ship(250, 700, 50, 50);
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	ObjectManager manager = new ObjectManager(ship);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		if (needImage) {
			loadImage("background.png");
		}
	}

	void startGame() {
		timer.start();
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		ship.update();
		manager.update();
		if (ship.isActive == false) {
			currentState = END_STATE;
			alienSpawn.stop();
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("League Invaders", 75, 300);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		manager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 125, 300);
		g.drawString("Score: " + manager.score, 125, 350);
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
	public void actionPerformed(ActionEvent e) {
		ship.update();
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
				startGame();
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
				ship = new Ship(250, 700, 50, 50);
				manager = new ObjectManager(ship);
			}
		} else if (currentState == GAME_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				ship.up();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				ship.down();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				ship.left();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				ship.right();
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				manager.addProjectile(ship.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
