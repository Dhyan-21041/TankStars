package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
    float x;
    float y;
    public static final float FrameLength = 0.5f;
    public static final int Size=32;


    private float stateTime;
    public boolean isFinished=false;
    private static Animation<TextureRegion> anim =null;


    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;

        stateTime = 0;

        if(anim ==null){

            anim = new Animation(FrameLength, new TextureRegion(new Texture("explosion.png"), Size, Size));

        }


    }

    public void update(float delta){
        stateTime+=delta;
        if(anim.isAnimationFinished(stateTime)){
            isFinished=true;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(anim.getKeyFrame(stateTime), x, y, 200, 150);
    }


}
