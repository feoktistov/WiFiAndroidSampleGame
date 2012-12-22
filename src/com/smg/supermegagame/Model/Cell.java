package com.smg.supermegagame.Model;


import android.util.Log;

import com.smg.supermegagame.R;

public class Cell {
	private static Integer closeName = R.drawable.card;
	private int name;
	private Game.CellState state;

    public Game.CellState getState() 
    {
        return state;
    }

    public void setState(Game.CellState state) 
    {
        this.state = state;
    }
    
    public int getName()
    {
    	if (state == Game.CellState.CLOSED) {
    		return closeName;
    	}
    	Log.d("Cell", "Get name " + name);
    	if(name == -1) {
    		return closeName;
    	}
    	return name;
    }
    
    public void setName(Integer str)
    {
    	Log.d("Cell", "Set name " + str);
    	if(name == -1) {
    		name = str;
    	}
    }

    Cell() 
    {
    	state = Game.CellState.CLOSED;
    	name = -1;
    }
    
    
}
