package Shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {
	private static final String vertexFile = "D:/Coding/Projects/PixelPuffEngine/src/Shaders/vertexShader.txt";
	private static final String fragmentFile = "D:/Coding/Projects/PixelPuffEngine/src/Shaders/fragmentShader.txt";

	int location_transformationMatrix;

	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttribute() {
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);

	}

	@Override
	protected void getAllUniformLocations() {

		location_transformationMatrix = super.getUniformLocation("transformationMatrix");

	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

}
