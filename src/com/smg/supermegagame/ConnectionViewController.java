package com.smg.supermegagame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class ConnectionViewController extends BaseViewController  {
	private Button buttonStart;
	private Button buttonConnect;
	private Button buttonAppend;
	private EditText serverIpAdress;
	private TextView textViewMessage;
	
	public static final String TAG = "ConnectionViewController";
	
	public ConnectionViewController(Activity activity) {
		super(activity);
	}
	
	@Override
	public void show() {
		super.show();
		showConnectionView(false);
    	showStartScreen(true);
	}
	
	@Override
	protected View getViewFromSource() {
		 View view = findViewById(R.layout.connecton_menu);
	
		 textViewMessage = (TextView)view.findViewById(R.id.textViewMessage);
	        
	        buttonAppend = (Button) view.findViewById(R.id.ButtonAppend);
	        buttonAppend.setOnClickListener(new Button.OnClickListener() {
	            public void onClick(View v) {
	                 connectToServer(); 
	                 showConnectionView(false);
	            }
	        });
	        
	        serverIpAdress = (EditText)view.findViewById(R.id.editTextIp);
	        
	        buttonStart = (Button) view.findViewById(R.id.ButtonStartGame);
	        buttonStart.setOnClickListener(new Button.OnClickListener() {
	            public void onClick(View v) {
	            	serverLaunch();
	            	showStartScreen(false);
	          
	            	textViewMessage.setText(getIpAddr());
	            }
	        });
	        
	 
	        
	        buttonConnect = (Button) view.findViewById(R.id.ButtonConnect);
	        buttonConnect.setOnClickListener(new Button.OnClickListener() {
	            public void onClick(View v) {
	            	showConnectionView(true);
	            	showStartScreen(false);
	            }
	        });
			
		
		return view;
	}
	
    private void showConnectionView(boolean visible) {
    	buttonAppend.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    	serverIpAdress.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
    
    private void showStartScreen(boolean visible) {
    	buttonConnect.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    	buttonStart.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
    
    public String getIpAddr() {
 	   WifiManager wifiManager = (WifiManager) activity.getSystemService(activity.WIFI_SERVICE);
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
