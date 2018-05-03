/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.myrpg.factory.CharacterFactory;
import com.myrpg.game.MyRpgGame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ann
 */
public class GameModel implements BotInterface {
    
    private GameField field;
    private Character player;
    private Random rnd = new Random();
      
    public GameField getField(){
        return field;
    }
    
    public GameModel(){
        field = new GameField(17, 26);
    }
    
    public void start(MyRpgGame.playerType type){    
        CharacterFactory factory = new CharacterFactory();
        
        // Генерируем поле
        generateField();
        
        // Генерируем игрока
        generatePlayer(factory, type);
        
        // Генерируем монстров
        generateMonsters(factory);
    }
    
    @Override
    public boolean determineEndOfTheGame(){
        // Если все монстры убиты - победа
        if(field.getMonsters().isEmpty() || player == null){
            return true;
        }
        return false;
    }
    
    public void gameEnd(){
        // Удалить игрока
        player.remove();
        player = null;
        
        // Удалить монстров
        ArrayList <Monster> list = field.getMonsters();
        for(Monster monster:list)
            monster.remove();
        
        // Очистить все ячейки
        field.clear();
    }
    
    private void generateField(){
        field.clear(); // Очищаем поле, создавая новый массив ячеек
        // Заполняем поле пустыми ячейками, присваивая ячейкам позицию
        for(int i = 0; i < field.getHeight(); i++){
            for(int j = 0; j < field.getWidth(); j++){
                field.setCell(new Point(j, i), new Cell());
            }
        }
    }
    
    private void generateMonsters(CharacterFactory factory){
        int lvl;
        Character monster;
        
        // Определяем количество монстров. Пусть это будет 3% от размеров поля
        int count = (int) (field.getHeight()*field.getWidth()*0.02);
        
        // Генерируем заданное количество монстров
        for(int i = 0; i < count; i++){
            // Выбираем незанятую ячейку поля
            Cell cell = field.getEmptyCell();
            
            // Создаем монстров различного уровня
            lvl = rnd.nextInt((int)(count*0.3))+1; 
            // С некоторой вероятностью создаем обычного, либо агрессивного монстра
            if(rnd.nextInt(100) < 40)
                monster = factory.createAggressiveMonster(lvl, 3);
            else
                monster = factory.createPassiveMonster(lvl);
            // Создаем случайный предмет для монстра
            if(rnd.nextBoolean()) // Временный предмет
                monster.addItem(new TemporaryItem());
            else // Постоянный предмет
                monster.addItem(new Item());
            monster.setCell(cell);
        }
    }
    
    private void generatePlayer(CharacterFactory factory, MyRpgGame.playerType type){
        // Случайным образом выбрать незанятую ячейку поля
        Cell cell = field.getEmptyCell();
        
        // Создать игрока с заданным уровнем и параметрами
        if(type == MyRpgGame.playerType.warrior)
            player = factory.createWarriorPlayer(1);
        else
            player = factory.createArcherPlayer(1, 5);
        
        // Добавить игрока на поле
        player.setCell(cell);
    }
    
    public Player getPlayer(){
        return (Player)player;
    }
    
    public void monsterMovement(){
        // Получаем всех монстров
        ArrayList <Monster> monsters = field.getMonsters();
        
        for(Monster monster:monsters){
            monster.move(field);
        }
    }
    
    public void monsterAttack() {
        ArrayList <Monster> monsters = field.getMonsters();
        // Каждый монстр атакует игрока, если он у него в таргете
        for(Monster monster:monsters){
            monster.attack(field);
        } 
    }

    @Override
    public ArrayList<Monster> getMonsters() {
        return field.getMonsters();
    }

    @Override
    public void movePlayerToTargetAndAttack() {
        if(player.getType() == "archer player")
            ((ArcherPlayer)player).moveToMonster(field);
        else
            ((Player)player).moveToMonster(field);
    }

    @Override
    public boolean movePlayerToItemAndPickUp(Cell cellWithItem) {
        if(player.getState() == com.myrpg.model.Character.CharacterState.stay){
                if(!cellWithItem.getItems().isEmpty()){
                    if(player.moveToTarget(cellWithItem, getField(), 0)){                    
                        ((Player)player).pickUpItems(cellWithItem);
                        player.setTarget(null);
                        return true;
                    }                
                }                
            }
        return false;
    }
}
