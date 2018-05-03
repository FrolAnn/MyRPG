package com.myrpg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myrpg.extensions.ModuleEngine;
import com.myrpg.game.MyRpgGame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ann
 */
public class EndScreen extends InputAdapter implements Screen  {
    
    private Texture background, warrior, warriorHover, archer, archerHover, victory, defeat, warriorBot, warriorBotHover, archerBot, archerBotHover;;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private boolean warriorIsHover, archerIsHover, warriorBotIsHover, archerBotIsHover;
    private MyRpgGame game;
    private boolean isVictory;
    
    public EndScreen(MyRpgGame _game, boolean _isVictory){
        game = _game;
        isVictory = _isVictory;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(860, 645);
        batch = new SpriteBatch();
        warriorIsHover = false;
        archerIsHover = false;
        warriorBotIsHover = false;
        archerBotIsHover = false;
        
        // Загружаем текстуры для меню
        background = new Texture(Gdx.files.internal("end screen/bg.png"));
        warrior = new Texture(Gdx.files.internal("start screen/warrior.png"));
        warriorHover = new Texture(Gdx.files.internal("start screen/warriorHover.png"));
        archer = new Texture(Gdx.files.internal("start screen/archer.png"));
        archerHover = new Texture(Gdx.files.internal("start screen/archerHover.png"));
        victory = new Texture(Gdx.files.internal("end screen/victory11.png"));
        defeat = new Texture(Gdx.files.internal("end screen/defeat.png"));
        
        warriorBot = new Texture(Gdx.files.internal("end screen/warriorBot.png"));
        warriorBotHover = new Texture(Gdx.files.internal("end screen/warriorBotHover.png"));
        archerBot = new Texture(Gdx.files.internal("end screen/archerBot.png"));
        archerBotHover = new Texture(Gdx.files.internal("end screen/archerBotHover.png"));
        
        Gdx.input.setInputProcessor(this);      
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Эта строка очищает экран
        batch.begin();
        // Здесь происходит отрисовка!
        batch.draw(background, 0, 0, 860, 645);
        if(!warriorIsHover)
            batch.draw(warrior, 0, 0, 860, 645);
        else
            batch.draw(warriorHover, 0, 0, 860, 645);
        if(!archerIsHover)
            batch.draw(archer, 0, -100, 860, 645);
        else
            batch.draw(archerHover, 0, -100, 860, 645);
        if(isVictory)
            batch.draw(victory, -70, -70, 1024, 768);
        else
            batch.draw(defeat, -60, -70, 1024, 768);
        if(!warriorBotIsHover)
            batch.draw(warriorBot, -60, 100, 800, 600);
        else
            batch.draw(warriorBotHover, -60, 100, 800, 600);
        if(!archerBotIsHover)
            batch.draw(archerBot, 190, 100, 800, 600);
        else
            batch.draw(archerBotHover, 190, 100, 800, 600);
        batch.end();
        camera.update();
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        
        background.dispose();
        warrior.dispose();
        warriorHover.dispose();
        archer.dispose();
        archerHover.dispose();
    }
    
    @Override
    public boolean mouseMoved (int screenX, int screenY){
        if(screenX >= 304 && screenX <= 568 && screenY >= 434 && screenY <= 508)
            warriorIsHover = true;
        else
            warriorIsHover = false;
        if(screenX >= 304 && screenX <= 568 && screenY >= 534 && screenY <= 608)
            archerIsHover = true;
        else
            archerIsHover = false;
        if(screenX >= 190 && screenX <= 410 && screenY >= 345 && screenY <= 400)
            warriorBotIsHover = true;
        else
            warriorBotIsHover = false;
        if(screenX >= 440 && screenX <= 660 && screenY >= 345 && screenY <= 400)
            archerBotIsHover = true;
        else
            archerBotIsHover = false;
        return true;
    }
    
    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button){
        if(screenX >= 304 && screenX <= 568 && screenY >= 434 && screenY <= 508)
        {
            dispose();
            // Переход на экран игрового поля
            game.startGame(MyRpgGame.playerType.warrior, false);
            return true;
        }
        else if(screenX >= 304 && screenX <= 568 && screenY >= 534 && screenY <= 608){
            dispose();
            // Переход на экран игрового поля
            game.startGame(MyRpgGame.playerType.archer, false);
            return true;
        }
        else if(screenX >= 190 && screenX <= 410 && screenY >= 345 && screenY <= 400){
            dispose();
            // Запускаем бота - война
            game.startGame(MyRpgGame.playerType.warrior, true);
            String[] arr = new String[1];
            arr[0] = "..\\build\\classes\\main\\com\\myrpg\\modules";
            ModuleEngine.main(arr, game, MyRpgGame.playerType.warrior);
        }
        else if(screenX >= 440 && screenX <= 660 && screenY >= 345 && screenY <= 400){
            dispose();
            // Запускаем бота-лучника
            game.startGame(MyRpgGame.playerType.archer, true);
            String[] arr = new String[1];
            arr[0] = "..\\build\\classes\\main\\com\\myrpg\\modules";
            ModuleEngine.main(arr, game, MyRpgGame.playerType.archer);
        }
        return false;    
    }  
}
