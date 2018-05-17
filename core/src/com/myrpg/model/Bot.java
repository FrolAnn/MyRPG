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
public interface Bot {
    
    /**
     * Получение списка всех монстров на карте
     * @return список монстров
     */
    public ArrayList <Monster> getMonsters();
    
    /**
     * Определение окончания игры (когда игрок мертв, либо когда все монстры убиты)
     * @return признак, что игра окончена
     */
    public boolean determineEndOfTheGame();
    
    /**
     * Перемещение персонажа к цели с последующей атакой
     */
    public void movePlayerToTargetAndAttack();
    
    /**
     * Перемещение персонажа к клетке с предметом и поднятие предмета
     * @return признак, что предмет поднят
     */
    public boolean movePlayerToItemAndPickUp(Cell cellWithItem);
    
    /**
     * Получение игрока
     * @return объект игрока
     */ 
    public Character getPlayer();
    
    /**
     * Получение поля
     * @return объект поля
     */
    public GameField getField();
}
