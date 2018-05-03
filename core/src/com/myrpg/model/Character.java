/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.myrpg.navigation.Direction;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Ann
 */
public abstract class Character {
    
    private Level lvl;
    private Parameters parameters;
    private Сharacteristics characteristics;
    
    private Cell cell = null;
    
    private Direction direction;
    private Character target = null;
    
    public float stateTime = 0f;
    public TextureRegion currentFrame;
    protected Random rnd = new Random();
       
    private CharacterState state = CharacterState.stay;
    
     // Состояния персонажа
    public enum CharacterState {stay, movement, attack, die};
    
    public Character(int _lvl){
        // Здаем лвл и характеристики персонажа
        lvl = new Level(_lvl);
        parameters = new Parameters(_lvl);
        characteristics = new Сharacteristics(parameters);
        
        // Задаем случайное направление
        direction = Direction.randomDirection();
        
        inventory = new ArrayList <Item>();
    }
    
    private ArrayList <Item> inventory;
    
    public void addItem(Item item){
        inventory.add(item);
    }

    public boolean getDamage(Character attacker) {
        target = attacker;
        
        // С вероятностью уклонения потерять здоровье
        if((double)(rnd.nextInt(100))/100 > characteristics.getEvasion()){
            characteristics.decreaseHealth((int)(attacker.characteristics.getAttackPower()*(1-characteristics.getBlockingOfDamage())));
        }
        
        // Проверить, жив ли персонаж
        if(characteristics.getCurrHealth() == 0){
            state = CharacterState.die;
            // Оповестить модель, что монстр мертв, чтобы она начислила опыт
            return false;
        }
        return true;
    }
    
    public Parameters getParameters(){
        return parameters;
    }
    
    public Сharacteristics getCharacteristics(){
        return characteristics;
    }
    
    public Character getTarget(){
        return target;
    }
    
    public void setTarget(Character ch){
        target = ch;
    }
    
    public Level getLevel(){
        return lvl;
    }

    public String getShortInfo() {
        String info = lvl.getValue() + " lvl. " + characteristics.getCurrHealth() + "/" + characteristics.getMaxHealth() + "HP";
        return info;
    }
    
    public HashMap<String, String> getFullInfo(){
        HashMap<String, String> info = new HashMap<String, String>();       
        info.put("lvl", "Level: " + lvl.getValue() + ". Exp: " + lvl.getCurrExp() + "/" + lvl.getMaxExp());
        info.put("hp", "HP: " + characteristics.getCurrHealth() + "/" + characteristics.getMaxHealth());
        info.put("attack", "Attack: " + characteristics.getAttackPower());
        info.put("evasion", "Evasion: " + characteristics.getEvasion());
        info.put("con", "Constitution: " + parameters.getConstitution());
        info.put("power", "Power: " + parameters.getPower());
        info.put("agility", "Agility: " + Math.rint(100*parameters.getAgility())/100);
        info.put("protection", "Protection: " + parameters.getProtection());
        info.put("blockingOfDamage", "Blocking of damage: " + Math.rint(100*characteristics.getBlockingOfDamage())/100);
        info.put("intellect", "Intellect: " + parameters.getIntellect());
        info.put("charisma", "Charisma: " + parameters.getCharisma());
        info.put("avoidingCombat", "Probability of avoiding combat: " + Math.rint(100*characteristics.getAvoidingCombat())/100);
        return info;
    }
      
    public void setState(CharacterState _state){
        state = _state;
    }
    
    public CharacterState getState(){
        return state;
    }
    
    protected void setCell(Cell _cell){
        if(cell != null)
            cell.clear();
        _cell.setCharacter(this);
        cell = _cell;
    }
    
    public Cell getCell(){
        return cell;
    }
    
    public Direction getDirection(){
        return direction;
    }
    
    protected void setDirection(Direction dir){
        direction = dir;
    }
    
    public boolean moveToCellInDir(GameField field, Direction dir){
        // Если пресонаж не смотрит в сторону движения
        if(!getDirection().equals(dir)){
            // Поворачиваем персонажа
            setDirection(dir);
            return false;
        }
        // Иначе перемещаем персонажа
        else{     
            Cell nextCell;
            nextCell = field.getNextEmptyCell(getCell(), dir);
            if(nextCell != null){
                setState(Character.CharacterState.movement);
                setCell(nextCell);
                return true;
            }
            return false;
        }
    }
    
    public  ArrayList<Item> getInventory(){
        return inventory;
    }
    
    public Character determineTarget(GameField field){
        Cell cellOfTarget = field.getNextCell(getCell(), getDirection());
        if(cellOfTarget != null){
            return cellOfTarget.getCharacter();
        }
        return null;
    }
    
    protected void remove(){
        // Очищаем инвентарь
        inventory = null;
        
        // Удаляем монстра с ячейки
        cell.setCharacter(null);
        cell = null;
    }


    public boolean moveToTarget(Cell cell, GameField field, int distance){
        Point currPos = getCell().getPosition();
        Point playerPos = cell.getPosition();
        
        if(cell.getPosition().y > getCell().getPosition().y)
            setDirection(Direction.north());
        else if(cell.getPosition().x > getCell().getPosition().x)
            setDirection(Direction.east());
        else if(cell.getPosition().y < getCell().getPosition().y)
            setDirection(Direction.south());
        else if(cell.getPosition().x < getCell().getPosition().x)
            setDirection(Direction.west());
        if(currPos.x + distance == playerPos.x && currPos.y == playerPos.y || currPos.x == playerPos.x && currPos.y-distance == playerPos.y 
           || currPos.x-distance == playerPos.x && currPos.y == playerPos.y || currPos.x == playerPos.x && currPos.y + distance == playerPos.y)
            return true;
        moveToCellInDir(field, getDirection());
        return false;
    }
    
    public abstract String getType();
    
    public abstract boolean attack(GameField field);
}
