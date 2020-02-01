import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	Timer alienSpawn;
	 Ship ship = new Ship(250, 700, 50, 50);
	 final int MENU_STATE = 0;
	 final int GAME_STATE = 1;
	 final int END_STATE = 2;
	 int currentState = MENU_STATE;
	 Font titleFont;
	 ObjectManager manager = new ObjectManager(ship);

	GamePanel(){
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		manager.draw(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 125, 300);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ship.update();
		repaint();
		 if(currentState == MENU_STATE){
             updateMenuState();
		 }else if(currentState == GAME_STATE){
             updateGameState();
		 }else if(currentState == END_STATE){
             updateEndState();
		 }
	}
	
	 @Override
	 	public void paintComponent(Graphics g){
	        if(currentState == MENU_STATE){
                drawMenuState(g);
	        }else if(currentState == GAME_STATE){
                drawGameState(g);
        	}else if(currentState == END_STATE){
                drawEndState(g);
        	}
	 }

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentState == MENU_STATE) {
				currentState = GAME_STATE;
				startGame();
			}else if (currentState == GAME_STATE) {
				currentState = END_STATE;
				alienSpawn.stop();
			}else if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}
		}else if (currentState == GAME_STATE) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				ship.up();
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				ship.down();
			}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				ship.left();
			}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				ship.right();
			}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				manager.addProjectile(ship.getProjectile());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
