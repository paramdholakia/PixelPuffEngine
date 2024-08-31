package Shaders;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {

    private int programID, vertexShaderID, fragmentShaderID;
    private FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(String vertexFile, String fragmentFile) {
        programID = GL20.glCreateProgram();
        try {
            vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
            fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Shader file loading failed", e);
        }

        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        GL20.glLinkProgram(programID);
        checkProgramError(programID, GL20.GL_LINK_STATUS, "Program linking failed");

        GL20.glValidateProgram(programID);
        checkProgramError(programID, GL20.GL_VALIDATE_STATUS, "Program validation failed");

        getAllUniformLocations(); // Retrieve locations for all uniforms
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void getAllUniformLocations();
    protected abstract void bindAttribute();

    protected int getUniformLocation(String varName) {
        return GL20.glGetUniformLocation(programID, varName);
    }

    // Bind attribute locations
    protected void bindAttribute(String variableName, int attribute) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    // Load values into shader uniforms
    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void load2DVector(int location, Vector2f vector) {
        GL20.glUniform2f(location, vector.x, vector.y);
    }

    protected void load3DVector(int location, Vector3f vector) {
        GL20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.store(floatBuffer);
        floatBuffer.flip();
        GL20.glUniformMatrix4(location, false, floatBuffer);
    }

    protected void loadBoolean(int location, boolean value) {
        GL20.glUniform1f(location, value ? 1 : 0);
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

    // Load and compile shader
    private int loadShader(String shaderFile, int type) throws IOException {
        StringBuilder shaderSource = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(shaderFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        }

        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        checkShaderError(shaderID, "Shader compilation failed");
        return shaderID;
    }

    // Check for shader compilation errors
    private void checkShaderError(int shaderID, String errorMessage) {
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println(GL20.glGetShaderInfoLog(shaderID, 1000));
            throw new RuntimeException(errorMessage);
        }
    }

    // Check for program linking and validation errors
    private void checkProgramError(int programID, int statusType, String errorMessage) {
        if (GL20.glGetProgrami(programID, statusType) == GL11.GL_FALSE) {
            System.err.println(GL20.glGetProgramInfoLog(programID, 1000));
            throw new RuntimeException(errorMessage);
        }
    }
}
