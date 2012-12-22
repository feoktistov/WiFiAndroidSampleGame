package com.example.supermegagame;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

	static final boolean IS_SERVER = true;
	
	private static String TAG = "GameActivity";
	
	private Button buttonStart;
	private Button buttonConnect;
	private Button buttonAppend;
	private EditText serverIpAdress;
	private TextView textViewMessage;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_game);
        
        textViewMessage = (TextView)findViewById(R.id.textViewMessage);
        
        buttonAppend = (Button) findViewById(R.id.ButtonAppend);
        buttonAppend.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                 connectToServer(); 
                 showConnectionView(false);
            }
        });
        
        serverIpAdress = (EditText)findViewById(R.id.editTextIp);
        
        buttonStart = (Button) findViewById(R.id.ButtonStartGame);
        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
            	serverLaunch();
            	showStartScreen(false);
          
            	textViewMessage.setText(getIpAddr());
            }
        });
        
 
        
        buttonConnect = (Button) findViewById(R.id.ButtonConnect);
        buttonConnect.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
            	showConnectionView(true);
            	showStartScreen(false);
            }
        });
        
        showConnectionView(false);
    }
    
    private void showConnectionView(boolean visible) {
    	buttonAppend.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    	serverIpAdress.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
    
    private void showStartScreen(boolean visible) {
    	buttonConnect.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    	buttonStart.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
    
    public String getIpAddr() {
    	   WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
    	   WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    	   int ip = wifiInfo.getIpAddress();

    	   String ipString = String.format(
    	   "%d.%d.%d.%d",
    	   (ip & 0xff),
    	   (ip >> 8 & 0xff),
    	   (ip >> 16 & 0xff),
    	   (ip >> 24 & 0xff));

    	   return ipString;
    	}
    
    
    
    private void serverLaunch() {
    	Thread thread = new Thread()
    	{
    	      @Override
    	      public void run() {
    	    	  try {
    	    		  Log.d(TAG, "serverLaunch");
    	              Boolean end = false;
    	              ServerSocket ss = new ServerSocket(12345);
    	              while(!end){
    	                      //Server is waiting for client here, if needed
    	                      Socket s = ss.accept();
    	                      BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
    	                      PrintWriter output = new PrintWriter(s.getOutputStream(),true); //Autoflush
    	                      final String st = input.readLine();
    	                      
    	                      textViewMessage.post(new Runnable() {
    	                    	  public void run() {
    	                    		  textViewMessage.setText("From client:" + st);
    	                    	  }
    	                      });
    	                      
    	                      Log.d("Tcp Example", "From client: "+st);
    	                      output.println("Good bye and thanks for all the fish :)");
    	                      s.close();
    	                     // if ( STOPPING conditions){ end = true; }
    	              }
    	              ss.close();
    	  	    } catch (UnknownHostException e) {
    	  	            // TODO Auto-generated catch block
    	  	            e.printStackTrace();
    	  	    } catch (IOException e) {
    	  	            // TODO Auto-generated catch block
    	  	            e.printStackTrace();
    	  	    }
    	      }
    	  };

    	thread.start();
    	
    	
    	
    }
    
    private void connectToServer() {
    	Thread thread = new Thread()
    	{
    	      @Override
    	      public void run() {
    	    	  
			    	try {
			    		Log.d(TAG, "connectToServer");
			    		String ip = serverIpAdress.getText().toString();
			            Socket s = new Socket(ip,12345);
			
			            //outgoing stream redirect to socket
			            OutputStream out = s.getOutputStream();
			
			            PrintWriter output = new PrintWriter(out);
			            output.println("Hello Android!");
			            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			            //read line(s)
			            String st = input.readLine();
			            //. . .
			            //Close connection
			            s.close();
			    	} catch (UnknownHostException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				    } catch (IOException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				    }
    	      }
    	 };
    	 thread.start();
    	  
    }
    
}
