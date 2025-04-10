package org.sutormin.pixelsim;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameObject {
    public double[] pos;
    public double[] vel;
    public double width;
    public double height;
    public Texture texture;
    public Bounds bounds;

    public GameObject(double[] startPos, double[] startVel, double width, double height, Texture texture, Bounds bounds) {
        this.pos = startPos;
        this.vel = startVel;
        this.width = width;
        this.height = height;

        this.texture = texture;
        this.texture.setWrap(
            Texture.TextureWrap.Repeat,
            Texture.TextureWrap.Repeat
        );
        this.bounds = bounds;
    }

    public void update(PixelContainer pixelContainer) {
        this.vel[0] = Utils.clamp(-100, 100, Utils.closenToZero(this.vel[0], .1f));
        this.vel[1] = Utils.clamp(-100, 100, Utils.closenToZero(this.vel[1], .1f));

        this.pos[0] += this.vel[0];
        this.pos[1] += this.vel[1];

        for (GameObject gameObject : pixelContainer.getGameObjectList()) {
            if (gameObject != this && Utils.isColliding(this, gameObject)) {
                this.pos[0] -= this.vel[0];
                this.vel[0] = -this.vel[0] / 2;
                this.pos[1] -= this.vel[1];
                this.vel[1] = -this.vel[1] / 2;
            }
        }

        if (pos[0] < bounds.minX) {
            this.pos[0] = bounds.minX;
            this.vel[0] = -this.vel[0] / 2;
        }

        if (pos[0] > bounds.maxX) {
            this.pos[0] = bounds.maxX;
            this.vel[0] = -this.vel[0] / 2;
        }

        if (pos[1] < bounds.minY) {
            this.pos[1] = bounds.minY;
            this.vel[1] = -this.vel[1] / 2;
        }

        if (pos[1] > bounds.maxY) {
            this.pos[1] = bounds.maxY;
            this.vel[1] = -this.vel[1] / 2;
        }
    }

    public void draw(SpriteBatch batch) {
        TextureRegion region = new TextureRegion(texture,
            (int) Utils.roundToNearest(this.width, 1f),
            (int) Utils.roundToNearest(this.height, 1f));

        batch.draw(region,
            (int) Utils.roundToNearest(pos[0], 1f),
            (int) Utils.roundToNearest(pos[1], 1f));
    }
}
