/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.myrpg.event.LevelUpEvent;
import com.myrpg.event.LevelUpListener;
import com.myrpg.navigation.Direction;
import java.util.ArrayList;

/**
 *
 * @author Ann
 */
public class Player extends Character{
    
    public Player(int _lvl) {
        super(_lvl);
        LevelObserver observer = new LevelObserver();
        addLevelUpListener(observer);
    }
    
    public void increaseExp(int exp){
        if(getLevel().increaseExp(exp))
            fireLevelUpEvent();
    }
    
    @Override
    public boolean attack(GameField field){
        setState(Character.CharacterState.attack);
        
        // Определить цель атаки
        Character target = determineTarget(field);

        // Если цель атаки существует, то атаковать монстра
        if(target != null){
            if(!target.getDamage(this)){
                // Начислить игроку опыт
                increaseExp(target.getLevel().getValue()*3+3);
                // Сделать цель мертвой
                target.setState(Character.CharacterState.die);
                //setTarget(null);
                return true;
            }
        }
        return false;
    }
    
    public boolean move(GameField field, Direction dir){
        if(super.moveToCellInDir(field, dir)){
            if(!getCell().getItems().isEmpty())
                pickUpItems(getCell());
            return true;
        }
        return false;
    }
    
    public boolean pickUpItems(Cell cell){
        ArrayList <Item> items = cell.getItems();       
        // Добавляем все предметы с поля в инвентарь
        for(Item item:items){
            getInventory().add(item);
            // Надеваем предмет, меняя параметры и характеристики, если предмет подходит по интеллекту
            if(item.getRequiredIntellect() <= getParameters().getIntellect()){
                getParameters().addParameters(item.getParameters());
                getCharacteristics().setCharacteristics(getParameters());
                // Если предмет временный, то активируем его
                if(item.getType() == "temporary item")
                    ((TemporaryItem)item).setActivatedStatus(true);
                item.setEquipItem(true);
                // Проходим по всем остальным предметам и смотрим, стали ли они доступны/недоступны по интеллекту
                checkItemsByIntellect(item);
            }
        }
        if(items.isEmpty())
            return false;       
        // Удаляем все предметы с поля
        cell.clearItems();
        return true;
    }
    
    private void checkItemsByIntellect(Item curr){
        for(Item item:getInventory()){
            if(!item.equals(curr)){
                if(item.getRequiredIntellect() <= getParameters().getIntellect() && item.isEquip() == false){
                    getParameters().addParameters(item.getParameters());
                    getCharacteristics().setCharacteristics(getParameters());
                    // Если предмет временный, то активируем его
                    if(item.getType() == "temporary item")
                        ((TemporaryItem)item).setActivatedStatus(true);
                    item.setEquipItem(true);
                }
                else if(item.getRequiredIntellect() > getParameters().getIntellect() && item.isEquip()){
                    // Удаляем характеристики и параметры предмета
                    getParameters().removeParameters(item.getParameters());
                    getCharacteristics().setCharacteristics(getParameters());
                    if(item.getType() == "temporary item")
                        ((TemporaryItem)item).setActivatedStatus(false);
                    item.setEquipItem(false);
                }
            }
        }
    }
    
    public void checkInventory(){
        for(int i = 0; i < getInventory().size(); i++){
            if(getInventory().get(i).getType() == "temporary item"){
                // Если срок действия предмета вышел
                if(((TemporaryItem)getInventory().get(i)).isActivated()){
                    if(!((TemporaryItem)getInventory().get(i)).increaseTime()){
                        // Удаляем характеристики и параметры предмета
                        getParameters().removeParameters(getInventory().get(i).getParameters());
                        getCharacteristics().setCharacteristics(getParameters());
                        checkItemsByIntellect(getInventory().get(i));
                        // Удаляем предмет
                        getInventory().remove(i);                        
                        i--;                        
                    }
                }
            }
        }
    }
    
    public void moveToMonster(GameField field){
        if(getState() == com.myrpg.model.Character.CharacterState.stay){
            if(moveToTarget(getTarget().getCell(), field, 1)){
                // Атаковать цель
                attack(field);
            }
        }
    }
    
    @Override
    public String getType(){
        return "player";
    }
    
    // ------------------- Слушатели -------------------------
    public class LevelObserver implements LevelUpListener{

        @Override
        public void LevelUp(LevelUpEvent e) {
            if(getType() == "player"){
                // Увеличить параметры
                getParameters().addParameters(new Parameters(2,2,2,1,1,3));
            }
            else {
                getParameters().addParameters(new Parameters(1,1,1,1,1,1));
            }
            // Увеличить характеристики
            getCharacteristics().setCharacteristics(getParameters());
            // Отрегенить здоровье
            getCharacteristics().setMaxCurrHealth();
        }       
    }
    
    private ArrayList<LevelUpListener> listeners = new ArrayList<LevelUpListener>();
    
    public void addLevelUpListener(LevelUpListener listener){
 	listeners.add(listener);
    }
    
    public LevelUpListener[] getLevelUpListeners(){
 	return listeners.toArray(new LevelUpListener[listeners.size()]);
    }

    public void removeLevelUpListener(LevelUpListener listener){
 	listeners.remove(listener);
    }
    
    protected void fireLevelUpEvent(){
 	LevelUpEvent e = new LevelUpEvent(this);
 	for (LevelUpListener listener : listeners){
 	    listener.LevelUp(e);
 	}
    }
}
