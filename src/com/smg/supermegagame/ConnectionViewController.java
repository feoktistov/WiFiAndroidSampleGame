package com.smg.supermegagame;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	private Button buttonSend;
	
	private EditText serverIpAdress;
	private TextView textViewMessage;
	
	private EditText textOut;
	private TextView textIn;
	
	private boolean isServer = false;
	
	public static final String TAG = "ConnectionViewController";
	
	public ConnectionViewController(Activity activity) {
		super(activity);
	}
	
	@Override
	public void show() {
		super.show();
		showConnectionView(true);
    	showStartScreen(true);
	}
	
	@Override
	protected View getViewFromSource() {
		 View view = findViewById(R.layout.connecton_menu);
	
		 textViewMessage = (TextView)view.findViewById(R.id.textViewMessage);
		 textViewMessage.setText("");
		 
		 textIn = (TextView)view.findViewById(R.id.TextViewIn);
		 textOut = (EditText)view.findViewById(R.id.EditTextSend);
	        
	        serverIpAdress = (EditText)view.findViewById(R.id.editTextIp);
	        
	        buttonStart = (Button) view.findViewById(R.id.ButtonStartGame);
	        buttonStart.setOnClickListener(new Button.OnClickListener() {
	            public void onClick(View v) {
	            	serverLaunch();
	            	showStartScreen(false);
	          
	            	textViewMessage.setText(getIpAddr());
	            }
	        });
	        
	        buttonSend = (Button) view.findViewById(R.id.ButtonSend);
	        buttonSend.setOnClickListener(new Button.OnClickListener() {
	            public void onClick(View v) {
	            	sendToServer(textOut.getText().toString());
	            }
	        });
	      
		return view;
	}
	
    private void showConnectionView(boolean visible) {
    	serverIpAdress.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
    
    private void showStartScreen(boolean visible) {
    	buttonStart.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    	buttonSend.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
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
	 isServer = true;
 	Thread thread = new Thread()
 	{
 	      @Override
 	      public void run() {
 	    	  try {
 	    		  Log.d(TAG, "serverLaunch");
 	              Boolean end = false;
 	              ServerSocket ss = new ServerSocket(8888);
 	              while(!end){
 	                      //Server is waiting for client here, if needed
 	                    Socket socket = ss.accept();
 	                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
 	                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
 	                      
 	                    prepareMessageForClient( textOut.getText().toString() );
 	                    dataOutputStream.writeUTF(messageForClient);
 	                    getText( dataInputStream.readUTF() );
 	                     

 	                     socket.close();
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
 
 private void getText(final String message) {
	 textIn.post( new Runnable() {
     	 public void run() {
     		textIn.setText(message);
     	 }
      });
 }
 
 private String messageForClient = "";
 private void prepareMessageForClient(String message) {
	 messageForClient = message;
 }
 
 
 private void sendToServer(final String message) {
 	Thread thread = new Thread()
 	{
 	      @Override
 	      public void run() {
 	    	  
			    	try {
			    		Log.d(TAG, "connectToServer");
			    		String ip = serverIpAdress.getText().toString();
			            Socket socket = new Socket(ip,8888);
			
			            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
	                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
	                      
	                    dataOutputStream.writeUTF(message);
	                    getText( dataInputStream.readUTF() );
			            
	                    socket.close();
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
