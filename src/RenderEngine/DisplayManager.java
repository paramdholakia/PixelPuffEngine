package RenderEngine;

// Import the necessary LWJGL classes
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 * The DisplayManager class is responsible for creating and managing the game window.
 * It handles the creation of the display, updating the display each frame, and closing the display.
 * 
 * This class includes three main methods:
 * - {@link #createDisplay()} - Initializes and sets up the window.
 * - {@link #updateDisplay()} - Synchronizes and updates the display, as well as handles keyboard input.
 * - {@link #closeDisplay()} - Destroys the display and exits the application.
 * 
 */
public class DisplayManager {

    // Constants for the window dimensions
    private static final int WIDTH = 800;  // Width of the display window in pixels
    private static final int HEIGHT = 600; // Height of the display window in pixels

    // Constant for the maximum frames per second (FPS) the display will run at
    private static final int FPSCAP = 144; // Frame rate cap

    /**
     * Creates the display window and initializes the OpenGL context.
     * Sets up the display mode, pixel format, and viewport.
     * 
     * @throws LWJGLException if there is an issue initializing the LWJGL display
     */
    public static void createDisplay() {
        // ContextAttribs defines the context attributes for the OpenGL version
        ContextAttribs attribs = new ContextAttribs(3, 2)
                                    .withForwardCompatible(true)  // Makes the context forward-compatible
                                    .withProfileCore(true);       // Requests the core profile

        try {
            // Set the display mode to the specified width and height
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            // Create the display with the specified pixel format and context attributes
            Display.create(new PixelFormat(), attribs);
            // Set the title of the window
            Display.setTitle("PixelPuff's First Title!");
            // Set the display to fullscreen mode
            Display.setFullscreen(true);

            // Define the OpenGL viewport dimensions based on the display width and height
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());

        } catch (LWJGLException e) {
            // Handle any exceptions related to LWJGL (Lightweight Java Game Library)
            e.printStackTrace();
        } catch (Exception e) {
            // Handle any other exceptions that may occur during display creation
            System.out.println("Error in DisplayManager.createDisplay()");
            e.printStackTrace();
        }

        // Grab the mouse input (hides the cursor and locks it to the window)
        Mouse.setGrabbed(true);
    }

    /**
     * Updates the display at the specified FPS cap and handles input events.
     * Syncs the display to the FPS cap and checks for keyboard input.
     */
    public static void updateDisplay() {
        // Synchronize the display to the FPS cap, ensuring it doesn't run faster than the specified frame rate
        Display.sync(FPSCAP);
        // Update the display with the latest frame (swap buffers)
        Display.update();

        // Check for any new keyboard events
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) { // Check if a key was just pressed down

                // If the ESC key is pressed, close the display
                if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                    closeDisplay();
                }

                // Toggle mouse grabbing with the 'E' key
                if (Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
                    // If 'E' is pressed and the mouse is grabbed, release it
                    Mouse.setGrabbed(false);
                } else if (Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
                    // If 'E' is pressed and the mouse is not grabbed, grab it
                    Mouse.setGrabbed(true);
                }
            }
        }
    }

    /**
     * Closes the display and exits the application.
     * Destroys the display resources and terminates the program.
     */
    public static void closeDisplay() {
        // Destroy the display, releasing any resources it holds
        Display.destroy();
        // Exit the application
        System.exit(0);
    }
}
