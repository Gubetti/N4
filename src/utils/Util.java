package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

		desenhaSpaceInvader(tijolos, -10f);
		desenhaSpaceInvader(tijolos, -11.7f);
		desenhaSpaceInvader(tijolos, -13.4f);
		
		return tijolos;
	}
	
	private static void desenhaSpaceInvader(List<Tijolo> tijolos, float eixoZ) {
		Random random = new Random();
		// primeira linha
		tijolos.add(new Tijolo(null, -5.4f, 0f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -2.7f, 0f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 2.7f, 0f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 5.4f, 0f, eixoZ, random.nextInt(3) + 1));

		// segunda linha
		tijolos.add(new Tijolo(null, -13.5f, 1.7f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -8.1f, 1.7f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 8.1f, 1.7f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 13.5f, 1.7f, eixoZ, random.nextInt(3) + 1));

		// terceira linha
		tijolos.add(new Tijolo(null, -13.5f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -8.1f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -5.4f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -2.7f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 0f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 2.7f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 5.4f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 8.1f, 3.4f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 13.5f, 3.4f, eixoZ, random.nextInt(3) + 1));

		// quarta linha
		tijolos.add(new Tijolo(null, -13.5f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -10.8f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -8.1f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -5.4f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -2.7f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 0f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 2.7f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 5.4f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 8.1f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 10.8f, 5.1f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 13.5f, 5.1f, eixoZ, random.nextInt(3) + 1));
		
		// quinta linha
		tijolos.add(new Tijolo(null, -10.8f, 6.8f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -8.1f, 6.8f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -2.7f, 6.8f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 0f, 6.8f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 2.7f, 6.8f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 8.1f, 6.8f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 10.8f, 6.8f, eixoZ, random.nextInt(3) + 1));
		
		// sexta linha
		tijolos.add(new Tijolo(null, -8.1f, 8.5f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -5.4f, 8.5f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, -2.7f, 8.5f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 0f, 8.5f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 2.7f, 8.5f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 5.4f, 8.5f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 8.1f, 8.5f, eixoZ, random.nextInt(3) + 1));
		
		// setima linha
		tijolos.add(new Tijolo(null, -5.4f, 10.2f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 5.4f, 10.2f, eixoZ, random.nextInt(3) + 1));
		
		// oitava linha
		tijolos.add(new Tijolo(null, -8.1f, 11.9f, eixoZ, random.nextInt(3) + 1));
		tijolos.add(new Tijolo(null, 8.1f, 11.9f, eixoZ, random.nextInt(3) + 1));
	}
}
