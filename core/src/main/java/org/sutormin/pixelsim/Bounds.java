package org.sutormin.pixelsim;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bounds {
    public double minX;
    public double minY;
    public double maxX;
    public double maxY;

    public Bounds(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(255, 255, 255, 255);
        shapeRenderer.rectLine((float) minX, 0, (float) minY, 0, 5);
        shapeRenderer.rectLine((float) minY, 0, 0, (float) maxY, 5);
        shapeRenderer.rectLine((float) minY, (float) maxX, (float) maxY, (float) maxX, 5);
        shapeRenderer.rectLine((float) maxX, 0, (float) maxY, 0, 5);
    }
}
