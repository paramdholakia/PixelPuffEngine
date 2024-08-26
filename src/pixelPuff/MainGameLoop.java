package pixelPuff;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

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
        
        float vertices[] = {
            // Triangle 1 (Top Left, Bottom Left, Bottom Right) Vertex Positions
            -0.5f,  0.5f, 0f, // Top Left
            -0.5f, -0.5f, 0f, // Bottom Left
             0.5f, -0.5f, 0f, // Bottom Right
            // Triangle 2 (Top Left, Bottom Right, Top Right) Vertex Positions
             0.5f,  0.5f, 0f, // Top Right
        };
        
        int indices[] = {0,1,2,
        				 2,3,0};
        
        float uv[] = {
        		0,0,
        		0,1,
        		1,1,
        		1,0
        };
        
        // Load the vertex data into a Vertex Array Object (VAO)
        RawModel model = loader.loadToVAO(vertices, indices, uv);
        ModelTexture texture = new ModelTexture(loader.loadTexture("dirtTex"));
        TexturedModel textureModel = new TexturedModel(model, texture);
        Entity entity = new Entity(textureModel, new Vector3f(0,0,0), 0, 0, 0, 1);
        
        
        
        // Create an instance of MasterRenderer to handle rendering tasks
        MasterRenderer renderer = new MasterRenderer();
        
        // Main game loop: runs until the display window is closed by the user
        while (!Display.isCloseRequested()) {
            // Prepare the renderer (clear the screen and set the background color)
            renderer.prepare();
            
            
            // Update the entity's position
            entity.increasePosition(0.001f, 0, 0);
            entity.increaseRotation(0, 0, 0.1f);
            entity.increaseScale(-0.001f);
            
            // Start the shader program            
            staticShader.start();
            
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