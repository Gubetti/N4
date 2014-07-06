package model;

public class Camera {
	private double xEye, yEye, zEye;
	private double xCenter, yCenter, zCenter;
	//private final double xUp = 0.0f, yUp = 1.0f, zUp = 0.0f; //O que ser isso?

	public Camera() {
		visao1();
	}

	// Métodos para mudar o valor da câmera entre os diversos ângulos...
	public void visao1() {
		// setxEye(20f); por exemplo
	}

	public void visao2() {

	}

	public double getxEye() {
		return xEye;
	}

	public void setxEye(double xEye) {
		this.xEye = xEye;
	}

	public double getyEye() {
		return yEye;
	}

	public void setyEye(double yEye) {
		this.yEye = yEye;
	}

	public double getzEye() {
		return zEye;
	}

	public void setzEye(double zEye) {
		this.zEye = zEye;
	}

	public double getxCenter() {
		return xCenter;
	}

	public void setxCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public double getyCenter() {
		return yCenter;
	}

	public void setyCenter(double yCenter) {
		this.yCenter = yCenter;
	}

	public double getzCenter() {
		return zCenter;
	}

	public void setzCenter(double zCenter) {
		this.zCenter = zCenter;
	}
}