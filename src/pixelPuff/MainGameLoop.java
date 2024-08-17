package pixelPuff;

import org.lwjgl.opengl.Display;

import models.RawModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;

/**
 * The {@code MainGameLoop} class serves as the entry point for the PixelPuff voxel engine application.
 * It handles the initialization of the display, creation of the renderer, and execution of the main game loop.
 * The game loop is responsible for rendering and updating the display continuously until the application exits.
 * 
 * This class is responsible for managing the overall lifecycle of the application, from setting up the display
 * to rendering graphics and handling user input.
 * 
 */
public class MainGameLoop {

    /**
     * A static {@code Loader} object for managing the loading of vertex data into VAOs.
     * This is a placeholder implementation that will be refined in future iterations.
     */
    public static Loader loader1 = null;

    /**
     * The main method initializes the display, creates a loader to handle VAOs, sets up vertex data for rendering,
     * and enters the main game loop. The loop continuously prepares the renderer, renders the model, and updates
     * the display until the user closes the window.
     * 
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create and initialize the display window with the DisplayManager
        DisplayManager.createDisplay();
        
        // Instantiate a Loader to handle the loading of vertex data into VAOs
        Loader loader = new Loader();
        loader1 = loader; // Assign the Loader to the static field for future use

        // Define the vertex positions for a square composed of two triangles
        float vertices[] = {
            // Triangle 1 (Top Left, Bottom Left, Bottom Right) Vertex Positions
            -0.5f,  0.5f, 0f, // Top Left
            -0.5f, -0.5f, 0f, // Bottom Left
             0.5f, -0.5f, 0f, // Bottom Right

            // Triangle 2 (Top Left, Bottom Right, Top Right) Vertex Positions
             0.5f, -0.5f, 0f, // Bottom Right
             0.5f,  0.5f, 0f, // Top Right
            -0.5f,  0.5f, 0f  // Top Left
        };

        // Load the vertex data into a Vertex Array Object (VAO)
        RawModel model = loader.loadToVAO(vertices);

        // Create an instance of MasterRenderer to handle rendering tasks
        MasterRenderer renderer = new MasterRenderer();
        
        // Main game loop: runs until the display window is closed by the user
        while (!Display.isCloseRequested()) {
            // Prepare the renderer (clear the screen and set the background color)
            renderer.prepare();

            // Render the loaded model using the MasterRenderer
            renderer.render(model);

            // Update the display with the new frame and process any input events
            DisplayManager.updateDisplay();
        }
        
        // Close the display and clean up resources when the loop exits
        DisplayManager.closeDisplay();
    }
}