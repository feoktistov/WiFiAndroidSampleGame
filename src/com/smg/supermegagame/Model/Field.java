package com.smg.supermegagame.Model;

import java.util.ArrayList;
import java.util.Random;


import com.smg.supermegagame.R;


public class Field {
	
	
	public Integer[] CellNames = new Integer[]{R.drawable.ferm1, R.drawable.ferm2, R.drawable.ferm3, R.drawable.ferm4, R.drawable.ferm5, R.drawable.ferm6, R.drawable.ferm7, R.drawable.ferm8};
	
	 private Cell cells[];
	 private int width;
	 private int height;

	 Field(int w, int h)
	 {
		    width = w;
		    height = h;
	        cells = new Cell[w*h];
	        

	        for(int i = 0; i < cells.length; i++)
	        {
	            cells[i] = new Cell();
	        }
	        
	        ArrayList<Integer> intList = new ArrayList<Integer>();
	        for (Integer i:CellNames)
	        {
	           intList.add(i);
	           intList.add(i);
	        }
	        
	        for(int i = 0; i < cells.length; i++)
	 	    {
 	            	Random rand = new Random();
 	            	int rndInt = rand.nextInt(intList.size());
 	            	cells[i].setName(intList.get(rndInt).intValue());
 	            	intList.remove(rndInt);
	 	            
	 	            		
	 	    }    	  
	 }
	 
	 public Game.CellState get(int x)
	 {
		 return cells[x].getState();
	 }

     public void set(int x, Game.CellState newValue)
     {
         cells[x].setState(newValue);
	 }
     
     public int getName (int x)
     {
    	 return cells[x].getName();
     }
     
     public void setName (int x, int str)
     {
    	 cells[x].setName(str);
     }
     
     public int getLength ()
     {
    	 return width*height;
     }
}
     
   
