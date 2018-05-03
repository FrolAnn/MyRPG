/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

/**
 *
 * @author Ann
 */
public class Level {
    
    private int currLvl;
    private int currExp;
    private int maxExp;
    
    public Level(int _currLvl){
        currLvl = _currLvl;
        currExp = 0;
        maxExp = currLvl*5;
    }
    
    public int getValue(){
        return currLvl;
    }
    
    public int getCurrExp(){
        return currExp;
    }
    
    public int getMaxExp(){
        return maxExp;
    }
    
    public boolean increaseExp(int exp){
        boolean levelUp = false;
        while(currExp + exp > maxExp){
            exp -= maxExp - currExp;
            currLvl++;
            currExp = 0;
            maxExp = currLvl*5;
            levelUp = true;
        }
        currExp += exp;
        return levelUp;
    }
    
    // -------------------- Слушатели -------------------------
    
    
}
