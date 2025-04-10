package org.sutormin.pixelsim;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.math.MathUtils.lerp;

public class GameScreen extends ScreenAdapter {
    Main game;
    OrthographicCamera camera;
    OrthographicCamera HUDCamera;
    Bounds bounds;
    PixelContainer pixelContainer;
    GameObject player;
    Background background;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        HUDCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bounds = new Bounds(Float.NEGATIVE_INFINITY, 0, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        pixelContainer = new PixelContainer();
        player = new GameObject(new double[]{500, 500}, new double[]{0, 0},
            30, 30, new Texture("textures/player.png"), bounds);
        camera.position.set(500, 500, 0);
        pixelContainer.addGameObject(player);
        background = new Background(new Texture("textures/background.png"), camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(game.titleScreen);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.vel[0] -= .5f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.vel[0] += .5f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.vel[1] += .5f;
        } else {
            player.vel[1] -= .5f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.vel[1] -= .5f;
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float posX = Gdx.input.getX();
            float posY = Gdx.input.getY();
            Vector3 posInGame = camera.unproject(new Vector3(posX, posY, 0));
            pixelContainer.addGameObject(new GameObject(new double[]{posInGame.x, posInGame.y}, new double[]{0, 0},
                20, 20, new Texture("textures/player.png"), bounds));
        }

        pixelContainer.updateAll();
        /*if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            player.vel[0] = 0;
            player.vel[1] = 0;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            player.pos[0] = 0;
            player.pos[1] = 0;
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            float posX = Gdx.input.getX();
            float posY = Gdx.input.getY();
            Vector3 posInGame = camera.unproject(new Vector3(posX, posY, 0));
            player.pos[0] = posInGame.x;
            player.pos[1] = posInGame.y;
        }*/
        camera.position.set(lerp(camera.position.x, (float) player.pos[0], 0.1f),
            lerp(camera.position.y, (float) player.pos[1], 0.1f), 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        background.draw(game.batch);
        game.batch.end();

        game.batch.begin();
        pixelContainer.drawAll(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.shapeRenderer.setProjectionMatrix(camera.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        bounds.draw(game.shapeRenderer);
        game.shapeRenderer.end();
        game.batch.end();

        game.batch.setProjectionMatrix(HUDCamera.combined);
        game.batch.begin();
        drawText("Player X: " + String.format("%.3f", player.pos[0]) + ", Player Y: " + String.format("%.3f", player.pos[1]) +
            "\nPlayer Vel X: " + String.format("%.3f", player.vel[0]) + ", Player Vel Y: " + String.format("%.3f", player.vel[1]) +
            "\nFPS: " + Gdx.graphics.getFramesPerSecond());
        game.batch.end();
    }

    @Override
    public void hide() {
    }

    private void drawText(String text) {
        String[] textLines = text.split("\n");
        for (int i = 0; i < textLines.length; i++) {
            game.font.draw(game.batch, textLines[i],
                -HUDCamera.viewportWidth / 2 + 5,
                HUDCamera.viewportHeight / 2 - (5 + (game.font.getLineHeight() * i)));
        }
    }
}
