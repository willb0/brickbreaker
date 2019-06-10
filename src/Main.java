import javax.swing.JFrame;
public class Main {
	public static void main(String[] args) {
		JFrame GAMEWIN = new JFrame(); 
		game gMain = new game();
		GAMEWIN.setBounds(10, 10, 700, 600);
		GAMEWIN.setTitle("brickbreaker");
		GAMEWIN.setResizable(false);
		GAMEWIN.setVisible(true);
		GAMEWIN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GAMEWIN.add(gMain);
	}
}