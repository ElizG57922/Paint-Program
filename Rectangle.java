package serializeAndSwingGUI;

import java.awt.Graphics2D;
import java.io.Serializable;

public class Rectangle extends Shape implements Serializable {
	private int width, height;
	
	public Rectangle ()
	{
		super();
		this.width = 0;
		this.height = 0;
	}
	
	public Rectangle (int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	
	}
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	
		if(getFilled()) {
			g2d.fillRect(x, y, width, height);
		}
		else {
    		g2d.drawRect(x, y, width, height);
		}
	}
	public String toString()
	{
		return super.toString() + ": Rectangle: " + width + ", " + height + "(" + x + ", " + y + ")";
	}
}
