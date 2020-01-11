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
	 GameObject objectI;
	 final int MENU_STATE = 0;
	 final int GAME_STATE = 1;
	 final int END_STATE = 2;
	 int currentState = MENU_STATE;
	 Font titleFont;

	GamePanel(){
		timer = new Timer(1000/60, this);
		objectI = new GameObject(10, 10, 100, 100);
		titleFont = new Font("Arial", Font.PLAIN, 48);
	}
	
	void startGame() {
		timer.start();
	}
	
	void updateMenuState() {
		
	}
	
	void updateGameState() {
		
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
		objectI.update();
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
	        objectI.draw(g);
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
			}else if (currentState == GAME_STATE) {
				currentState = END_STATE;
			}else if (currentState == END_STATE) {
				currentState = MENU_STATE;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			objectI.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			objectI.down = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			objectI.left = true;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			objectI.right = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		objectI.up = false;
		objectI.down = false;
		objectI.left = false;
		objectI.right = false;
	}
}
