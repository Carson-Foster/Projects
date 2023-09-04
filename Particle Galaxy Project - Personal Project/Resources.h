#pragma once
#include <SDL.h>


struct Particle { // Particle structure to create multiple particle objects that possess coordinates on the screen and a unique speed. 

	double speed; // Speed of the particle.
	double t; // Variable used to change the x and y position of the particle.
	Particle();
	void update(); // update() updates the particle's position.
	void init(); // init() initializes the particles speed, position and initial angle of trajectory.
	double xPos; // x position of the particle.
	double yPos; // y position of the particle.

};

struct Screen {// Screen Class to manage SDL assets and processes.

public:

	SDL_Event event; // Event object to store and interpret events.
	SDL_Window* window; // Pointer to a window object; required for a window to be created using SDL.
	SDL_Renderer* renderer; // Pointer to a renderer object; required to draw pixel data using SDL.
	SDL_Texture* texture; // Pointer to a texture object; required to supply the renderer with data to draw to the screen using SDL.
	Uint32* buffer1; // Buffers provide individual pixel rgb data for texture object. Buffer1 is used as the finalized pixel data to be supplied to the texture.
	Uint32* buffer2; // Buffer2 is used to add box blur to the pixel data.

	~Screen();
	void close(); // Closes all SDL processes.
	void draw(); // Draws onto the screen.
	int init(); // initializes Screen object and processes.
	bool quitState(); // A boolean function that determines whether the "x" button has been pressed or not by the user to exit the window.
	void setPixel(int x, int y, Uint8 red, Uint8 blue, Uint8 green); // Sets an individual pixel's RGB data in buffer1.
	static const int HEIGHT = 900; // Constants that determine the dimensions of the window.
	static const int WIDTH = 1200;
	void blur(); // blur() uses a box blur formula to blur pixel data, which makes the end result look a lot more appealing.

};

class Swarm {// The swarm class manages a swarm of particles that occupy pixels on the screen.

private:

	Particle* particleArray; // Particle object pointer stores the address of the starting position in an array of particle objects.  

public:

	const static int NPARTICLES = 5000; // Number of particles that are on the screen. This number can be adjusted based on performance.
	Swarm();
	~Swarm();
	void update(); // Updates all particles' positions.
	Particle* getParticles();  // Returns the particle object array address.


};


