package serializeAndSwingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;


// -- inherit from JFrame so that we can add custom functionality
public class JavaSwingImage extends JFrame {
	
	private GraphicPanel gp;
	private ControlPanel cp;
	private MyList<Shape> shapeArrayList;
	private boolean isFilled, isRectangle;
	private Color c;

	GraphicPanel getGraphicPanel()
	{
		return gp;
	}
	
	public JavaSwingImage(int height, int width) {

		setTitle("Swing GUI");
		
		setSize(width, height);
		
		setLocationRelativeTo(null);
		
		BufferedImage bi = null;		
		File inputfile = new File("./logo.png");
		try {
			bi = ImageIO.read(inputfile);
		} catch (IOException e) {
			System.out.println("error reading logo, exiting");
			System.exit(0);
		}
		setIconImage(bi);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// -- set the layout manager and add items
		//    5, 5 is the border around the edges of the areas
		setLayout(new BorderLayout(5, 5));

		gp = new GraphicPanel();
		this.add(gp, BorderLayout.CENTER);
		
		cp = new ControlPanel();
		this.add(cp, BorderLayout.EAST);
		
		this.setVisible(true);
		
		isFilled = true;
		isRectangle = true;
		shapeArrayList = new MyList<Shape>();
		c = Color.white;
	}
    
	// -- Inner class for control panel, inherits from JPanel
	public class GraphicPanel extends JPanel {
		private int x0, y0, x1, y1;
		private Timer animationTimer = null;	
		private Graphics2D g2d;

		public Timer getAnimationTimer() {
			return animationTimer;
		}

