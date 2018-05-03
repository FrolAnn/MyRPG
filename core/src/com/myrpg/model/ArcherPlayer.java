/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Ann
 */
public class ArcherPlayer extends Player{
    
    private int attackRange;
    private Arrow arrow;
    private boolean inAttack = false;
    
    public ArcherPlayer(int _lvl, int _attackRange) {
        super(_lvl);
        attackRange = _attackRange;
    }
    
    public int getAttackRange(){
        return attackRange;
    }
    
    public Arrow getArrow(){
        return arrow;
    }
    
    @Override
    public Character determineTarget(GameField field){
        ArrayList <Cell> cells = field.getLineOfCells(getCell(), getDirection(), attackRange);
        ArrayList <Cell> arrowTrajectory = new ArrayList <Cell>();
        
        for(Cell cell:cells){
            if(cell.getCharacter() != null){
                arrow = new Arrow(arrowTrajectory, getDirection());
                return cell.getCharacter();
            }
            arrowTrajectory.add(cell);
        }
        arrow = new Arrow(arrowTrajectory, getDirection());
        return null;
    }
    
    @Override
    public void moveToMonster(GameField field){
        Point targetPoint = getTarget().getCell().getPosition();
        Point currPoint = getCell().getPosition();
        if(getState() == com.myrpg.model.Character.CharacterState.stay){
            if(!inAttack){
                if(moveToTarget(getTarget().getCell(), field, getAttackRange())){
                    inAttack = true;
                }
                else if((targetPoint.x == currPoint.x && (Math.abs(targetPoint.y - currPoint.y) <= this.getAttackRange())) 
                        || (targetPoint.y == currPoint.y && (Math.abs(targetPoint.x - currPoint.x) <= this.getAttackRange()))){
                    inAttack = true;
                }
            }
            else{
                if(attack(field))
                    inAttack = false;
            }
        }
    }
    
    @Override
    public String getType(){
        return "archer player";
    }
}
