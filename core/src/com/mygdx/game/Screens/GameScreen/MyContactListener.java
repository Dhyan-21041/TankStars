package com.mygdx.game.Screens.GameScreen;


import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.AP_Game;


public class MyContactListener extends AP_Game implements ContactListener {




    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA==null || fixB==null ) return;
        if(fixA.getUserData() == null || fixB.getUserData() == null) return;

        System.out.println("Contact");
        isGroundBullet(fixA, fixB);
        isBody1Bullet(fixA, fixB);
        isBody2Bullet(fixA, fixB);

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();


        if(fixA==null || fixB==null) return;
        if(fixA.getUserData() == null || fixB.getUserData() == null) return;

        System.out.println("collision ended");

    }


    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean isGroundBullet(Fixture fixA, Fixture fixB) {
        if(fixA.getUserData() == "ground" && fixB.getUserData() == "bullet"){
            System.out.println("Collision between ground and bullet");

            TypesOfCollision.BulletBodies.add(fixB.getBody());

            return true;
        }
        return false;
    }

    private boolean isBody1Bullet(Fixture fixA, Fixture fixB) {
        if(fixA.getUserData() == "player1TankBody" && fixB.getUserData() == "bullet" || fixA.getUserData() == "player2TankBody" && fixB.getUserData() == "bullet") {
            System.out.println("Collision between Player1 and bullet");

            TypesOfCollision.BulletBodies.add(fixB.getBody());

            return true;
        }
        return false;
    }

    private boolean isBody2Bullet(Fixture fixA, Fixture fixB) {
        if(fixA.getUserData() == "player2TankBody" && fixB.getUserData() == "bullet") {
            System.out.println("Collision between Player2 and bullet");

            TypesOfCollision.BulletBodies.add(fixB.getBody());

            return true;
        }
        return false;
    }





}
