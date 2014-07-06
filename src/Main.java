/**
 * Objetivo: usar as transformações geométricas: translação, escala e rotação.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
//import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

public class Main implements GLEventListener, KeyListener {
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;
	private double xEye, yEye, zEye;
	private double xCenter, yCenter, zCenter;
	private final double xUp = 0.0f, yUp = 1.0f, zUp = 0.0f;
	private Texture textureBola;
	private Texture texturePlataforma;
	private Texture textureTijolo;
	
	// 0 = X, 1 = Y, 2 = Z
	private float translacao[] = new float[3];
	private float escala[] = new float[3];
	
//    private float corRed[] = { 1.0f, 0.0f, 0.0f, 1.0f };
//    private float corGreen[] = { 0.0f, 1.0f, 0.0f, 1.0f };
//    private float corBlue[] = { 0.0f, 0.0f, 1.0f, 1.0f };
    private float corWhite[] = { 1.0f, 1.0f, 1.0f, 1.0f };
//    private float corBlack[] = { 0.0f, 0.0f, 0.0f, 1.0f };

	//private static final int SIZE = 2;

	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glut = new GLUT();
		glDrawable.setGL(new DebugGL(gl));

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		xEye = 20.0f;
		yEye = 20.0f;
		zEye = 20.0f;
		xCenter = 0.0f;
		yCenter = 0.0f;
		zCenter = 0.0f;
		
	    float posLight[] = { 5.0f, 5.0f, 10.0f, 0.0f };
	    gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0);
	    gl.glEnable(GL.GL_LIGHT0);

	    gl.glEnable(GL.GL_CULL_FACE);
	    gl.glEnable(GL.GL_DEPTH_TEST);
	    
		// inicializa as imagens
	    textureBola = loadImage("texture/textureBall.png");
	    texturePlataforma = loadImage("texture/texturePlatform.png");
	    textureTijolo = loadImage("texture/textureBrick.png");
	}

	public Texture loadImage(String fileName) {
		try {
			InputStream stream = new FileInputStream(new File(fileName));
			TextureData data = TextureIO.newTextureData(stream, false, "png");
			return TextureIO.newTexture(data);
		} catch (IOException exc) {
			exc.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	    gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 0.1, 100);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
		glu.gluLookAt(xEye, yEye, zEye, xCenter, yCenter, zCenter, xUp, yUp, zUp);
		Debug();
	}

	public void display(GLAutoDrawable drawable) {
		// gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
		glu.gluLookAt(xEye, yEye, zEye, xCenter, yCenter, zCenter, xUp, yUp, zUp);

		drawAxis();
//		drawCube(2.0f,2.0f,2.0f);
//		gl.glPushMatrix();
//		gl.glTranslated(0.0f, 0.0f, 1.5f);
//			drawCube(1.0f,1.0f,1.0f);
//		gl.glPopMatrix();
//		drawCylinder(gl, 1.0f, 1.0f, 1.0f); // cilindro branco
		
		// evite aumetar as linhas da bola, ocupa muito processamento
		setTranslacao(0, 5, 0);
		setEscala(1, 1, 1);
		desenhaBola(10, 10);
		setTranslacao(0, 0, 0);
		setEscala(3.5f, 1.7f, 0.5f);
		desenhaCubo(texturePlataforma);
		setTranslacao(0, 2, 0);
		setEscala(2.5f, 1.5f, 1.5f);
		desenhaCubo(textureTijolo);
		
		gl.glFlush();
	}
	
	private void desenhaBola(int linhasVerticais, int linhasHorizontais) {
		// Habilita a textura
        textureBola.enable();
        textureBola.bind();
        
		gl.glColor3f(1f, 1f, 1f);
        GLUquadric bola = glu.gluNewQuadric();
        // Define as propriedades da textura na bola
        glu.gluQuadricTexture(bola, true);
        glu.gluQuadricDrawStyle(bola, GLU.GLU_FILL);
        glu.gluQuadricNormals(bola, GLU.GLU_SMOOTH);
        glu.gluQuadricOrientation(bola, GLU.GLU_OUTSIDE);
        // Desenha a bola com a translação
		gl.glPushMatrix();
			gl.glTranslatef(translacao[0], translacao[1], translacao[2]);
			gl.glScalef(escala[0], escala[1], escala[2]);
	        glu.gluSphere(bola, 1.0f, linhasVerticais, linhasHorizontais);
		gl.glPopMatrix();
		// Elimina as propriedades textura da memoria
        glu.gluDeleteQuadric(bola);
	}
	
	private void desenhaCubo(Texture texture) {
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
		
		// Habilita e seta a textura
		texture.enable();
		texture.bind();

		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corWhite, 0);
	    gl.glEnable(GL.GL_LIGHTING);
		gl.glPushMatrix();
			// Translada primeiro com o centro do cubo para o ponto 0,0,0
			gl.glTranslatef(-(escala[0] / 2), -(escala[1] / 2), -(escala[2] / 2));
			// Depois translada conforme os parâmentros
			gl.glTranslatef(translacao[0], translacao[1], translacao[2]);
			gl.glScalef(escala[0], escala[1], escala[2]);
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
	
//	private void drawCube(float xS, float yS, float zS) {
//	    gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, corRed, 0);
//	    gl.glEnable(GL.GL_LIGHTING);
//
//		gl.glPushMatrix();
//			gl.glScalef(xS,yS,zS);
//			glut.glutSolidCube(1.0f);
//		gl.glPopMatrix();
//		
//		gl.glDisable(GL.GL_LIGHTING);
//	}

//	private void drawCylinder(GL gl, float red, float green, float blue) {
//		gl.glColor3f(red, green, blue);
//
//		GLUquadric quad;
//		quad = glu.gluNewQuadric();
//		glu.gluQuadricDrawStyle(quad, GLU.GLU_FILL);
//		glu.gluQuadricOrientation(quad, GLU.GLU_OUTSIDE);
//		glu.gluQuadricNormals(quad, GLU.GLU_SMOOTH);
//		glu.gluQuadricTexture(quad, true);
//		glu.gluCylinder(quad, 0.25f, 0.25f, 1.0f, 60, 30);
//		glu.gluDeleteQuadric(quad);
//	}

	public void drawAxis() {
		// eixo X - Red
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(10.0f, 0.0f, 0.0f);
		gl.glEnd();
		// eixo Y - Green
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f, 10.0f, 0.0f);
		gl.glEnd();
		// eixo Z - Blue
		gl.glColor3f(0.0f, 0.0f, 1.0f);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0.0f, 0.0f, 0.0f);
		gl.glVertex3f(0.0f, 0.0f, 10.0f);
		gl.glEnd();
	}
	
	public void setTranslacao(float X, float Y, float Z) {
		translacao[0] = X;
		translacao[1] = Y;
		translacao[2] = Z;
	}
	
	public void setEscala(float X, float Y, float Z) {
		escala[0] = X;
		escala[1] = Y;
		escala[2] = Z;
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:
			System.exit(1);
		break;
		case KeyEvent.VK_1:
			xEye = 20.0f;
			yEye = 20.0f;
			zEye = 20.0f;
		break;
		//De tras
		case KeyEvent.VK_2:
			xEye = 0.0f;
			yEye = 0.0f;
			zEye = 20.0f;
			break;
		//De lado
		case KeyEvent.VK_3:
			xEye = 20.0f;
			yEye = 0.0f;
			zEye = 0.0f;
			break;
		//De cima
		case KeyEvent.VK_4:
			xEye = 0.0f;
			yEye = 20.0f;
			zEye = 0.0001f;
			break;
		}
		glDrawable.display();	
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void Debug() {
	}

}
