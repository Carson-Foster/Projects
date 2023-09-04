#include "Resources.h"
#include <iostream>




Particle::Particle() { // Constructing a particle object causes the particle to be initialized.

	init();

}



void Particle::init() {

	t = (rand() / (double)RAND_MAX) * 2 * M_PI; // Initialize t to be anywhere from 0 to 2 * pi. This will initialize the particles' starting angle. t acts as an independent variable.

	xPos = 0;
	yPos = 0;

	speed = (rand() / (double)RAND_MAX) * 0.078; // // Randomize the particle's speed, anywhere from 0 to 0.078.
	speed *= speed;

}



void Particle::update() { // Function to update the particles' position on the screen.

	t += 0.02; // increment t by 0.02 every time the update() function is called.

	xPos += speed * 3 * cos(t) + sin(SDL_GetTicks() * 0.0001) * sin(t) * speed;						  // Adding values to xPos and yPos that will cause the particles to trace out an ellipse that changes direction over time. 
	yPos += speed * -0.5 * sin(t) * sin(SDL_GetTicks() * 0.0001 + M_PI * 0.5) + speed * 0.5 * cos(t); // This is achieved by making some of the trig functions dependent on time.


	if (xPos < -1 || xPos > 1 || yPos < -1 || yPos > 1 || rand() < RAND_MAX / 300) { // If the particle is off the screen, reinitialize it.

		init();

	}

}



int Screen::init() { // Constructors cannot return integer values, so a pseudo-constructor function is defined to return an integer to the main() function if SDL initialization fails.

	if (SDL_Init(SDL_INIT_VIDEO) < 0) { // SDL_Init() will return a value less than zero if SDL initialization fails. In that case, print an error message and close the program.

		std::cout << "SDL Initialization failed. Program Ending..." << std::endl;
		SDL_Quit(); // Terminate SDL processes before ending program.
		return 1; // Return value to main() to end program.
	}

	std::cout << "Screen Object Created. Creating Window, Renderer, Texture and Buffer objects... " << std::endl;


	window = SDL_CreateWindow("Particle Galaxy", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, WIDTH, HEIGHT, SDL_WINDOW_SHOWN); // Supplying Window object with the required parameters.

	if (window == NULL) { // Window returns NULL if window initialization was unsuccessful. In that case, print an error message and close the program.

		std::cout << "Window object creation failed. Program ending..." << std::endl;
		SDL_Quit();
		return 2;
	}

	std::cout << "Window object creation successful." << std::endl;

	renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_PRESENTVSYNC); // Supplying Renderer object with the required parameters. VSYNC is enabled to prevent screen shearing.

	if (renderer == NULL) { // Renderer returns NULL if renderer initialization was unsuccessful. In that case, print an error message and close the program.

		std::cout << "Renderer Object Initialization failed. Program ending..." << std::endl;
		SDL_DestroyWindow(window);
		SDL_Quit();
		return 3;

	}

	std::cout << "Render object creation successful." << std::endl;

	texture = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_RGBA8888, SDL_TEXTUREACCESS_STATIC, WIDTH, HEIGHT); // Texture object composes the rendering data.

	if (texture == NULL) { // Texture returns NULL if texture initialization was unsuccessful. In that case, print an error message and close the program.

		std::cout << "Texture Object Initialization failed. Program ending..." << std::endl;
		SDL_DestroyRenderer(renderer);
		SDL_DestroyWindow(window);
		SDL_Quit();
		return 4;
	}

	std::cout << "Texture object creation successful." << std::endl;

	buffer1 = new Uint32[WIDTH * HEIGHT]; // Integer arrays allocated to store RGBA data for each pixel in the window. Buffer2 is needed to process Box Blur computations, and
	buffer2 = new Uint32[WIDTH * HEIGHT]; // buffer1 is to store finalized pixel color data. WIDTH * HEIGHT returns the amount of pixels in the window.

}



Screen::~Screen() {

	std::cout << "Destroying Screen object..." << std::endl; // Deconstructor message for the Screen Desconstructor.

}



