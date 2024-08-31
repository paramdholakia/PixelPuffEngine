package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Camera {

	Vector3f position;
	float rotX, rotY, rotZ;
	float speed = 0.50f;
	float sensitivity = 0.50f;
	float moveAt = 0.50f;
	
	public Camera(Vector3f position, float rotX, float rotY, float rotZ) {
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }
	
	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveAt = -speed;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveAt = speed;
        }
        else {
            moveAt = 0;
        }
		
		rotX += -Mouse.getDY() * sensitivity;
		rotY += Mouse.getDX() * sensitivity;
	
		
		float dx = (float) -(moveAt * Math.sin(Math.toRadians(rotY)));
		float dy = (float) (moveAt * Math.sin(Math.toRadians(rotX)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotY)));

		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
}