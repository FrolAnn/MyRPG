/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import java.util.HashMap;

/**
 *
 * @author Ann
 */
public class Teleportator extends Item{
    private boolean activation = false;
    private String description = "Teleports the player to the selected cell. \nTo teleport left-click on the field, to cancel press esc.\nAfter using the item disappears.";;
    
    public String getDescription(){
        return description;
    }
    
    public void setActivation(boolean _activation){
        activation = _activation;
    }
    
    public boolean getActivation(){
        return activation;
    }
    
    public void use(Player player, Cell cell){
        if(cell.isEmpty()){
            player.getCell().setCharacter(null);
            player.setCell(cell);
            setActivation(true);
        }
    }
    
    public String getType(){
        return "teleportator";
    }
    
    @Override
    public HashMap<String, String> getInfo(){
        HashMap<String, String> info = super.getInfo();
        info.put("description", description);
        return info;
    }
}
