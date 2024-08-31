package pixelPuff;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.Entity;
import Shaders.StaticShader;
import Textures.ModelTexture;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;

public class MainGameLoop {

	public static Loader loader1 = null;

	public static StaticShader staticShader1 = null;

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		Loader loader = new Loader();
		loader1 = loader; // Assign the Loader to the static field for future use

		StaticShader staticShader = new StaticShader();
		staticShader1 = staticShader;
		float[] vertices = { -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f,

				-0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,

				0.5f, 0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, 0.5f,

				-0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f,

				-0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f,

				-0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f

		};

		int indices[] = { 0, 1, 3, 3, 1, 2, 4, 5, 7, 7, 5, 6, 8, 9, 11, 11, 9, 10, 12, 13, 15, 15, 13, 14, 16, 17, 19,
				19, 17, 18, 20, 21, 23, 23, 21, 22 };

		float[] uv = {
				0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0,
				1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0
		};

		// Load the vertex data into a Vertex Array Object (VAO)
		RawModel model = loader.loadToVAO(vertices, indices, uv);
		ModelTexture texture = new ModelTexture(loader.loadTexture("dirtTex"));
		TexturedModel textureModel = new TexturedModel(model, texture);
		Entity entity = new Entity(textureModel, new Vector3f(0, 0, -1), 0, 0, 0, 1);

		Camera camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);

		// Create an instance of MasterRenderer to handle rendering tasks
		MasterRenderer renderer = new MasterRenderer(staticShader);

		while (!Display.isCloseRequested()) {
			camera.move();

			// Prepare the renderer (clear the screen and set the background color)
			renderer.prepare();

			// Update the entity's position
			entity.increasePosition(0f, 0, 0f);
			entity.increaseScale(0);
			entity.increaseRotation(0, 1, 0f);

			// Start the shader program
			staticShader.start();

			staticShader.loadViewMatrix(camera);

			// Render the loaded model using the MasterRenderer
			renderer.render(entity, staticShader);
			staticShader.stop();

			// Update the display with the new frame and process any input events
			DisplayManager.updateDisplay();
		}

		// Close the display and clean up resources when the loop exits
		DisplayManager.closeDisplay();
	}
}