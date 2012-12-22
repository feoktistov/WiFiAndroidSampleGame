package com.smg.supermegagame;

import android.app.Activity;
import android.view.View;

public class MainMenuViewController extends BaseViewController {

	public MainMenuViewController(Activity activity) {
		super(activity);
	}

	@Override
	protected View getViewFromSource() {
		View view = findViewById(R.layout.game_main_menu);
		return view;
	}
}
