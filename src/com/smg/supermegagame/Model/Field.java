package com.smg.supermegagame.Model;

import java.util.ArrayList;
import java.util.Random;




public class Field {
	
	
	String[] CellNames = new String[] {"1", "2", "3", "4", "5", "6", "7", "8"};
	
	 private Cell cells[][];
	 private int size;

	 Field(int n)
	 {
		    size = n;
	        cells = new Cell[n][n];

	        for(int i = 0; i < n; i++)
	        {
	            for(int j = 0; j < n; j++)
	            {
	                cells[i][j] = new Cell();
	            }
	        }
	        
	        ArrayList<String> stringList = new ArrayList<String>();
	        for (String i:CellNames)
	        {
	           stringList.add(i);
	           stringList.add(i);
	        }
	        
	        for(int i = 0; i < n; i++)
	 	    {
	 	       for(int j = 0; j < n; j++)
	 	       {
	 	            if (cells[i][j].getName().equals(null))
	 	            {
 	            		Random rand = new Random();
 	            		int rndInt = rand.nextInt(stringList.size());
 	            		setName(i, j, stringList.get(rndInt));
 	            		stringList.remove(rndInt);
	 	            }
	 	        }
	 	            		
	 	    }    	  
	 }
	 
	 public Game.CellState get(int x, int y)
	 {
		 return cells[x][y].getState();
	 }

     public void set(int x, int y, Game.CellState newValue)
     {
         cells[x][y].setState(newValue);
	 }
     
     public String getName (int x, int y)
     {
    	 return cells[x][y].getName();
     }
     
     public void setName (int x, int y, String str)
     {
    	 cells[x][y].setName(str);
     }
     
     public int getSize ()
     {
    	 return size;
     }
}
