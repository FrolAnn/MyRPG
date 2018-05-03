package com.myrpg.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myrpg.game.MyRpgGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
	    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "MyRpg";
            config.width = 860;
            config.height = 640;
            config.resizable = false;
            new LwjglApplication(new MyRpgGame(), config);
	}
}
