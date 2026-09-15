package com.anlai.superjumper;

import com.anlai.util.CocosStartUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class HighScoreScreen implements Screen {
    private SuperJumper game;
    private SpriteBatch batch;
    private BitmapFont font;
//    private Texture background;
//    private TextureRegion background_final;
    private Stage stage;
    private Group group;

//    private Texture items;
//    private TextureRegion exit;
    public void addButtonListeners() {
        Actor exitButton = group.findActor("quit");
        if (exitButton != null) {
            exitButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new StartScreen(game));
                    System.out.println("ExitButton clicked");
                }
            }); //圆括号后面跟随前面 快速调用
        }
    }

    public HighScoreScreen(SuperJumper game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage(new ExtendViewport(320,480),batch);
        group = CocosStartUtil.parseScene("res/json/HighScore.json");
        stage.addActor(group);
        Gdx.input.setInputProcessor(stage);
        addButtonListeners();
       font = new BitmapFont(Gdx.files.internal("font.fnt"));
//        items = new Texture("items.png");
//        background = new Texture("background.png");
//        exit = new TextureRegion(items,256,180,126,36);
//        background_final = new TextureRegion(background,0,0,320,480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
//        //检查点击
//        if(Gdx.input.justTouched()){
//            float touchX = Gdx.input.getX();
//            float touchY = 480 - Gdx.input.getY();
//        if(touchX >= 94 && touchX <= 220 && touchY >= 23 && touchY <= 58 ){
//            System.out.println("touch!!!");
//            game.setScreen(new StartScreen(game));
//
//        }
//        }
        stage.act(delta);
        stage.draw();
        batch.begin();
//        batch.draw(background_final,0,0,320,480);
        // 显示历史最高成绩
        font.draw(batch, "High Score", 80, 350);
        font.draw(batch, "Score:" + game.saveManager.getHighScore(), 75, 300);
        font.draw(batch, "Time:" + game.saveManager.getBestTime() + "s", 90, 250);
//        batch.draw(exit,90,20);
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
//        background.dispose();
//        items.dispose();
    }
}
