/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.myrpg.extensions.ModuleEngine;
import com.myrpg.game.MyRpgGame;
import com.myrpg.model.Cell;
import com.myrpg.model.GameField;
import com.myrpg.model.GameModel;
import com.myrpg.navigation.Direction;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import com.myrpg.model.Character;
import com.myrpg.model.Monster;
import com.myrpg.model.Player;
import com.myrpg.model.ArcherPlayer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ann
 */
public class GameScreen extends InputAdapter implements Screen {
    
    private GameModel model;
    private GameField field;
    private MyRpgGame game;
    private SpriteBatch batch;
    private Texture land, player, playerArcher, monster, item, aggrMonster/*, arrow*/;
    private Texture invBtn, invBtnPressed, inventory, closeBtn, invStatus, nextItemBtn, nextItemBtnPressed, nextItemBtnDisabled, prevItemBtn, prevItemBtnPressed, prevItemBtnDisabled;
    private int currInvItem = 0;
    private boolean invBtnIsPressed = false, nextItemBtnIsPressed = false, prevItemBtnIsPressed = false;
    private Map<String, TextureRegion> landRegions = new HashMap<String, TextureRegion>();
    private TextureRegion[] playerMoveUp, playerMoveRight, playerMoveDown, playerMoveLeft;
    private TextureRegion[] playerAttackUp, playerAttackRight, playerAttackDown, playerAttackLeft;
    private TextureRegion[] playerDieUp, playerDieRight, playerDieDown, playerDieLeft;
    private TextureRegion[] playerArcherMoveUp, playerArcherMoveRight, playerArcherMoveDown, playerArcherMoveLeft;
    private TextureRegion[] playerArcherAttackUp, playerArcherAttackRight, playerArcherAttackDown, playerArcherAttackLeft;
    private TextureRegion[] playerArcherDieUp, playerArcherDieRight, playerArcherDieDown, playerArcherDieLeft;
    private TextureRegion[] monsterMoveUp, monsterMoveRight, monsterMoveDown, monsterMoveLeft;
    private TextureRegion[] monsterAttackUp, monsterAttackRight, monsterAttackDown, monsterAttackLeft;
    private TextureRegion[] monsterDieUp, monsterDieRight, monsterDieDown, monsterDieLeft;
    private TextureRegion[] aggrMonsterMoveUp, aggrMonsterMoveRight, aggrMonsterMoveDown, aggrMonsterMoveLeft;
    private TextureRegion[] aggrMonsterAttackUp, aggrMonsterAttackRight, aggrMonsterAttackDown, aggrMonsterAttackLeft;
    private TextureRegion[] aggrMonsterDieUp, aggrMonsterDieRight, aggrMonsterDieDown, aggrMonsterDieLeft;
    private TextureRegion[] itemRegion;
    //private TextureRegion arrowUp, arrowRight, arrowDown, arrowLeft;
    private Animation playerMoveUpAnimation, playerMoveRightAnimation, playerMoveDownAnimation, playerMoveLeftAnimation;
    private Animation playerAttackUpAnimation, playerAttackRightAnimation, playerAttackDownAnimation, playerAttackLeftAnimation;
    private Animation playerDieUpAnimation, playerDieRightAnimation, playerDieDownAnimation, playerDieLeftAnimation;
    private Animation playerArcherMoveUpAnimation, playerArcherMoveRightAnimation, playerArcherMoveDownAnimation, playerArcherMoveLeftAnimation;
    private Animation playerArcherAttackUpAnimation, playerArcherAttackRightAnimation, playerArcherAttackDownAnimation, playerArcherAttackLeftAnimation;
    private Animation playerArcherDieUpAnimation, playerArcherDieRightAnimation, playerArcherDieDownAnimation, playerArcherDieLeftAnimation;
    private Animation monsterMoveUpAnimation, monsterMoveRightAnimation, monsterMoveDownAnimation, monsterMoveLeftAnimation;
    private Animation monsterDieUpAnimation, monsterDieRightAnimation, monsterDieDownAnimation, monsterDieLeftAnimation;
    private Animation monsterAttackUpAnimation, monsterAttackRightAnimation, monsterAttackDownAnimation, monsterAttackLeftAnimation;
    private Animation aggrMonsterMoveUpAnimation, aggrMonsterMoveRightAnimation, aggrMonsterMoveDownAnimation, aggrMonsterMoveLeftAnimation;
    private Animation aggrMonsterDieUpAnimation, aggrMonsterDieRightAnimation, aggrMonsterDieDownAnimation, aggrMonsterDieLeftAnimation;
    private Animation aggrMonsterAttackUpAnimation, aggrMonsterAttackRightAnimation, aggrMonsterAttackDownAnimation, aggrMonsterAttackLeftAnimation;
    private Animation itemAnimation;
    private BitmapFont font10, font14;
    private float delta = 0;
    private boolean useBot;
    
