package com.anlai.superjumper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HelpScreen implements Screen {
    private SuperJumper game;
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture[] helpImages = new Texture[5];
    private TextureRegion[] help = new TextureRegion[5];
    private int page = 0;

    public HelpScreen(SuperJumper game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        helpImages[0] = new Texture("help1.png");
        helpImages[1] = new Texture("help2.png");
        helpImages[2] = new Texture("help3.png");
        helpImages[3] = new Texture("help4.png");
        helpImages[4] = new Texture("help5.png");
        help[0] = new TextureRegion(helpImages[0],0,0,320,480);
        help[1] = new TextureRegion(helpImages[1],0,0,320,480);
        help[2] = new TextureRegion(helpImages[2],0,0,320,480);
        help[3] = new TextureRegion(helpImages[3],0,0,320,480);
        help[4] = new TextureRegion(helpImages[4],0,0,320,480);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(help[page], 0, 0, 320, 480);
        //显示按钮
        if (page == 0) {
            font.draw(batch, "Menu", 30, 40);
            font.draw(batch, "Next", 230, 40);
        }
        if (page == 1) {
            font.draw(batch, "Back", 30, 40);
            font.draw(batch, "Next", 230, 40);
            font.draw(batch, "Menu", 130, 40);
        }
        if (page == 2) {
            font.draw(batch, "Back", 30, 40);
            font.draw(batch, "Next", 230, 40);
            font.draw(batch, "Menu", 130, 40);
        }
        if (page == 3) {
            font.draw(batch, "Back", 30, 40);
            font.draw(batch, "Next", 230, 40);
            font.draw(batch, "Menu", 130, 40);
        }
        if (page == 4) {
            font.draw(batch, "Back", 30, 40);
            font.draw(batch, "Menu", 230, 40);
        }

        batch.end();
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = 480 - Gdx.input.getY();
            // Next按钮
            if (page == 0) {
                if (touchX >= 24 && touchX <= 103 && touchY >= 22 && touchY <= 45) {
                    game.setScreen(new StartScreen(game));
                } else if (touchX >= 223 && touchX <= 303 && touchY >= 20 && touchY <= 43) {
                    page++;
                }
            }
            else if (page > 0 && page < 4 ) {
                if (touchX >= 24 && touchX <= 103 && touchY >= 22 && touchY <= 45) {
                    page--;
                } else if (touchX >= 223 && touchX <= 303 && touchY >= 20 && touchY <= 43) {
                    page++;
                }else if (touchX >= 125 && touchX <= 201 && touchY >= 19 && touchY <= 43){
                    game.setScreen(new StartScreen(game));
                }
            }
            else if (page == 4) {
                if (touchX >= 24 && touchX <= 103 && touchY >= 22 && touchY <= 45) {
                    page--;
                } else if (touchX >= 223 && touchX <= 303 && touchY >= 20 && touchY <= 43) {
                    game.setScreen(new StartScreen(game));
                }
            }
        }
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
        for(int i = 0; i < 5; i++){
            helpImages[i].dispose();
        }
    }
}
