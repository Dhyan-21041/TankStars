package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.physics.box2d.*;

public class MyContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        Fixture fixC

        if(fixA==null || fixB==null ) return;
        if(fixA.getUserData() == null || fixB.getUserData() == null) return;

        System.out.println("Contact");

        if (isTutorialContact(fixA, fixB , fixC)) {
            System.out.println("Tutorial Contact");
        }
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

    private boolean isTutorialContact(Fixture fixA, Fixture fixB , Fixture fixC) {
        if(fixA.getUserData() == "ground" || fixB.getUserData() == "bullet"){
            System.out.println("Contact 1");
            return true;
        }
        return false;
    }
}
