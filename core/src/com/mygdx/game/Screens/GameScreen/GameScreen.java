package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.player1.AtomicTank1;
import com.mygdx.game.Screens.player1.Mark1Tank1;
import com.mygdx.game.Screens.player1.ToxicTank1;
import com.badlogic.gdx.InputProcessor;

import java.util.Objects;

import static com.mygdx.game.AP_Game.camera;

public class GameScreen implements Screen {

    private AP_Game game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private BodyDef bodyDef = new BodyDef();

    private Body body;

    private FixtureDef fixtureDef = new FixtureDef();;

    private PolygonShape shape;
    private Stage stage;
    private ChainShape groundshape;

    private Texture player1Tank;
    private Texture player2Tank;

    private Image player1TankImage;
    private Image player2TankImage;

    private Vector2 movement = new Vector2();
    private Vector2 movement2 = new Vector2();
    private float speed = 25000;

    private Body player1TankBody;
    private Body player2TankBody;





    public GameScreen(AP_Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);

        if(game.getPlayer1Tank().equals("AtomicTank")){
            player1Tank = new Texture("Atomic.png");}
        else if(game.getPlayer1Tank().equals("ToxicTank")){
            player1Tank = new Texture("Toxic.png");}
        else if(game.getPlayer1Tank().equals("Mark1Tank")){
            player1Tank = new Texture("Mark1.png");}

        if(game.getPlayer2Tank().equals("AtomicTank")){
            player2Tank = new Texture("Atomic.png");}
        else if(game.getPlayer2Tank().equals("ToxicTank")){
            player2Tank = new Texture("Toxic_reverse.png");}
        else if(game.getPlayer2Tank().equals("Mark1Tank")){
            player2Tank = new Texture("Mark1.png");}

        player1TankImage = new Image(player1Tank);
        player2TankImage = new Image(player2Tank);

        player1TankImage.setPosition(0,200);
        player2TankImage.setPosition(1000,200);

        stage.addActor(player1TankImage);
        stage.addActor(player2TankImage);

        }




    @Override
    public void show() {
    world = new World(new Vector2(0, -200f), false);
    debugRenderer = new Box2DDebugRenderer();
    camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


    Gdx.input.setInputProcessor((new InputController() {
                @Override
                public boolean keyDown(int keycode) {
                    switch (keycode) {
                        case Input.Keys.W:
                            movement.y = speed;
                            break;
                        case Input.Keys.UP:
                            movement2.y = speed;
                            break;
                        case Input.Keys.A:
                            movement.x = -speed;
                            break;
                        case Input.Keys.LEFT:
                            movement2.x = -speed;
                            break;
                        case Input.Keys.S:
                            movement.y = -speed;
                            break;
                        case Input.Keys.DOWN:
                            movement2.y = -speed;
                            break;
                        case Input.Keys.D:
                            movement.x = speed;
                            break;
                        case Input.Keys.L:
                            movement2.x = speed;
                            break;

                    }
                    return true;
                }

                @Override
                public boolean keyUp(int keycode) {
                    switch (keycode) {
                        case Input.Keys.W:
                            movement.y = 0;
                            break;
                        case Input.Keys.A:
                            movement.x = 0;
                            break;
                        case Input.Keys.S:
                            movement.y = 0;
                            break;
                        case Input.Keys.D:
                            movement.x = 0;
                            break;
                    }
                    return true;
                }


            }));




        //Circle body

        BodyDef balldef1 = new BodyDef();
        balldef1.type = BodyDef.BodyType.DynamicBody;

        BodyDef balldef2 = new BodyDef();
        balldef2.type = BodyDef.BodyType.DynamicBody;

        //ball shape

        CircleShape ballshape = new CircleShape();
        ballshape.setRadius(10);


    //fixture def

        FixtureDef Player1 = new FixtureDef();
        Player1.shape = ballshape;
        Player1.density = 2.5f;
        Player1.friction = 0.70f;
        Player1.restitution = 0.75f;
        balldef1.position.set(-50, 100);

        FixtureDef Player2 = new FixtureDef();
        Player2.shape = ballshape;
        Player2.density = 2.5f;
        Player2.friction = 0.70f;
        Player2.restitution = 0.75f;
        balldef2.position.set(50, 100);



    //Ground DEFINITION
    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set(0, 1);
    groundshape = new ChainShape();
    groundshape.createChain(new Vector2[]{
            new Vector2(-10000, -100),
            new Vector2(10000, -100),

        });

        //fixture definition
        fixtureDef.shape = groundshape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;


        //CREATION
        player1TankBody = world.createBody(balldef1);
        player1TankBody.createFixture(Player1);
        player2TankBody = world.createBody(balldef2);
        player2TankBody.createFixture(Player2);
        world.createBody(bodyDef).createFixture(fixtureDef);
        groundshape.dispose();
        ballshape.dispose();


    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 8, 3);
        player1TankBody.applyForceToCenter(movement, true);
        //player2TankBody.applyForceToCenter(movement2, true);

        game.batch.begin();
        game.batch.end();

    }

    private void update(float delta){
        stage.act(delta);

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
}