    public GameScreen(MyRpgGame _game, GameModel _model, GameField _field, boolean _useBot){
        model = _model;
        field = _field;  
        game = _game;
        useBot = _useBot;
        
        font10 = new BitmapFont(Gdx.files.internal("game screen/font10.fnt"), false);
        font10.setColor(Color.WHITE);
        font14 = new BitmapFont(Gdx.files.internal("game screen/font14.fnt"), false);
        font14.setColor(Color.WHITE);
        
        playerMoveUp = new TextureRegion[4];
        playerMoveRight = new TextureRegion[4];
        playerMoveDown = new TextureRegion[4];
        playerMoveLeft = new TextureRegion[4]; 
        
        playerAttackUp = new TextureRegion[4];
        playerAttackRight = new TextureRegion[4];
        playerAttackDown = new TextureRegion[4];
        playerAttackLeft = new TextureRegion[4]; 
        
        playerDieUp = new TextureRegion[3];
        playerDieRight = new TextureRegion[3];
        playerDieDown = new TextureRegion[3];
        playerDieLeft = new TextureRegion[3]; 
        
        playerArcherMoveUp = new TextureRegion[4];
        playerArcherMoveRight = new TextureRegion[4];
        playerArcherMoveDown = new TextureRegion[4];
        playerArcherMoveLeft = new TextureRegion[4]; 
        
        playerArcherAttackUp = new TextureRegion[3];
        playerArcherAttackRight = new TextureRegion[3];
        playerArcherAttackDown = new TextureRegion[3];
        playerArcherAttackLeft = new TextureRegion[3]; 
        
        playerArcherDieUp = new TextureRegion[3];
        playerArcherDieRight = new TextureRegion[3];
        playerArcherDieDown = new TextureRegion[3];
        playerArcherDieLeft = new TextureRegion[3];
        
        monsterMoveUp = new TextureRegion[5];
        monsterMoveRight = new TextureRegion[5];
        monsterMoveDown = new TextureRegion[5];
        monsterMoveLeft = new TextureRegion[5]; 
        
        monsterAttackUp = new TextureRegion[4];
        monsterAttackRight = new TextureRegion[4];
        monsterAttackDown = new TextureRegion[4];
        monsterAttackLeft = new TextureRegion[4]; 
        
        monsterDieUp = new TextureRegion[3];
        monsterDieRight = new TextureRegion[3];
        monsterDieDown = new TextureRegion[3];
        monsterDieLeft = new TextureRegion[3];
        
        aggrMonsterMoveUp = new TextureRegion[4];
        aggrMonsterMoveRight = new TextureRegion[4];
        aggrMonsterMoveDown = new TextureRegion[4];
        aggrMonsterMoveLeft = new TextureRegion[4]; 
        
        aggrMonsterAttackUp = new TextureRegion[4];
        aggrMonsterAttackRight = new TextureRegion[4];
        aggrMonsterAttackDown = new TextureRegion[4];
        aggrMonsterAttackLeft = new TextureRegion[4]; 
        
        aggrMonsterDieUp = new TextureRegion[3];
        aggrMonsterDieRight = new TextureRegion[3];
        aggrMonsterDieDown = new TextureRegion[3];
        aggrMonsterDieLeft = new TextureRegion[3];
        
        itemRegion  = new TextureRegion[4]; 
        
        Timer.schedule(new Task(){
                @Override
                public void run() {
                    if(model.getPlayer() != null)
                        model.getPlayer().checkInventory();
                }
            }, 
            1, // Задержка
            1 // Интервал
        );
    }
    
    private void loadInventoryTexture(){
       invBtn = new Texture(Gdx.files.internal("game screen/invBtn.png"));
       invBtnPressed = new Texture(Gdx.files.internal("game screen/invBtnPressed.png"));
       inventory = new Texture(Gdx.files.internal("game screen/inventory.png"));
       closeBtn = new Texture(Gdx.files.internal("game screen/closeBtn.png"));
       invStatus = new Texture(Gdx.files.internal("game screen/invStatus.png"));
       nextItemBtn = new Texture(Gdx.files.internal("game screen/nextItemBtn.png"));
       nextItemBtnPressed = new Texture(Gdx.files.internal("game screen/nextItemBtnPressed.png"));
       nextItemBtnDisabled = new Texture(Gdx.files.internal("game screen/nextItemBtnDisabled.png"));
       prevItemBtn = new Texture(Gdx.files.internal("game screen/prevItemBtn.png"));
       prevItemBtnPressed = new Texture(Gdx.files.internal("game screen/prevItemBtnPressed.png"));
       prevItemBtnDisabled = new Texture(Gdx.files.internal("game screen/prevItemBtnDisabled.png"));
       // Продолжение следует...
    }
    
    private void loadPlayerRegions(){
        player  = new Texture(Gdx.files.internal("game screen/footman.png"));
        
        TextureRegion tmp[][] = TextureRegion.split(player, player.getWidth()/5, player.getHeight()/12);
        for(int i = 0; i < 4; i++){
            // Изображения с движением игрока
            playerMoveUp[i] = tmp[i][0];
            playerMoveRight[i] = tmp[i][2];
            playerMoveLeft[i] = new TextureRegion(tmp[i][2]);
            playerMoveLeft[i].flip(true, false);
            playerMoveDown[i] = tmp[i][4];
            
            // Изображения с атакой 
            playerAttackUp[i] = tmp[i+5][0];
            playerAttackRight[i] = tmp[i+5][2];
            playerAttackLeft[i] = new TextureRegion(tmp[i+5][2]);
            playerAttackLeft[i].flip(true, false);
            playerAttackDown[i] = tmp[i+5][4];
            
            // Изображения со смертью игрока
            if(i < 3){
                playerDieUp[i] = tmp[i+9][0];
                playerDieRight[i] = tmp[i+9][2];
                playerDieLeft[i] = new TextureRegion(tmp[i+9][2]);
                playerDieLeft[i].flip(true, false);
                playerDieDown[i] = tmp[i+9][4];
            }
        }
        
        playerArcher  = new Texture(Gdx.files.internal("game screen/playerArcher.png"));
        
        tmp = TextureRegion.split(playerArcher, playerArcher.getWidth()/5, playerArcher.getHeight()/10);
        for(int i = 0; i < 4; i++){
            // Изображения с движением игрока
            playerArcherMoveUp[i] = tmp[i][0];
            playerArcherMoveRight[i] = tmp[i][2];
            playerArcherMoveLeft[i] = new TextureRegion(tmp[i][2]);
            playerArcherMoveLeft[i].flip(true, false);
            playerArcherMoveDown[i] = tmp[i][4];
            
            // Изображения с атакой 
            if(i < 3){
                playerArcherAttackUp[i] = tmp[i+4][0];
                playerArcherAttackRight[i] = tmp[i+4][2];
                playerArcherAttackLeft[i] = new TextureRegion(tmp[i+4][2]);
                playerArcherAttackLeft[i].flip(true, false);
                playerArcherAttackDown[i] = tmp[i+4][4];
            }
            
            // Изображения со смертью игрока
            if(i < 3){
                playerArcherDieUp[i] = tmp[i+7][0];
                playerArcherDieRight[i] = tmp[i+7][2];
                playerArcherDieLeft[i] = new TextureRegion(tmp[i+7][2]);
                playerArcherDieLeft[i].flip(true, false);
                playerArcherDieDown[i] = tmp[i+7][4];
            }
        }
        /*arrow = new Texture(Gdx.files.internal("game screen/arrow.png"));
        tmp = TextureRegion.split(arrow, arrow.getWidth()/5, arrow.getHeight());
        arrowUp = tmp[0][0];
        arrowRight = tmp[0][2];
        arrowLeft = new TextureRegion(tmp[0][2]);
        arrowLeft.flip(true, false);
        arrowDown = tmp[0][4];*/
    }
    
