package PixelPuff;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.MasterRenderer;

/**
 * The MainGameLoop class is the entry point of the PixelPuff voxel engine application.
 * It initializes the display, creates a renderer, and runs the main game loop
 * where rendering and display updates occur.
 * 
 * This class is responsible for managing the entire lifecycle of the game loop,
 * from initialization to termination.
 * 
 * @since 1.0
 * @version 1.0
 */
public class MainGameLoop {

    /**
     * The main method serves as the starting point of the application.
     * It sets up the display, initializes the renderer, and enters the main game loop
     * where the rendering process and display updates are handled.
     * 
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create and initialize the display window
        DisplayManager.createDisplay();
        
        // Create an instance of MasterRenderer to handle rendering tasks
        MasterRenderer renderer = new MasterRenderer();
        
        // Main game loop: continues running until the display window is closed
        while (!Display.isCloseRequested()) {
            // Prepare the renderer (clear the screen and set background color)
            renderer.prepare();
            // Update the display with the new frame and handle any input events
            DisplayManager.updateDisplay();
        }
        
        // Close the display and clean up resources when the game loop ends
        DisplayManager.closeDisplay();
    }
}