package org.sutormin.pixelsim;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

public class PixelContainer {
    private List<GameObject> gameObjectList;

    public PixelContainer() {
        gameObjectList = new ArrayList<>();
    }

    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(List<GameObject> newGameObjectList) {
        gameObjectList = newGameObjectList;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjectList.add(gameObject);
    }

    public GameObject getGameObject(int index) {
        return gameObjectList.get(index);
    }

    public void updateAll() {
        for (GameObject gameObject: gameObjectList) {
            gameObject.update(this);
        }
    }

    public void drawAll(SpriteBatch batch) {
        for (GameObject gameObject: gameObjectList) {
            gameObject.draw(batch);
        }
    }
}
