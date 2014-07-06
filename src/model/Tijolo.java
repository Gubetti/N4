package model;

import javax.media.opengl.GL;

import utils.Util;

public class Tijolo extends ObjetoGrafico {
	
	private int durabilidade; //Quantidade de batidas...
	
	public Tijolo(BBox bBox, float xTranslacao, float yTranslacao, float zTranslacao) {
		super(xTranslacao, yTranslacao, zTranslacao);
		
		setxEscala(2.5f);
		setyEscala(1.5f);
		setzEscala(1.5f);
		setbBox(bBox);
		setTexture(Util.loadImage("texture/textureBrick.png"));
	}
	
	public void desenhar(GL gl) {
		
	}

	public int getDurabilidade() {
		return durabilidade;
	}

	public void setDurabilidade(int durabilidade) {
		this.durabilidade = durabilidade;
	}
}
