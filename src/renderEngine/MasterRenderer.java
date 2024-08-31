package renderEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import Entities.Entity;
import Shaders.StaticShader;

public class MasterRenderer {

    private Matrix4f projectionMatrix;
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.001f;  // A small positive value
    private static final float FAR_PLANE = 10000; // Far enough to encompass your scene

    public MasterRenderer(StaticShader shader) {
        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enable depth testing to handle object occlusion
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix); // Pass projection matrix to shader
        shader.stop();
    }

    // Prepare for rendering
    public void prepare() {
    	GL11.glEnable(GL11.GL_DEPTH_TEST); // Enable depth testing
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f); // Set clear color
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // Clear color and depth buffers
    }

    // Render an entity
    public void render(Entity entity, StaticShader shader) {
        EntityRenderer.render(entity, shader); // Render entity using the provided shader
    }

    // Create a projection matrix based on FOV, near and far planes
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float zp = FAR_PLANE + NEAR_PLANE;
        float zm = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -zp / zm;
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm;
        projectionMatrix.m33 = 0;
    }

}
