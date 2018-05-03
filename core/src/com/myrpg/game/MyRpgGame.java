package com.myrpg.game;

import com.myrpg.screens.EndScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myrpg.model.GameModel;
import com.myrpg.screens.GameScreen;
import com.myrpg.screens.StartScreen;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyRpgGame extends Game {
    
    public StartScreen start;
    public GameScreen game;
    public EndScreen end;
    private GameModel model;
    
    public enum playerType {warrior, archer};
    
    @Override
    public void create () {
        start = new StartScreen(this);
        setScreen(start);
    }
    
    public void startGame(playerType typeOfPlayer, boolean useBot){
        model = new GameModel();
        model.start(typeOfPlayer);
        
        game = new GameScreen(this, model, model.getField(), useBot);
        setScreen(game);
    }
    
    public void gameOver(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyRpgGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.gameEnd();
        end = new EndScreen(this, false);
        setScreen(end);
    }
    
    public void gameWin(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyRpgGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.gameEnd();
        end = new EndScreen(this, true);
        setScreen(end);
    }
    
    public GameModel getModel(){
        return model;
    }
	
    @Override
    public void dispose () {
    }

}
