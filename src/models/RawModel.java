package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a raw 3D model used in rendering.
 * 
 * This class encapsulates information about a 3D model required for rendering,
 * including the Vertex Array Object (VAO) identifier and the number of vertices.
 * 
 * In a graphics application using OpenGL, the {@code vaoID} is used to identify
 * the VAO that stores vertex attribute configurations, while {@code vertexCount}
 * indicates how many vertices are in the model.</p>
 * 
 */
@Getter
@Setter
@AllArgsConstructor
public class RawModel {

    /**
     * The ID of the Vertex Array Object (VAO) associated with this model.
     * 
     * The VAO is used to store and manage vertex attribute configurations,
     * such as vertex positions, colors, and texture coordinates. It simplifies
     * the process of binding and unbinding vertex attributes during rendering.</p>
     * 
     * @see org.lwjgl.opengl.GL30#glGenVertexArrays()
     * @see org.lwjgl.opengl.GL30#glBindVertexArray(int)
     */
    private int vaoID;

    /**
     * The number of vertices in the model.
     * 
     * This value indicates how many vertices are used to represent the 3D model.
     * It is used in rendering functions to determine how many vertices to process
     * when drawing the model.</p>
     * 
     * @see org.lwjgl.opengl.GL11#glDrawArrays(int, int, int)
     */
    private int vertexCount;
}
