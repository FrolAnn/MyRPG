package test.java.com.myrpg.modules;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.myrpg.game.MyRpgGame;
import com.myrpg.model.Bot;
import com.myrpg.model.Cell;
import com.myrpg.model.GameModel;
import com.myrpg.model.Item;
import com.myrpg.model.TemporaryItem;
import java.awt.Point;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ann
 */
public class TestBot {

    
    /**
     * Test of moveToTarget method, of class Easy.
     */
    @Test
    public void Test_movePlayerToItemAndPickUp_withoutItems() {
        Bot bot = new GameModel();
        ((GameModel)bot).start(MyRpgGame.playerType.warrior);
        Cell cell = new Cell(new Point(10,3));
        
        bot.movePlayerToItemAndPickUp(cell);
        
        assertTrue(bot.getPlayer().getInventory().isEmpty());
    }
    
    @Test
    public void Test_movePlayerToItemAndPickUp_oneItems() {
        Bot bot = new GameModel();
        ((GameModel)bot).start(MyRpgGame.playerType.warrior);
        Cell cell = new Cell(new Point(10,3));
        bot.getPlayer().setCell(cell);
        Item item = new Item();
        ArrayList <Item> items = new ArrayList<Item>();
        items.add(item);
        cell.addItems(items);
        
        bot.movePlayerToItemAndPickUp(cell);
        
        int itemCount = bot.getPlayer().getInventory().size();
        assertTrue(itemCount == 1);
    }
    
    @Test
    public void Test_movePlayerToItemAndPickUp_manyItems() {
        Bot bot = new GameModel();
        ((GameModel)bot).start(MyRpgGame.playerType.warrior);
        Cell cell = new Cell(new Point(10,3));
        bot.getPlayer().setCell(cell);
        Item item1 = new Item();
        TemporaryItem item2 = new TemporaryItem();
        ArrayList <Item> items = new ArrayList<Item>();
        items.add(item1);
        items.add(item2);
        cell.addItems(items);
        
        bot.movePlayerToItemAndPickUp(cell);
        
        int itemCount = bot.getPlayer().getInventory().size();
        assertTrue(itemCount == 2);
    }
}
