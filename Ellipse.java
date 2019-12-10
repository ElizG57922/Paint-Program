package serializeAndSwingGUI;

import java.awt.Graphics2D;
import java.io.Serializable;

public class Ellipse extends Shape implements Serializable {
	private int width, height;

	public Ellipse ()
	{
		super();
		this.width = 0;
		this.height = 0;
	}

	public Ellipse (int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;

	}
	public void draw(Graphics2D g2d) {
		super.draw(g2d);

		if(getFilled()) {
			g2d.fillOval(x, y, width, height);
		}
		else {
			g2d.drawOval(x, y, width, height);
		}
	}
	public String toString()
	{
		return super.toString() + ": Ellipse: " + width + ", " + height + "(" + x + ", " + y + ")";
	}
}