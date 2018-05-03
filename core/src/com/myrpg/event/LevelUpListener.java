/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.event;

import com.myrpg.model.Parameters;
import java.util.EventListener;

/**
 *
 * @author Ann
 */
public interface LevelUpListener extends EventListener {
    
    public void LevelUp(LevelUpEvent e);
}
