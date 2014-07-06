package model;

public class Camera {
	private double xEye, yEye, zEye;
	private double xCenter, yCenter, zCenter;
	private final double xUp = 0.0f, yUp = 1.0f, zUp = 0.0f;

	public Camera() {
		xCenter = 0f;
		yCenter = 0.5f;
		zCenter = 0f;
		visao1();
	}

	// Métodos para mudar o valor da câmera entre os diversos ângulos...
	public void visao1() {
		xEye = 20.0f;
		yEye = 20.0f;
		zEye = 20.0f;
	}

	public void visao2() {
		xEye = 0.0f;
		yEye = 0.0f;
		zEye = 20.0f;
	}

	public double getxEye() {
		return xEye;
	}

	public double getyEye() {
		return yEye;
	}

	public double getzEye() {
		return zEye;
	}

	public double getxCenter() {
		return xCenter;
	}

	public double getyCenter() {
		return yCenter;
	}

	public double getzCenter() {
		return zCenter;
	}

	public double getxUp() {
		return xUp;
	}

	public double getyUp() {
		return yUp;
	}

	public double getzUp() {
		return zUp;
	}
}