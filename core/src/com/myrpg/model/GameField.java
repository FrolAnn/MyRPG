/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrpg.model;

import com.myrpg.navigation.Direction;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ann
 */
public class GameField {
    
    private int width;
    private int height;
    
    private Cell arrayOfCell[][];
    
    private Random rnd = new Random();
    
    public GameField(int _height, int _width){
        height = _height;
        width = _width; 
        arrayOfCell = new Cell[height][width];
    }
    
    public void clear() {  
        arrayOfCell = new Cell[height][width];
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }

    public void setCell(Point point, Cell cell) {
        cell.setPosition(point);
        arrayOfCell[point.y][point.x] = cell;
    }
    
    public Cell getEmptyCell(){
        int x, y; 
        do{
            x = rnd.nextInt(width);
            y = rnd.nextInt(height);
        }while(!arrayOfCell[y][x].isEmpty());
        return arrayOfCell[y][x];
    }
    
    public Cell getNextCell(Cell currCell, Direction dirToNextCell){
        Point currPos =  currCell.getPosition();
        int x = currPos.x;
        int y =  currPos.y;
        if(dirToNextCell.equals(Direction.east()) && x+1 <= width-1)
            x += 1;
        else if(dirToNextCell.equals(Direction.west()) && x-1 >= 0)
            x -= 1;
        else if(dirToNextCell.equals(Direction.north()) && y+1 <= height-1)
            y += 1;
        else if(dirToNextCell.equals(Direction.south()) && y-1 >= 0)
            y -= 1;
        else
            return null;
        
        return arrayOfCell[y][x];
    }
    
    public Cell getNextEmptyCell(Cell currCell, Direction dirToNextCell){
        Cell cell = getNextCell(currCell, dirToNextCell);
            if(cell != null){
            int y = cell.getPosition().y;
            int x = cell.getPosition().x;
            if(arrayOfCell[y][x].getCharacter() == null || (arrayOfCell[y][x].getCharacter() != null && arrayOfCell[y][x].getCharacter().getState()  == Character.CharacterState.die))
                return arrayOfCell[y][x];
            else
                return null;
        }
        return cell;
    }
    
    public Cell getCell(Point pos){
        if(pos.x <= width && pos.y <= height)
            return arrayOfCell[pos.y][pos.x];
        else
            return null;
    }

    public ArrayList<Monster> getMonsters() {
        ArrayList <Monster> monsters = new ArrayList <Monster>();
        Character character;
        for(int i = 0; i < height; i++){
           for(int j = 0; j < width; j++){
               character = arrayOfCell[i][j].getCharacter();
               if(character != null && (character.getType() == "monster" || character.getType() == "aggressive monster") && character.getState() != Character.CharacterState.die)
                   monsters.add((Monster)character);
           }
        }
        return monsters;
    }
    
    public ArrayList<Cell> getLineOfCells(Cell currCell, Direction dir, int lengthOfLine){
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Cell cell = currCell; 
        for(int i = 0; i < lengthOfLine; i++){
            cell = getNextCell(cell, dir);
            if(cell == null)
                break;
            cells.add(cell);
        }
        return cells;
    }
    
    public ArrayList<Cell> getAreaAroundTheCell(Cell currCell, int areaRadius){
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Point pos = currCell.getPosition();
        int x = pos.x;
        int y = pos.y;  
        Point leftUp = new Point(), rightUp = new Point(), leftDown = new Point(), rightDown = new Point(); // Границы области
        // Определяем границы области
        leftUp.x = x - areaRadius < 0 ? 0 : x - areaRadius;
        leftUp.y = y + areaRadius > height-1 ? height-1 : y + areaRadius;
        rightUp.x = x + areaRadius > width-1 ? width-1 : x + areaRadius;
        rightUp.y = leftUp.y;   
        rightDown.y = y - areaRadius < 0 ? 0 : y - areaRadius;
        rightDown.x = rightUp.x;
        leftDown.x = leftUp.x;
        leftDown.y = rightDown.y;
        
        for(int i = leftDown.y; i <= leftUp.y; i++){
            for(int j = leftDown.x ; j <= rightDown.x; j++){
                cells.add(arrayOfCell[i][j]);
            }
        }
         
        return cells;
    }
}
