/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Ann
 */
public class TemporaryItem extends Item {
    
    private int lifetime;
    private boolean isActivated;
    private int currTime;
    
    public TemporaryItem(){
        super();
        isActivated = false;
        currTime = 0;
        // Определяем случайное время существования предмета от 30 секунд до 3 минут
        Random rnd = new Random();
        lifetime = rnd.nextInt(150)+30;
    }
    
    public void setActivatedStatus(boolean _isActivate){
        isActivated = _isActivate;
    }
    
    public boolean increaseTime(){
        currTime++;
        if(currTime > lifetime)
            return false;
        return true;
    }
    
    public boolean isActivated(){
        return isActivated;
    }
    
    @Override
    public HashMap<String, String> getInfo(){
        HashMap<String, String> info = super.getInfo();
        info.put("lifetime", "Lifetime: " + lifetime + " sec");
        info.put("currTime", "Current time: " + currTime + " sec");
        return info;
    }
    
    @Override
    public String getType(){
        return "temporary item";
    }
}
