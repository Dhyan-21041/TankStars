package com.mygdx.game.Screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.AP_Game;
import com.mygdx.game.Screens.MenuScreen;

public class SaveGame implements Screen {


    public SaveGame(AP_Game game){


        int i=0;
        FileHandle file1 = Gdx.files.local("file1.txt");
        FileHandle file2 = Gdx.files.local("file2.txt");
        FileHandle file3 = Gdx.files.local("file3.txt");
        String text1=file1.readString();
        String text2=file2.readString();
        String text3=file3.readString();
        System.out.println("wasup"+text1);

        if(text1.length()==0){
            FileHandle file = Gdx.files.local("file1.txt");
            String a = Float.toString(TypesOfCollision.Health_Player1);
            file.writeString(a+"\n", false);

            String b = Float.toString(TypesOfCollision.Health_Player2);
            file.writeString(b+"\n", true);

            String c = Float.toString(GameScreen.Fuel_player1);
            file.writeString(c+"\n", true);

            String d = Float.toString(GameScreen.Fuel_player2);
            file.writeString(d+"\n", true);

            if (GameScreen.player1==1){
                file.writeString("1"+"\n", true);
            }
            else if (GameScreen.player1==2){
                file.writeString("2"+"\n", true);
            }
            else if (GameScreen.player1==3){
                file.writeString("3"+"\n", true);
            }

            if (GameScreen.player2==1){
                file.writeString("1"+"\n", true);
            }
            else if (GameScreen.player2==2){
                file.writeString("2"+"\n", true);
            }
            else if (GameScreen.player2==3){
                file.writeString("3"+"\n", true);
            }


            System.out.println("file1.txt");


        } else if (text2.length()==0){
            FileHandle file = Gdx.files.local("file2.txt");
            String a = Float.toString(TypesOfCollision.Health_Player1);
            file.writeString(a+"\n", false);

            String b = Float.toString(TypesOfCollision.Health_Player2);
            file.writeString(b+"\n", true);

            String c = Float.toString(GameScreen.Fuel_player1);
            file.writeString(c+"\n", true);

            String d = Float.toString(GameScreen.Fuel_player2);
            file.writeString(d+"\n", true);


            if (GameScreen.player1==1){
                file.writeString("1"+"\n", true);
            }
            else if (GameScreen.player1==2){
                file.writeString("2"+"\n", true);
            }
            else if (GameScreen.player1==3){
                file.writeString("3"+"\n", true);
            }

            if (GameScreen.player2==1){
                file.writeString("1"+"\n", true);
            }
            else if (GameScreen.player2==2){
                file.writeString("2"+"\n", true);
            }
            else if (GameScreen.player2==3){
                file.writeString("3"+"\n", true);
            }


            System.out.println("file2.txt");


        } else if (text3.length()==0){
            FileHandle file = Gdx.files.local("file3.txt");
            String a = Float.toString(TypesOfCollision.Health_Player1);
            file.writeString(a+"\n", false);

            String b = Float.toString(TypesOfCollision.Health_Player2);
            file.writeString(b+"\n", true);

            String c = Float.toString(GameScreen.Fuel_player1);
            file.writeString(c+"\n", true);

            String d = Float.toString(GameScreen.Fuel_player2);
            file.writeString(d+"\n", true);

            if (GameScreen.player1==1){
                file.writeString("1"+"\n", true);
            }
            else if (GameScreen.player1==2){
                file.writeString("2"+"\n", true);
            }
            else if (GameScreen.player1==3){
                file.writeString("3"+"\n", true);
            }

            if (GameScreen.player2==1){
                file.writeString("1"+"\n", true);
            }
            else if (GameScreen.player2==2){
                file.writeString("2"+"\n", true);
            }
            else if (GameScreen.player2==3){
                file.writeString("3"+"\n", true);
            }



            System.out.println("file3.txt");

            game.setScreen(new MenuScreen(game));



        }



//     String text = file1.readString();
//        for (String line : text.split("\n")) {
//            arr[i] = line;
//            i++;
//        }
//
//        for (int j = 0; j < arr.length-1; j++) {
//            System.out.println(parseFloat(arr[j]));
//        }



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
}
