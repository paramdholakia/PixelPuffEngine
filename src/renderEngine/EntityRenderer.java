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
 * This class provides a method to render a given {@link RawModel}.
 * 
 */
public class EntityRenderer {

    /**
     * Renders the provided model by binding its VAO and issuing a draw call.
     * 
     * This method binds the VAO associated with the given {@link RawModel},
     * enables the vertex attributes for the shader, performs the drawing operation
     * with the specified mode, and then disables the vertex attributes and unbinds
     * the VAO to restore the OpenGL state.
     * 
     * @param rawModel The {@link RawModel} to be rendered. It contains the VAO ID and vertex count.
     */
    public static void render(RawModel rawModel) {
        // Bind the VAO associated with the model
        GL30.glBindVertexArray(rawModel.getVaoID());
        
        // Enable the vertex attribute at index 0 (typically used for positions)
        GL20.glEnableVertexAttribArray(0);
        
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        
        // Disable the vertex attribute at index 0
        GL20.glDisableVertexAttribArray(0);
        
        // Unbind the VAO to avoid affecting other rendering operations
        GL30.glBindVertexArray(0);
    }
}
