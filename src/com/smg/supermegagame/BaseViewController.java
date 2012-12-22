package com.smg.supermegagame;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseViewController implements IViewController {
	
	private View view;
	protected Activity activity;
	
	public BaseViewController(Activity activity) {
		initWithActivity(activity);
	}
	
	public View getView() {
		return view;
	}
	
	protected View findViewById(int id) {
		LayoutInflater inflater = 
		        (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 return inflater.inflate(id, null);
	}
	
	protected void initWithActivity(Activity activity) {
		this.activity = activity;
		view = getViewFromSource();
	}
	
	protected abstract View getViewFromSource();
	
	private void setVisibilty(boolean visible) {
		view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
		view.setClickable(visible);
	}
	
	public void show() {
		this.setVisibilty(true);
	}
	
	public void hide() {
		this.setVisibilty(false);
	}
	
	public void addViewToSuperView() {
		activity.setContentView(view);
	}
}