		public GraphicPanel() {
			super();			

			x0 = 0;
			y0 = 0;
			x1 = 0;
			y1 = 0;
			this.setBackground(Color.gray);

			// -- Define the mouse motion listener
			this.addMouseMotionListener(new MouseMotionListener() { //anonymous object

				public void mouseDragged(MouseEvent arg0) {
					if(isRectangle) {
    					if(x0 < x1 && y0 < y1) {
	    					g2d.drawRect(x0, y0, x1 - x0, y1 - y0);
		    			}
			    		else if(x1 < x0 && y0 < y1) {
				    		g2d.drawRect(x1, y0, x0 - x1, y1 - y0);
					    }
	    				else if(x0 < x1 && y1 < y0) {
		    				g2d.drawRect(x0, y1, x1 - x0, y0 - y1);
			    		}
				    	else {
					    	g2d.drawRect(x1, y1, x0 - x1, y0 - y1);
					    }
					}
					else {
						if(x0 < x1 && y0 < y1) {
	    					g2d.drawOval(x0, y0, x1 - x0, y1 - y0);
		    			}
			    		else if(x1 < x0 && y0 < y1) {
				    		g2d.drawOval(x1, y0, x0 - x1, y1 - y0);
					    }
	    				else if(x0 < x1 && y1 < y0) {
		    				g2d.drawOval(x0, y1, x1 - x0, y0 - y1);
			    		}
				    	else {
					    	g2d.drawOval(x1, y1, x0 - x1, y0 - y1);
					    }
					}
					
					x1 = arg0.getX();
					y1 = arg0.getY();
					
					if(isRectangle) {
    					if(x0 < x1 && y0 < y1) {
	    					g2d.drawRect(x0, y0, x1 - x0, y1 - y0);
		    			}
			    		else if(x1 < x0 && y0 < y1) {
				    		g2d.drawRect(x1, y0, x0 - x1, y1 - y0);
					    }
	    				else if(x0 < x1 && y1 < y0) {
		    				g2d.drawRect(x0, y1, x1 - x0, y0 - y1);
			    		}
				    	else {
					    	g2d.drawRect(x1, y1, x0 - x1, y0 - y1);
					    }
					}
					else {
						if(x0 < x1 && y0 < y1) {
	    					g2d.drawOval(x0, y0, x1 - x0, y1 - y0);
		    			}
			    		else if(x1 < x0 && y0 < y1) {
				    		g2d.drawOval(x1, y0, x0 - x1, y1 - y0);
					    }
	    				else if(x0 < x1 && y1 < y0) {
		    				g2d.drawOval(x0, y1, x1 - x0, y0 - y1);
			    		}
				    	else {
					    	g2d.drawOval(x1, y1, x0 - x1, y0 - y1);
					    }
					}
				
				//	System.out.println("Dragged: " + x0 + ", " + y0+"   "+ x1 + ", " + y1);
				}

				public void mouseMoved(MouseEvent arg0) {
			//		int x = arg0.getX();
			//		int y = arg0.getY();
					
				//	System.out.println("Moved: " + x + ", " + y);
				}
				
			});
			// -- Define the mouse listener
			this.addMouseListener(new MouseListener() {
			

				public void mouseClicked(MouseEvent arg0) {
		//			int x = arg0.getX();
		//			int y = arg0.getY();

		//			System.out.println("Clicked: " + x + ", " + y);
					
			//		repaint();
				}

				public void mouseEntered(MouseEvent arg0) {
		//			System.out.println("Entered: " + arg0.getX() + ", " + arg0.getY());
				}

				public void mouseExited(MouseEvent arg0) {
		//			System.out.println("Exited: " + arg0.getX() + ", " + arg0.getY());
				}

				public void mousePressed(MouseEvent arg0) {
		//			System.out.println("Pressed: " + arg0.getX() + ", " + arg0.getY());
					x0 = arg0.getX();
					y0 = arg0.getY();
					x1 = x0 + 1;
					y1 = y0 + 1;
					g2d = (Graphics2D) gp.getGraphics();
					g2d.setXORMode(Color.white);
					if(isRectangle) {
    					g2d.drawRect(x0, y0, 1, 1);
					}
					else {
						g2d.drawOval(x0, y0, 1, 1);
					}
				}

				public void mouseReleased(MouseEvent arg0) {
	//				System.out.println("Released: " + arg0.getX() + ", " + arg0.getY());
			        
					
					
                    if(isRectangle) {
			    		if(x0 < x1 && y0 < y1) {
	    					g2d.drawRect(x0, y0, x1 - x0, y1 - y0);
					    }
				    	else if(x1 < x0 && y0 < y1) {
			    			g2d.drawRect(x1, y0, x0 - x1, y1 - y0);
	    				}
    					else if(x0 < x1 && y1 < y0) {
						    g2d.drawRect(x0, y1, x1 - x0, y0 - y1);
				    	}
			    		else {
		    				g2d.drawRect(x1, y1, x0 - x1, y0 - y1);
    					}
                    }
                    else {
                    	if(x0 < x1 && y0 < y1) {
    						g2d.drawOval(x0, y0, x1 - x0, y1 - y0);
    					}
    					else if(x1 < x0 && y0 < y1) {
    						g2d.drawOval(x1, y0, x0 - x1, y1 - y0);
    					}
    					else if(x0 < x1 && y1 < y0) {
    						g2d.drawOval(x0, y1, x1 - x0, y0 - y1);
    					}
    					else {
    						g2d.drawOval(x1, y1, x0 - x1, y0 - y1);
    					}
                    }
                    x1 = arg0.getX();
					y1 = arg0.getY();
                    if(isRectangle) {
			    		if(x0 < x1 && y0 < y1) {
    						shapeArrayList.add(new Rectangle(x0, y0, x1 - x0, y1 - y0));
					    }
				    	else if(x1 < x0 && y0 < y1) {
		    				shapeArrayList.add(new Rectangle(x1, y0, x0 - x1, y1 - y0));
	    				}
    					else if(x0 < x1 && y1 < y0) {
					    	shapeArrayList.add(new Rectangle(x0, y1, x1 - x0, y0 - y1));
				    	}
			    		else {
	    					shapeArrayList.add(new Rectangle(x1, y1, x0 - x1, y0 - y1));
    					}
                    }
                    else {
                    	if(x0 < x1 && y0 < y1) {
    						shapeArrayList.add(new Ellipse(x0, y0, x1 - x0, y1 - y0));
    					}
    					else if(x1 < x0 && y0 < y1) {
    						shapeArrayList.add(new Ellipse(x1, y0, x0 - x1, y1 - y0));
    					}
    					else if(x0 < x1 && y1 < y0) {
    						shapeArrayList.add(new Ellipse(x0, y1, x1 - x0, y0 - y1));
    					}
    					else {
    						shapeArrayList.add(new Ellipse(x1, y1, x0 - x1, y0 - y1));
    					}
                    }
					shapeArrayList.get(shapeArrayList.size() - 1).setFilled(isFilled);
					shapeArrayList.get(shapeArrayList.size() - 1).setColor(c);
					g2d.setPaintMode();

					
					repaint();

				}
			}
			);
		
			// -- Timer will generate an event every 100mSec once it is started
			//    First parameter is the delay in mSec, second is the ActionListener
			animationTimer = new Timer(100, 
					// -- ActionListener for the timer event
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("tic");
					}
				}
				);
		}
		
		//keeps layout manager from making the size 0, 0
		public Dimension getPreferredSize() {
			return new Dimension(50, 50);
		}
		
		// -- this override is where all the painting should be done. 
		//    DO NOT call it directly. Rather, call repaint() and let the
		//    event handling system decide when to call it
		//    DO NOT put graphics function calls elsewhere in the code, although
		//    legal, it's bad practice and could destroy the integrity of the
		//    display
		public void paint(Graphics g) {
			// -- the base class paintComponent(g) method ensures 
			//    the drawing area will be cleared properly. Do not
			//    modify any attributes of g prior to sending it to
			//    the base class
			super.paintComponent(g);
			// -- for legacy reasons the parameter comes in as type Graphics
			//    but it is really a Graphics2D object. Cast it up since the
			//    Graphics2D class is more capable
            
			g2d = (Graphics2D)g;
			int h = this.getHeight();
			int w = this.getWidth();
			
			BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			
			for (int i = 0; i < h; ++i) { //paint here if you want something to be permanent
				for (int j = 0; j < w; ++j) {
					int red = 255;
					int green = 255;
					int blue = 128;
					bi.setRGB(j,  i, red << 16 | green << 8 | blue);
				}
			}

			g2d.drawImage(bi, 0, 0, w, h, null);

			for(int i = 0; i < shapeArrayList.size(); i++) {
				if(shapeArrayList.get(i) instanceof Rectangle) {
					Rectangle r = (Rectangle)shapeArrayList.get(i);
			        r.draw(g2d);
				}
				else if(shapeArrayList.get(i) instanceof Ellipse) {
					Ellipse e = (Ellipse)shapeArrayList.get(i);    
				    e.draw(g2d);
				}
			}
		}
	}
	
	// -- Inner class for control panel, also inherits from JPanel
	public class ControlPanel extends JPanel {

		private JTextField textfield;
		
		public String getTextField() {
			return textfield.getText();
		}
		
		public ControlPanel() {
			setLayout(new GridLayout(20, 1, 2, 2));

			// Radio buttons
			JRadioButton rectangleButton = new JRadioButton("Rectangle");
			rectangleButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							isRectangle = true;
						}
					}
				);
			
			JRadioButton ellipseButton = new JRadioButton("Ellipse");
			ellipseButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							isRectangle = false;
						}
					}
				);
			
			JRadioButton filled = new JRadioButton("Filled");
			filled.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							isFilled = true;
						}
					}
				);
			
			JRadioButton lines = new JRadioButton("Lines");
			lines.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							isFilled = false;
						}
					}
				);
			
			// -- JButtons
			JButton startButton = new JButton("Start Timer");
			startButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							gp.getAnimationTimer().start();
						}
					}
				);
			JButton stopButton = new JButton("Stop Timer");
			stopButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							gp.getAnimationTimer().stop();
						}
					}
				);
			JButton colorChooser = new JButton("Choose Color");
			colorChooser.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							c = JColorChooser.showDialog(null, "Set Color", getBackground());
						}
					}
				);
			
			JButton saveFile = new JButton("Save File");
			saveFile.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Shape[] shapeArray = new Shape[shapeArrayList.size()];
									shapeArrayList.toArray(shapeArray);
							try {
								FileOutputStream fileOut = new FileOutputStream("shapeArray.ser");
								ObjectOutputStream out = new ObjectOutputStream(fileOut);
								// write the object to the file
								out.writeObject(shapeArray);
								out.flush();
								// close the stream
								out.close();
								fileOut.close();
								System.out.println("Serialized data is saved in shapeArray.ser");
							} catch (IOException i) {
								System.out.println("Cannot open file");
							}
						}
					}
				);
			
			JButton loadFile = new JButton("Load File");
			loadFile.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							Shape shapeArray[] = null;
							try {
								FileInputStream fileIn = new FileInputStream("shapeArray.ser");
								ObjectInputStream in = new ObjectInputStream(fileIn);
								// read the object from the file
								Object ob = in.readObject();
								// cast to shape
								if (ob instanceof Shape[]) {
									shapeArray = (Shape[])ob;
								}
								// close the stream
								in.close();
								fileIn.close();
								shapeArrayList = new MyList<Shape>(shapeArray.length, 2);
								for(Shape i: shapeArray) { // place contents of shape array in arraylist
									shapeArrayList.add(i);
								}
								gp.repaint();
								System.out.println("File loaded.");
							} catch (IOException i) {
								System.out.println("Cannot open file");
								return;
							} catch (ClassNotFoundException c) {
								System.out.println("Shape class not found");
								return;
							}
						}
					}
				);
			
			JButton computeButton = new JButton("Do Something");
			computeButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.out.println(textfield.getText());
						}
					}
				);
			textfield = new JTextField("2.5");
			

			// -- group the radio buttons
			ButtonGroup bg = new ButtonGroup(); //if you push one, unpush the other
			bg.add(rectangleButton);
			bg.add(ellipseButton);
			// -- set the initially pressed radio button
			rectangleButton.setSelected(true);
			
			ButtonGroup bg2 = new ButtonGroup();
			bg2.add(filled);
			bg2.add(lines);
			filled.setSelected(true);
			
			// -- add all controls
			add(startButton);
			add(stopButton);
			add(textfield);
			add(filled);
			add(lines);
			add(computeButton);
			add(rectangleButton);
			add(ellipseButton);
			add(saveFile);
			add(loadFile);
			add(colorChooser);
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(100, 500);
		}
	}
	
	public static void main(String[] args) {
		// -- instantiate an anonymous object
		new JavaSwingImage(512, 768);
	}
}