    private void loadItemRegions(){
        item  = new Texture(Gdx.files.internal("game screen/item.png"));
        
        TextureRegion tmp[][] = TextureRegion.split(item, item.getWidth(), item.getHeight()/4);
        for(int i = 0; i < 4; i++){
            itemRegion[i] = tmp[i][0];       
        }
    }
    
    private void loadMonsterRegions(){
        monster  = new Texture(Gdx.files.internal("game screen/grunt.png"));
        
        TextureRegion tmp[][] = TextureRegion.split(monster, monster.getWidth()/5, monster.getHeight()/12);
        for(int i = 0; i < 5; i++){
            // Изображения с движением монстра
            monsterMoveUp[i] = tmp[i][0];
            monsterMoveRight[i] = tmp[i][2];
            monsterMoveLeft[i] = new TextureRegion(tmp[i][2]);
            monsterMoveLeft[i].flip(true, false);
            monsterMoveDown[i] = tmp[i][4];
            
            // Изображения с атакой монстра
            if(i < 4){
                monsterAttackUp[i] = tmp[i+5][0];
                monsterAttackRight[i] = tmp[i+5][2];
                monsterAttackLeft[i] = new TextureRegion(tmp[i+5][2]);
                monsterAttackLeft[i].flip(true, false);
                monsterAttackDown[i] = tmp[i+5][4];
            }
            
            // Изображения со смертью монстра
            if(i < 3){
                monsterDieUp[i] = tmp[i+9][0];
                monsterDieRight[i] = tmp[i+9][2];
                monsterDieLeft[i] = new TextureRegion(tmp[i+9][2]);
                monsterDieLeft[i].flip(true, false);
                monsterDieDown[i] = tmp[i+9][4];
            }
        }
        
        aggrMonster  = new Texture(Gdx.files.internal("game screen/gruntAggr.png"));
        
        tmp = TextureRegion.split(aggrMonster, aggrMonster.getWidth()/5, aggrMonster.getHeight()/12);
        for(int i = 0; i < 4; i++){
            // Изображения с движением монстра
            aggrMonsterMoveUp[i] = tmp[i][0];
            aggrMonsterMoveRight[i] = tmp[i][2];
            aggrMonsterMoveLeft[i] = new TextureRegion(tmp[i][2]);
            aggrMonsterMoveLeft[i].flip(true, false);
            aggrMonsterMoveDown[i] = tmp[i][4];
            
            // Изображения с атакой монстра
            aggrMonsterAttackUp[i] = tmp[i+5][0];
            aggrMonsterAttackRight[i] = tmp[i+5][2];
            aggrMonsterAttackLeft[i] = new TextureRegion(tmp[i+5][2]);
            aggrMonsterAttackLeft[i].flip(true, false);
            aggrMonsterAttackDown[i] = tmp[i+5][4];
            
            // Изображения со смертью монстра
            if(i < 3){
                aggrMonsterDieUp[i] = tmp[i+9][0];
                aggrMonsterDieRight[i] = tmp[i+9][2];
                aggrMonsterDieLeft[i] = new TextureRegion(tmp[i+9][2]);
                aggrMonsterDieLeft[i].flip(true, false);
                aggrMonsterDieDown[i] = tmp[i+9][4];
            }
        }
    }
    
    private void loadLandRegions(){
        land  = new Texture(Gdx.files.internal("game screen/summer.png"));
        
        TextureRegion tmp[][] = TextureRegion.split(land, land.getWidth()/16, land.getHeight()/24);
        landRegions.put("borderVertical", tmp[14][2]);
        landRegions.put("cornerOfBorder", tmp[14][11]);
        landRegions.put("land", tmp[21][0]);
        landRegions.put("water", tmp[19][0]);
        landRegions.put("borderHorizontal", tmp[13][4]);
    }
    
    private void createPlayerAnimation(){
        // Движение
        playerMoveUpAnimation = new Animation(0.07f, playerMoveUp);
        playerMoveRightAnimation = new Animation(0.07f, playerMoveRight);
        playerMoveDownAnimation = new Animation(0.07f, playerMoveDown);
        playerMoveLeftAnimation = new Animation(0.07f, playerMoveLeft);
        
        // Атака
        playerAttackUpAnimation = new Animation(0.1f, playerAttackUp);
        playerAttackRightAnimation = new Animation(0.1f, playerAttackRight);
        playerAttackDownAnimation = new Animation(0.1f, playerAttackDown);
        playerAttackLeftAnimation = new Animation(0.1f, playerAttackLeft);
        
        // Смерть
        playerDieUpAnimation = new Animation(0.2f, playerDieUp);
        playerDieRightAnimation = new Animation(0.2f, playerDieRight);
        playerDieDownAnimation = new Animation(0.2f, playerDieDown);
        playerDieLeftAnimation = new Animation(0.2f, playerDieLeft);
        
        // Движение
        playerArcherMoveUpAnimation = new Animation(0.06f, playerArcherMoveUp);
        playerArcherMoveRightAnimation = new Animation(0.06f, playerArcherMoveRight);
        playerArcherMoveDownAnimation = new Animation(0.06f, playerArcherMoveDown);
        playerArcherMoveLeftAnimation = new Animation(0.06f, playerArcherMoveLeft);
        
        // Атака
        playerArcherAttackUpAnimation = new Animation(0.15f, playerArcherAttackUp);
        playerArcherAttackRightAnimation = new Animation(0.15f, playerArcherAttackRight);
        playerArcherAttackDownAnimation = new Animation(0.15f, playerArcherAttackDown);
        playerArcherAttackLeftAnimation = new Animation(0.15f, playerArcherAttackLeft);
        
        // Смерть
        playerArcherDieUpAnimation = new Animation(0.2f, playerArcherDieUp);
        playerArcherDieRightAnimation = new Animation(0.2f, playerArcherDieRight);
        playerArcherDieDownAnimation = new Animation(0.2f, playerArcherDieDown);
        playerArcherDieLeftAnimation = new Animation(0.2f, playerArcherDieLeft);
    }
    
