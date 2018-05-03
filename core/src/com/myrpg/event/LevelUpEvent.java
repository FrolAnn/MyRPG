/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.event;

import com.myrpg.model.Level;
import com.myrpg.model.Parameters;
import com.myrpg.model.Сharacteristics;
import java.util.EventObject;

/**
 *
 * @author Ann
 */
public class LevelUpEvent extends EventObject{
    
    public LevelUpEvent(Object source) {
        super(source); 
    }  
}
