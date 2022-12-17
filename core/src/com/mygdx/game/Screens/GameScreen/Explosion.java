package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
    public float x;
    public float y;
    public static final float FrameLength = 0.5f;
    public int Size=32;


    private float stateTime;
    private boolean isFinished=false;
    private static Animation explosionAnimation=null;


    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;

        stateTime = 0;

        if(explosionAnimation==null){
            explosionAnimation = new Animation(FrameLength, TextureRegion.split(new Texture("explosion.png"), Size, Size)[0]);
        }

    }

    public void update(float delta){
        stateTime+=delta;
        if(explosionAnimation.isAnimationFinished(stateTime)){
            isFinished=true;
        }
    }

    public void render(SpriteBatch sprite){
        sprite.draw((Texture) explosionAnimation.getKeyFrame(stateTime), x, y);
    }

}
