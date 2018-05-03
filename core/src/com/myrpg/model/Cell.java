/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Ann
 */
public class Cell {

    private Point position;
    private Character character = null;
    private ArrayList<Item> items = new ArrayList<Item>();
    public float itemStateTime = 0f;
    public TextureRegion itemCurrentFrame;
    
    public void setPosition(Point point) {
        position = point;
    }
    
    public Point getPosition(){
        return position;
    }
    
    public boolean isEmpty(){
        return character == null && items.isEmpty();
    }
    
    public void setCharacter(Character _character){
        character = _character;
    }
    
    public Character getCharacter(){
        return character;
    }
    
    public ArrayList<Item> getItems(){
        return items;
    }
    
    public void clear(){
        character = null;
        clearItems();
    }
    
    public void clearItems(){
        items.clear();
    }
    
    public void addItems(ArrayList<Item> _items){
        for(Item item:_items)
            items.add(item);
    }
}