    private void createItemAnimation(){
        itemAnimation = new Animation(0.2f, itemRegion);
        itemAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
     }
    
    private void createMonsterAnimation(){
        // Движение
        monsterMoveUpAnimation = new Animation(0.095f, monsterMoveUp);
        monsterMoveRightAnimation = new Animation(0.095f, monsterMoveRight);
        monsterMoveDownAnimation = new Animation(0.095f, monsterMoveDown);
        monsterMoveLeftAnimation = new Animation(0.095f, monsterMoveLeft);
        
        // Атака
        monsterAttackUpAnimation = new Animation(0.15f, monsterAttackUp);
        monsterAttackRightAnimation = new Animation(0.15f, monsterAttackRight);
        monsterAttackDownAnimation = new Animation(0.15f, monsterAttackDown);
        monsterAttackLeftAnimation = new Animation(0.15f, monsterAttackLeft);
        
        // Смерть
        monsterDieUpAnimation = new Animation(0.2f, monsterDieUp);
        monsterDieRightAnimation = new Animation(0.2f, monsterDieRight);
        monsterDieDownAnimation = new Animation(0.2f, monsterDieDown);
        monsterDieLeftAnimation = new Animation(0.2f, monsterDieLeft);
        
        // Движение
        aggrMonsterMoveUpAnimation = new Animation(0.12f, aggrMonsterMoveUp);
        aggrMonsterMoveRightAnimation = new Animation(0.12f, aggrMonsterMoveRight);
        aggrMonsterMoveDownAnimation = new Animation(0.12f, aggrMonsterMoveDown);
        aggrMonsterMoveLeftAnimation = new Animation(0.12f, aggrMonsterMoveLeft);
        
        // Атака
        aggrMonsterAttackUpAnimation = new Animation(0.15f, aggrMonsterAttackUp);
        aggrMonsterAttackRightAnimation = new Animation(0.15f, aggrMonsterAttackRight);
        aggrMonsterAttackDownAnimation = new Animation(0.15f, aggrMonsterAttackDown);
        aggrMonsterAttackLeftAnimation = new Animation(0.15f, aggrMonsterAttackLeft);
        
        // Смерть
        aggrMonsterDieUpAnimation = new Animation(0.2f, aggrMonsterDieUp);
        aggrMonsterDieRightAnimation = new Animation(0.2f, aggrMonsterDieRight);
        aggrMonsterDieDownAnimation = new Animation(0.2f, aggrMonsterDieDown);
        aggrMonsterDieLeftAnimation = new Animation(0.2f, aggrMonsterDieLeft);
    }
    
    private void drawInventory(){
        int size = model.getPlayer().getInventory().size();
        if(size == 0)
            currInvItem = 0;
        else if(currInvItem > size-1)
            currInvItem = size-1;
        // Рисуем элементы
        if(!invBtnIsPressed)
            batch.draw(invBtn, (field.getWidth()-3)*32, (field.getHeight()+2)*32, 106, 28);
        else{
            batch.draw(invBtnPressed, (field.getWidth()-3)*32, (field.getHeight()+2)*32, 106, 28);
            batch.draw(inventory, field.getWidth()*16-176, (field.getHeight()+2)*16-176, 352, 352);
            batch.draw(closeBtn, field.getWidth()*16-176+320, (field.getHeight()+2)*16-176+320, 16, 16);
            batch.draw(invStatus, field.getWidth()*16-176+66, (field.getHeight()+2)*16-176+280, 224, 28);
            if(currInvItem == 0)
                batch.draw(prevItemBtnDisabled, field.getWidth()*16-160, (field.getHeight()+2)*16-10, 20, 19);
            else if(!prevItemBtnIsPressed)
                batch.draw(prevItemBtn, field.getWidth()*16-160, (field.getHeight()+2)*16-10, 20, 19);
            else{
                batch.draw(prevItemBtnPressed, field.getWidth()*16-160, (field.getHeight()+2)*16-10, 20, 19);
                prevItemBtnIsPressed = false;
            }
            if(size == 0 || currInvItem == size-1)
                batch.draw(nextItemBtnDisabled, field.getWidth()*16-176+352-32, (field.getHeight()+2)*16-10, 20, 19);
            else if(!nextItemBtnIsPressed)
                batch.draw(nextItemBtn, field.getWidth()*16-176+352-32, (field.getHeight()+2)*16-10, 20, 19);
            else{
                batch.draw(nextItemBtnPressed, field.getWidth()*16-176+352-32, (field.getHeight()+2)*16-10, 20, 19);
                nextItemBtnIsPressed = false;
            }
            
            // Отрисовка статуса инвентаря
            if(size == 0)
                font14.draw(batch, "Inventory is empty.", field.getWidth()*16-176+72, (field.getHeight()+2)*16-176+298);
            else{              
                font14.draw(batch, "Current item " + (currInvItem+1) + " out of " + size + " items.", field.getWidth()*16-176+72, (field.getHeight()+2)*16-176+298);
                // Отрисовка информации о предмете
                drawItemInfo();
            }  
        }
    }
    
