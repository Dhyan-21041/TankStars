package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.player1.AtomicTank1;
import com.mygdx.game.Screens.player1.Mark1Tank1;
import com.mygdx.game.Screens.player1.ToxicTank1;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.mygdx.game.AP_Game.camera;

public class GameScreen implements Screen {

    //ap game

    private AP_Game game;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;

    private BodyDef bodyDef = new BodyDef();

    private FixtureDef fixtureDef = new FixtureDef();;

    private PolygonShape shape;
    private Stage stage;
    private ChainShape groundshape;

    private Texture player1Tank;
    private Texture player2Tank;

    private Vector2 movement = new Vector2();
    private Vector2 movement2 = new Vector2();
    private float speed = 50000;

    private Body player1TankBody;
    private Body player2TankBody;

    private Sprite player1TankSprite;
    private Sprite player2TankSprite;

    private SpriteBatch batch;

    private Array<Body> bodies= new Array<Body>();

    private float mouse_x;
    private float mouse_y;

    private float mouse_x_2;
    private float mouse_y_2;

    private Texture aim;
    private Image aim_image;

    private Texture aim2;
    private Image aim_image2;

    private BodyDef bullet = new BodyDef();

    public ArrayList<Body> BulletBodies = new ArrayList<Body>();

    private Body bulletBody;

    private Texture explosion;
    private Image explosion_image;

    private boolean isExplosion = false;







    public GameScreen(AP_Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);

        aim = new Texture("aim.png");
        aim_image = new Image(aim);
        aim2 = new Texture("aim.png");
        aim_image2 = new Image(aim2);

        batch = new SpriteBatch();

        if(game.getPlayer1Tank().equals("AtomicTank")){
            player1Tank = new Texture("Atomic.png");}
        else if(game.getPlayer1Tank().equals("ToxicTank")){
            player1Tank = new Texture("Toxic.png");}
        else if(game.getPlayer1Tank().equals("Mark1Tank")){
            player1Tank = new Texture("Mark1.png");}

        if(game.getPlayer2Tank().equals("AtomicTank")){
            player2Tank = new Texture("Atomic.png");}
        else if(game.getPlayer2Tank().equals("ToxicTank")){
            player2Tank = new Texture("Toxic.png");}
        else if(game.getPlayer2Tank().equals("Mark1Tank")){
            player2Tank = new Texture("Mark1.png");}

        explosion = new Texture("explosion.png");
        explosion_image = new Image(explosion);




