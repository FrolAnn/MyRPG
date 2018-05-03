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
public class Parameters {
    
    private int constitution; // Телосложение
    private int power; // Сила
    private int agility; // Ловкость
    private int protection; // Защита
    private int intellect; // Интеллект
    private int charisma; // Харизма
    
    public Parameters(int lvl){
        setParameters(lvl);
    }
    
    public Parameters(int _constitution, int _power, int _agility, int _protection, int _intellect, int _charisma){
        constitution = _constitution;
        power = _power;
        agility = _agility;
        protection = _protection;
        intellect = _intellect;
        charisma = _charisma;
    }
    
    public void setParameters(int lvl){
        constitution = lvl*2;
        power = lvl*2;
        agility = lvl*2;
        protection = lvl;
        intellect = lvl;
        charisma = lvl*3;
    }
    
    public int getConstitution(){
        return constitution;
    }
    
    public int getCharisma(){
        return charisma;
    }
    
    public int getPower(){
        return power;
    }
    
    public int getAgility(){
        return agility;
    }
    
    public int getProtection(){
        return protection;
    }
    
    public int getIntellect(){
        return intellect;
    }
    
    public void addParameters(Parameters param){
        constitution = constitution + param.getConstitution() >= 0 ? constitution + param.getConstitution() : 0;
        power = power + param.getPower()>= 0 ? power + param.getPower() : 0;
        agility = agility + param.getAgility()>= 0 ? agility + param.getAgility() : 0;
        protection = protection + param.getProtection()>= 0 ? protection + param.getProtection() : 0;
        intellect = intellect + param.getIntellect()>= 0 ? intellect + param.getIntellect() : 0;
        charisma = charisma + param.getCharisma()>= 0 ? charisma + param.getCharisma() : 0;
    }
    
    public void removeParameters(Parameters param){
        constitution = constitution - param.getConstitution() >= 0 ? constitution - param.getConstitution() : 0;
        power = power - param.getPower()>= 0 ? power - param.getPower() : 0;
        agility = agility - param.getAgility()>= 0 ? agility - param.getAgility() : 0;
        protection = protection - param.getProtection()>= 0 ? protection - param.getProtection() : 0;
        intellect = intellect - param.getIntellect()>= 0 ? intellect - param.getIntellect() : 0;
        charisma = charisma - param.getCharisma()>= 0 ? charisma - param.getCharisma() : 0;
    }
}
