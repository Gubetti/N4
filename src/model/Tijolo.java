package model;

import javax.media.opengl.GL;

import utils.Util;

public class Tijolo extends ObjetoGrafico {
	
	private int durabilidade; //Quantidade de batidas...
	
	public Tijolo(BBox bBox) {
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
