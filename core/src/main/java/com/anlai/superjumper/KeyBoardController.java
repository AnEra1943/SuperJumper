package com.anlai.superjumper;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class KeyBoardController extends InputAdapter {
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spaceJustPressed = false;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == com.badlogic.gdx.Input.Keys.LEFT) {
            leftPressed = true;
        }
        if (keycode == com.badlogic.gdx.Input.Keys.RIGHT) {
            rightPressed = true;
        }
        if (keycode == com.badlogic.gdx.Input.Keys.SPACE) {
            spaceJustPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == com.badlogic.gdx.Input.Keys.LEFT) {
            leftPressed = false;
        }
        if (keycode == com.badlogic.gdx.Input.Keys.RIGHT) {
            rightPressed = false;
        }
        return true;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isSpaceJustPressed() {
        return spaceJustPressed;
    }

    public void resetSpaceJustPressed() {
        spaceJustPressed = false;
    }
}
