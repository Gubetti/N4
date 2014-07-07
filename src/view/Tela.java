package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.GLUT;

import model.Mundo;

public class Tela implements GLEventListener, KeyListener, MouseMotionListener {
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;
	private Mundo mundo;

	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glut = new GLUT();
		glDrawable.setGL(new DebugGL(gl));

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		float posLight[] = { 5.0f, 5.0f, 10.0f, 0.0f };
		gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, posLight, 0);
		gl.glEnable(GL.GL_LIGHT0);

		gl.glEnable(GL.GL_CULL_FACE);
		gl.glEnable(GL.GL_DEPTH_TEST);

		mundo = new Mundo(3);
	}
	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	    gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 0.1, 100);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
		glu.gluLookAt(mundo.getCamera().getxEye(), mundo.getCamera().getyEye(), mundo.getCamera().getzEye(), 
				mundo.getCamera().getxCenter(), mundo.getCamera().getyCenter(), mundo.getCamera().getzCenter(),
				mundo.getCamera().getxUp(), mundo.getCamera().getyUp(), mundo.getCamera().getzUp());
	}

	public void display(GLAutoDrawable drawable) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		glu.gluLookAt(mundo.getCamera().getxEye(), mundo.getCamera().getyEye(), mundo.getCamera().getzEye(), 
				mundo.getCamera().getxCenter(), mundo.getCamera().getyCenter(), mundo.getCamera().getzCenter(),
				mundo.getCamera().getxUp(), mundo.getCamera().getyUp(), mundo.getCamera().getzUp());

		drawAxis();
		mundo.desenhar(gl, glu, glut);

		gl.glFlush();
	}

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

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {

		case KeyEvent.VK_ESCAPE:
			System.exit(1);
		break;
		case KeyEvent.VK_1:
			mundo.getCamera().visao1();
			break;
		case KeyEvent.VK_2:
			mundo.getCamera().visao2();
			break;
		case KeyEvent.VK_3:
			mundo.getCamera().visao3();
			break;
		}
		glDrawable.display();	
	}

	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try {
			int dif = glDrawable.getWidth() / 2;
			mundo.getPlataforma().setxTranslacao((e.getX() - dif) * 0.05f);
			mundo.getPlataforma().setyTranslacao((e.getY() - dif) * -0.05f);
			glDrawable.display();
		} catch (NullPointerException npe) {
		}
	}
}