    private void drawItemInfo(){
        HashMap<String, String> info = model.getPlayer().getInventory().get(currInvItem).getInfo();
        int width = field.getWidth()*16-176+66;
        int height = (field.getHeight()+2)*16-176+280;
        font14.draw(batch, info.get("isEquip"), width, height-15);
        font14.draw(batch, info.get("constitution"), width, height-35);
        font14.draw(batch, info.get("power"), width, height-55);
        font14.draw(batch, info.get("agility"), width, height-75);
        font14.draw(batch, info.get("protection"), width, height-95);
        font14.draw(batch, info.get("intellect"), width, height-115);
        font14.draw(batch, info.get("requiredIntellect"), width, height-135);
        font14.draw(batch, info.get("charisma"), width, height-155);
        if(info.containsKey("lifetime")){
            font14.draw(batch, info.get("lifetime"), width, height-175);
            font14.draw(batch, info.get("currTime"), width, height-195);
        }
    }
    
    private void drawPlayerInfo(){
        HashMap<String, String> info = model.getPlayer().getFullInfo();
        int height = (field.getHeight()+3)*32;
        font14.draw(batch, info.get("lvl"), 32, height-5);
        font14.draw(batch, info.get("hp"), 160, height-5);
        font14.draw(batch, info.get("attack"), 160, height-17);
        font14.draw(batch, info.get("evasion"), 160, height-29);
        font14.draw(batch, info.get("con"), 258, height-5);
        font14.draw(batch, info.get("power"), 258, height-17);
        font14.draw(batch, info.get("agility"), 258, height-29);
        font14.draw(batch, info.get("protection"), 360, height-5);       
        font14.draw(batch, info.get("charisma"), 360, height-17);
        font14.draw(batch, info.get("intellect"), 360, height-29);
        font14.draw(batch, info.get("blockingOfDamage"), 460, height-5);
        font14.draw(batch, info.get("avoidingCombat"), 460, height-17);
    }
    
    private void drawField(){
        for(int i = field.getWidth(); i >= 0; i--){
            batch.draw(landRegions.get("water"), i*32, (field.getHeight()+1)*32, 16, 16, 32, 32, 1, 1, 180);
            batch.draw(landRegions.get("water"), i*32, (field.getHeight()+2)*32, 16, 16, 32, 32, 1, 1, 180);
        }
        for(int i = field.getHeight(); i >= 0; i--){
            for(int j = field.getWidth(); j >= 0; j--){               
                if(i == 0 && j == 0)
                    batch.draw(landRegions.get("cornerOfBorder"), j*32, i*32, 16, 16, 32, 32, 1, 1, 270);
                else if(i == 0 && j == field.getWidth())
                    batch.draw(landRegions.get("cornerOfBorder"), j*32, i*32, 16, 16, 32, 32, 1, 1, 0);       
                else if(i == field.getHeight() && j == 0)
                    batch.draw(landRegions.get("cornerOfBorder"), j*32, i*32, 16, 16, 32, 32, 1, 1, 180);
                else if(i == field.getHeight() && j == field.getWidth())
                    batch.draw(landRegions.get("cornerOfBorder"), j*32, i*32, 16, 16, 32, 32, 1, 1, 90);
                else if(i == field.getHeight())
                    batch.draw(landRegions.get("borderHorizontal"), j*32, i*32, 16, 16, 32, 32, 1, 1, 0);
                else if(j == field.getWidth())
                    batch.draw(landRegions.get("borderVertical"), j*32, i*32, 16, 16, 32, 32, 1, 1, 0);
                else if(i == 0)
                    batch.draw(landRegions.get("borderHorizontal"), j*32, i*32, 16, 16, 32, 32, 1, 1, 180);
                else if(j == 0)
                    batch.draw(landRegions.get("borderVertical"), j*32, i*32, 16, 16, 32, 32, 1, 1, 180);
                else
                    batch.draw(landRegions.get("land"), j*32, i*32, 32, 32);
            }
        }
        for(int i = field.getHeight(); i >= 0; i--){
            for(int j = field.getWidth(); j >= 0; j--){               
                if(i < field.getHeight() && j < field.getWidth()){
                    Cell cell = field.getCell(new Point(j, i));
                    if(!cell.isEmpty()){
                        if(!cell.getItems().isEmpty())
                            drawItem(cell);
                        if(cell.getCharacter() != null){
                            if(cell.getCharacter().getType() == "player" || cell.getCharacter().getType() == "archer player" )
                                drawPlayer();                               
                            else
                                drawMonster(cell);
                            if(model.getPlayer() == null)
                                    return;
                        }                     
                    }
                }
            }
        }
    }
    
    private void drawItem(Cell cell){
        Point pos = cell.getPosition();
        cell.itemStateTime += Gdx.graphics.getDeltaTime();
        cell.itemCurrentFrame = (TextureRegion) itemAnimation.getKeyFrame(cell.itemStateTime, true);
        batch.draw(cell.itemCurrentFrame, (pos.x)*32+20, (pos.y)*32+30, 32, 32);           
    }
    
