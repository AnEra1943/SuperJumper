package com.anlai.superjumper;
import com.anlai.util.CocosStartUtil;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HelpScreen implements Screen {
    private SuperJumper game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Group group;
    private Stage stage;

    private Group[] helpimages = new Group[5];

//    private Texture[] helpImages = new Texture[5];
//    private TextureRegion[] help = new TextureRegion[5];
    private int page = 0;
    public void loadimages() {
        for (int i = 0; i < 5; i++) {
            String jsonFileName = "res/json/help" + (i + 1) + ".json";
            helpimages[i] = CocosStartUtil.parseScene(jsonFileName);
        }
    }
    public void addButtonListeners() {
        // 这里可以添加按钮的监听器
        Actor backButton = group.findActor("Back");
        if (backButton != null) {
            backButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(page < 4){
                        page--;
                        group.remove();
                        group = helpimages[page];
                        stage.addActor(group);
                        addButtonListeners();

                    }
                    System.out.println("BackButton clicked");
                }
            });
        }
        Actor menuButton = group.findActor("Menu");
        if (menuButton != null) {
            menuButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new StartScreen(game));
                    System.out.println("MenuButton clicked");
                }
            });
        }
        Actor nextButton = group.findActor("Next");
        if (nextButton != null) {
            nextButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (page < 4) {
                        page++;
                        group.remove();
                        group = helpimages[page];
                        stage.addActor(group);
                        addButtonListeners();
                    }
                    System.out.println("NextButton clicked");
                }
            });
        }
    }
    public HelpScreen(SuperJumper game){
        this.game = game;
    }
    @Override
    public void show() {
        loadimages();
        batch = new SpriteBatch();
        stage = new Stage(new ExtendViewport(320,480),batch);
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        Gdx.input.setInputProcessor(stage);
        group = helpimages[0];
        stage.addActor(group);//捕捉舞台上的actor
        addButtonListeners();
//        helpImages[0] = new Texture("help1.png");
//        helpImages[1] = new Texture("help2.png");
//        helpImages[2] = new Texture("help3.png");
//        helpImages[3] = new Texture("help4.png");
//        helpImages[4] = new Texture("help5.png");
//        help[0] = new TextureRegion(helpImages[0],0,0,320,480);
//        help[1] = new TextureRegion(helpImages[1],0,0,320,480);
//        help[2] = new TextureRegion(helpImages[2],0,0,320,480);
//        help[3] = new TextureRegion(helpImages[3],0,0,320,480);
//        help[4] = new TextureRegion(helpImages[4],0,0,320,480);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
//        batch.begin();
//        batch.draw(help[page], 0, 0, 320, 480);
//        //显示按钮
//        if (page == 0) {
//            font.draw(batch, "Menu", 30, 40);
//            font.draw(batch, "Next", 230, 40);
//        }
//        if (page == 1) {
//            font.draw(batch, "Back", 30, 40);
//            font.draw(batch, "Next", 230, 40);
//            font.draw(batch, "Menu", 130, 40);
//        }
//        if (page == 2) {
//            font.draw(batch, "Back", 30, 40);
//            font.draw(batch, "Next", 230, 40);
//            font.draw(batch, "Menu", 130, 40);
//        }
//        if (page == 3) {
//            font.draw(batch, "Back", 30, 40);
//            font.draw(batch, "Next", 230, 40);
//            font.draw(batch, "Menu", 130, 40);
//        }
//        if (page == 4) {
//            font.draw(batch, "Back", 30, 40);
//            font.draw(batch, "Menu", 230, 40);
//        }
//
//        batch.end();
//        if (Gdx.input.justTouched()) {
//            float touchX = Gdx.input.getX();
//            float touchY = 480 - Gdx.input.getY();
//            // Next按钮
//            if (page == 0) {
//                if (touchX >= 24 && touchX <= 103 && touchY >= 22 && touchY <= 45) {
//                    game.setScreen(new StartScreen(game));
//                } else if (touchX >= 223 && touchX <= 303 && touchY >= 20 && touchY <= 43) {
//                    page++;
//                }
//            }
//            else if (page > 0 && page < 4 ) {
//                if (touchX >= 24 && touchX <= 103 && touchY >= 22 && touchY <= 45) {
//                    page--;
//                } else if (touchX >= 223 && touchX <= 303 && touchY >= 20 && touchY <= 43) {
//                    page++;
//                }else if (touchX >= 125 && touchX <= 201 && touchY >= 19 && touchY <= 43){
//                    game.setScreen(new StartScreen(game));
//                }
//            }
//            else if (page == 4) {
//                if (touchX >= 24 && touchX <= 103 && touchY >= 22 && touchY <= 45) {
//                    page--;
//                } else if (touchX >= 223 && touchX <= 303 && touchY >= 20 && touchY <= 43) {
//                    game.setScreen(new StartScreen(game));
//                }
//            }
//        }
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
        stage.dispose();
//        batch.dispose();
//        font.dispose();
//        for(int i = 0; i < 5; i++){
//            helpImages[i].dispose();
//        }
    }
}
