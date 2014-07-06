package model;

public class BBox {
	
	private float Xmin;
	private float Xmax;
	private float Ymin;
	private float Ymax;
	private float Zmin;
	private float Zmax;
	
	public BBox(float xmin, float xmax, float ymin, float ymax, float zmin, float zmax) {
		Xmin = xmin;
		Xmax = xmax;
		Ymin = ymin;
		Ymax = ymax;
		Zmin = zmin;
		Zmax = zmax;
	}

	public float getXmin() {
		return Xmin;
	}

	public void setXmin(float xmin) {
		Xmin = xmin;
	}

	public float getXmax() {
		return Xmax;
	}

	public void setXmax(float xmax) {
		Xmax = xmax;
	}

	public float getYmin() {
		return Ymin;
	}

	public void setYmin(float ymin) {
		Ymin = ymin;
	}

	public float getYmax() {
		return Ymax;
	}

	public void setYmax(float ymax) {
		Ymax = ymax;
	}

	public float getZmin() {
		return Zmin;
	}

	public void setZmin(float zmin) {
		Zmin = zmin;
	}

	public float getZmax() {
		return Zmax;
	}

	public void setZmax(float zmax) {
		Zmax = zmax;
	}	
}