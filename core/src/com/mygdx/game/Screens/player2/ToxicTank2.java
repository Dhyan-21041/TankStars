package com.mygdx.game.Screens.player2;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.GameScreen.GameScreen;
import com.mygdx.game.Screens.MenuScreen;
import com.mygdx.game.Screens.player1.Mark1Tank1;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class ToxicTank2 implements Screen {

    private AP_Game game;
    private Texture img;
    private Stage stage;
    private Image img_background;

    private OrthographicCamera camera;

    private Image Button1;
    private Texture img_button1;
    private Image Button2;
    private Texture img_button2;
    private Texture player;
    private Image playerImage;

    private Skin skin;

    private TextButton choosebutton;

    private Texture undo;
    private Image undobutton;




    public ToxicTank2(AP_Game game) {
        this.game = game;
        img = new Texture("ToxicTank.png");
        camera = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));


        Gdx.input.setInputProcessor(stage);
        img_background = new Image(img);
        img_background.setPosition(0,0);
        img_background.setSize(1280,720);
        img_button1 = new Texture("image-right.png");
        Button1 = new Image(img_button1);
        Button1.setSize(110,110);
        Button1.setPosition(1100,300);

        img_button2 = new Texture("image-left.png");
        Button2 = new Image(img_button2);
        Button2.setSize(110,110);
        Button2.setPosition(100,300);


        player = new Texture("PLAYER (2).png");

        playerImage = new Image(player);
        playerImage.setSize(150,65);

        undo = new Texture("undobutton.png");
        undobutton = new Image(undo);
        undobutton.setSize(80,80);
        undobutton.setPosition(75,615);


        stage.addActor(img_background);
        stage.addActor(Button1);
        stage.addActor(Button2);
        stage.addActor(playerImage);
        stage.addActor(undobutton);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
        img_background.addAction(forever(sequence(alpha(0), fadeIn(0.4F))));
        Button1.addAction((sequence(fadeIn(0.4F))));
        undobutton.addAction(forever(sequence(fadeIn(0.4F))));


        playerImage.addAction(forever(sequence(fadeIn(0.4F))));
        playerImage.setPosition(100,35);

        Button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new AtomicTank2(game));
            }
        });

        Button2.addAction((sequence(fadeIn(0.4F))));
        Button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Mark1Tank2(game));
            }
        });
        undobutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Mark1Tank1(game));
            }
        });

        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
        this.skin.add("default-font", new BitmapFont());
        this.skin.load(Gdx.files.internal("skin/uiskin.json"));

        Choosebutton();


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

    private void  Choosebutton(){

        choosebutton= new TextButton("SELECT TANK", skin,"default");

        choosebutton.addAction((sequence(fadeIn(2f))));
        choosebutton.setPosition(950, 35);
        choosebutton.setSize(200, 65);

        stage.addActor(choosebutton);

        choosebutton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setPlayer2TankName("ToxicTank");
                game.setScreen(new GameScreen(game));

            }
        });
    }


}