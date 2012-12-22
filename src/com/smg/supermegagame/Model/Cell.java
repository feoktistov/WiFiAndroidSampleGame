package com.smg.supermegagame.Model;


import com.smg.supermegagame.R;

public class Cell {
	
	private Integer name;
	private Game.CellState state;

    public Game.CellState getState() 
    {
        return state;
    }

    public void setState(Game.CellState state) 
    {
        this.state = state;
    }
    
    public Integer getName()
    {
    	if (state.equals(Game.CellState.CLOSED))
    		return R.drawable.card;
    	return name;
    }
    
    public void setName(Integer str)
    {
    	name = str;
    }

    Cell() 
    {
    	state = Game.CellState.CLOSED;
    	name = 0;
    }
    
    
}
