package Snowman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;



public class SnowmanPanel extends JPanel
{
	private AffineTransform at;
	private int c1Reset = -250, c2Reset = -250, c3Reset = -250, c4Reset = -250;
	private int c1InThing = 800, c2InThing = 1000, c3InThing = 1200, c4InThing = 1400;
	private Cloud c1, c2, c3, c4;
	private Timer c1Timer, c2Timer, c3Timer, c4Timer;
	private int c1Time, c2Time, c3Time, c4Time;

	public SnowmanPanel(int w, int h)
	{
		this.setPreferredSize(new Dimension(w,h));

		Random rand = new Random();

		c1Time = rand.nextInt(10)+7;
		c1Timer = new Timer(c1Time, new C1Listener());
		c1 = new Cloud(c1Time, c1InThing);

		c2Time = rand.nextInt(10)+7;
		c2Timer = new Timer(c2Time, new C2Listener());
		c2 = new Cloud(c2Time, c2InThing);

		c3Time = rand.nextInt(10)+7;
		c3Timer = new Timer(c3Time, new C3Listener());
		c3 = new Cloud(c3Time, c3InThing);

		c4Time = rand.nextInt(10)+7;
		c4Timer = new Timer(c4Time, new C4Listener());
		c4 = new Cloud(c4Time, c4InThing);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.GRAY);
        Graphics2D g2 = (Graphics2D) g;
		at = new AffineTransform();
		at.setToScale(0.75, 0.75);
		at.setToTranslation(135, 250);

	    int[] xPoints = {240, 240, 215, 160, 130, 130, 145, 220, 235};
	    int[] yPoints = {190, 220, 240, 260, 260, 240, 240, 230, 220};

	    int[] carrotX = {300, 350 ,300};
	    int[] carrotY = {135, 148, 161};

	    g.setColor(Color.blue);
	    g.fillRect(0, 0, 600, 600);


		for (Point point : c1.getSnowPoints()) 
		{
			g.setColor(Color.WHITE);
			g.fillRect(point.x-250, point.y, 1, 1);
		}
		for(int i = 0; i < c1.getXList().size(); i++)
		{
			g.setColor(Color.DARK_GRAY);
			g.fillOval(c1.getXList().get(i)-250, c1.getYList().get(i), c1.getWList().get(i), c1.getWList().get(i));
		}

		for (Point point : c2.getSnowPoints()) 
		{
			g.setColor(Color.WHITE);
			g.fillRect((point.x)-450, point.y, 1, 1);
		}
		for(int i = 0; i < c2.getXList().size(); i++)
		{
			g.setColor(Color.DARK_GRAY);
			g.fillOval((c2.getXList().get(i))-450, c2.getYList().get(i), c2.getWList().get(i), c2.getWList().get(i));
		}

		for (Point point : c3.getSnowPoints()) 
		{
			g.setColor(Color.WHITE);
			g.fillRect((point.x)-650, point.y, 1, 1);
		}
		for(int i = 0; i < c3.getXList().size(); i++)
		{
			g.setColor(Color.DARK_GRAY);
			g.fillOval((c3.getXList().get(i))-650, c3.getYList().get(i), c3.getWList().get(i), c3.getWList().get(i));
		}

		for (Point point : c4.getSnowPoints()) 
		{
			g.setColor(Color.WHITE);
			g.fillRect((point.x)-850, point.y, 1, 1);
		}
		for(int i = 0; i < c4.getXList().size(); i++)
		{
			g.setColor(Color.DARK_GRAY);
			g.fillOval((c4.getXList().get(i))-850, c4.getYList().get(i), c4.getWList().get(i), c4.getWList().get(i));
		}

	    g.setColor(Color.white);
	    g.fillRect(0, 540, 600, 60);
	    g2.setTransform(at);
	    g.setColor(Color.white);
	    g.fillOval(180, 330, 240, 240);
	    g.fillOval(210, 190, 180, 180);
	    g.fillOval(235, 90, 130, 130);

	    g.setColor(Color.green);
	    int topYOfSticks = 240;
	    int bottomYOfSticks = 280;

	    for(int i = 0; i<4;i++)
	      {
		    g.drawLine(120,topYOfSticks+i,240,bottomYOfSticks+i);
		    g.drawLine(480,topYOfSticks+i,360,bottomYOfSticks+i);
	      }
	    g.setColor(Color.white);
	    g.fillOval(210, 190, 180, 180);
	    g.setColor(Color.BLACK);
	    g.fillOval(292, 260, 15, 15);
	    g.fillOval(292, 290, 15, 15);
	    g.fillOval(292, 320, 15, 15);
	    g.fillOval(292, 350, 15, 15);
	    g.fillOval(292, 380, 15, 15);
	    g.fillOval(292, 410, 15, 15);
	    g.fillRect(240, 85, 120, 20);
	    g.fillRect(265, 10, 70, 90);

	    g.setColor(Color.red);
	    g.fillRect(240, 190, 120, 30);
	    g.fillPolygon(xPoints, yPoints, 9);
	    g.setColor(Color.orange);
	    g.fillPolygon(carrotX, carrotY, 3);
	    g.setColor(Color.black);
	    g.fillOval(275, 110, 20, 20);
	    g.fillOval(310, 110, 20, 20);

	    c1Timer.start();
	    c2Timer.start();
	    c3Timer.start();
	    c4Timer.start();
	}

	private class C1Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
			for(int i = 0; i < c1.getXList().size(); i++)
			{
				c1.getXList().set(i, c1.getXList().get(i)+1);
			}

			c1Reset++;

			if(c1Reset > c1InThing)
			{
				c1Reset = -250;
				c1 = new Cloud(c1Time, c1InThing);
			}
		}
	}
	private class C2Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
			for(int i = 0; i < c2.getXList().size(); i++)
			{
				c2.getXList().set(i, c2.getXList().get(i)+1);
			}

			c2Reset++;

			if(c2Reset > c2InThing)
			{
				c2Reset = -250;
				c2 = new Cloud(c2Time, c2InThing);
			}
		}
	}
	private class C3Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
			for(int i = 0; i < c3.getXList().size(); i++)
			{
				c3.getXList().set(i, c3.getXList().get(i)+1);
			}

			c3Reset++;

			if(c3Reset > c3InThing)
			{
				c3Reset = -250;
				c3 = new Cloud(c3Time, c3InThing);
			}
		}
	}
	private class C4Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			repaint();
			for(int i = 0; i < c4.getXList().size(); i++)
			{
				c4.getXList().set(i, c4.getXList().get(i)+1);
			}

			c4Reset++;

			if(c4Reset > c4InThing)
			{
				c4Reset = -250;
				c4 = new Cloud(c4Time, c4InThing);
			}
		}
	}
}