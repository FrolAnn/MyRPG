/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Ann
 */
public class Item {
    
    private Parameters param;
    private Random rnd = new Random();
    private int requiredIntellect;
    private boolean isEquip;
    
    public Item(){
        // Создаются случайные параметры в диапазоне от -2 до 4 и защита от 0 до 2
        param = new Parameters(rnd.nextInt(6)-2, rnd.nextInt(6)-2, rnd.nextInt(6)-2, rnd.nextInt(2), rnd.nextInt(6)-2, rnd.nextInt(6)-2);
        requiredIntellect = rnd.nextInt(10);
        isEquip = false;
    }
    
    public Parameters getParameters(){
        return param;
    }
    
    public boolean isEquip(){
        return isEquip;
    }
    
    public void setEquipItem(boolean _isEquip){
        isEquip = _isEquip;
    }
    
    public int getRequiredIntellect(){
        return requiredIntellect;
    }
    
    public String getType(){
        return "eternal item";
    }
    
    public HashMap<String, String> getInfo(){
        HashMap<String, String> info = new HashMap<String, String>();       
        info.put("constitution", "Constitution: " + param.getConstitution());
        info.put("power", "Power: " + param.getPower());
        info.put("agility", "Agility: " + param.getAgility());  
        info.put("protection", "Protection: " + param.getProtection());
        info.put("intellect", "Intellect: " + param.getIntellect());
        info.put("requiredIntellect", "Required intellect: " + requiredIntellect);
        info.put("charisma", "Charisma: " + param.getCharisma());
        info.put("isEquip", "Item is " + (isEquip ? "equip" : "not equip"));
        return info;
    }
}
