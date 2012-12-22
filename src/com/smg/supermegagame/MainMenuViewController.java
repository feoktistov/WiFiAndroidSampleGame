package com.smg.supermegagame;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainMenuViewController extends BaseViewController {

	private Button buttonPlay;
	
	public MainMenuViewController(Activity activity) {
		super(activity);
	}
	
	private void onPlay() {
		NavigationController.onPlay();
	}

	@Override
	protected View getViewFromSource() {
		View view = findViewById(R.layout.game_main_menu);
		
		buttonPlay = (Button) view.findViewById(R.id.buttonPlay);
		buttonPlay.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                 onPlay();
            }
        });
		
		return view;
	}
}
