package models;

import Textures.ModelTexture;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TexturedModel {
	RawModel model;
	ModelTexture texture;
}
