package forwardKinematics;


import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * ADDITIONS
 * Allow user to place object to be collected
 * Run random looping animations using phase offset and a sine wave
 */


public class ForwardKinematics {

	public static void main(String args[]) throws InterruptedException {

		Random random = new Random();

		//So that I can stop and start the simulation
		boolean running = true;

		int frameRate = 60;
		int frame = 0;
		int changesPerSecond = 60;


		//Creates an array containing all segments of the "arm"
		int amountOfSegments = 20;
		Segment[] segments = new Segment[amountOfSegments];
		Segment parent = null;


		int width = 5;
		int length = 20;
		int angle = -90;

		double phase = 0;


		for (int i = 0; i < amountOfSegments; i++) {
			if (parent != null) {
				//having a correlated parent segment allows us to utilise important data that is required for our calculations
				segments[i] = new Segment(0, 0, angle, phase, width, length, parent);
				segments[i].updatePosition();
				parent = segments[i];

				//Offsetting phase for cool shit - Each segment uses a sine wave to form an animation of sorts, this offsets that value for each parent --> child relationship
				//phase += 0.15;
				phase += random.nextDouble();

				//length += 2;
				//length -= 2; //This can lead to some cool "reverese" segments if allowed to go into negative values

			}
			else {
				segments[i] = new Segment(400, 400, angle, phase, width, length);
				segments[i].updatePosition();
				parent = segments[i];

				//Offsetting phase for cool shit - Each segment uses a sine wave to form an animation of sorts, this offsets that value for each parent --> child relationship
				phase += 0;
			}


		}

		//Create a visual pop up to display the simulation
		Display display = new Display(800, 600, segments);

		//Loop to maintain control over the simulation
		while(running) {
			frame += 1;

			//run next iteration of the simulation
			//Time between each simulation update
			TimeUnit.MILLISECONDS.sleep(1000/frameRate);


			//Apply changes to each segment here###########################


			//I only want to apply angle shift/segment changes a certain amount of times every second, independent of the frame rate. This code allows this.
			if (frame % (frameRate/changesPerSecond) == 0) {

				//Iterating over every segment
				for (int i = 0; i < segments.length; i++) {

					//Alters angle
					segments[i].changeAngle();
					//Updates position on the segment factoring in the parent segment and the new angle of this specific segment
					segments[i].updatePosition();
				}

			}

			//This is just so the frame count doesn't get too unwieldy
			if (frame == 100) {
				//System.out.println("Resetting Frames");
				frame = 0;
			}
		}
	}
}
