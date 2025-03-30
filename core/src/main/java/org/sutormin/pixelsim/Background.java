package org.sutormin.pixelsim;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {
    Texture texture;
    Camera camera;

    public Background(Texture texture, Camera camera) {
        this.texture = texture;
        this.texture.setWrap(
            Texture.TextureWrap.Repeat,
            Texture.TextureWrap.Repeat
        );
        this.camera = camera;
    }

    void draw(SpriteBatch batch) {
        int xOffset = (int) camera.position.x;
        int yOffset = (int) -camera.position.y;
        TextureRegion region = new TextureRegion(texture);
        region.setRegionX(xOffset % texture.getWidth());
        region.setRegionY(yOffset % texture.getHeight());
        region.setRegionWidth((int) camera.viewportWidth);
        region.setRegionHeight((int) camera.viewportHeight);
        batch.draw(region, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2);
    }
}