        world = new World(new Vector2(0, -11f), false);
        this.world.setContactListener(new MyContactListener());

        }




    @Override
    public void show() {
    debugRenderer = new Box2DDebugRenderer();

//    Gdx.input.setInputProcessor(multiplexer);
//    multiplexer.addProcessor(stage);


    Gdx.input.setInputProcessor((new InputController() {
                @Override
                public boolean keyDown(int keycode) {
                    switch (keycode) {
                        case Input.Keys.Y:
                            mouse_y=mouse_y+5;
                            aim_image.moveBy(0, 5);
                            break;
                        case Input.Keys.H:
                            mouse_y=mouse_y-5;
                            aim_image.moveBy(0, -5);
                            break;
                        case Input.Keys.G:
                            mouse_x=mouse_x-5;
                            aim_image.moveBy(-5, 0);
                            break;
                        case Input.Keys.J:
                            mouse_x=mouse_x+5;
                            aim_image.moveBy(5, 0);
                            break;

                        case Input.Keys.O:
                            mouse_y_2=mouse_y_2+5;
                            aim_image2.moveBy(0, 5);
                            break;
                        case Input.Keys.L:
                            mouse_y_2=mouse_y_2-5;
                            aim_image2.moveBy(0, -5);
                            break;
                        case Input.Keys.I:
                            mouse_x_2=mouse_x_2-5;
                            aim_image2.moveBy(-5, 0);
                            break;
                        case Input.Keys.P:
                            mouse_x_2=mouse_x_2+5;
                            aim_image2.moveBy(5, 0);
                            break;

                        case Input.Keys.W:
                            movement.y = speed;
                            break;
                        case Input.Keys.UP:
                            movement2.y = speed;
                            break;
                        case Input.Keys.A:
                            player1TankBody.applyLinearImpulse(-speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                            movement.x = -speed;
                            break;
                        case Input.Keys.LEFT:
                            player2TankBody.applyLinearImpulse(-speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                            movement2.x = -speed;
                            break;
                        case Input.Keys.S:
                            movement.y = -speed;
                            break;
                        case Input.Keys.DOWN:
                            movement2.y = -speed;
                            break;
                        case Input.Keys.D:
                            player1TankBody.applyLinearImpulse(speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                            movement.x = speed;
                            break;
                        case Input.Keys.RIGHT:
                            player2TankBody.applyLinearImpulse(speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                            movement2.x = speed;
                            break;
                        case Input.Keys.F:
                            bullet.type = BodyDef.BodyType.DynamicBody;

                            CircleShape ballshape = new CircleShape();
                            ballshape.setRadius(5);

                            FixtureDef bulletFixture = new FixtureDef();
                            bulletFixture.shape = ballshape;
                            bulletFixture.density = 0.3f;
                            bulletFixture.friction = 0.4f;
                            bulletFixture.restitution = 0.5f;

                            bullet.position.set(70+player1TankBody.getPosition().x, 20+player1TankBody.getPosition().y);

                            bulletBody = world.createBody(bullet);
                            BulletBodies.add(bulletBody);
                            bulletBody.createFixture(bulletFixture).setUserData("bullet");
                            bulletBody.applyLinearImpulse((mouse_x - player1TankBody.getPosition().x)*50, (mouse_y - player1TankBody.getPosition().y)*300, player1TankBody.getPosition().x, player1TankBody.getPosition().y, true);
                            break;

                        case Input.Keys.SPACE:
                            bullet.type = BodyDef.BodyType.DynamicBody;

                            CircleShape ballshape2 = new CircleShape();
                            ballshape2.setRadius(5);

                            FixtureDef bulletFixture2 = new FixtureDef();
                            bulletFixture2.shape = ballshape2;
                            bulletFixture2.density = 0.3f;
                            bulletFixture2.friction = 0.4f;
                            bulletFixture2.restitution = 0.5f;

                            bullet.position.set(-70+player2TankBody.getPosition().x, 20+player2TankBody.getPosition().y);

                            bulletBody = world.createBody(bullet);
                            BulletBodies.add(bulletBody);
                            bulletBody.createFixture(bulletFixture2).setUserData("bullet");
                            bulletBody.applyLinearImpulse((mouse_x_2 - player2TankBody.getPosition().x)*50, (mouse_y_2 - player2TankBody.getPosition().y)*300, player2TankBody.getPosition().x, player2TankBody.getPosition().y, true);
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
                        case Input.Keys.UP:
                            movement2.y = 0;
                            break;
                        case Input.Keys.A:
                            player1TankBody.applyLinearImpulse(speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                            movement.x = 0;
                            break;
                        case Input.Keys.LEFT:
                            player2TankBody.applyLinearImpulse(speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                            movement2.x = 0;
                            break;
                        case Input.Keys.D:
                            player1TankBody.applyLinearImpulse(-speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                            movement.x = 0;
                            break;
                        case Input.Keys.RIGHT:
                            player2TankBody.applyLinearImpulse(-speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                            movement2.x = 0;
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

        PolygonShape recshape = new PolygonShape();
        recshape.setAsBox(50,25);


    //fixture def

        FixtureDef Player1 = new FixtureDef();
        Player1.shape = recshape;
        Player1.density = 1.0f;
        Player1.friction = 0f;
        Player1.restitution = 0.25f;
        balldef1.position.set(150, 290);

        FixtureDef Player2 = new FixtureDef();
        Player2.shape = recshape;
        Player2.density = 1.0f;
        Player2.friction = 0f;
        Player2.restitution = 0.25f;
        balldef2.position.set(1100, 290);



    //Ground DEFINITION
    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set(0, 1);
    groundshape = new ChainShape();
    groundshape.createChain(new Vector2[]{
            new Vector2(-10000, 250),
            new Vector2(10000, 250),

        });

        //fixture definition
        fixtureDef.shape = groundshape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.2f;


        //CREATION
        player1TankBody = world.createBody(balldef1);
        player1TankBody.createFixture(Player1).setUserData("player1TankBody");

        player1TankSprite = new Sprite(player1Tank);
        player1TankSprite.setSize(200, 70);


        player1TankBody.setUserData(player1TankSprite);

        player2TankBody = world.createBody(balldef2);
        player2TankBody.createFixture(Player2).setUserData("player2TankBody");

        player2TankSprite = new Sprite(player2Tank);
        player2TankSprite.setSize(200, 70);
        player2TankSprite.flip(true, false);


        player2TankBody.setUserData(player2TankSprite);

        world.createBody(bodyDef).createFixture(fixtureDef).setUserData("ground");



        explosion_image.setSize(100, 100);




        groundshape.dispose();
        recshape.dispose();


    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.justTouched()){

            mouse_x= 300;
            mouse_y= 300;
            aim_image.setPosition(mouse_x, mouse_y);
            stage.addActor(aim_image);


            mouse_x_2= 800;
            mouse_y_2= 300;
            aim_image2.setPosition(mouse_x_2, mouse_y_2);
            stage.addActor(aim_image2);


            aim_image.setSize(150, 100);
            aim_image2.setSize(150, 100);

        }


        update(delta);
        stage.draw();

        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 3);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        world.getBodies(bodies);
        for(Body body : bodies)
            if(body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(-70+((body.getPosition().x)), (-25+(body.getPosition().y)));
                if(sprite == player2TankSprite){
                    sprite.setPosition(-130+((body.getPosition().x)), (-25+(body.getPosition().y)));
                }
                sprite.draw(batch);
            }


        for (Body body: TypesOfCollision.BulletBodies) {
            explosion_image.setPosition(-30+body.getPosition().x, -30+body.getPosition().y);
            world.destroyBody(body);
            player1TankBody.setLinearVelocity(0, 0);

            stage.addActor(explosion_image);
            isExplosion = true;

        }
        TypesOfCollision.BulletBodies.clear();


        if(isExplosion){

            explosion_image.addAction(Actions.sequence(Actions.delay(0.5f),Actions.fadeOut(0.1f), Actions.removeActor()));
            System.out.println("explosion");
            isExplosion = false;
        }







        batch.end();

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
        world.dispose();
        stage.dispose();

    }
}
