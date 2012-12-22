package com.smg.supermegagame.Model;
import com.smg.supermegagame.Controller.*;


public class Game {

    public enum GameState {WON, DEFEATED, INPROCESS, NOTSTARTED};
    public enum CellState {OPENED, CLOSED, RESOLVED};

    private GameState status;

    Field field;

    Controller controller;

    int time;
    
    public GameState GetStatus ()
    {
    	return status;
    }
    
    public void SetStatus (GameState newStatus)
    {
    	status = newStatus;
    }

    public void OpenCell(int x, int y)
    {
    	int count = 0, x1 = 0, y1 = 0;
    	for (int i = 0; i < field.getSize(); i++)
    		for (int j = 0; j < field.getSize(); j++)
    			if (field.get(i, j).equals(CellState.OPENED))
    			{
    				x1 = i;
    				y1 = j;
    				count++;
    			}
    	if (0 == count)
    	{
    		field.set(x, y, CellState.OPENED);
    	}
    	if (1 == count)
    	{
    		CompareCells(x, y, x1, y1);
    	}
    	else return;
    }

    public void CloseCell(int x, int y)
    {
    	field.set(x, y, CellState.CLOSED);
    }

    public void RemoveCell(int x, int y)
    {
    	field.set(x, y, CellState.RESOLVED);
    }

    public void CompareCells(int x1, int y1, int x2, int y2)
    { 
    	if (field.getName(x1, y1).equals(field.getName(x2, y2)))
    	{
    		RemoveCell(x1, y1);
    		RemoveCell(x2, y2);
    	}
    	else 
    	{
    		CloseCell(x1, y1);
    		CloseCell(x2, y2);
    	}
    }

    public void UseHint(int t){}

    public void AddRecord (int time){}

    public Game(int n)
    {
    	field = new Field(n);
    	status = GameState.INPROCESS;
    }
}
