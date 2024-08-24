package Shaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ShaderProgram {

    int programID, vertexShaderID, fragmentShaderID;

    public ShaderProgram(String vertexFile, String fragmentFile) {
        programID = GL20.glCreateProgram();
        try {
            vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
            fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
    }

    protected abstract void bindAttribute();

    protected void bindAttribute(String variableName, int attribute) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    public void start() {
        GL20.glUseProgram(programID);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    private int loadShader(String shaderFile, int type) throws FileNotFoundException, IOException {
        StringBuilder shaderSource = new StringBuilder();

        try (InputStream inputStream = new FileInputStream(shaderFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not find shader source file! Path: " + shaderFile);
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);

        // Make sure shader actually compiled
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
            System.out.println("Couldn't compile shader");
        }
        return shaderID;
    }
}
