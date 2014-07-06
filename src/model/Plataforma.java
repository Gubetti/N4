package model;

import utils.Util;

public class Plataforma extends ObjetoGrafico {

	public Plataforma(float xTranslacao, float yTranslacao, float zTranslacao) {
		super(xTranslacao, yTranslacao, zTranslacao);

		setxEscala(3.5f);
		setyEscala(1.7f);
		setzEscala(0.5f);
		setTexture(Util.loadImage("texture/texturePlatform.png"));
	}

}
