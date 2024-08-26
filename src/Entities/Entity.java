package Entities;

import org.lwjgl.util.vector.Vector3f;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.TexturedModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entity {
	TexturedModel model;
	Vector3f position;
	float rotX, rotY, rotZ;
	float scale;
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
	
	public void increaseScale(float s) {
		this.scale += s;
	}
	
}
