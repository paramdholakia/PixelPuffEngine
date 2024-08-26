package renderEngine;

import org.lwjgl.opengl.GL11;

import Entities.Entity;
import Shaders.StaticShader;
public class MasterRenderer {

    public void prepare() {
        GL11.glClearColor(0.4f, 0.7f, 1.0f, 1.0f);
        
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void render(Entity entity, StaticShader shader) {
        EntityRenderer.render(entity, shader);
    }
}
