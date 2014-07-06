package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

public class Util {

	public static Texture loadImage(String fileName) {
		try {
			InputStream stream = new FileInputStream(new File(fileName));
			TextureData data = TextureIO.newTextureData(stream, false, "png");
			return TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
