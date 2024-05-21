package Snowman;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Snowman
{
	public static void main(String[] args)
	{
		int w = 600;
		int h = 600;

		JFrame frame = new JFrame("Snowman");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new SnowmanPanel(w,h));
		frame.pack();
		frame.setVisible(true);
	}
}