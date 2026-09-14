package com.anlai.superjumper;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;

public class StartScreen implements Screen {

    private final SuperJumper game;
    private Texture background;
    private SpriteBatch batch;
    private TextureRegion background_final;
    private Texture items;
    private TextureRegion title;
    private TextureRegion navigation;

    public StartScreen(SuperJumper game) {
        this.game = game;
    }

    @Override
    public void show() {
          batch = new SpriteBatch();
          items = new Texture("items.png");
          background = new Texture("background.png");
          background_final = new TextureRegion(background,0,0,320,480);
          title = new TextureRegion(items,0,350,281,153);
          navigation = new TextureRegion(items,0,228,300,112);

    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        // 检查有没有点击Play
        if (Gdx.input.justTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = 480 - Gdx.input.getY();

            // 如果点击的位置在这个区域里面
            if (touchX >= 102 && touchX <= 224 &&
                touchY >= 183 && touchY <= 216) {
                System.out.println("touch");
                game.setScreen(new GameScreen(game));
            }
        }
        // 检查有没有点击HighScore
        if (Gdx.input.justTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = 480 - Gdx.input.getY();

            // 如果点击的位置在这个区域里面
            if (touchX >= 15 && touchX <= 313 &&
                touchY >= 145 && touchY <= 179) {
                System.out.println("touch");
                game.setScreen(new HighScoreScreen(game));
            }
        }
        // 检查有没有点击Help
        if (Gdx.input.justTouched()) {

            float touchX = Gdx.input.getX();
            float touchY = 480 - Gdx.input.getY();

            // 如果点击的位置在这个区域里面
            if (touchX >= 101 && touchX <= 226 &&
                touchY >= 107 && touchY <= 142) {
                System.out.println("touch Help");
                game.setScreen(new HelpScreen(game));
            }
        }

        batch.begin();

        batch.draw(background_final, 0, 0, 320, 480);
        batch.draw(title, 25, 300);
        batch.draw(navigation, 14, 100);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
