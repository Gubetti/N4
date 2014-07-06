package model;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import utils.Util;

public class Mundo {

	private Camera camera;
	private Bola bola;
	private Plataforma plataforma;
	private List<Tijolo> tijolos;
	// private BBox bBox; //Será a melhor forma de controlar o limite da tela?
	private int vidas;

	public Mundo(int vidas) {
		this.camera = new Camera();
		this.bola = new Bola(1.0f, 0f, 5f, -5f);
		this.plataforma = new Plataforma(0f, -5f, 0f);
		this.vidas = vidas;
		this.tijolos = Util.carregarTijolos();
	}
	
	public void desenhar(GL gl, GLU glu) {
		bola.desenhar(gl, glu);
		plataforma.desenhar(gl);
		for (Tijolo tijolo : tijolos) {
			tijolo.desenhar(gl);
		}
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Bola getBola() {
		return bola;
	}

	public void setBola(Bola bola) {
		this.bola = bola;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public List<Tijolo> getTijolos() {
		return tijolos;
	}

	public void setTijolos(List<Tijolo> tijolos) {
		this.tijolos = tijolos;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
}