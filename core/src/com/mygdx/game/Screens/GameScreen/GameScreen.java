package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.player1.AtomicTank1;
import com.mygdx.game.Screens.player1.Mark1Tank1;
import com.mygdx.game.Screens.player1.ToxicTank1;

import java.util.Objects;

import static com.mygdx.game.AP_Game.camera;

public class GameScreen implements Screen {

    private AP_Game game;
    private Texture img;
    private Stage stage;
    private Image img_background;

    private Texture settings_button;
    private Image settings_button_image;

    private Texture tank1;
    private Image tank1_image;

    private Texture tank2;
    private Image tank2_image;

    public Player_1 player1;
    public Player_2 player2;

    private Texture AtmoicTank;
    private Image AtmoicTankImage;

    private Texture Mark1Tank;
    private Image Mark1TankImage;
    private Texture ToxicTank;
    private Image ToxicTankImage;

    private AtomicTank1 atomicTank1;
    private Mark1Tank1 mark1Tank1;
    private ToxicTank1 toxicTank1;
    
    public GameScreen(AP_Game game) {
        this.game = game;
        camera = new OrthographicCamera();

//        AtmoicTank = new Texture("Atomic.png");
//        AtmoicTankImage = new Image(AtmoicTank);
//
//        Mark1Tank = new Texture("Mark1.png");
//        Mark1TankImage = new Image(Mark1Tank);
//
//        ToxicTank = new Texture("Toxic.png");
//        ToxicTankImage = new Image(ToxicTank);
//
//
//        atomicTank1 = new AtomicTank1(game);
//        mark1Tank1 = new Mark1Tank1(game);
//        toxicTank1 = new ToxicTank1(game);

        player1 = new Player_1(game);
        player2 = new Player_2(game);

        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));



        img = new Texture("background.png");
        img_background = new Image(img);

        tank1= new Texture("Toxic.png");
        tank1_image = new Image(tank1);
        tank1_image.setPosition(100,100);
        tank1_image.setPosition(50,100);

        tank2= new Texture("Toxic_reverse.png");
        tank2_image = new Image(tank2);
        tank2_image.setPosition(1000,100);

        settings_button = new Texture("settingsbutton.png");
        settings_button_image = new Image(settings_button);



        stage.addActor(img_background);
        stage.addActor(settings_button_image);
        stage.addActor(tank1_image);
        stage.addActor(tank2_image);

        img_background.setSize(2000,1200);
        img_background.setPosition(-390,-200);

        settings_button_image.setSize(180,80);
        settings_button_image.setPosition(20,600);

//        stage.addActor(AtmoicTankImage);
//        stage.addActor(Mark1TankImage);
//        stage.addActor(ToxicTankImage);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

        settings_button_image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InGameMenu(game));
            }
        });

//        if(Objects.equals(player1_tank, atomicTank1.getTankName())){
//            Mark1TankImage.remove();
//            ToxicTankImage.remove();
//        }
//        if (Objects.equals(player1_tank, mark1Tank1.getTankName())){
//            AtmoicTankImage.remove();
//            ToxicTankImage.remove();
//        }
//        if (Objects.equals(player1_tank, toxicTank1.getTankName())){
//            AtmoicTankImage.remove();
//            Mark1TankImage.remove();
//        }





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
