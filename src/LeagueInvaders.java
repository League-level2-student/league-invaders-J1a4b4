import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {

	JFrame frame;
	final static int WIDTH = 500;
	final static int HEIGHT = 800;
	GamePanel gamePanel;
	
	LeagueInvaders(){
		frame = new JFrame();
		gamePanel = new GamePanel();
	}
	
	public static void main(String[] args) {
		LeagueInvaders invader = new LeagueInvaders();
		invader.setup();
	}
	
	void setup() {
		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.addKeyListener(gamePanel);
		frame.pack();
		gamePanel.startGame();
	}
}
