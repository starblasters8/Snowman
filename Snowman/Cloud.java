package Snowman;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Cloud
{
	private int snowReset = -250;
	private int minX, maxX, maxY, offY;
	private int val = 0, inThing, w;
	private int prevX = 0, prevW = 0;
	private ArrayList<Integer> xList, yList, wList;
	private ArrayList<Point> snowList;
	private Timer time;
	Random rand = new Random();

	public Cloud(int speed, int inThing)
	{
		this.inThing = inThing;

		time = new Timer(speed, new SnowListener());
		xList = new ArrayList<>();
		yList = new ArrayList<>();
		wList = new ArrayList<>();

		this.offY = rand.nextInt(100);

		cloudDraw();
		time.start();
	}

	public void cloudDraw()
	{
		xList.clear();
		yList.clear();
		wList.clear();

		int x = 0, y, w = 0;
		minX = 100000;
		maxX = 0;
		maxY = 0;

		int i = rand.nextInt(2)+3;
		while(i > 0)
		{
			w = rand.nextInt(50)+70;
			x = (prevX + (prevW/2)-10);
			y = rand.nextInt(25);

			prevW = w;
			prevX = x;
			if(minX > x)
				minX = x;
			if(maxX < (x+w))
				maxX = x+w;
			if(maxY < y)
				maxY = y+offY;

			xList.add(x);
			yList.add(y+offY);
			wList.add(w);
			i--;
		}
		snowList = new ArrayList<>();

	}

	private class SnowListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for(int i = 0; i < snowList.size(); i++)
			{
				snowList.set(i, new Point(snowList.get(i).x+(rand.nextInt(3)-1), snowList.get(i).y+rand.nextInt(6)));
				if(snowList.get(i).y > 550 || snowList.get(i).x > 600+(inThing-550))
					snowList.remove(i);

			}
			for(int i = 0; i < rand.nextInt(50); i++)
			{
				snowList.add(new Point(rand.nextInt(maxX-minX-15)+val, maxY+(w/2)+50));
			}
			val++;

			snowReset++;
			if(snowReset > inThing)
			{
				snowReset = -250;
				val = 0;
			}
		}
	}

	public int getSnowY()
	{
		return maxY;
	}
	public int getY()
	{
		return this.maxY;
	}
	public int getMinX()
	{
		return minX;
	}

	public int getMaxX()
	{
		return maxX;
	}

	public int getWidth()
	{
		return maxX-minX;
	}

	public ArrayList<Integer> getXList()
	{
		return xList;
	}

	public ArrayList<Integer> getYList()
	{
		return yList;
	}

	public ArrayList<Integer> getWList()
	{
		return wList;
	}

	public ArrayList<Point> getSnowPoints()
	{
		return this.snowList;
	}

}

