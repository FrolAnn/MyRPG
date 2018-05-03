package com.myrpg.extensions;

import com.myrpg.game.MyRpgGame;
import java.io.File;
import javax.swing.JFileChooser;

public class ModuleEngine {
    
 public static Module _execute = null;
  public static void main(String args[], MyRpgGame game, MyRpgGame.playerType botType) {
    String modulePath = args[0];
    System.out.print("Module path: ");
    System.out.println(modulePath);
    /**
     * Создаем загрузчик модулей.
     */
    ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());
    
    JFileChooser fileopen = new JFileChooser();    
    fileopen.setCurrentDirectory(new File(args[0]));
    int ret = fileopen.showDialog(null, "Открыть файл");
    String filePath="";
    if(ret == JFileChooser.APPROVE_OPTION)
    {
        filePath =  fileopen.getSelectedFile().getPath();
        System.out.println(filePath);
    }
    
    if (filePath == "")
    {
        System.out.println("Module path does not choose");
        return;
    }
    /**
     * Загружаем и исполняем каждый модуль.
     */
    if (filePath.endsWith(".class")) {
        try {
            String moduleName = filePath.substring(filePath.lastIndexOf("\\")+1, filePath.length()-6);
            if (moduleName.equals("Module") == false) {
                System.out.print("Executing loading module: ");
                System.out.println(moduleName);

                Class clazz = loader.loadClass( "com.myrpg.modules." + moduleName);
                _execute = (Module) clazz.newInstance();
                _execute.load(game.getModel());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
  }
}
