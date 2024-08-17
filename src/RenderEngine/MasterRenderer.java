package RenderEngine;

import org.lwjgl.opengl.GL11;

/**
 * The MasterRenderer class is responsible for preparing the rendering process in the game.
 * It sets up the background color and clears the screen before rendering each frame.
 * 
 * This class plays a crucial role in ensuring that each frame is rendered correctly
 * by clearing the previous frame's data and setting the background color.
 * 
 */
public class MasterRenderer {

    /**
     * {@link #prepare()} Prepares the renderer for a new frame by clearing the color buffer
     * and setting the background color.
     * 
     * This method should be called at the beginning of each rendering cycle
     * to ensure that the screen is cleared and ready for new content to be drawn.
     */
    public void prepare() {
        // Set the clear color to a light blue shade (RGB: 0.4, 0.7, 1.0)
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f);
        
        // Clear the color buffer, removing any previous content from the screen
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }
}
