package com.smg.supermegagame.Model;
import com.smg.supermegagame.Controller.*;


public class Game {

    public enum GameState {WON, DEFEATED, INPROCESS, NOTSTARTED};
    public enum CellState {OPENED, CLOSED, RESOLVED};

    private GameState status;

    public Field field;

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

    public void OpenCell(int x)
    {
    	int count = 0, y = 0;
    	for (int i = 0; i < field.getLength(); i++)
    			if (field.get(i).equals(CellState.OPENED))
    			{
    				y = i;
    				count++;
    			}
    	if (0 == count)
    	{
    		field.set(x, CellState.OPENED);
    	}
    	if (1 == count)
    	{
    		CompareCells(x, y);
    	}
    	else return;
    }

    public void CloseCell(int x)
    {
    	field.set(x, CellState.CLOSED);
    }

    public void RemoveCell(int x)
    {
    	field.set(x, CellState.RESOLVED);
    }

    public void CompareCells(int x1, int x2)
    { 
    	if (field.getName(x1) == field.getName(x2))
    	{
    		RemoveCell(x1);
    		RemoveCell(x2);
    	}
    	else 
    	{
    		CloseCell(x1);
    		CloseCell(x2);
    	}
    }

    public void UseHint(int t){}

    public void AddRecord (int time){}

    public Game(int w, int h)
    {
    	field = new Field(w, h);
    	status = GameState.INPROCESS;
    }
}
