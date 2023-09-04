#include <stdlib.h>
#include <time.h>
#include "Resources.h"





int main(int argc, char* argv[]) {

	srand(time(NULL)); // Seed the random number generator.

	Screen screen; // Create a screen object, and initialize it.
	screen.init();

	Swarm swarm; // Create a swarm object. Array of NPARTICLES particles created.

	for (int i = 0; i < Screen::WIDTH * Screen::HEIGHT; i++) { // Set the background of the window to black by setting all data in buffer1 to zero, then drawing it on line 23

		screen.buffer1[i] = 0;

	}

	screen.draw();

	while (true) { // Game loop. Updates particle positions, colors, blurring effects, drawing to the screen, and processing events.


		swarm.update(); // update each particles position in the array.

		unsigned char red = (1 + cos(SDL_GetTicks() * 0.0003 + (M_PI * 0.25))) * 128;	  // Oscillating color RGB values. Each individual color value is stored in a 1 byte variable
		unsigned char green = (1 + cos(SDL_GetTicks() * 0.0001 + (M_PI * 0.5))) * 128; // by using an unsigned char.
		unsigned char blue = (1 + sin(SDL_GetTicks() * 0.00025 + (M_PI * 0.25))) * 128;

		const Particle* const particles = swarm.getParticles(); // Constant particle pointer that points to a constant address points to the first element in particleArray.

		for (int i = 0; i < Swarm::NPARTICLES; i++) { // For loop that updates buffer1 with proper pixel data.

			int x = (particles[i].xPos + 1) * (Screen::WIDTH / 2); // Conversion formula from xPos (which ranges from -1 to 1) to a value that ranges from 0 to Screen::WIDTH. 
			int y = particles[i].yPos * Screen::WIDTH / 2 + Screen::HEIGHT / 2; // Conversion formula from yPos (which ranges from -1 to 1) to a value that ranges from 0 to Screen::HEIGHT. 

			screen.setPixel(x, y, red, green, blue); // Set the pixel occupying (x,y) coordinates on the screen to the proper color.

		}

		screen.blur(); // Blur the screen using the box blur formula
		screen.draw(); // Draw updated buffer1 data to the screen.




		if (screen.quitState() == false) { // If the user presses the "x" button, break from the while loop.
			break;
		}


	}

	screen.close(); // Close the screen processes once the game loop has been broken out of, and close the program.
	return 0;
}
