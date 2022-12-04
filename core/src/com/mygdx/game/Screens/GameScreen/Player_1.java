package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.TankFunctionality.AtomicTankFunctionality;
import com.mygdx.game.Screens.TankFunctionality.MarkTankFunctionality;
import com.mygdx.game.Screens.TankFunctionality.ToxicTankFunctionality;
public class Player_1 implements Screen {

    private float health;
    private Texture healthBar;
    private Image healthBar_image;

    private float fuel;
    private Texture fuelBar;
    private Image fuelBar_image;

    private AtomicTankFunctionality atomicTankFunctionality;
    private MarkTankFunctionality markTankFunctionality;
    private ToxicTankFunctionality toxicTankFunctionality;

    public Player_1(AP_Game game){
        atomicTankFunctionality = new AtomicTankFunctionality();
        markTankFunctionality = new MarkTankFunctionality();
        toxicTankFunctionality = new ToxicTankFunctionality();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    public void Health(){

    }

}
