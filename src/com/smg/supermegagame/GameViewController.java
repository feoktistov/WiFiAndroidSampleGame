package com.smg.supermegagame;

import com.smg.supermegagame.Model.Game;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GameViewController extends BaseViewController {
	Game game;
	ImAdapter Ad;
	
	public GameViewController(Activity activity) {
		super(activity);
	}

	@Override
	protected View getViewFromSource() {
		View view = findViewById(R.layout.field);
		
		game = new Game (3, 5);
		
		GridView gridview = (GridView) findViewById(R.id.gridView1);
		
		Ad = new ImAdapter(activity);
		Ad.setGame(game);
		gridview.setAdapter(Ad);

		gridview.setOnItemClickListener(gridviewOnItemClickListener);
	    
		
		return view;
	}
	
	private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			game.OpenCell(position);
			Ad.notifyDataSetChanged();
		}
		
	};
}
