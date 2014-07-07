package model;

import utils.Util;

public class Tijolo extends ObjetoGrafico {
	
	private int durabilidade; //Quantidade de batidas...
	private int forca;
	
	public Tijolo(BBox bBox, float xTranslacao, float yTranslacao, float zTranslacao, int forca) {
		super(xTranslacao, yTranslacao, zTranslacao);
		
		setxEscala(2.5f);
		setyEscala(1.5f);
		setzEscala(1.5f);
		setbBox(bBox);
		this.forca = forca;
		setDurabilidade(forca);
	}

	public int getDurabilidade() {
		return durabilidade;
	}

	public void setDurabilidade(int durabilidade) {
		String image = "texture/tijolos/fraco.png";
		switch (forca) {
		case 3:
			if (durabilidade == 3) {
				image = "texture/tijolos/forte3.png";
			} else {
				if (durabilidade == 2) {
					image = "texture/tijolos/forte2.png";
				} else {
					image = "texture/tijolos/forte1.png";
				}
			}
			break;
		case 2:
			if (durabilidade == 2) {
				image = "texture/tijolos/medio2.png";
			} else {
				image = "texture/tijolos/medio1.png";
			}
			break;
		}
		setTexture(Util.loadImage(image));
		this.durabilidade = durabilidade;
	}
}
