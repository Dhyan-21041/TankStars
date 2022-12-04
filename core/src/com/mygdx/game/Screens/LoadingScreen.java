package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;

import static com.mygdx.game.AP_Game.camera;

public class LoadingScreen implements Screen {

    private AP_Game game;
    private Texture img;
    private Stage stage;
    private Image img_background;



    public LoadingScreen(AP_Game game) {
        this.game = game;
        img = new Texture("tank-stars-pc-full-version.jpg");

        camera = new OrthographicCamera();


        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);
        img_background = new Image(img);
        img_background.setPosition(0,40);


        stage.addActor(img_background);

    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        update(delta);
        stage.draw();

        game.batch.begin();

        game.font.draw(game.batch, "LOADING.", 600, 30);

        if (Gdx.graphics.getFramesPerSecond() > 0) {
            game.font.draw(game.batch, "LOADING...", 600, 30);
        }

        if(Gdx.graphics.getFramesPerSecond()>60){
            game.setScreen(game.menuScreen);

        }


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
