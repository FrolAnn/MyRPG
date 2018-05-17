/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.modules;

import com.myrpg.extensions.Module;
import com.myrpg.game.MyRpgGame;
import com.myrpg.model.Cell;
import com.myrpg.model.Monster;
import com.myrpg.model.Character;
import java.util.ArrayList;
import com.myrpg.model.Bot;

public class SimpleMyRpgBotModule implements Module {

  private Bot bot;
  private Monster target;
  private Character player;
  
  @Override
  public void load(Bot _bot) {
    System.out.println("Module " + this.getClass() + " loading ...");
    bot = _bot;
    player = bot.getPlayer();
  }

  @Override
  public int run() {
    System.out.println("Module " + this.getClass() + " running ...");
    ArrayList <Monster> monsters = new ArrayList <Monster>();
    if(!bot.determineEndOfTheGame()){
        if(player.getTarget() == null){
            // Получаем список монстров
            monsters = bot.getMonsters();

            // Выбираем монстра наименьшего уровня
            target = monsters.get(0);
            for(Monster monster:monsters){
                if(monster.getLevel().getValue() < target.getLevel().getValue())
                    target = monster;
            }

            player.setTarget(target);                                              
        } 
        else{
            if(player.getTarget().getState() == Character.CharacterState.die)
                player.setTarget(null);
            else
                bot.movePlayerToTargetAndAttack();
        }
    }
    return Module.EXIT_SUCCESS;
  }

  @Override
  public void unload() {
    System.out.println("Module " + this.getClass() + " inloading ...");
  }
}
