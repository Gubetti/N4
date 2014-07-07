package model;

import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

import utils.Util;

public class Mundo {

	private Camera camera;
	private Bola bola;
	private Plataforma plataforma;
	private List<Tijolo> tijolos;
	private Mesa mesa;
	private int vidas;

	public Mundo(int vidas) {
		this.camera = new Camera();
		this.vidas = vidas;
		this.tijolos = Util.carregarTijolos();
		origens();
	}
	
	public void origens() {
		this.bola = new Bola(1.0f, 0f, 5f, -2f);
		this.plataforma = new Plataforma(0f, 5f, 18f);
		this.mesa = new Mesa(15f);
	}
	
	public void desenhar(GL gl, GLU glu, GLUT glut) {
		bola.desenhar(gl, glu);
		plataforma.desenhar(gl);
		mesa.desenhar(gl, glut);
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
	
	public Mesa getMesa() {
		return mesa;
	}
	
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
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