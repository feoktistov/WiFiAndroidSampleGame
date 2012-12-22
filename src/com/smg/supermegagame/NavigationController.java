package com.smg.supermegagame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.widget.FrameLayout;

public class NavigationController {
	private  Activity activity;
	private FrameLayout mainView;
	private IViewController currentActiveView;
	private HashMap<String,IViewController> views;
	
	public static final String MAIN_VIEW = "main_view";
	public static final String GAME_VIEW = "game_view";
	public static final String CONECTION_VIEW = "conection_view";
	
	private static NavigationController instance = null;
	
	public NavigationController(Activity activity, FrameLayout mainView ) {
		instance = this;
		
		this.activity = activity;
		this.mainView = mainView;
		views = new HashMap<String,IViewController>();
		
		views.put(MAIN_VIEW, new MainMenuViewController(activity));
		views.put(GAME_VIEW, new ConnectionViewController(activity));
		views.put(CONECTION_VIEW, new ConnectionViewController(activity));
		
		for(IViewController ctrl : views.values()) {
			mainView.addView(ctrl.getView());
			ctrl.hide();
		}
		
		showViewByName(MAIN_VIEW);
	}
	
	public void showViewByName(String name) {
		for(String caption : views.keySet()) {
			if(caption.equals(name)) {
				if(currentActiveView != null) {
					currentActiveView.hide();
				}
				currentActiveView = views.get(name); 
				currentActiveView.show();
			}
		}
	}
	
	public static void onPlay() {
		instance.showViewByName(GAME_VIEW);
	}
}
