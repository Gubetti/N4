package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.Tijolo;

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
	
	public static List<Tijolo> carregarTijolos() {
		List<Tijolo> tijolos = new ArrayList<Tijolo>();
		tijolos.add(new Tijolo(null, 0f, 1f, 0f));
		tijolos.add(new Tijolo(null, 0f, 2.5f, 0f));
		tijolos.add(new Tijolo(null, 0f, 4f, 0f));
		return tijolos;
	}
}
