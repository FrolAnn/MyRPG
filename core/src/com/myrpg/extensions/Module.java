/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.extensions;

import com.myrpg.game.MyRpgGame;
import com.myrpg.model.Bot;

public interface Module {

  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = 1;

  public void load(Bot _bot);
  public int run();
  public void unload();
}
