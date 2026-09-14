package com.anlai.superjumper;
import com.badlogic.gdx.Game;




/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SuperJumper extends Game {

    public SaveManager saveManager;

    @Override
    public void create() {
        saveManager = new SaveManager();
        setScreen(new StartScreen(this));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {

    }
}