    private void drawMonster(Cell cell){
        Monster monster = (Monster) cell.getCharacter();
        Direction dir = monster.getDirection();
        Point pos = cell.getPosition();
        String info = monster.getShortInfo();

        if(monster.getState() == Character.CharacterState.movement){
            drawMonsterMovement(monster, dir, pos);
        }
        else if(monster.getState() == Character.CharacterState.attack){
            drawMonsterAttack(monster, dir, pos);
            font10.draw(batch, info, (pos.x)*32, (pos.y+2)*32+12);
        }
        else if(monster.getState() == Character.CharacterState.die){
            drawMonsterDie(monster, dir, pos);
        }
        else{
            if(monster.getType() == "monster"){
                if(dir.equals(Direction.north()))
                    batch.draw(monsterMoveUp[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.east()))
                    batch.draw(monsterMoveRight[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.south()))
                    batch.draw(monsterMoveDown[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else
                    batch.draw(monsterMoveLeft[0], (pos.x)*32, (pos.y)*32, 72, 72);
            }
            else if(monster.getType() == "aggressive monster"){
                if(dir.equals(Direction.north()))
                    batch.draw(aggrMonsterMoveUp[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.east()))
                    batch.draw(aggrMonsterMoveRight[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.south()))
                    batch.draw(aggrMonsterMoveDown[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else
                    batch.draw(aggrMonsterMoveLeft[0], (pos.x)*32, (pos.y)*32, 72, 72);
            }
            font10.draw(batch, info, (pos.x)*32, (pos.y+2)*32+12);
        }
    }
    
    private void drawMonsterMovement(Monster monster, Direction dir, Point pos){
        monster.stateTime += Gdx.graphics.getDeltaTime();
        String info = monster.getShortInfo();
        if(monster.getType() == "monster"){
            if(dir.equals(Direction.north())){
                monster.currentFrame = (TextureRegion) monsterMoveUpAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y-1)*32+8*monsterMoveUpAnimation.getKeyFrameIndex(monster.stateTime), 72, 72);
                font10.draw(batch, info, (pos.x)*32, (pos.y+1)*32+8*monsterMoveUpAnimation.getKeyFrameIndex(monster.stateTime)+12);
                if(monsterMoveUpAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                monster.currentFrame = (TextureRegion) monsterMoveRightAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x-1)*32+8*monsterMoveRightAnimation.getKeyFrameIndex(monster.stateTime), (pos.y)*32, 72, 72);
                font10.draw(batch, info, (pos.x-1)*32+8*monsterMoveRightAnimation.getKeyFrameIndex(monster.stateTime), (pos.y+2)*32+12);
                if(monsterMoveRightAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                monster.currentFrame = (TextureRegion) monsterMoveDownAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y+1)*32-8*monsterMoveDownAnimation.getKeyFrameIndex(monster.stateTime), 72, 72);
                font10.draw(batch, info, (pos.x)*32, (pos.y+3)*32-8*monsterMoveDownAnimation.getKeyFrameIndex(monster.stateTime)+12);
                if(monsterMoveDownAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else{
                monster.currentFrame = (TextureRegion) monsterMoveLeftAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x+1)*32-8*monsterMoveLeftAnimation.getKeyFrameIndex(monster.stateTime), (pos.y)*32, 72, 72);
                font10.draw(batch, info, (pos.x+1)*32-8*monsterMoveLeftAnimation.getKeyFrameIndex(monster.stateTime), (pos.y+2)*32+12);
                if(monsterMoveLeftAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
        }
        else if(monster.getType() == "aggressive monster"){
            if(dir.equals(Direction.north())){
                monster.currentFrame = (TextureRegion) aggrMonsterMoveUpAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y-1)*32+8*aggrMonsterMoveUpAnimation.getKeyFrameIndex(monster.stateTime), 72, 72);
                font10.draw(batch, info, (pos.x)*32, (pos.y+1)*32+8*aggrMonsterMoveUpAnimation.getKeyFrameIndex(monster.stateTime)+12);
                if(aggrMonsterMoveUpAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                monster.currentFrame = (TextureRegion) aggrMonsterMoveRightAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x-1)*32+8*aggrMonsterMoveRightAnimation.getKeyFrameIndex(monster.stateTime), (pos.y)*32, 72, 72);
                font10.draw(batch, info, (pos.x-1)*32+8*aggrMonsterMoveRightAnimation.getKeyFrameIndex(monster.stateTime), (pos.y+2)*32+12);
                if(aggrMonsterMoveRightAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                monster.currentFrame = (TextureRegion) aggrMonsterMoveDownAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y+1)*32-8*aggrMonsterMoveDownAnimation.getKeyFrameIndex(monster.stateTime), 72, 72);
                font10.draw(batch, info, (pos.x)*32, (pos.y+3)*32-8*aggrMonsterMoveDownAnimation.getKeyFrameIndex(monster.stateTime)+12);
                if(aggrMonsterMoveDownAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else{
                monster.currentFrame = (TextureRegion) aggrMonsterMoveLeftAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x+1)*32-8*aggrMonsterMoveLeftAnimation.getKeyFrameIndex(monster.stateTime), (pos.y)*32, 72, 72);
                font10.draw(batch, info, (pos.x+1)*32-8*aggrMonsterMoveLeftAnimation.getKeyFrameIndex(monster.stateTime), (pos.y+2)*32+12);
                if(aggrMonsterMoveLeftAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
        }
    }
    
    private void drawMonsterAttack(Monster monster, Direction dir, Point pos){
        monster.stateTime += Gdx.graphics.getDeltaTime();
        if(monster.getType() == "monster"){
            if(dir.equals(Direction.north())){
                monster.currentFrame = (TextureRegion) monsterAttackUpAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterAttackUpAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                monster.currentFrame = (TextureRegion) monsterAttackRightAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterAttackRightAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                monster.currentFrame = (TextureRegion) monsterAttackDownAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterAttackDownAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else{
                monster.currentFrame = (TextureRegion) monsterAttackLeftAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterAttackLeftAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            } 
        }
        else if(monster.getType() == "aggressive monster"){
            if(dir.equals(Direction.north())){
                monster.currentFrame = (TextureRegion) aggrMonsterAttackUpAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterAttackUpAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                monster.currentFrame = (TextureRegion) aggrMonsterAttackRightAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterAttackRightAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                monster.currentFrame = (TextureRegion) aggrMonsterAttackDownAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterAttackDownAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            }
            else{
                monster.currentFrame = (TextureRegion) aggrMonsterAttackLeftAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterAttackLeftAnimation.isAnimationFinished(monster.stateTime)){
                    monster.setState(Character.CharacterState.stay);
                    monster.stateTime = 0;
                }
            } 
        }
    }
    
