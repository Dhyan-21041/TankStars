package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Screens.GameScreen.Player_1;
import com.mygdx.game.Screens.GameScreen.TypesOfCollision;
import com.mygdx.game.Screens.LoadingScreen;
import com.mygdx.game.Screens.MenuScreen;


public class AP_Game extends Game {
	public SpriteBatch batch;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static OrthographicCamera camera;

	public LoadingScreen loadingScreen;
	public MenuScreen menuScreen;






	public BitmapFont font;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		loadingScreen = new LoadingScreen(this);
		menuScreen = new MenuScreen(this);

		setScreen(loadingScreen);

		Gdx.graphics.setTitle("Tank Stars");
		Font();

	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		loadingScreen.dispose();
		menuScreen.dispose();
	}

	public void Font(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Arcon.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 25;
		font = generator.generateFont(parameter);
		generator.dispose();

	}

	public TypesOfCollision typesOfCollision = new TypesOfCollision();



	public String Player1TankName;
	public String Player2TankName;

	public void setPlayer1TankName(String name){
		Player1TankName = name;
	}
	public void setPlayer2TankName(String name){
		Player2TankName = name;
	}
	public String getPlayer1Tank(){
		return Player1TankName;
	}

	public String getPlayer2Tank(){
		return Player2TankName;
	}


}
