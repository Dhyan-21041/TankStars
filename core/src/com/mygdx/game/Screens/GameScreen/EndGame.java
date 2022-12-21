package com.mygdx.game.Screens.GameScreen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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
import com.mygdx.game.Screens.MenuScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.mygdx.game.AP_Game.camera;

public class EndGame implements Screen {
    private AP_Game game;

    private Image Gameover_image;

    private Texture Background;
    private Image Background_image;


    private Texture Gameover;

    private Texture isWinner;
    private Image isWinner_image;


    private Skin skin;

    private TextButton NewGameButton;
    private TextButton MainMenuButton;


    private ShapeRenderer shapeRenderer;

    private Stage stage;

    public static int flag =0 ;


//dhyan

    public EndGame(AP_Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        Gameover = new Texture("Gameover.png");
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        this.shapeRenderer = new ShapeRenderer();

        if(TypesOfCollision.Health_Player1==0){
            isWinner = new Texture("Player1wins.png");
        }
        else{
            isWinner = new Texture("Player2wins.png");
        }

        isWinner_image = new Image(isWinner);

        Gameover_image = new Image(Gameover);

        Background = new Texture("backgroundEndGame.png");
        Background_image = new Image(Background);

        Background_image.setSize(AP_Game.WIDTH, AP_Game.HEIGHT);

        Gameover_image.setSize(300, 300);
        stage.addActor(Background_image);

        stage.addActor(Gameover_image);
        stage.addActor(isWinner_image);


    }


    @Override
    public void show() {


        Gdx.input.setInputProcessor(stage);
        Gameover_image.addAction(forever(sequence(alpha(0), fadeIn(0.4F))));

        Gameover_image.setPosition(500, 300);

        isWinner_image.addAction(forever(sequence(alpha(0), fadeIn(0.4F))));

        isWinner_image.setPosition(300, 200);

        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
        this.skin.add("default-font", new BitmapFont());
        this.skin.load(Gdx.files.internal("skin/uiskin.json"));

        Button1Function();
        Button2Function();


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
        flag=0;
        NewGameButton.addAction((sequence(fadeIn(2f))));
        NewGameButton.setPosition(400, 100);
        NewGameButton.setSize(120, 50);
        stage.addActor(NewGameButton);

        NewGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
    }



    private void Button2Function(){
        MainMenuButton = new TextButton("Main Menu", skin,"default");
        MainMenuButton.addAction(forever(sequence(fadeIn(2))));
        MainMenuButton.setPosition(700, 100);
        MainMenuButton.setSize(120, 50);

        stage.addActor(MainMenuButton);

        MainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

    }

}