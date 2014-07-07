package model;

import javax.media.opengl.GL;
import com.sun.opengl.util.GLUT;

import utils.Util;

public class Mesa extends ObjetoGrafico {

	public Mesa() {
		super(0f, 0f, 0f);

		// escala da parte inferior
		setxEscala(30.0f);
		setyEscala(0.1f);
		setzEscala(40.0f);
		// Se a bola passar do limite onde a plataforma se encontra é 1 vida perdida
		// então provavelmente não será utilizado o eixo Z da mesa pra controlar isso.
		// Eixo Y não é utilizado, já que todos os objetos ficam no mesmo eixo Y.
		setbBox(new BBox(-10.0f, 10.0f, -1.2f, 1.2f, -15.0f, 15.0f));
	}
	
	public void desenhar(GL gl, GLUT glut) {
		gl.glShadeModel(GL.GL_FLAT);
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
		gl.glColor3f( 1f, 1f, 1f );

		float corWhite[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		setTexture(Util.loadImage("texture/textureBrick.png"));
		getTexture().enable();
		getTexture().bind();
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corWhite, 0);
	    gl.glEnable(GL.GL_LIGHTING);
		gl.glPushMatrix();
			gl.glTranslatef(0f, -1.2f, 0f);
			gl.glScalef(getxEscala(), getyEscala(), getzEscala());
		    glut.glutSolidCube(1f);
        gl.glPopMatrix();

        
		setTexture(Util.loadImage("texture/tijolos/medio2.png"));
		getTexture().enable();
		getTexture().bind();
        gl.glPushMatrix();
			gl.glTranslatef(-(getxEscala() / 2), -0.2f, 0f);
			gl.glScalef(0.1f, 2.5f, getzEscala());
			glut.glutSolidCube(1f);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);

	
		setTexture(Util.loadImage("texture/tijolos/medio2.png"));
		getTexture().enable();
		getTexture().bind();
        gl.glPushMatrix();
			gl.glTranslatef(getxEscala() / 2, -0.2f, 0f);
			gl.glScalef(0.1f, 2.5f, getzEscala());
			glut.glutSolidCube(1f);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);
	

		setTexture(Util.loadImage("texture/tijolos/fraco.png"));
		getTexture().enable();
		getTexture().bind();
        gl.glPushMatrix();
			gl.glTranslatef(0f, -0.2f, -(getzEscala() / 2));
			gl.glScalef(getxEscala(), 2.5f, 0.1f);
			glut.glutSolidCube(1f);
		gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);
	}

}
