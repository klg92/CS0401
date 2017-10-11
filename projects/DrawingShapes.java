package projects;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//Still no reason to import java.util.*;
import java.util.ArrayList;

//The frame represents the whole window
class DrawingShapes extends JFrame {

	public static void main(String[] args) {
		DrawingShapes app = new DrawingShapes();
	}

	public DrawingShapes() {
		super("Drawing Shapes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Make our top and bottom parts (code below)
		ButtonPanel buttons = new ButtonPanel();
		ShapesPanel pane = new ShapesPanel(buttons);
		setLayout(new BorderLayout());
		add(buttons, BorderLayout.SOUTH);
		add(pane, BorderLayout.CENTER);
		pack();
		
		//Time to show ourselves!
		setVisible(true);
	}
}

//This class represents the set of buttons at the bottom
class ButtonPanel extends JPanel implements ActionListener {
	private JRadioButton circleButton = new JRadioButton("Circle");
	private JRadioButton squareButton = new JRadioButton("Square");
	private JRadioButton triangleButton = new JRadioButton("Triangle");
	private JRadioButton smileyButton = new JRadioButton("Smiley Face");

	//TODO: Change the text of this button to describe your custom shape
	private JRadioButton customButton = new JRadioButton("Diamond");

	private JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(100, 1, 200, 10));

	//These are the 5 types of things we can draw. We will represent the current shape
	//as an integer called mode, that takes one of these values.
	public static final int CIRCLE_MODE = 0;
	public static final int SQUARE_MODE = 1;
	public static final int TRIANGLE_MODE = 2;
	public static final int SMILEY_MODE = 3;
	public static final int CUSTOM_MODE = 4;

	private int mode = CIRCLE_MODE;

	public ButtonPanel() {
		//The buttons will be laid out in a 4 row by 2 column grid
		setLayout(new GridLayout(4, 2));

		//Group the radio buttons. This allows only one to be selected at a time
		ButtonGroup group = new ButtonGroup();
		circleButton.setSelected(true);
		group.add(circleButton);
		group.add(squareButton);
		group.add(triangleButton);
		group.add(smileyButton);
		group.add(customButton);

		//Register a listener for the radio buttons.
		circleButton.addActionListener(this);
		squareButton.addActionListener(this);
		triangleButton.addActionListener(this);
		smileyButton.addActionListener(this);
		customButton.addActionListener(this);

		//Add the radio buttons
		add(circleButton);
		add(squareButton);
		add(triangleButton);
		add(smileyButton);
		add(customButton);

		//Make a panel that will hold a label and a spinner that allows you to
		//select a size
		JPanel sizePanel = new JPanel();
		JLabel sizeLabel = new JLabel("Size");
        sizePanel.add(sizeLabel);
        sizeLabel.setLabelFor(sizeSpinner);
        sizePanel.add(sizeSpinner);

		//Add the size panel to our grid
		add(sizePanel);
	}

	public void actionPerformed(ActionEvent event) {
		//All radio buttons being clicked end up calling this method.
		//We distinguish which button by asking the event for its source
		//and seeing if that is a particular button
		if(event.getSource() == circleButton) {
			mode = CIRCLE_MODE;
		}else if (event.getSource() == squareButton){
			mode = SQUARE_MODE;
		}else if (event.getSource() == triangleButton){
			mode = TRIANGLE_MODE;
		}else if (event.getSource() == smileyButton){
			mode = SMILEY_MODE;
		}else if (event.getSource() == customButton){
			mode = CUSTOM_MODE;
		}
	}

	//This returns the current size from the Spinner
	public int getShapeSize() {
		return (int)((SpinnerNumberModel)sizeSpinner.getModel()).getNumber();
	}

	//This returns the mode selected
	public int getMode() {
		return mode;
	}
}


//This class represents the space where shapes are drawn.
class ShapesPanel extends JPanel {
	private ArrayList<ShapesInterface> shapes = new ArrayList<>();
	private ButtonPanel bp;

