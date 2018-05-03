/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myrpg.navigation.Direction;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Ann
 */
public class Arrow {
    
    private Texture arrow;
    private TextureRegion arrowUp, arrowRight, arrowDown, arrowLeft;
    private ArrayList <Cell> trajectory;
    private Direction dir;
    private Point pos;
    
    public Arrow(ArrayList <Cell> _trajectory, Direction _dir){
        arrowUp = new TextureRegion();
        arrowLeft = new TextureRegion();
        arrowDown = new TextureRegion();
        arrowRight = new TextureRegion();
        
        arrow = new Texture(Gdx.files.internal("game screen/arrow.png"));
        TextureRegion tmp[][] = TextureRegion.split(arrow, arrow.getWidth()/5, arrow.getHeight());
        arrowUp = tmp[0][0];
        arrowRight = tmp[0][2];
        arrowLeft = new TextureRegion(tmp[0][2]);
        arrowLeft.flip(true, false);
        arrowDown = tmp[0][4];
        
        trajectory = _trajectory;
        dir = _dir;
    }
    
    public void draw(SpriteBatch batch){
        if(!trajectory.isEmpty()){
            pos = trajectory.get(0).getPosition();
            trajectory.remove(0);
            if(dir.equals(Direction.north()))
                batch.draw(arrowUp, (pos.x)*32+16, (pos.y)*32+16, 32, 32);
            else if(dir.equals(Direction.south()))
                batch.draw(arrowDown, (pos.x)*32+16, (pos.y)*32+16, 32, 32);
            else if(dir.equals(Direction.east()))
                batch.draw(arrowRight, (pos.x)*32+16, (pos.y)*32+16, 32, 32);
            else
                batch.draw(arrowLeft, (pos.x)*32+16, (pos.y)*32+16, 32, 32);
        }
    }
}
