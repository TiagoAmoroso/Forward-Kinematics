package forwardKinematics;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;

import java.lang.Math.*;
import java.util.Random;

public class Segment extends JComponent{
	
	int x1;
	int y1;
	double angle;
	double angleDiff;
	
	int width;
	int length;

	int x2;
	int y2;
	
	double phase;

	Segment parent = null;

	//Constructor
	public Segment(int x, int y, double angle, double phase, int width, int length, Segment parent) {

		this.x1 = x;
		this.y1 = y;
		this.angle = angle;
		this.angleDiff = 0;
		
		this.width = width;
		this.length = length;

		this.x2 = 0;
		this. y2 = 0;

		this.parent = parent;
		
		this.phase = phase;

	}
	

	//Secondary contstructor for when there isn't a parent segment
	public Segment(int x, int y, double angle, double phase, int width, int length) {

		this.x1 = x;
		this.y1 = y;
		this.angle = angle;
		this.angleDiff = angle;
		
		this.width = width;
		this.length = length;

		this.x2 = 0;
		this. y2 = 0;
		
		this.phase = phase;
	}
	
	
	public void changeAngle() {
		Random random = new Random();
		
		//ALL THESE ARE CONSISTENT LOOPING ANIMATIONS
		/*
		//Cool
		double change = Math.sin(phase);
		phase += 0.001; //As it is a sine wave this will perpetually produce a value between -1 and 1 in a consistent manner
		*/
		
		/*
		//Very cool when paired with phase offset in each segment, this can be randomised for a more interesting result. 
		//The phase offset between segments is done when the segments are originally generated in main in ForwardKinematics.java
		double change = Math.sin(phase);
		phase += 0.03;
		*/
		
		double change = Math.cos(phase);
		phase += 0.03;
		
		//#####FORMULA FOR MAPPING A SET OF VALUES ONTO ANOTHER SET OF VALUES WITH A DIFFERENT RANGE#####
		//output = output_start + ((output_end - output_start) / (input_end - input_start)) * (input - input_start)
		//change = -0.1 + ((0.1 - -0.1) / (-1 - 1)) * (change - -1);
		
		
		
		//THESE ARE RANDOM AND A BIT JANKY
		/*
		double factor = 2;
		
		double change = (random.nextDouble() * factor) - (factor * 0.5);
		*/
		
		this.angleDiff += change;
	}

	/**
	 * This method is called from the display's panel to paint the segment to the screen graphics 
	 * @param g2D the graphics object upon which the segments are being painted
	 */
	public void paintSegment(Graphics2D g2D) {

		BasicStroke stroke = new BasicStroke(width);
		g2D.setStroke(stroke);

		g2D.setPaint(Color.BLUE);

		g2D.drawLine(x1, y1, x2, y2);
	}


	/**
	 * This method updates the start and end vector coordinates for the segment
	 */
	public void updatePosition() {
		//Angle diff is the angle change relative to the parent segment. This allows for each segment to have an individual rotation
		//Angle is the actual angle on a grid that will be used in calculations
		this.angle = this.angleDiff;
		
		if (parent != null) {
			
			this.x1 = parent.x2;
			this.y1 = parent.y2;
			
			this.angle += parent.angle;
		}

		calculateEndPosition();
	}


	/**
	 * This method calculates and stores the end vector coordinates of the segment
	 */
	public void calculateEndPosition() {
		double angleInRadians = Math.toRadians(this.angle);
		
		//Uses trigonemetry to find the change in x and y using the hypotenuse
		double dx = (double)this.length * Math.cos(angleInRadians);
		double dy = (double)this.length * Math.sin(angleInRadians);
		
		//Updates the end position of this segment using the start position and dx and dy
		this.x2 = this.x1 + (int)dx;
		this.y2 = this.y1 + (int)dy;
	}

}
