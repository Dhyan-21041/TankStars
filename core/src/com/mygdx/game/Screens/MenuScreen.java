package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.GameScreen.GameScreen;
import com.mygdx.game.Screens.GameScreen.InGameMenu;

import static com.mygdx.game.AP_Game.camera;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;



public class MenuScreen implements Screen {
    private AP_Game game;

    private Image imgBackground;

    private Texture logo;
    private Image logo_image;

    private Texture img_background;


    private Skin skin;

    private TextButton NewGameButton;
    private TextButton LoadGameButton;
    private TextButton ExitGameButton;


    private ShapeRenderer shapeRenderer;

    private Stage stage;


//dhyan

    public MenuScreen(AP_Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        img_background = new Texture("mainmenubackground.png");
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        this.shapeRenderer = new ShapeRenderer();

        logo = new Texture("tankstarslogo.png");
        logo_image = new Image(logo);
        logo_image.setSize(600, 400);
        logo_image.setPosition(330, 200);

        Gdx.input.setInputProcessor(stage);
        imgBackground = new Image(img_background);

        imgBackground.setSize(1280,720);
        stage.addActor(imgBackground);
        stage.addActor(logo_image);

    }


    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        imgBackground.addAction(forever(sequence(alpha(0), fadeIn(0.4F))));

        logo_image.addAction(forever(sequence(fadeIn(0.4F))));


        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
        this.skin.add("default-font", new BitmapFont());
        this.skin.load(Gdx.files.internal("skin/uiskin.json"));

        Button1Function();
        Button2Function();
        Button3Function();


    }

    private void update(float delta) {
        stage.act(delta);

    }

    @Override
    public void render(float delta) {

        game.batch.setProjectionMatrix(camera.combined);
        update(delta);

        stage.draw();
        game.batch.begin();
        game.batch.end();

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
        shapeRenderer.dispose();

    }

    private void Button1Function(){
        NewGameButton= new TextButton("NEW GAME", skin,"default");

        NewGameButton.addAction((sequence(fadeIn(2f))));
        NewGameButton.setPosition(590, 100);
        NewGameButton.setSize(120, 50);

        stage.addActor(NewGameButton);

        NewGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new com.mygdx.game.Screens.player1.Mark1Tank1(game));
            }
        });
    }

    private void Button2Function(){
        LoadGameButton= new TextButton("LOAD GAME", skin,"default");

        LoadGameButton.addAction(forever(sequence(fadeIn(2))));
        LoadGameButton.setPosition(390, 100);
        LoadGameButton.setSize(120, 50);

        stage.addActor(LoadGameButton);
//        LoadGameButton.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new LoadGame(game));
//            }
//        });
    }

    private void Button3Function(){
        ExitGameButton= new TextButton("EXIT", skin,"default");
        ExitGameButton.addAction(forever(sequence(fadeIn(2))));
        ExitGameButton.setPosition(790, 100);
        ExitGameButton.setSize(120, 50);

        stage.addActor(ExitGameButton);

        ExitGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

}

