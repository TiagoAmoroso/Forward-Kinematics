package forwardKinematics;

import java.awt.*;

import javax.swing.*;

//Don't understand this warning
public class Panel extends JPanel{

	int width;
	int height;
	Dimension dimension;

	Segment[] segments;


	//Constructor
	public Panel(int width, int height, Segment[] segments){
		this.width = width;
		this.height = height;

		this.dimension = new Dimension(width, height);
		this.setPreferredSize(dimension);

		this.segments = segments;
	}


	//This is an overwritten method from java.awt. In this case, it means that is automatically sets up the appropriate graphics object for painting
	public void paint(Graphics g) {
		//Graphics is somewhat outdated so we cast this to Graphics2D
		Graphics2D g2D = (Graphics2D)g;

		//Resets the background of the panel compnent
		g2D.fillRect(0, 0, this.width, this.height);


		BasicStroke stroke = new BasicStroke(5);

		//Setting stroke type and colour
		g2D.setStroke(stroke);
		g2D.setPaint(Color.RED);

		//Drawing
		//g2D.drawLine(0, 0, this.width, this.height);
		//g2D.drawRect(10, 10, 10, 100);
		//g2D.fillRect(10, 10, 10, 100);


		//int[] xCoords = {150,250,350};
		//int[] yCoords = {300,150,300};

		//This can easily be used to represent an arm
		//g2D.fillPolygon(xCoords, yCoords, 3);

		//g2D.setFont(new Font("Ink Free", Font.BOLD, 50));
		//g2D.drawString("Bananas", this.pos,100);

		//g2D.drawImage(Image object, 300,300, null);

		//Iterate over each segment and draw it ################################


		for (Segment segment : this.segments) {
			segment.paintSegment(g2D);
		}

		this.repaint();

	}


}
