package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.mygdx.game.AP_Game.camera;

public class ChooseLoadScreen implements Screen {


    private AP_Game game;

    private Image imgBackground;

    private Texture logo;
    private Image logo_image;

    private Texture img_background;


    private Skin skin;

    private TextButton SaveGame1;
    private TextButton SaveGame2;
    private TextButton SaveGame3;


    private ShapeRenderer shapeRenderer;

    private Stage stage;

    public static String file="";


    public ChooseLoadScreen(AP_Game game) {

        this.game = game;
        camera = new OrthographicCamera();
        img_background = new Texture("mainmenubackground.png");
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        this.shapeRenderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(stage);
        imgBackground = new Image(img_background);

        imgBackground.setSize(1280,720);
        stage.addActor(imgBackground);


    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.skin = new Skin();
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal("skin/uiskin.atlas")));
        this.skin.add("default-font", new BitmapFont());
        this.skin.load(Gdx.files.internal("skin/uiskin.json"));

        Button1Function();
        Button2Function();
        Button3Function();


    }

    @Override
    public void render(float delta) {

        stage.draw();


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

    }

    private void Button1Function(){


        SaveGame1= new TextButton("LOAD GAME #1", skin,"default");

        SaveGame1.addAction((sequence(fadeIn(2f))));
        SaveGame1.setPosition(590, 100);
        SaveGame1.setSize(120, 50);

        stage.addActor(SaveGame1);
        file="file1.txt";

        SaveGame1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new com.mygdx.game.Screens.GameScreen.GameScreen(game));
            }
        });
    }

    private void Button2Function(){

//        FileHandle file3 = Gdx.files.local("file2.txt");
//        String text2=file3.readString();

//        if(text2.length()>0) {
            SaveGame2 = new TextButton("LOAD GAME #2", skin, "default");
//        }
//        else{
//            SaveGame2 = new TextButton("LOAD GAME #2 not available", skin, "default");
//
//        }



        SaveGame2.addAction(forever(sequence(fadeIn(2))));
        SaveGame2.setPosition(390, 100);
        SaveGame2.setSize(120, 50);

        stage.addActor(SaveGame2);
        //file=2;
        SaveGame2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Load Game");
                game.setScreen(new com.mygdx.game.Screens.GameScreen.ChooseLoadScreen(game));
            }
        });
    }

    private void Button3Function(){
        SaveGame3= new TextButton("LOAD GAME #3", skin,"default");
        SaveGame3.addAction(forever(sequence(fadeIn(2))));
        SaveGame3.setPosition(790, 100);
        SaveGame3.setSize(120, 50);

        stage.addActor(SaveGame3);
        //file=3;

        SaveGame3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Load Game");
                game.setScreen(new com.mygdx.game.Screens.GameScreen.GameScreen(game));
            }
        });

    }



}


