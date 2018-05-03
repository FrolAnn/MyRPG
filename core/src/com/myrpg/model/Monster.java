/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.myrpg.navigation.Direction;
import java.awt.Point;

/**
 *
 * @author Ann
 */
public class Monster extends Character {

    public Monster(int _lvl) {
        super(_lvl);
    }

    public void move(GameField field) {
        Direction dir;
        if (rnd.nextInt(100) < 7 && getState() == Character.CharacterState.stay && getTarget() == null) {
            // Для каждого генерируем случайное направление
            dir = Direction.randomDirection();

            // Перемещаем каждого монстра в этом направлении
            if (getDirection().equals(dir)) {
                super.moveToCellInDir(field, dir);
            } else {
                super.moveToCellInDir(field, dir);
                super.moveToCellInDir(field, dir);
            }
        }
    }
    
    @Override
    public boolean attack(GameField field) {
        Character target = getTarget();
       
        if(target != null && getState() == Character.CharacterState.stay){
            // Повернуться к игроку
            turnToThePlayer(field, target.getCell());
            Character ch = field.getNextCell(getCell(), getDirection()).getCharacter();
            if(ch == null || (ch != null && ch.getType() != "player" && ch.getType() != "archer player")){
                // Подойти к игроку, если он не рядом
                super.moveToTarget(target.getCell(), field, 1);                 
            }
            else {
                setState(Character.CharacterState.attack);
                target.getDamage(this);
                target.setTarget(this);
            }            
        }    
        return false;
    }
    
    private void turnToThePlayer(GameField field, Cell cell){
        if(cell.getPosition().y > getCell().getPosition().y)
            setDirection(Direction.north());
        else if(cell.getPosition().x > getCell().getPosition().x)
            setDirection(Direction.east());
        else if(cell.getPosition().y < getCell().getPosition().y)
            setDirection(Direction.south());
        else
            setDirection(Direction.west());
    }
    
    @Override
    public void remove(){
        // Выбрасываем на ячейку предмет
        getCell().addItems(getInventory());
        
        super.remove();
    }
    
    @Override
    public String getType(){
        return "monster";
    }
}
