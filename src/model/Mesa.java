package model;

import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;

import utils.Util;

public class Mesa extends ObjetoGrafico {
	
	private float altura;
	// dif = abaixar a mesa para não precisa abaixar todos os outros objetos.
	private float dif = 1.2f;

	public Mesa(float altura) {
		super(0f, 0f, 0f);

		// escala da parte inferior
		setxEscala(30.0f);
		setyEscala(0.1f);
		setzEscala(40.0f);
		
		this.altura = altura;
		// Se a bola passar do limite onde a plataforma se encontra é 1 vida perdida
		// então provavelmente não será utilizado o eixo Z da mesa pra controlar isso.
		// A bola e a plataforma tem que estar dentro da Bbox da Mesa.
		setbBox(new BBox(-(getxEscala()/2), (getxEscala()/2), -dif, getAltura() - dif, -(getzEscala()/2), (getzEscala()/2)));
	}
	
	public void desenhar(GL gl, GLUT glut) {
		gl.glShadeModel(GL.GL_FLAT);
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
		gl.glColor3f( 1f, 1f, 1f );

		float corWhite[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		
		// base
		setTexture(Util.loadImage("texture/textureBrick.png"));
		getTexture().enable();
		getTexture().bind();
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corWhite, 0);
	    gl.glEnable(GL.GL_LIGHTING);
		gl.glPushMatrix();
			gl.glTranslatef(0f, -dif, 0f);
			gl.glScalef(getxEscala(), getyEscala(), getzEscala());
		    glut.glutSolidCube(1f);
        gl.glPopMatrix();

        // esquerda
		setTexture(Util.loadImage("texture/tijolos/medio2.png"));
		getTexture().enable();
		getTexture().bind();
        gl.glPushMatrix();
			gl.glTranslatef(-(getxEscala() / 2), (getAltura() / 2) - dif, 0f);
			gl.glScalef(0.1f, getAltura(), getzEscala());
			glut.glutSolidCube(1f);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);

		// direita
		setTexture(Util.loadImage("texture/tijolos/medio2.png"));
		getTexture().enable();
		getTexture().bind();
        gl.glPushMatrix();
			gl.glTranslatef(getxEscala() / 2, (getAltura() / 2) - dif, 0f);
			gl.glScalef(0.1f, getAltura(), getzEscala());
			glut.glutSolidCube(1f);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);
	
		// fundo
		setTexture(Util.loadImage("texture/tijolos/fraco.png"));
		getTexture().enable();
		getTexture().bind();
        gl.glPushMatrix();
			gl.glTranslatef(0f, (getAltura() / 2) - dif, -(getzEscala() / 2));
			gl.glScalef(getxEscala(), getAltura(), 0.1f);
			glut.glutSolidCube(1f);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setAltura(float altura) {
		this.altura = altura;
	}

}
