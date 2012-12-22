package com.smg.supermegagame;

import android.app.Activity;

import android.view.View;

public abstract class BaseViewController implements IViewController {
	
	private View view;
	protected Activity activity;
	
	protected void initWithActivity(Activity activity) {
		this.activity = activity;
		view = getViewFromSource();
	}
	
	protected abstract View getViewFromSource();
	
	private void setVisibilty(boolean visible) {
		view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
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
