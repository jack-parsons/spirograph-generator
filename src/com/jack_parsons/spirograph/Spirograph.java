package com.jack_parsons.spirograph;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Spirograph {
	private double radiusRatio, penOffset;
	private int numLoops = 500;
	private double changeInAngle = 0.1;
	double scale;
	double xOffset;
	double yOffset;
	ArrayList<Double> xPositions;
	ArrayList<Double> yPositions;

	public Spirograph(double radiusRatio, double penOffset) {
		this.radiusRatio = radiusRatio;
		this.penOffset = penOffset;
	}
	
	private void getAutoScaling(ArrayList<Double> xPositions, ArrayList<Double> yPositions) {
		double maxX = -10000;
		double maxY = -10000;
		double minX = 10000;
		double minY = 10000;
		
		for (int i = 0; i < xPositions.size(); i ++) {
			maxX = Math.max(maxX, xPositions.get(i));
			maxY = Math.max(maxY, yPositions.get(i));
			minX = Math.min(minX, xPositions.get(i));
			minY = Math.min(minY, yPositions.get(i));
		}
		
		// Scale between 0-1
		scale = Math.min(1/(maxY-minY), 1/(maxX-minX));
		xOffset = (minX - maxX)/2;
		yOffset = (minY - maxY)/2;
	}
	
	public void update(float windowXSize, float windowYSize) {
		xPositions = new ArrayList<Double>();
		yPositions = new ArrayList<Double>();
		
		for (double t = 0; t < Math.PI * 2 * numLoops; t += changeInAngle) {
			xPositions.add((1 - radiusRatio) * Math.cos(t) + penOffset * radiusRatio * Math.cos((1 - radiusRatio) * t / radiusRatio));
			yPositions.add((1 - radiusRatio) * Math.sin(t) - penOffset * radiusRatio * Math.sin((1 - radiusRatio) * t / radiusRatio));
		}
		
		getAutoScaling(xPositions, yPositions);
		
		for (int i = 0; i < xPositions.size(); i ++) {
			xPositions.set(i, (xPositions.get(i) - xOffset) * scale * windowXSize);
			yPositions.set(i, (yPositions.get(i) - yOffset) * scale * windowYSize+80);
		}
	}

	public void paint(Graphics2D canvas) {
		for (int i = 1; i < xPositions.size(); i ++) {
			Line2D lin = new Line2D.Double(
					xPositions.get(i),
					yPositions.get(i),
					xPositions.get(i-1),
					yPositions.get(i-1));
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			canvas.draw(lin);
		}
	}

	public double getPenOffset() {
		return penOffset;
	}

	public void setPenOffset(double penOffset) {
		this.penOffset = penOffset;
	}

	public double getRadiusRatio() {
		return radiusRatio;
	}

	public void setRadiusRatio(double radiusRatio) {
		this.radiusRatio = radiusRatio;
	}

}
