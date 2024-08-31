package Shaders;

import org.lwjgl.util.vector.Matrix4f;

import Entities.Camera;
import ToolBox.Maths;

public class StaticShader extends ShaderProgram {
    private static final String vertexFile = "D:/Coding/Projects/PixelPuffEngine/src/Shaders/vertexShader.txt";
    private static final String fragmentFile = "D:/Coding/Projects/PixelPuffEngine/src/Shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    
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
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
	}
}
