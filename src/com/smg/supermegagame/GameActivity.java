package com.smg.supermegagame;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.smg.supermegagame.Model.*;
public class GameActivity extends Activity {

	static final boolean IS_SERVER = true;
	Game game;
	private static String TAG = "GameActivity";
	ImAdapter Ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.field);

	//	mSelectText = (TextView) findViewById(R.id.info);
		GridView gridview = (GridView) findViewById(R.id.gridView1);
		Ad = new ImAdapter(this);
	    game = new Game (3, 5);
	    Ad.setGame(game);
		gridview.setAdapter(Ad);

		gridview.setOnItemClickListener(gridviewOnItemClickListener);
	 
    }
    
    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			game.OpenCell(position);
			Ad.notifyDataSetChanged();
			// TODO Auto-generated method stub

		//	mSelectText.setText(String.valueOf(position));
		}
		
	};


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
}
