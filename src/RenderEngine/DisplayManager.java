package RenderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/*
 * 
 * DisplayManager class is responsible for creating the window 
 * and managing the display of the game.
 * 
 * 
 * We have 3 main methods:
 * createDisplay() - creates the window
 * updateDisplay() - updates the display
 * closeDisplay() - closes the display
 * 
 * */
public class DisplayManager {

	// WIDTH and HEIGHT of the window (in pixels)
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	// Frames Per Second Cap
	private static final int FPSCAP = 144;

	public static void createDisplay() {

		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("PixelPuff's First Title!");
			Display.setFullscreen(true);

			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());

		} catch (LWJGLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error in DisplayManager.createDisplay()");
			e.printStackTrace();
		}
		
		Mouse.setGrabbed(true);

	}

	public static void updateDisplay() {

		Display.sync(FPSCAP);
		Display.update();

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					closeDisplay();
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_E) && Mouse.isGrabbed()) {
					Mouse.setGrabbed(false);
				} else if (Keyboard.isKeyDown(Keyboard.KEY_E) && !Mouse.isGrabbed()) {
					Mouse.setGrabbed(true);
				}
			}
		}

	}

	public static void closeDisplay() {
		Display.destroy();
		System.exit(0);
	}

}
