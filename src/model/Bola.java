package model;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import utils.Util;

public class Bola extends ObjetoGrafico {

	private float raio;
	private final int LINHAS_VERTICAIS = 40;
	private final int LINHAS_HORIZONTAIS = 40;

	public Bola(float raio, float xTranslacao, float yTranslacao, float zTranslacao) {
		super(xTranslacao, yTranslacao, zTranslacao);
		this.raio = raio;

		setxEscala(1f);
		setyEscala(1f);
		setzEscala(1f);
		setTexture(Util.loadImage("texture/textureBall.png"));
	}

	public void desenhar(GL gl, GLU glu) {
		getTexture().enable();
		getTexture().bind();

		gl.glColor3f(1f, 1f, 1f);
		GLUquadric bola = glu.gluNewQuadric();
		// Define as propriedades da textura na bola
		glu.gluQuadricTexture(bola, true);
		glu.gluQuadricDrawStyle(bola, GLU.GLU_FILL);
		glu.gluQuadricNormals(bola, GLU.GLU_SMOOTH);
		glu.gluQuadricOrientation(bola, GLU.GLU_OUTSIDE);
		
		// Desenha a bola com a translação
		gl.glPushMatrix();
			gl.glTranslatef(getxTranslacao(), getyTranslacao(), getzTranslacao());
			gl.glScalef(getxEscala(), getyEscala(), getzEscala());
			glu.gluSphere(bola, raio, LINHAS_VERTICAIS, LINHAS_HORIZONTAIS);
		gl.glPopMatrix();
		
		// Elimina as propriedades textura da memoria
		glu.gluDeleteQuadric(bola);
	}

	public float getRaio() {
		return raio;
	}

	public void setRaio(float raio) {
		this.raio = raio;
	}
}