void Screen::close() { // Closes all Screen object processes.

	std::cout << "Destroying SDL objects..." << std::endl;
	SDL_DestroyRenderer(renderer); // Destroy Buffers, Window, Texture, and Renderer before quitting SDL, then close the program.
	SDL_DestroyTexture(texture);
	SDL_DestroyWindow(window);
	delete[] buffer1; // Delete integer arrays to prevent memory leaks.
	delete[] buffer2;
	SDL_Quit();

}



void Screen::draw() { // Updates the texture with the buffer1 data, clears the renderer of any previous data, copies texture data onto the renderer, and then the renderer draws the texture data onto the screen.

	SDL_UpdateTexture(texture, NULL, buffer1, WIDTH * sizeof(Uint32));
	SDL_RenderClear(renderer);
	SDL_RenderCopy(renderer, texture, NULL, NULL);
	SDL_RenderPresent(renderer);

}



bool Screen::quitState() { // quitState() returns false if the user presses the "x" button on the window to exit out.

	while (SDL_PollEvent(&event)) { // While there is an event queue, run this while loop.

		if (event.type == SDL_QUIT) { // If the event is of type SDL_QUIT (user wants to exit out of the window), return false.
			return false;
		}
	}

	return true; // Returns true otherwise.

}



void Screen::setPixel(int x, int y, Uint8 red, Uint8 blue, Uint8 green) {

	Uint32 color = 0; // Create a 32 bit integer and store RBGA data by bitshifting the inputted RGB data along the variable into it's proper position to be used later.
	color += red;
	color <<= 8; // Red data is left-shifted one byte along the 4 byte color variable to create space for the blue data.
	color += blue;
	color <<= 8; // Blue data is left-shifted one byte along the 4 byte color variable to create space for the green data.
	color += green;
	color <<= 8; // Green data is left-shifted one byte along the 4 byte color variable to create space for the alpha data, which is not going to be used in this program, but still needs to occupy one byte of space in the color variable.
	color += 0xFF;
	if (y < 0 || y >= Screen::HEIGHT || x < 0 || x >= Screen::WIDTH) { // If the pixel's position is outside of the view of the screen, don't draw it.

		return;
	}

	buffer1[(y * WIDTH) + x] = color; // (y * WIDTH) + x is the conversion formula from cartesian coordinates (inputted into the function) to the pixel's position in the buffer1 array.

}



void Screen::blur() {

	Uint32* temp = buffer1; //Swapping buffers: since buffer1 is used as the finalized pixel data, it needs to point to the array that has proper pixel data. 
	//Buffer2 has the properly processed data from the last time blur() was called, so buffer1 points to that. Buffer2 then points to what 
	// buffer1 pointed to in order to process the data.
	buffer1 = buffer2;
	buffer2 = temp;

	for (int y = 0; y < Screen::HEIGHT; y++) { // Box blur formula implemented.

		for (int x = 0; x < Screen::WIDTH; x++) {


			double redTotal = 0;
			double greenTotal = 0;
			double blueTotal = 0;

			for (int row = -1; row <= 1; row++) {

				for (int col = -1; col <= 1; col++) {
					int currentx = x + col;
					int currenty = y + row;
					if (currentx >= 0 && currentx < Screen::WIDTH && currenty >= 0 && currenty < Screen::HEIGHT) {
						Uint32 color = buffer2[(currenty * WIDTH) + currentx];
						unsigned char red = color >> 24;
						unsigned char green = color >> 16;
						unsigned char blue = color >> 8;
						redTotal += red * 0.96;
						greenTotal += green * 0.96;
						blueTotal += blue * 0.96;
					}

				}

			}

			Uint8 red = (redTotal) / 9;
			Uint8 green = (greenTotal) / 9;
			Uint8 blue = (blueTotal) / 9;
			setPixel(x, y, red, green, blue); // Finalized blurred pixel data is sent into buffer1.
		}
	}
}



Swarm::Swarm() {// Create an array of NPARTICLES particles.

	particleArray = new Particle[NPARTICLES];

}



Swarm::~Swarm() { // Delete the particle array

	delete[] particleArray;

}



Particle* Swarm::getParticles() { // Return the address of the first element in the particle array.

	return particleArray;

}



void Swarm::update() { //  Updates the position of each particle in the particle array by using a for loop.

	for (int i = 0; i < Swarm::NPARTICLES; i++) {
		particleArray[i].update();

	}

}