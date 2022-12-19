package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.MenuScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.mygdx.game.AP_Game.camera;

public class InGameMenu implements Screen {

    private AP_Game game;
    private Texture img;
    private Stage stage;
    private Image img_background;

    private Texture ResumeButton;
    private Texture SoundButton;
    private Texture SaveButton;
    private Texture MainmenuButton;
    private  Image img_ResumeButton;
    private  Image img_SoundButton;
    private  Image img_SaveButton;
    private  Image img_MainmenuButton;




    public InGameMenu(AP_Game game) {
        this.game = game;
        img = new Texture("SETTINGS 2.png");


        camera = new OrthographicCamera();


        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);
        img_background = new Image(img);
        img_background.setSize(4000, 2000);
        img_background.setPosition(-1350,-620);


        // SAVE BUTTON doesnt make any sense

        ResumeButton = new Texture("Resume.png");
        SoundButton = new Texture("Sound.png");
        SaveButton = new Texture("save 1.png");
        MainmenuButton = new Texture("MainmenuButton copy.png");

        img_ResumeButton = new Image(ResumeButton);
        img_ResumeButton.setSize(100, 50);
        img_ResumeButton.setPosition(400,300);
        img_SoundButton = new Image(SoundButton);
        img_SoundButton.setSize(100, 50);
        img_SoundButton.setPosition(400,230);
        img_SaveButton = new Image(SaveButton);
        img_SaveButton.setSize(100, 50);
        img_SaveButton.setPosition(400,170);
        img_MainmenuButton = new Image(MainmenuButton);
        img_MainmenuButton.setSize(100, 50);
        img_MainmenuButton.setPosition(400,100);

        stage.addActor(img_background);
        stage.addActor(img_ResumeButton);
        stage.addActor(img_SoundButton);
        stage.addActor(img_SaveButton);
        stage.addActor(img_MainmenuButton);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        img_background.addAction(forever(sequence(alpha(0), fadeIn(0.4F))));

        img_ResumeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //img_SaveButton.addAction(forever(sequence(alpha(0), fadeOut(0.4F),removeActor())));


            }
        });

        img_MainmenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });



    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        update(delta);
        stage.draw();
        game.batch.begin();
        game.batch.end();

    }
    private void update(float delta){
        stage.act(delta);

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
        stage.dispose();

    }


}