    private void drawMonsterDie(Monster monster, Direction dir, Point pos){
        monster.stateTime += Gdx.graphics.getDeltaTime();
        if(monster.getType() == "monster"){
            if(dir.equals(Direction.north())){
                monster.currentFrame = (TextureRegion) monsterDieUpAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterDieUpAnimation.isAnimationFinished(monster.stateTime)){
                    monster.remove();
                }
            }
            else if(dir.equals(Direction.east())){
                monster.currentFrame = (TextureRegion) monsterDieRightAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterDieRightAnimation.isAnimationFinished(monster.stateTime)){
                   monster.remove();             
                }
            }
            else if(dir.equals(Direction.south())){
                monster.currentFrame = (TextureRegion) monsterDieDownAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterDieDownAnimation.isAnimationFinished(monster.stateTime)){
                    monster.remove();
                }
            }
            else{
                monster.currentFrame = (TextureRegion) monsterDieLeftAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(monsterDieLeftAnimation.isAnimationFinished(monster.stateTime)){
                   monster.remove();
                }
            } 
        }
        else if(monster.getType() == "aggressive monster"){
            if(dir.equals(Direction.north())){
                monster.currentFrame = (TextureRegion) aggrMonsterDieUpAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterDieUpAnimation.isAnimationFinished(monster.stateTime)){
                    monster.remove();
                }
            }
            else if(dir.equals(Direction.east())){
                monster.currentFrame = (TextureRegion) aggrMonsterDieRightAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterDieRightAnimation.isAnimationFinished(monster.stateTime)){
                   monster.remove();             
                }
            }
            else if(dir.equals(Direction.south())){
                monster.currentFrame = (TextureRegion) aggrMonsterDieDownAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterDieDownAnimation.isAnimationFinished(monster.stateTime)){
                    monster.remove();
                }
            }
            else{
                monster.currentFrame = (TextureRegion) aggrMonsterDieLeftAnimation.getKeyFrame(monster.stateTime, false);
                batch.draw(monster.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(aggrMonsterDieLeftAnimation.isAnimationFinished(monster.stateTime)){
                   monster.remove();
                }
            }
        }
        if(model.determineEndOfTheGame()){
            dispose();
            game.gameWin();
        }
    }
        
    private void drawPlayer(){
        Player player = model.getPlayer();
        Point pos = player.getCell().getPosition();
        Direction dir = player.getDirection();
        
        if(player.getState() == Character.CharacterState.movement){
            drawPlayerMovement(player, dir, pos);
        }
        else if(player.getState() == Character.CharacterState.attack){
            drawPlayerAttack(player, dir, pos);
        }
        else if(player.getState() == Character.CharacterState.die){
            drawPlayerDie(player, dir, pos);
        }
        else{
            if(player.getType() == "player"){
                if(dir.equals(Direction.north()))
                    batch.draw(playerMoveUp[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.east()))
                    batch.draw(playerMoveRight[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.south()))
                    batch.draw(playerMoveDown[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else
                    batch.draw(playerMoveLeft[0], (pos.x)*32, (pos.y)*32, 72, 72);
                }
            else if(player.getType() == "archer player"){
                if(dir.equals(Direction.north()))
                    batch.draw(playerArcherMoveUp[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.east()))
                    batch.draw(playerArcherMoveRight[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else if(dir.equals(Direction.south()))
                    batch.draw(playerArcherMoveDown[0], (pos.x)*32, (pos.y)*32, 72, 72);
                else
                    batch.draw(playerArcherMoveLeft[0], (pos.x)*32, (pos.y)*32, 72, 72);
            }
        }     
    }
    
    private void drawPlayerAttack(Player player, Direction dir, Point pos) {
        player.stateTime += Gdx.graphics.getDeltaTime();
        if(player.getType() == "player"){
            if(dir.equals(Direction.north())){
                player.currentFrame = (TextureRegion) playerAttackUpAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerAttackUpAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                player.currentFrame = (TextureRegion) playerAttackRightAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerAttackRightAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                player.currentFrame = (TextureRegion) playerAttackDownAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerAttackDownAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else{
                player.currentFrame = (TextureRegion) playerAttackLeftAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerAttackLeftAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            } 
        }
        else if(player.getType() == "archer player"){
            ArrayList <Cell> cells = field.getLineOfCells(field.getCell(pos), dir, ((ArcherPlayer)player).getAttackRange());
            if(dir.equals(Direction.north())){
                player.currentFrame = (TextureRegion) playerArcherAttackUpAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherAttackUpAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }                         
            }
            else if(dir.equals(Direction.east())){
                player.currentFrame = (TextureRegion) playerArcherAttackRightAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherAttackRightAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                player.currentFrame = (TextureRegion) playerArcherAttackDownAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherAttackDownAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else{
                player.currentFrame = (TextureRegion) playerArcherAttackLeftAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherAttackLeftAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
        }
    }
    
    private void drawPlayerDie(Player player, Direction dir, Point pos){
        player.stateTime += Gdx.graphics.getDeltaTime();
        if(player.getType() == "player"){
            if(dir.equals(Direction.north())){
                player.currentFrame = (TextureRegion) playerDieUpAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerDieUpAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            }
            else if(dir.equals(Direction.east())){
                player.currentFrame = (TextureRegion) playerDieRightAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerDieRightAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            }
            else if(dir.equals(Direction.south())){
                player.currentFrame = (TextureRegion) playerDieDownAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerDieDownAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            }
            else{
                player.currentFrame = (TextureRegion) playerDieLeftAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerDieLeftAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            } 
        }
        else if(player.getType() == "archer player"){
            if(dir.equals(Direction.north())){
                player.currentFrame = (TextureRegion) playerArcherDieUpAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherDieUpAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            }
            else if(dir.equals(Direction.east())){
                player.currentFrame = (TextureRegion) playerArcherDieRightAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherDieRightAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            }
            else if(dir.equals(Direction.south())){
                player.currentFrame = (TextureRegion) playerArcherDieDownAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherDieDownAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            }
            else{
                player.currentFrame = (TextureRegion) playerArcherDieLeftAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y)*32, 72, 72);
                if(playerArcherDieLeftAnimation.isAnimationFinished(player.stateTime)){
                    player.stateTime = 0;
                    dispose();
                    game.gameOver();
                }
            } 
        }
    }
    
    private void drawPlayerMovement(Player player, Direction dir, Point pos){
        player.stateTime += Gdx.graphics.getDeltaTime();
        if(player.getType() == "player"){
            if(dir.equals(Direction.north())){
                player.currentFrame = (TextureRegion) playerMoveUpAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y-1)*32+8*playerMoveUpAnimation.getKeyFrameIndex(player.stateTime), 72, 72);
                if(playerMoveUpAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                player.currentFrame = (TextureRegion) playerMoveRightAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x-1)*32+8*playerMoveRightAnimation.getKeyFrameIndex(player.stateTime), (pos.y)*32, 72, 72);
                if(playerMoveRightAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                player.currentFrame = (TextureRegion) playerMoveDownAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y+1)*32-8*playerMoveDownAnimation.getKeyFrameIndex(player.stateTime), 72, 72);
                if(playerMoveDownAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else{
                player.currentFrame = (TextureRegion) playerMoveLeftAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x+1)*32-8*playerMoveLeftAnimation.getKeyFrameIndex(player.stateTime), (pos.y)*32, 72, 72);
                if(playerMoveLeftAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            } 
        }
        else if(player.getType() == "archer player"){
            if(dir.equals(Direction.north())){
                player.currentFrame = (TextureRegion) playerArcherMoveUpAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y-1)*32+8*playerArcherMoveUpAnimation.getKeyFrameIndex(player.stateTime), 72, 72);
                if(playerArcherMoveUpAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.east())){
                player.currentFrame = (TextureRegion) playerArcherMoveRightAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x-1)*32+8*playerArcherMoveRightAnimation.getKeyFrameIndex(player.stateTime), (pos.y)*32, 72, 72);
                if(playerArcherMoveRightAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else if(dir.equals(Direction.south())){
                player.currentFrame = (TextureRegion) playerArcherMoveDownAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x)*32, (pos.y+1)*32-8*playerArcherMoveDownAnimation.getKeyFrameIndex(player.stateTime), 72, 72);
                if(playerArcherMoveDownAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            }
            else{
                player.currentFrame = (TextureRegion) playerArcherMoveLeftAnimation.getKeyFrame(player.stateTime, false);
                batch.draw(player.currentFrame, (pos.x+1)*32-8*playerArcherMoveLeftAnimation.getKeyFrameIndex(player.stateTime), (pos.y)*32, 72, 72);
                if(playerArcherMoveLeftAnimation.isAnimationFinished(player.stateTime)){
                    player.setState(Character.CharacterState.stay);
                    player.stateTime = 0;
                }
            } 
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        
        Gdx.input.setInputProcessor(this);
        
        loadLandRegions();
        loadPlayerRegions();
        loadMonsterRegions();
        loadItemRegions();
        
        loadInventoryTexture();
        
        createPlayerAnimation();
        createMonsterAnimation();
        createItemAnimation();
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Эта строка очищает экран
        delta += Gdx.graphics.getDeltaTime();
        if(useBot){
            ModuleEngine._execute.run();
        }
        model.monsterAttack();
        if(delta > 0.3f){                
            model.monsterMovement();
            delta = 0;
        }
        batch.begin();       
        drawField();
        if(model.getPlayer() != null){
            drawPlayerInfo();
            drawInventory();
            Player pl = model.getPlayer();
            if(pl.getType() == "archer player"){
                if(((ArcherPlayer)pl).getArrow() != null)
                    ((ArcherPlayer)pl).getArrow().draw(batch);
            }
        }
        batch.end();
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

        land.dispose();
        player.dispose();
        monster.dispose();
        item.dispose();
        invBtn.dispose();
        invBtnPressed.dispose();
        inventory.dispose();
        closeBtn.dispose();
        invStatus.dispose();
        nextItemBtn.dispose();
        nextItemBtnPressed.dispose();
        nextItemBtnDisabled.dispose();
        prevItemBtn.dispose();
        prevItemBtnPressed.dispose();
        prevItemBtnDisabled.dispose();
    }
    
    // -------------- Методы обработки клавиатуры и мыши ------------
    
    @Override
    public boolean keyTyped (char character) {
        Player player = model.getPlayer();
        GameField field = model.getField();
        if(player.getState() == Character.CharacterState.stay){
            if(character == 'w' || character == 'W' || character == 'ц' || character == 'Ц'){
                player.move(field, Direction.north());
            }
            else if(character == 'd' || character == 'D' || character == 'в' || character == 'В'){
                player.move(field, Direction.east());
            }
            else if(character == 's' || character == 'S' || character == 'ы' || character == 'Ы'){
                player.move(field, Direction.south());
            }
            else if(character == 'a' || character == 'A' || character == 'ф' || character == 'Ф'){
                player.move(field, Direction.west());
            }
            else if(character == 'e' || character == 'E' || character == 'у' || character == 'У'){
                player.attack(field);
            }
        }
        return true;
    }
    
    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button){
        screenY = (field.getHeight()+3)*32 - screenY;
        int size = model.getPlayer().getInventory().size();
        if(!invBtnIsPressed && screenX >= (field.getWidth()-3)*32 && screenX <= (field.getWidth()-3)*32+106 && screenY >= (field.getHeight()+2)*32 && screenY <= (field.getHeight()+2)*32+28){
            invBtnIsPressed = true;
            return true;
        }
        else if(invBtnIsPressed && screenX >= field.getWidth()*16-176+320 && screenX <= field.getWidth()*16-176+336 && screenY >= (field.getHeight()+2)*16-176+320 && screenY <= (field.getHeight()+2)*16-176+336){
            invBtnIsPressed = false;
            return true;
        }
        else if(invBtnIsPressed && currInvItem < size-1 && screenX >= field.getWidth()*16-176+352-32 && screenX <= field.getWidth()*16-176+352-12 && screenY >= (field.getHeight()+2)*16-10 && screenY <= (field.getHeight()+2)*16+9){
            nextItemBtnIsPressed = true;
            currInvItem++;
            return true;
        }
        else if(invBtnIsPressed && currInvItem != 0 && screenX >= field.getWidth()*16-160 && screenX <= field.getWidth()*16-140 && screenY >= (field.getHeight()+2)*16-10 && screenY <= (field.getHeight()+2)*16+9){
            prevItemBtnIsPressed = true;
            currInvItem--;
            return true;
        }
        return false;    
    }
}
