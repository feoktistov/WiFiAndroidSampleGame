package com.smg.supermegagame.Model;

public class Cell {
	
	private String name;
	private Game.CellState state;

    public Game.CellState getState() 
    {
        return state;
    }

    public void setState(Game.CellState state) 
    {
        this.state = state;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public void setName(String str)
    {
    	name = str;
    }

    Cell() 
    {
    	state = Game.CellState.CLOSED;
    }
    
    
}
