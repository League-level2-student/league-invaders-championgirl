import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	GamePanel gamePanel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;

	public static void main(String[] args) {
       LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}

	public LeagueInvaders() {

		this.frame = new JFrame();
		this.gamePanel = new GamePanel();
		frame.addKeyListener(gamePanel);
	}

	void setup() {
		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(WIDTH,HEIGHT);
	}

}
