/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.factory;

import com.myrpg.model.AggressiveMonster;
import com.myrpg.model.Player;
import com.myrpg.model.Character;
import com.myrpg.model.Monster;
import com.myrpg.model.ArcherPlayer;

/**
 *
 * @author Ann
 */
public class CharacterFactory {
    public Character createWarriorPlayer(int lvl){
        return new Player(lvl);
    }
    public Character createArcherPlayer(int lvl, int attackRange){
        return new ArcherPlayer(lvl, attackRange);
    }
    public Character createPassiveMonster(int lvl){
        return new Monster(lvl);
    }
    public Character createAggressiveMonster(int lvl, int aggressionRadius){
        return new AggressiveMonster(lvl, aggressionRadius);
    }
}
