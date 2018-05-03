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
public class Сharacteristics  {
    
    private int maxHealth; // Макс. здоровье
    private int currHealth; // Текущее здоровье
    private int attackPower; // Сила атаки
    private double evasion; // Уклонение
    private double blockingOfDamage; // Процент блокировки урона
    private double avoidingCombat; // Вероятность избежания боя с агрессивными монстрами
    
    public Сharacteristics(Parameters param){
        setCharacteristics(param);
        currHealth = maxHealth;
    }
    
    public void setCharacteristics(Parameters param){
        maxHealth = param.getConstitution()*10;
        if(currHealth > maxHealth)
            currHealth = maxHealth;
        attackPower = param.getPower()*2;
        if(param.getProtection()*0.02 > 0.7)
            blockingOfDamage = 0.7;
        else
            blockingOfDamage = param.getProtection()*0.02;
        if(param.getAgility()*0.02 > 0.7)
            evasion = 0.7;
        else
            evasion = param.getAgility()*0.02;
        if(param.getCharisma()*0.02 > 1)
            avoidingCombat = 1;
        else
            avoidingCombat = param.getCharisma()*0.02;
    }
    
    public double getAvoidingCombat(){
        return avoidingCombat;
    }
    
    public double getBlockingOfDamage(){
        return blockingOfDamage;
    }
    
    public int getAttackPower(){
        return attackPower;
    }
    
    public double getEvasion(){
        return evasion;
    }
    
    public int getCurrHealth(){
        return currHealth;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public void decreaseHealth(int damage){
        if(damage <= currHealth)
            currHealth -= damage;
        else
            currHealth = 0;
    }
    
    public void setMaxCurrHealth(){
        currHealth = maxHealth;
    }
}
