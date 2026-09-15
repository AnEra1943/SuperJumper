package com.anlai.superjumper;

import com.anlai.util.CocosStartUtil;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class StartScreen implements Screen {
    private final SuperJumper game;
    private SpriteBatch batch;
    private Stage stage;
    private Group group;
    public StartScreen(SuperJumper game) {
        this.game = game;
    }


    private void addButtonListeners() {
        //
            Actor playButton = group.findActor("Button_2");
            if(playButton!=null){
                playButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new GameScreen(game));
                        System.out.println("PlayButton clicked");
                    }
                });
            }
            Actor highScoreButton = group.findActor("Button_3");
            if(highScoreButton !=  null){
                highScoreButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new HighScoreScreen(game));
                        System.out.println("HighScoreButton clicked");
                    }
                });
            }
            Actor helpButton = group.findActor("Button_4");
            if(helpButton != null){
                helpButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(new HelpScreen(game));
                        System.out.println("HelpButton clicked");
                    }
                });
            }
        }

    @Override
    public void show() {
          batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(320,480), batch);
       // Gdx.input.setCatchKey(Input.Keys.BACK, true);//捕捉返回键直接让libgdx来操作
        Gdx.input.setInputProcessor(stage);//舞台来捕捉按键
        group = CocosStartUtil.parseScene("res/json/Start.json");
        group.setTouchable(Touchable.enabled);
        stage.addActor(group);
        addButtonListeners();
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        //初始化stage
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
