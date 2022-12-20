package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class BulletCreation {


    private static BulletCreation bullet = new BulletCreation();
    public static BodyDef.BodyType bulletType = BodyDef.BodyType.DynamicBody;
    public static float bulletDensity = 0.1f;
    public static float bulletFriction = 0.4f;
    public static float bulletRestitution = 0.6f;
    public static float bulletRadius = 5;

    private BulletCreation() {
    }

    public static BulletCreation getInstance() {
        return bullet;
    }

    public float getBulletRadius() {
        return bulletRadius;
    }
    public float getBulletDensity() {
        return bulletDensity;
    }
    public float getBulletFriction() {
        return bulletFriction;
    }
    public float getBulletRestitution() {
        return bulletRestitution;
    }





    }
