package serializeAndSwingGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

// -- if the base class is not Serializable its fields 
//    will not be written
public abstract class Shape implements Serializable {
	protected int x, y;
	private Color color;
	private boolean isFilled;
	
	protected Shape ()
	{
		this.x = 0;
		this.y = 0;
	}

	protected Shape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		isFilled = false;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	public Color getColor() {
		return color;
	}
	
	public void setFilled(boolean isFilled) {
		this.isFilled = (isFilled)? true: false;
	}
	public boolean getFilled() {
        return isFilled;
    }

	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
	}
	
	public String toString ()
	{
		return x + ", " + y;
	}
}
