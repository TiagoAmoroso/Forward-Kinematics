package forwardKinematics;

import java.awt.*;

import javax.swing.*;

public class Display extends JFrame{
	int width;
	int height;
	
	
	Panel panel;


	//Constructor
	public Display(int width, int height, Segment[] segments) {
		panel = new Panel(width, height, segments);
		
		//Important! If you don't use this the program continues to run even when window is closed causeing a fat memory and CPU L...
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.add(panel);
		
		this.pack();
		this.setVisible(true);
		
	}

}
