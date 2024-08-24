package Shaders;


public class StaticShader extends ShaderProgram{
	private static final String vertexFile = "D:/Coding/Projects/PixelPuffEngine/src/Shaders/vertexShader.txt"; 
	private static final String fragmentFile = "D:/Coding/Projects/PixelPuffEngine/src/Shaders/fragmentShader.txt";
	
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}
	
	@Override
	protected void bindAttribute() {
		super.bindAttribute("position",0);
		
	}
	
	
	
}
