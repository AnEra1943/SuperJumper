package com.anlai.superjumper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class HighScoreScreen implements Screen {
    private SuperJumper game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Texture background;
    private TextureRegion background_final;

    private Texture items;
    private TextureRegion exit;
    public HighScoreScreen(SuperJumper game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        items = new Texture("items.png");
        background = new Texture("background.png");
        exit = new TextureRegion(items,256,180,126,36);
        background_final = new TextureRegion(background,0,0,320,480);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        //检查点击
        if(Gdx.input.justTouched()){
            float touchX = Gdx.input.getX();
            float touchY = 480 - Gdx.input.getY();
        if(touchX >= 94 && touchX <= 220 && touchY >= 23 && touchY <= 58 ){
            System.out.println("touch!!!");
            game.setScreen(new StartScreen(game));

        }
        }
        batch.begin();
        batch.draw(background_final,0,0,320,480);
        // 显示历史最高成绩
        font.draw(batch, "High Score", 80, 350);
        font.draw(batch, "Score:" + game.saveManager.getHighScore(), 75, 300);
        font.draw(batch, "Time:" + game.saveManager.getBestTime() + "s", 90, 250);
        batch.draw(exit,90,20);
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
        batch.dispose();
        font.dispose();
        background.dispose();
        items.dispose();
    }
}
