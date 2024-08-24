package renderEngine;

import org.lwjgl.opengl.GL11;

import models.RawModel;

/**
 * The {@code MasterRenderer} class is responsible for preparing and managing the rendering process
 * in the game. It ensures that each frame is rendered correctly by setting up the background color
 * and clearing the screen before rendering new content.
 * 
 * This class plays a crucial role in preparing the rendering environment, clearing old frame data,
 * and ensuring the screen is ready for new content.
 * 
 * Key Methods:
 * 
 *     {@link #prepare()} - Clears the screen and sets the background color.</li>
 *     {@link #render(RawModel)} - Renders the given model using the {@code EntityRenderer}.</li>
 * 
 * 
 * @since 1.0
 * @version 1.0
 */
public class MasterRenderer {

    /**
     * Prepares the renderer for a new frame by clearing the color buffer
     * and setting the background color.
     * 
     * This method should be called at the beginning of each rendering cycle
     * to ensure that the screen is cleared and ready for new content to be drawn.
     */
    public void prepare() {
        // Set the clear color to a light blue shade with RGBA values (0.4, 0.7, 1.0, 1.0)
        // This determines the color used to clear the screen before rendering a new frame.
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f);
        
        // Clear the color buffer, removing any previous content from the screen.
        // This ensures that the new frame starts with a clean slate.
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Renders the given model using the {@code EntityRenderer}.
     * 
     * This method is responsible for invoking the rendering process
     * for the provided model, allowing it to be drawn on the screen.
     * 
     * @param model The {@link RawModel} to be rendered.
     */
    public void render(RawModel model) {
        // Delegate the rendering task to the {@code EntityRenderer}.
        // The {@code render()} method of {@code EntityRenderer} is called
        // to draw the provided model to the screen.
        EntityRenderer.render(model);
    }
}
