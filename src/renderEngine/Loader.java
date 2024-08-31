package renderEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import models.RawModel;


public class Loader {

	static ArrayList<Integer> vaos = new ArrayList<Integer>();

	static ArrayList<Integer> vbos = new ArrayList<Integer>();

	static ArrayList<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] positions, int[] indices, float[] uv) {
		int vaoID = createVAO();

		storeDataInAttributeList(positions, 0, 3);

		storeDataInAttributeList(uv, 1, 2);

		
		bindIndicesBuffer(indices);
		
		GL30.glBindVertexArray(0);

		return new RawModel(vaoID, indices.length );
	}

	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();

		vaos.add(vaoID);

		GL30.glBindVertexArray(vaoID);

		return vaoID;
	}
	
		
	public int loadTexture(String fileName) {
	    Texture texture = null;
	    try {
	        // Correct the path if needed
	        String filePath = "D:/Coding/Projects/PixelPuffEngine/resources/res/" + fileName + ".PNG";
	        texture = TextureLoader.getTexture("PNG", new FileInputStream(filePath));
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Failed to load texture: " + fileName);
	    }

	    if (texture == null) {
	        throw new RuntimeException("Texture could not be loaded: " + fileName);
	    }

	    int textureID = texture.getTextureID();
	    textures.add(textureID);
	    return textureID;
	}

	
	

	/**
	 * Stores vertex data in a Vertex Buffer Object (VBO) and binds it to the
	 * specified attribute in the VAO.
	 * 
	 * This method generates a VBO, stores the provided data in it, and specifies
	 * how the data should be interpreted by the vertex shader. The data is stored
	 * in a FloatBuffer before being uploaded to the GPU.
	 * 
	 * @param data            The vertex data to be stored in the VBO.
	 * @param attributeNumber The attribute index in the VAO to which the data is
	 *                        bound.
	 * @param dimensions      The number of components per vertex attribute (e.g., 3
	 *                        for x, y, z).
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

	IntBuffer storeDataInIntBuffer(int data[]) {
		IntBuffer intBuffer = BufferUtils.createIntBuffer(data.length);
		intBuffer.put(data);
		intBuffer.flip();
		return intBuffer;
	}

	private void bindIndicesBuffer(int indices[]) {
		int vboID = GL15.glGenBuffers();
		
		vbos.add(vboID);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);

		IntBuffer intBuffer = storeDataInIntBuffer(indices);
	
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL15.GL_STATIC_DRAW);
	
	}

	/**
	 * Converts an array of float data into a FloatBuffer.
	 * 
	 * This method allocates a FloatBuffer, puts the provided data into it, and then
	 * flips the buffer to prepare it for reading by OpenGL.
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
	 * This method deletes all VAOs and VBOs that have been created during the
	 * application's lifecycle. It ensures that all resources are properly released
	 * when they are no longer needed.
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
		
		// Delete all generated Textures
		for(int texture : textures) {
			GL11.glDeleteTextures(texture);
		}
	}
}
