package renderEngine;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL11;

import models.RawModel;

/**
 * The {@code EntityRenderer} class is responsible for rendering entities in the scene.
 * It handles the process of drawing models by binding the appropriate Vertex Array Object (VAO),
 * enabling vertex attributes, issuing draw calls, and then cleaning up the state.
 * 
 * <p>This class provides a method to render a given {@link RawModel}.</p>
 * 
 * @since 1.0
 * @version 1.0
 */
public class EntityRenderer {

    /**
     * Renders the provided model by binding its VAO and issuing a draw call.
     * 
     * <p>This method binds the VAO associated with the given {@link RawModel},
     * enables the vertex attributes for the shader, performs the drawing operation
     * with the specified mode, and then disables the vertex attributes and unbinds
     * the VAO to restore the OpenGL state.</p>
     * 
     * @param rawModel The {@link RawModel} to be rendered. It contains the VAO ID and vertex count.
     */
    public static void render(RawModel rawModel) {
        // Bind the VAO associated with the model
        GL30.glBindVertexArray(rawModel.getVaoID());
        
        // Enable the vertex attribute at index 0 (typically used for positions)
        GL20.glEnableVertexAttribArray(0);
        
        // Issue a draw call to render the model using GL_TRIANGLES mode
        // Start from vertex index 0 and draw up to the specified vertex count
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, rawModel.getVertexCount());
        
        // Disable the vertex attribute at index 0
        GL20.glDisableVertexAttribArray(0);
        
        // Unbind the VAO to avoid affecting other rendering operations
        GL30.glBindVertexArray(0);
    }
}
