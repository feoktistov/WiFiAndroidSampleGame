package com.smg.supermegagame;

import com.smg.supermegagame.Model.Game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImAdapter extends BaseAdapter {
	private Context mContext;

	private Game game;
	public ImAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mThumbIds.length;
	}

	
	public void setGame (Game g)
	{
		game = g;
	}
	
	public Object getItem(int position) 
	{
		return game.field.getName(position);
	}

	public long getItemId(int position) 
	{
		return position;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}

	// references to our images
	public	Integer[] mThumbIds = { R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card, R.drawable.card };
}
