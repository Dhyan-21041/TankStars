package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.*;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.MenuScreen;

import java.util.ArrayList;
import java.util.Objects;

import static com.mygdx.game.Screens.GameScreen.BulletCreation.bulletType;
import static java.lang.Float.parseFloat;

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

    private Texture healthbar_player1;
    private Texture healthbar_player2;

    public static Float Fuel_player1=1f;
    public static Float Fuel_player2=1f;

    private Texture fuelbar_player1;
    private Texture fuelbar_player2;

    private Texture background;
    private Image background_image;

    private Texture Ground;
    private Image Ground_image;

    private Texture settings_button;
    private Image settings_button_image;


    private Texture img;
    private Image img_background;

    private Texture ResumeButton;
    private Texture SoundButton;
    private Texture SaveButton;
    private Texture MainmenuButton;
    private  Image img_ResumeButton;
    private  Image img_SoundButton;
    private  Image img_SaveButton;
    private  Image img_MainmenuButton;

    private String NextTurn;

    public static int player1=0;
    public static int player2=0;

    public String text;

    private Texture health1;
    private Texture health2;

    private Image health1_image;
    private Image health2_image;

    private Texture fuel1;
    private Texture fuel2;

    private Image fuel1_image;
    private Image fuel2_image;





    public GameScreen(AP_Game game) {
        System.out.println(MenuScreen.flag);

        this.game = game;
        camera = new OrthographicCamera();
        this.stage = new Stage(new StretchViewport(AP_Game.WIDTH, AP_Game.HEIGHT, camera));
        Gdx.input.setInputProcessor(stage);

        aim = new Texture("aim.png");
        aim_image = new Image(aim);
        aim2 = new Texture("aim.png");
        aim_image2 = new Image(aim2);

        batch = new SpriteBatch();









//        if(game.getPlayer1Tank().equals("AtomicTank")){
//            player1=1;
//            player1Tank = new Texture("Atomic.png");}
//
//        else if(game.getPlayer1Tank().equals("ToxicTank")){
//            player1=2;
//            player1Tank = new Texture("Toxic.png");}
//        else if(game.getPlayer1Tank().equals("Mark1Tank")){
//            player1=3;
//            player1Tank = new Texture("Mark1.png");}
//
//
//
//
//        if(game.getPlayer2Tank().equals("AtomicTank")){
//            player2=1;
//            player2Tank = new Texture("Atomic.png");}
//        else if(game.getPlayer2Tank().equals("ToxicTank")){
//            player2=2;
//            player2Tank = new Texture("Toxic.png");}
//        else if(game.getPlayer2Tank().equals("Mark1Tank")){
//            player2=3;
//            player2Tank = new Texture("Mark1.png");}
//
//
//        healthbar_player1 = new Texture("Health.png");
//        healthbar_player2 = new Texture("Health.png");
//
//        fuelbar_player1 = new Texture("fuel.png");
//        fuelbar_player2 = new Texture("fuel.png");

        if (MenuScreen.flag==0){
            if(game.getPlayer1Tank().equals("AtomicTank")){
                player1=1;
                player1Tank = new Texture("Atomic.png");}

            else if(game.getPlayer1Tank().equals("ToxicTank")){
                player1=2;
                player1Tank = new Texture("Toxic.png");}
            else if(game.getPlayer1Tank().equals("Mark1Tank")){
                player1=3;
                player1Tank = new Texture("Mark1.png");}




            if(game.getPlayer2Tank().equals("AtomicTank")){
                player2=1;
                player2Tank = new Texture("Atomic.png");}
            else if(game.getPlayer2Tank().equals("ToxicTank")){
                player2=2;
                player2Tank = new Texture("Toxic.png");}
            else if(game.getPlayer2Tank().equals("Mark1Tank")){
                player2=3;
                player2Tank = new Texture("Mark1.png");}

            Fuel_player1 = 1f;
            Fuel_player2 = 1f;
            TypesOfCollision.Health_Player1 = 1;
            TypesOfCollision.Health_Player2 = 1;


            healthbar_player1 = new Texture("Health.png");
            healthbar_player2 = new Texture("Health.png");

            fuelbar_player1 = new Texture("fuel.png");
            fuelbar_player2 = new Texture("fuel.png");

        }
        else if (MenuScreen.flag == 1) {

            String arr[] = new String[7];
            int i=0;

            if(ChooseLoadScreen.file=="file1.txt"){
                System.out.println("entered");
                String file = "file1.txt";
                FileHandle file1 = Gdx.files.local(file);
                text = file1.readString();
                System.out.println(text);
            } else if (ChooseLoadScreen.file=="file2.txt"){
                String file = "file2.txt";
                FileHandle file1 = Gdx.files.local(file);
                text = file1.readString();
            } else if (ChooseLoadScreen.file=="file3.txt"){
                String file = "file3.txt";
                FileHandle file1 = Gdx.files.local(file);
                text = file1.readString();
            }





            System.out.println(text);
            for (String line : text.split("\n")) {
                arr[i] = line;
                i++;
            }
            //hp1,hp2,fp1,fp2
            for (int j = 0; j < 7; j++) {
                System.out.println(arr[j]);
            }

            Fuel_player1 = parseFloat(arr[2]);
            Fuel_player2 = parseFloat(arr[3]);
            TypesOfCollision.Health_Player1 = parseFloat(arr[0]);
            TypesOfCollision.Health_Player2 = parseFloat(arr[1]);

            healthbar_player1 = new Texture("Health.png");
            healthbar_player2 = new Texture("Health.png");

            fuelbar_player1 = new Texture("fuel.png");
            fuelbar_player2 = new Texture("fuel.png");



            if (arr[4].equals("1")) {
                player1 = 1;
                player1Tank = new Texture("Atomic.png");
            } else if (arr[4].equals("2")) {
                player1 = 2;
                player1Tank = new Texture("Toxic.png");
            } else if (arr[4].equals("3")) {
                player1 = 3;
                player1Tank = new Texture("Mark1.png");
            }


            if (arr[5].equals("1")) {
                player2 = 1;
                player2Tank = new Texture("Atomic.png");
            } else if (arr[5].equals("2")) {
                player2 = 2;
                player2Tank = new Texture("Toxic.png");
            } else if (arr[5].equals("3")) {
                player2 = 3;
                player2Tank = new Texture("Mark1.png");





            }


        }

        background = new Texture("IngameBack.png");
        background_image = new Image(background);

        background_image.setSize(AP_Game.WIDTH,AP_Game.HEIGHT);
        background_image.setPosition(0,0);
        stage.addActor(background_image);


        Ground = new Texture("Ground_texture.png");
        Ground_image = new Image(Ground);

        Ground_image.setPosition(0,0);
        Ground_image.setSize(1500,250);
        stage.addActor(Ground_image);

        settings_button = new Texture("settingsbutton.png");
        settings_button_image = new Image(settings_button);
        settings_button_image.setSize(120,60);
        settings_button_image.setPosition(10,640);
        stage.addActor(settings_button_image);


        img = new Texture("settings.png");
        img_background = new Image(img);
        img_background.setSize(240, 300);
        img_background.setPosition(500,260);


        health1 = new Texture("healthimg.png");
        health2 = new Texture("healthimg.png");

        health1_image = new Image(health1);
        health2_image = new Image(health2);

        health1_image.setSize(150,60);
        health1_image.setPosition(215,590);
        health2_image.setSize(150,60);
        health2_image.setPosition(890,590);
        stage.addActor(health2_image);
        stage.addActor(health1_image);

        fuel1 = new Texture("fuelimg.png");
        fuel2 = new Texture("fuelimg.png");
        fuel1_image = new Image(fuel1);
        fuel2_image = new Image(fuel2);

        fuel1_image.setSize(100,50);
        fuel1_image.setPosition(250,130);
        fuel2_image.setSize(100,50);
        fuel2_image.setPosition(950,130);
        stage.addActor(fuel2_image);
        stage.addActor(fuel1_image);


        // SAVE BUTTON doesnt make any sense

        ResumeButton = new Texture("Resume.png");
        SoundButton = new Texture("Sound.png");
        SaveButton = new Texture("save 1.png");
        MainmenuButton = new Texture("MainmenuButton copy.png");

        img_ResumeButton = new Image(ResumeButton);
        img_ResumeButton.setSize(100, 50);
        img_ResumeButton.setPosition(575,440);
        img_SoundButton = new Image(SoundButton);
        img_SoundButton.setSize(100, 50);
        img_SoundButton.setPosition(575,385);
        img_SaveButton = new Image(SaveButton);
        img_SaveButton.setSize(100, 50);
        img_SaveButton.setPosition(575,332);
        img_MainmenuButton = new Image(MainmenuButton);
        img_MainmenuButton.setSize(100, 50);
        img_MainmenuButton.setPosition(575,280);



        world = new World(new Vector2(0, -11f), false);
        this.world.setContactListener(new MyContactListener());

    }




    @Override
    public void show() {






    debugRenderer = new Box2DDebugRenderer();
    
    InputMultiplexer inputMultiplexer = new InputMultiplexer();
    inputMultiplexer.addProcessor(stage);


    settings_button_image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InGameMenu();

            }
        });

    inputMultiplexer.addProcessor(new InputController(){
        @Override
        public boolean keyDown(int keycode) {
            if(Fuel_player1>0){
                switch (keycode){

                case Input.Keys.D:
                    player1TankBody.applyLinearImpulse(speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                    Fuel_player1-=0.01f;

                    break;
                case Input.Keys.A:
                    player1TankBody.applyLinearImpulse(-speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                    Fuel_player1-=0.01f;

                    break;
                }

            }
            else if(Fuel_player1<=0){
                player1TankBody.setActive(false);

            }
            if(Fuel_player2>0){
                switch (keycode){

                    case Input.Keys.LEFT:
                        player2TankBody.applyLinearImpulse(-speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                        Fuel_player2-=0.01f;

                        break;
                    case Input.Keys.RIGHT:
                        player2TankBody.applyLinearImpulse(speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                        Fuel_player2-=0.01f;

                        break;
                }

            } else if (Fuel_player2<=0){
                player2TankBody.setActive(false);

            }
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
                case Input.Keys.S:
                    movement.y = -speed;
                    break;
                case Input.Keys.DOWN:
                    movement2.y = -speed;
                    break;
                case Input.Keys.F:

                    BulletCreation b = BulletCreation.getInstance();

                    bullet.type = bulletType;

                    NextTurn="Player2";

                    CircleShape ballshape = new CircleShape();
                    ballshape.setRadius(5);

                    FixtureDef bulletFixture = new FixtureDef();
                    bulletFixture.shape = ballshape;
                    bulletFixture.density = b.getBulletDensity() ;
                    bulletFixture.friction = b.getBulletFriction();
                    bulletFixture.restitution = b.getBulletRestitution();

                    bullet.position.set(70+player1TankBody.getPosition().x, 20+player1TankBody.getPosition().y);

                    bulletBody = world.createBody(bullet);
                    BulletBodies.add(bulletBody);
                    bulletBody.createFixture(bulletFixture).setUserData("bullet");
                    bulletBody.applyLinearImpulse((mouse_x - player1TankBody.getPosition().x)*100, (mouse_y - player1TankBody.getPosition().y)*200, player1TankBody.getPosition().x, player1TankBody.getPosition().y, true);
                    break;

                case Input.Keys.SPACE:
                    bullet.type = BodyDef.BodyType.DynamicBody;

                    BulletCreation b1 = BulletCreation.getInstance();

                    NextTurn="Player1";

                    CircleShape ballshape2 = new CircleShape();
                    ballshape2.setRadius(5);

                    FixtureDef bulletFixture2 = new FixtureDef();
                    bulletFixture2.shape = ballshape2;
                    bulletFixture2.density = b1.getBulletDensity();
                    bulletFixture2.friction = b1.getBulletDensity();
                    bulletFixture2.restitution = b1.getBulletDensity();

                    bullet.position.set(-70+player2TankBody.getPosition().x, 20+player2TankBody.getPosition().y);

                    bulletBody = world.createBody(bullet);
                    BulletBodies.add(bulletBody);
                    bulletBody.createFixture(bulletFixture2).setUserData("bullet");
                    bulletBody.applyLinearImpulse((mouse_x_2 - player2TankBody.getPosition().x)*50, (mouse_y_2 - player2TankBody.getPosition().y)*300, player2TankBody.getPosition().x, player2TankBody.getPosition().y, true);
                    break;

                case Input.Keys.ESCAPE:
                    new SaveGame();
                    break;


            }
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            if(Fuel_player1>0){
                switch (keycode){
                    case Input.Keys.D:
                        player1TankBody.applyLinearImpulse(-speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                        movement.x = 0;
                        break;
                    case Input.Keys.A:
                        player1TankBody.applyLinearImpulse(speed, 0, player1TankBody.getWorldCenter().x, player1TankBody.getWorldCenter().y, true);
                        movement.x = 0;
                        break;
                }

            }
            else if(Fuel_player1<=0){
                System.out.println("Out of fuel");
            }
            if(Fuel_player2>0){
                switch (keycode){

                    case Input.Keys.LEFT:
                        player2TankBody.applyLinearImpulse(speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                        movement2.x = 0;
                        break;
                    case Input.Keys.RIGHT:
                        player2TankBody.applyLinearImpulse(-speed, 0, player2TankBody.getWorldCenter().x, player2TankBody.getWorldCenter().y, true);
                        movement2.x = 0;
                        break;
                }

            } else if (Fuel_player2<=0){
                System.out.println("Out of fuel");
            }

            return true;
        }


    });

    Gdx.input.setInputProcessor(inputMultiplexer);




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

        player2TankSprite = new Sprite(player2Tank);




        groundshape.dispose();
        recshape.dispose();


    }


    @Override
    public void render(float delta) {

        

        if(Gdx.input.justTouched()){

            mouse_x= 260;
            mouse_y= 255;
            aim_image.setPosition(mouse_x, mouse_y);
            stage.addActor(aim_image);


            mouse_x_2= 850;
            mouse_y_2= 255;
            aim_image2.setPosition(mouse_x_2, mouse_y_2);
            stage.addActor(aim_image2);


            aim_image.setSize(150, 100);
            aim_image2.setSize(150, 100);

        }

        ArrayList<Explosion> explosions_remove = new ArrayList<Explosion>();
        for (Explosion explosion: TypesOfCollision.explosions_array) {
            explosion.update(delta);

            if(explosion.isFinished){

                explosions_remove.add(explosion);
            }
        }
        TypesOfCollision.explosions_array.removeAll(explosions_remove);



        update(delta);
        stage.draw();

        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        world.getBodies(bodies);
        for(Body body : bodies)
            if(body.getUserData() instanceof Sprite){
                Sprite sprite = (Sprite) body.getUserData();
                sprite.setPosition(-100+((body.getPosition().x)), (-25+(body.getPosition().y)));
                sprite.draw(batch);
            }



        for (Body body: TypesOfCollision.BulletBodies) {
            world.destroyBody(body);

            for(Explosion explosion : TypesOfCollision.explosions_array){
                explosion.render(batch);
            }

            PlayerTurn();

        }
        TypesOfCollision.BulletBodies.clear();



        batch.draw(healthbar_player1, 100, 570, 400*TypesOfCollision.Health_Player1, 30);


        batch.draw(healthbar_player2, 780, 570, 400*TypesOfCollision.Health_Player2, 30);




        if(Fuel_player1>0){batch.draw(fuelbar_player1, 200, 180, 200*Fuel_player1, 20);}

        if(Fuel_player2>0){batch.draw(fuelbar_player2, 900, 180, 200*Fuel_player2, 20);}



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

    public void InGameMenu(){

        stage.addActor(img_background);
        stage.addActor(img_MainmenuButton);
        stage.addActor(img_ResumeButton);
        stage.addActor(img_SoundButton);
        stage.addActor(img_SaveButton);

        img_ResumeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                img_background.remove();
                img_MainmenuButton.remove();
                img_ResumeButton.remove();
                img_SoundButton.remove();
                img_SaveButton.remove();

            }
        });

        img_MainmenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

    }

    public void PlayerTurn(){
        if(Objects.equals(NextTurn, "Player1")){
            System.out.println("Player 1 Turn");
        }
        if(Objects.equals(NextTurn, "Player2")){
            System.out.println("Player 2 Turn");
        }
    }

}
