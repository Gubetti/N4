package model;

import javax.media.opengl.GL;

import com.sun.opengl.util.texture.Texture;

public abstract class ObjetoGrafico {

	private float xTranslacao;
	private float yTranslacao;
	private float zTranslacao;
	private float xEscala;
	private float yEscala;
	private float zEscala;
	private Texture texture;
	private BBox bBox;
	
	public ObjetoGrafico(float xTranslacao, float yTranslacao, float zTranslacao) {
		this.xTranslacao = xTranslacao;
		this.yTranslacao = yTranslacao;
		this.zTranslacao = zTranslacao;
	}

	public void desenhar(GL gl) {
		float xMax, xMin, yMax, yMin, zMax, zMin;

		gl.glShadeModel(GL.GL_FLAT);
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
		gl.glColor3f( 1f, 1f, 1f );

		// largura
		xMax = 1f;
		xMin = 0f;
		// altura
		yMax = 1f;
		yMin = 0f;
		// profundidade
		zMax = 1f;
		zMin = 0;
		
		float corWhite[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		// Habilita e seta a textura
		texture.enable();
		texture.bind();

		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corWhite, 0);
	    gl.glEnable(GL.GL_LIGHTING);
		gl.glPushMatrix();
			// Translada primeiro com o centro do cubo para o ponto 0,0,0
			gl.glTranslatef(-(xEscala / 2), -(yEscala / 2), -(zEscala / 2));
			// Depois translada conforme os parâmentros
			gl.glTranslatef(xTranslacao, yTranslacao, zTranslacao);
			gl.glScalef(xEscala, yEscala, zEscala);
	        // Face frontal
	        gl.glBegin(GL.GL_QUADS);
	        gl.glNormal3f(0, 0, 1);
	        gl.glTexCoord2f(0.0f, 1.0f);
	        gl.glVertex3f(xMax, yMax, zMax);
	        gl.glTexCoord2f(1.0f, 1.0f);
	        gl.glVertex3f(xMin, yMax, zMax);
	        gl.glTexCoord2f(1.0f, 0.0f);
	        gl.glVertex3f(xMin, yMin, zMax);
	        gl.glTexCoord2f(0.0f, 0.0f);
	        gl.glVertex3f(xMax, yMin, zMax);
	        gl.glEnd();

	        // Face posterior
	        gl.glBegin(GL.GL_QUADS);
	        gl.glNormal3f(0, 0, -1);
	        gl.glTexCoord2f(1.0f, 0.0f);
	        gl.glVertex3f(xMax, yMax, zMin);
	        gl.glTexCoord2f(1.0f, 1.0f);
	        gl.glVertex3f(xMax, yMin, zMin);
	        gl.glTexCoord2f(0.0f, 1.0f);
	        gl.glVertex3f(xMin, yMin, zMin);
	        gl.glTexCoord2f(0.0f, 0.0f);
	        gl.glVertex3f(xMin, yMax, zMin);
	        gl.glEnd();

	        // Face lateral esquerda
	        gl.glBegin(GL.GL_QUADS);
	        gl.glNormal3f(-1, 0, 0);
	        gl.glTexCoord2f(0.0f, 0.0f);
	        gl.glVertex3f(xMin, yMax, zMax);
	        gl.glTexCoord2f(1.0f, 0.0f);
	        gl.glVertex3f(xMin, yMax, zMin);
	        gl.glTexCoord2f(1.0f, 1.0f);
	        gl.glVertex3f(xMin, yMin, zMin);
	        gl.glTexCoord2f(0.0f, 1.0f);
	        gl.glVertex3f(xMin, yMin, zMax);
	        gl.glEnd();

	        // Face lateral direita
	        gl.glBegin(GL.GL_QUADS);
	        gl.glNormal3f(1, 0, 0);
	        gl.glTexCoord2f(1.0f, 0.0f);
	        gl.glVertex3f(xMax, yMax, zMax);
	        gl.glTexCoord2f(1.0f, 1.0f);
	        gl.glVertex3f(xMax, yMin, zMax);
	        gl.glTexCoord2f(0.0f, 1.0f);
	        gl.glVertex3f(xMax, yMin, zMin);
	        gl.glTexCoord2f(0.0f, 0.0f);
	        gl.glVertex3f(xMax, yMax, zMin);
	        gl.glEnd();

	        // Face superior
	        gl.glBegin(GL.GL_QUADS);
	        gl.glNormal3f(0, 1, 0);
	        gl.glTexCoord2f(0.0f, 1.0f);
	        gl.glVertex3f(xMin, yMax, zMin);
	        gl.glTexCoord2f(0.0f, 0.0f);
	        gl.glVertex3f(xMin, yMax, zMax);
	        gl.glTexCoord2f(1.0f, 0.0f);
	        gl.glVertex3f(xMax, yMax, zMax);
	        gl.glTexCoord2f(1.0f, 1.0f);
	        gl.glVertex3f(xMax, yMax, zMin);
	        gl.glEnd();

	        // Face inferior
	        gl.glBegin(GL.GL_QUADS);
	        gl.glNormal3f(0, -1, 0);
	        gl.glTexCoord2f(1.0f, 1.0f);
	        gl.glVertex3f(xMin, yMin, zMin);
	        gl.glTexCoord2f(0.0f, 1.0f);
	        gl.glVertex3f(xMax, yMin, zMin);
	        gl.glTexCoord2f(0.0f, 0.0f);
	        gl.glVertex3f(xMax, yMin, zMax);
	        gl.glTexCoord2f(1.0f, 0.0f);
	        gl.glVertex3f(xMin, yMin, zMax);
	        gl.glEnd();

        gl.glPopMatrix();
		gl.glDisable(GL.GL_LIGHTING);
	}
	
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
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public BBox getbBox() {
		return bBox;
	}
	
	public void setbBox(BBox bBox) {
		this.bBox = bBox;
	}
}