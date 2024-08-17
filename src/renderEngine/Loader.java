package renderEngine;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import models.RawModel;

/**
 * The {@code Loader} class is responsible for managing and loading vertex data
 * into OpenGL for rendering. It facilitates the creation of Vertex Array Objects (VAOs)
 * and Vertex Buffer Objects (VBOs), which store and manage vertex data.
 * 
 * <p>The class provides methods to create VAOs, store vertex data in VBOs, and
 * clean up resources when they are no longer needed.</p>
 * 
 * <p>Important fields and methods:
 * <ul>
 *   <li>{@code vaos} - A list to keep track of generated VAOs.</li>
 *   <li>{@code vbos} - A list to keep track of generated VBOs.</li>
 *   <li>{@code loadToVAO(float[] positions)} - Loads vertex positions into a VAO.</li>
 *   <li>{@code createVAO()} - Creates a new VAO and returns its ID.</li>
 *   <li>{@code storeDataInAttributeList(float[] data, int attributeNumber, int dimensions)} - Stores vertex data in a VBO and binds it to the VAO.</li>
 *   <li>{@code storeDataInFloatBuffer(float[] data)} - Converts vertex data into a FloatBuffer.</li>
 *   <li>{@code cleanUp()} - Deletes all generated VAOs and VBOs.</li>
 * </ul>
 * </p>
 * 
 * @since 1.0
 * @version 1.0
 */
public class Loader {
	
    /**
     * List of generated Vertex Array Object (VAO) IDs.
     * 
     * <p>This list keeps track of all VAOs that have been created, allowing for
     * proper cleanup when the application exits or when VAOs are no longer needed.</p>
     */
    static ArrayList<Integer> vaos = new ArrayList<Integer>();

    /**
     * List of generated Vertex Buffer Object (VBO) IDs.
     * 
     * <p>This list keeps track of all VBOs that have been created, allowing for
     * proper cleanup when the application exits or when VBOs are no longer needed.</p>
     */
    static ArrayList<Integer> vbos = new ArrayList<Integer>();
    
    /**
     * Loads vertex positions into a new Vertex Array Object (VAO).
     * 
     * <p>This method creates a VAO, stores the provided vertex positions in a Vertex
     * Buffer Object (VBO), and binds the VBO to the VAO. It returns a {@link RawModel}
     * instance containing the VAO ID and the number of vertices.</p>
     * 
     * @param positions An array of vertex positions in 3D space.
     * @return A {@link RawModel} instance containing the VAO ID and the vertex count.
     */
    public RawModel loadToVAO(float[] positions) {
        // Create a new VAO and get its ID
        int vaoID = createVAO();
        
        // Store vertex positions in a VBO and bind it to the VAO
        storeDataInAttributeList(positions, 0, 3);
        
        // Unbind the VAO to avoid affecting other VAOs
        GL30.glBindVertexArray(0);
        
        // Return a RawModel instance with the VAO ID and vertex count
        return new RawModel(vaoID, positions.length / 3);
    }
    
    /**
     * Creates a new Vertex Array Object (VAO) and returns its ID.
     * 
     * <p>This method generates a VAO, binds it to the current context, and adds its
     * ID to the list of VAOs for tracking and cleanup purposes.</p>
     * 
     * @return The ID of the newly created VAO.
     */
    private int createVAO() {
        // Generate a new VAO and get its ID
        int vaoID = GL30.glGenVertexArrays();
        
        // Add the VAO ID to the list for tracking
        vaos.add(vaoID);
        
        // Bind the new VAO so that subsequent vertex attribute calls affect it
        GL30.glBindVertexArray(vaoID);
        
        // Return the ID of the created VAO
        return vaoID;
    }
    
    /**
     * Stores vertex data in a Vertex Buffer Object (VBO) and binds it to the
     * specified attribute in the VAO.
     * 
     * <p>This method generates a VBO, stores the provided data in it, and specifies
     * how the data should be interpreted by the vertex shader. The data is stored
     * in a FloatBuffer before being uploaded to the GPU.</p>
     * 
     * @param data The vertex data to be stored in the VBO.
     * @param attributeNumber The attribute index in the VAO to which the data is bound.
     * @param dimensions The number of components per vertex attribute (e.g., 3 for x, y, z).
     */
    private void storeDataInAttributeList(float[] data, int attributeNumber, int dimensions) {
        // Generate a new VBO and get its ID
        int vboID = GL15.glGenBuffers();
        
        // Add the VBO ID to the list for tracking
        vbos.add(vboID);
        
        // Bind the new VBO so that subsequent buffer operations affect it
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        
        // Convert vertex data to a FloatBuffer
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        
        // Upload the FloatBuffer data to the GPU
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        
        // Define the layout of the vertex data (e.g., position attributes)
        GL20.glVertexAttribPointer(attributeNumber, dimensions, GL11.GL_FLOAT, false, 0, 0);
        
        // Unbind the VBO to avoid affecting other VBOs
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }
    
    /**
     * Converts an array of float data into a FloatBuffer.
     * 
     * <p>This method allocates a FloatBuffer, puts the provided data into it, and then
     * flips the buffer to prepare it for reading by OpenGL.</p>
     * 
     * @param data The array of float data to be stored in the FloatBuffer.
     * @return The FloatBuffer containing the provided data.
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        // Create a FloatBuffer with a capacity equal to the length of the data array
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        
        // Put the float data into the buffer
        buffer.put(data);
        
        // Flip the buffer to prepare it for reading
        buffer.flip();
        
        // Return the populated FloatBuffer
        return buffer;
    }
    
    /**
     * Cleans up all generated VAOs and VBOs.
     * 
     * <p>This method deletes all VAOs and VBOs that have been created during the
     * application's lifecycle. It ensures that all resources are properly released
     * when they are no longer needed.</p>
     */
    public void cleanUp() {
        // Delete all generated VAOs
        for (int vao : vaos) {
            GL30.glDeleteVertexArrays(vao);
        }
        
        // Delete all generated VBOs
        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }
    }
}
