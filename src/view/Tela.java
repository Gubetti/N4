package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JOptionPane;

import com.sun.opengl.util.GLUT;

import model.Mundo;
import model.ObjetoGrafico;
import model.Tijolo;

public class Tela implements GLEventListener, KeyListener, MouseMotionListener {
	private GL gl;
	private GLU glu;
	private GLUT glut;
	private GLAutoDrawable glDrawable;
	private Mundo mundo;
	private float estadoDeslocamentoBolaX;
	private float estadoDeslocamentoBolaY;
	private float estadoDeslocamentoBolaZ;

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
		estadoDeslocamentoBolaX = 0;
		estadoDeslocamentoBolaY = 0;
		estadoDeslocamentoBolaZ = 0.5f;
		JOptionPane.showMessageDialog(null, "Pressione para começar.\nQuantidade de vidas: " + mundo.getVidas(), "Início", JOptionPane.INFORMATION_MESSAGE);
		iniciarBola();
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
		tratarColisoes();
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

	
	private void iniciarBola() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				movimentarBola();
				glDrawable.display();
			}
		}, 0, 50);
	}
	
	private void movimentarBola() {
		mundo.getBola().setxTranslacao(mundo.getBola().getxTranslacao() + estadoDeslocamentoBolaX);
		mundo.getBola().setyTranslacao(mundo.getBola().getyTranslacao() + estadoDeslocamentoBolaY);
		mundo.getBola().setzTranslacao(mundo.getBola().getzTranslacao() + estadoDeslocamentoBolaZ);
	}
	
	private void tratarColisoes() {
		// Primeiro com a plataforma
		if(verificarColisao(mundo.getPlataforma())){
			return;
		}

		// Verificar tijolos
		for (Tijolo tijolo : mundo.getTijolos()) {
			if(verificarColisao(tijolo)){
				tijolo.setDurabilidade(tijolo.getDurabilidade() - 1);
				if(tijolo.getDurabilidade() == 0) {
					mundo.getTijolos().remove(tijolo);
				}
				return;
			}
		}

		// Verificar paredes, chão e teto
		
		// Parede direita
		if(mundo.getBola().getbBox().getXmax() > mundo.getMesa().getbBox().getXmax() - 1f) {
			System.out.println("Parede direita");
			float valoresX[] = { -0.8f, -0.6f, -0.4f };
			estadoDeslocamentoBolaX = retornaPonto(valoresX);
			float valoresY[] = { -3f, -0.4f, -0.3f, -0.2f, -0.1f, -0.0f, 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 3f};
			estadoDeslocamentoBolaY = retornaPonto(valoresY);
		}
		
		// Parede esquerda
		if(mundo.getBola().getbBox().getXmin() < mundo.getMesa().getbBox().getXmin() + 1f) {
			System.out.println("Parede esquerda");
			float valoresX[] = { 3.0f, 0.8f, 0.6f, 0.4f };
			estadoDeslocamentoBolaX = retornaPonto(valoresX);
			float valoresY[] = { -3f, -0.4f, -0.3f, -0.2f, -0.1f, -0.0f, 0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 3f};
			estadoDeslocamentoBolaY = retornaPonto(valoresY);
		}
		
		// Teto
		if(mundo.getBola().getbBox().getYmax() > mundo.getMesa().getbBox().getYmax()) {
			System.out.println("Teto");
			float valoresY[] = { -0.8f, -0.6f, -0.4f };
			estadoDeslocamentoBolaY = retornaPonto(valoresY);
			float valoresX[] = { -0.4f, -0.3f, -0.2f, -0.1f, -0.0f, 0.1f, 0.2f, 0.3f, 0.4f };
			estadoDeslocamentoBolaX = retornaPonto(valoresX);
		}

		// Chão
		if(mundo.getBola().getbBox().getYmin() < mundo.getMesa().getbBox().getYmin() + 1f) {
			System.out.println("Chão");
			float valoresY[] = {5f, 0.8f, 0.6f, 0.4f };
			estadoDeslocamentoBolaY = retornaPonto(valoresY);
			float valoresX[] = { -0.4f, -0.3f, -0.2f, -0.1f, -0.0f, 0.1f, 0.2f, 0.3f, 0.4f };
			estadoDeslocamentoBolaX = retornaPonto(valoresX);
		}
		
		// Fundo da mesa
		if(mundo.getBola().getbBox().getZmin() < mundo.getMesa().getbBox().getZmin() - 1) {
			float valoresY[] = { -0.4f, -0.2f, -0.0f, 0.4f, 0.2f, 0.0f };
			estadoDeslocamentoBolaY = retornaPonto(valoresY);
			float valoresX[] = { -0.4f, -0.3f, -0.2f, -0.1f, -0.0f, 0.0f, 0.1f, 0.2f, 0.3f, 0.4f };
			estadoDeslocamentoBolaX = retornaPonto(valoresX);
			estadoDeslocamentoBolaZ = 0.5f;
		}
		
		// A bola passou
		if(mundo.getBola().getbBox().getZmax() > mundo.getMesa().getbBox().getZmax() + 2.5f) {
			mundo.setVidas(mundo.getVidas() - 1);
			mundo.origens();
			estadoDeslocamentoBolaX = 0;
			estadoDeslocamentoBolaY = 0;
			estadoDeslocamentoBolaZ = 0.5f;
			JOptionPane.showMessageDialog(null, "Perdeu uma vida.\nQuantidade de vidas: " + mundo.getVidas(), "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private boolean verificarColisao(ObjetoGrafico objetoGrafico) {
		int colidiuX = 0, colidiuY = 0; 
		boolean colidiuZ = false;
		
		// Verifica eixo X
		if(objetoGrafico.getbBox().getXmin() <= mundo.getBola().getbBox().getXmax() &&
				objetoGrafico.getbBox().getXmax() >= mundo.getBola().getbBox().getXmax()) {
			colidiuX = 2;
		}
		
		if(objetoGrafico.getbBox().getXmax() >= mundo.getBola().getbBox().getXmin() &&
				objetoGrafico.getbBox().getXmin() <= mundo.getBola().getbBox().getXmin()) {
			colidiuX = 3;
		}
		
		if(objetoGrafico.getbBox().getXmin() <= mundo.getBola().getbBox().getXmin() &&
				objetoGrafico.getbBox().getXmax() >= mundo.getBola().getbBox().getXmax()) {
			colidiuX = 1;
		}
		
		// Verifica eixo Y
		if(objetoGrafico.getbBox().getYmin() <= mundo.getBola().getbBox().getYmax() &&
				objetoGrafico.getbBox().getYmax() >= mundo.getBola().getbBox().getYmax()) {
			colidiuY = 2;
			System.out.println("Y = 2");
		}
		
		if(objetoGrafico.getbBox().getYmax() >= mundo.getBola().getbBox().getYmin() &&
				objetoGrafico.getbBox().getYmin() <= mundo.getBola().getbBox().getYmin()) {
			colidiuY = 3;
			System.out.println("Y = 3");
		}
		
		if(objetoGrafico.getbBox().getYmin() <= mundo.getBola().getbBox().getYmin() &&
				objetoGrafico.getbBox().getYmax() >= mundo.getBola().getbBox().getYmax()) {
			colidiuY = 1;
			System.out.println("Y = 1");
		}
		
		// Verifica eixo Z
		if(objetoGrafico.getbBox().getZmin() <= mundo.getBola().getbBox().getZmax() &&
				objetoGrafico.getbBox().getZmax() >= mundo.getBola().getbBox().getZmax()) {
			colidiuZ = true;
		}
		
		if(objetoGrafico.getbBox().getZmax() >= mundo.getBola().getbBox().getZmin() &&
				objetoGrafico.getbBox().getZmin() <= mundo.getBola().getbBox().getZmin()) {
			colidiuZ = true;
		}
		
		// Colidiu com o objeto
		if(colidiuZ && colidiuX != 0 && colidiuY != 0) {
			if(objetoGrafico.equals(mundo.getPlataforma())) {
				estadoDeslocamentoBolaZ = -0.5f;
			} else {
				estadoDeslocamentoBolaZ = estadoDeslocamentoBolaZ * -1;	
			}
			switch (colidiuX) {
			case 1:
				float valoresX1[] = { -0.15f, -0.1f, -0.0f, 0.0f, 0.0f, 0.15f };
				estadoDeslocamentoBolaX = retornaPonto(valoresX1);
				break;
			case 2:
				float valoresX2[] = { -0.35f, -0.3f, -0.25f, -0.2f, -0.15f, -0.0f};
				estadoDeslocamentoBolaX = retornaPonto(valoresX2);
				break;
			case 3:
				float valoresX3[] = { 0.35f, 0.3f, 0.25f, 0.2f, 0.15f, 0.0f};
				estadoDeslocamentoBolaX = retornaPonto(valoresX3);
			}
			
			switch (colidiuY) {
			case 1:
				float valoresY1[] = { -0.15f, -0.1f, -0.0f, 0.0f, 0.0f, 0.15f };
				estadoDeslocamentoBolaY = retornaPonto(valoresY1);
				break;
			case 2:
				float valoresY2[] = { -0.25f, -0.2f, -0.15f, -0.0f};
				estadoDeslocamentoBolaY = retornaPonto(valoresY2);
				break;
			case 3:
				float valoresY3[] = { 0.25f, 0.2f, 0.15f, 0.0f};
				estadoDeslocamentoBolaY = retornaPonto(valoresY3);
			}
			
			return true;
		}

		return false;
	}
	
	private float retornaPonto(float valores[]) {
		return valores[new Random().nextInt(valores.length)];
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
		//glDrawable.display();	
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
			float x = (e.getX() - dif) * 0.05f;
			float y = (e.getY() - dif) * -0.05f;
			if(y > mundo.getMesa().getbBox().getYmin() + 1.2f && y < mundo.getMesa().getbBox().getYmax()
					&& x - 3.5 > mundo.getMesa().getbBox().getXmin() && x + 3.5 < mundo.getMesa().getbBox().getXmax()) {	
				mundo.getPlataforma().setyTranslacao(y);
				mundo.getPlataforma().setxTranslacao(x);
				//glDrawable.display();
			}
		} catch (NullPointerException npe) {
		}
	}
}
