/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import java.util.ArrayList;

/**
 *
 * @author Ann
 */
public class AggressiveMonster extends Monster{
    
    private int aggressionRadius; // Радиус агрессивности
    
    public AggressiveMonster(int _lvl, int _aggressionRadius) {
        super(_lvl);
       aggressionRadius = _aggressionRadius;
    }
    
    public void findAndAttackPlayer(GameField field){
        // Искать игрока в радиусе вокруг монстра
        ArrayList <Cell> cells = field.getAreaAroundTheCell(getCell(), aggressionRadius);
        // Если игрок найден, то двигаться к нему
        for(Cell cell:cells){
            if(cell.getCharacter() != null && (cell.getCharacter().getType() == "player" || cell.getCharacter().getType() == "archer player")){
                if((double)(rnd.nextInt(100))/100 > cell.getCharacter().getCharacteristics().getAvoidingCombat()){
                    setTarget(cell.getCharacter());
                    if(getState() == CharacterState.stay){
                        if(moveToTarget(cell, field, 1)){
                            // Атаковать игрока
                            super.attack(field);
                        }
                    }
                }
            }
            else
                setTarget(null);
        }
    }   
    
    @Override
    public boolean attack(GameField field) {
        super.attack(field);      
        if(getTarget() == null)
            findAndAttackPlayer(field);
        return false;
    }
        
    @Override
    public String getType(){
        return "aggressive monster";
    }    
}
