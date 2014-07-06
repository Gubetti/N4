package model;

import com.sun.opengl.util.texture.Texture;

public class ObjetoGrafico {

	private float xTranslacao;
	private float yTranslacao;
	private float zTranslacao;
	private float xEscala;
	private float yEscala;
	private float zEscala;
	private Texture textureTijolo;
	private BBox bBox;
	
	public float getxTranslacao() {
		return xTranslacao;
	}
	
	public void setxTranslacao(float xTranslacao) {
		this.xTranslacao = xTranslacao;
	}
	
	public float getyTranslacao() {
		return yTranslacao;
	}
	
	public void setyTranslacao(float yTranslacao) {
		this.yTranslacao = yTranslacao;
	}
	
	public float getzTranslacao() {
		return zTranslacao;
	}
	
	public void setzTranslacao(float zTranslacao) {
		this.zTranslacao = zTranslacao;
	}
	
	public float getxEscala() {
		return xEscala;
	}
	
	public void setxEscala(float xEscala) {
		this.xEscala = xEscala;
	}
	
	public float getyEscala() {
		return yEscala;
	}
	
	public void setyEscala(float yEscala) {
		this.yEscala = yEscala;
	}
	
	public float getzEscala() {
		return zEscala;
	}
	
	public void setzEscala(float zEscala) {
		this.zEscala = zEscala;
	}
	
	public Texture getTextureTijolo() {
		return textureTijolo;
	}
	
	public void setTextureTijolo(Texture textureTijolo) {
		this.textureTijolo = textureTijolo;
	}
	
	public BBox getbBox() {
		return bBox;
	}
	
	public void setbBox(BBox bBox) {
		this.bBox = bBox;
	}
}