	public ShapesPanel(ButtonPanel panel) {
		bp = panel;
		addMouseListener(new DrawingShapesMouseListener());
		setOpaque(true);
		setBackground(Color.lightGray);
	}
	//Yes, we have a class inside of a class. This is called an "inner class"
	//We need to pass a MouseListener to our addMouseListener method to 
	//respond to mouse clicks. MouseAdapter implements MouseListener and
	//provides pre-made implementations that do nothing. We override the
	//method that gets called when the mouse is clicked.
	//
	//This is an inner class because we get to access the private variables of
	//ShapesPanel since scope is lexical. That allows us to get the reference
	//to the ButtonPanel to ask what mode we are in, and to add elements to the
	//shapes ArrayList.
	private class DrawingShapesMouseListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent source) {
			//We clicked on the shapes panel, so we should add a new shape.
			if(bp.getMode() == ButtonPanel.CIRCLE_MODE) {
				shapes.add(new Circle(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if (bp.getMode() == ButtonPanel.SQUARE_MODE){
				shapes.add(new Rectangle(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if (bp.getMode() == ButtonPanel.TRIANGLE_MODE){
				shapes.add(new Triangle(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if (bp.getMode() == ButtonPanel.SMILEY_MODE){
				shapes.add(new Smiley(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if (bp.getMode() == ButtonPanel.CUSTOM_MODE){
				shapes.add(new Diamond(source.getX(), source.getY(), bp.getShapeSize()));
			}
			
			//Redraw the window since we now have a new shape to show.
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		//Call our superclass's paintComponent before we draw our shapes.
		super.paintComponent(g);

		//Draw our shapes
		for (ShapesInterface shape: shapes){
			shape.draw(g);
		}
	}

	//We return the size we want so that the window starts off properly-sized.
	public Dimension getPreferredSize()
	{
		return new Dimension(300, 300);
	}

}

//Draw a circle
class Circle implements ShapesInterface{
	//Our center
	private int x;
	private int y;

	//Our diameter
	private int size;

	public Circle(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	public void draw(Graphics g) {
		//A circle is just a special oval whose width and height are equal
		g.drawOval(x-size/2,y-size/2,size,size);
	}
}
// draw a square
class Rectangle implements ShapesInterface{
	//pretty straightforward, same as a circle.
	private int x;
	private int y;
	private int size;
	
	public Rectangle(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void draw(Graphics g){
		g.drawRect(x-size/2, y-size/2, size, size);
	}
}

class Triangle implements ShapesInterface{
	//we need an array of points instead of the center
	private int[] arrayX = new int[3];
	private int[] arrayY = new int[3];
	
	public Triangle(int x, int y, int size){
		//the points are determined by their distance from the center, basic geometry.
		arrayX[0] = x;
		arrayY[0] = y+size/2;
		
		arrayX[1] = x-size/2;
		arrayY[1] = y-size/2;
		
		arrayX[2] = x+size/2;
		arrayY[2] = y-size/2;
	}
	// draw triangle
	public void draw(Graphics g){
		g.drawPolygon(arrayX, arrayY, 3);
	}
}
//terrible smiley face
class Smiley implements ShapesInterface{
	private int x;
	private int y;
	private int size;
	
	public Smiley(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public void draw (Graphics g){
		//original circle
		g.drawOval(x-size/2, y-size/2, size, size);
		//eye circles
		g.drawOval(x+size/5, y-size/5, size/5, size/5);
		g.drawOval(x-size/5, y-size/5, size/5, size/5);
		//arc smile
		g.drawArc(x-size/4, y, size/2, size/3, 0, -180);
	}
}
//custom shape: Diamond
class Diamond implements ShapesInterface{
	//array of points like in the triangle
	private int[] arrayX = new int[4];
	private int[] arrayY = new int[4];
	
	
	public Diamond(int x, int y, int size){
		//again some basic geometry to determine point location
		arrayX[0] = x;
		arrayY[0] = y+size/2;
		
		arrayX[1] = x + size/2;
		arrayY[1] = y;
		
		arrayX[2] = x;
		arrayY[2] = y - size/2;
		
		arrayX[3] = x - size/2;
		arrayY[3] = y;
	}
	public void draw(Graphics g){
		g.drawPolygon(arrayX, arrayY, 4);
	}
}