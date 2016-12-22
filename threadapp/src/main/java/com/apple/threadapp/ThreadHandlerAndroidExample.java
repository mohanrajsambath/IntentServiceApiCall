package com.apple.threadapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadHandlerAndroidExample extends AppCompatActivity {
	TextView tV_json_result_1,tV_json_result_2,tV_json_result_3;
	private String myHostUrl = "http://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_example_thread_handler);
		
		final Button GetServerData = (Button) findViewById(R.id.GetServerData);
		tV_json_result_1 = (TextView) findViewById(R.id.tV_json_result_1);
		tV_json_result_2 = (TextView) findViewById(R.id.tV_json_result_2);
		tV_json_result_3 = (TextView) findViewById(R.id.tV_json_result_3);

		bckMthd1();

		
		// On button click call this listener
		GetServerData.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pingWithExecutorService();
			}

		});
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		bckMthd2();
	}

	@Override
	protected void onResume() {
		super.onResume();
		bckMthd3();
	}

	private void bckMthd1(){
		//ALERT MESSAGE
		Toast.makeText(getBaseContext(),"Please wait, connecting to server. bckMthd1",Toast.LENGTH_SHORT).show();
		// Create Inner Thread Class
		Thread background = new Thread(new Runnable() {

			private final HttpClient Client = new DefaultHttpClient();
			//private String URL = "http://androidexample.com/media/webservice/getPage.php";
			private String URL = "http://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json";

			// After call for background.start this run method call
			public void run() {
				try {
					long startTime = System.currentTimeMillis();
					String SetServerString = "";
					HttpGet httpget = new HttpGet(URL);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					SetServerString = Client.execute(httpget, responseHandler);
					if(!SetServerString.equals("")) {
						long elapsedTime = System.currentTimeMillis() - startTime;
						System.out.println("bckMthd1 Total elapsed http request/response time in milliseconds: " + elapsedTime);
					}
					threadMsg(SetServerString);
				} catch (Throwable t) {
					// just end the background thread
					Log.i("Animation", "Thread  exception " + t);
				}
			}

			private void threadMsg(String msg) {
				if (!msg.equals(null) && !msg.equals("")) {
					Message msgObj = handler.obtainMessage();
					Bundle b = new Bundle();
					b.putString("message", msg);
					msgObj.setData(b);
					handler.sendMessage(msgObj);
				}
			}

			// Define the Handler that receives messages from the
			// thread and update the
			// progress
			private final Handler handler = new Handler() {
				public void handleMessage(Message msg) {

					String aResponse = msg.getData().getString("message");

					if ((null != aResponse)) {
						//ALERT MESSAGE
						Toast.makeText(getBaseContext(),"Server Response bckMthd1: "+aResponse,Toast.LENGTH_SHORT).show();
						tV_json_result_1.setText(aResponse);
						tV_json_result_1.setTextSize(10);

					}
					else
					{
						//ALERT MESSAGE
						Toast.makeText(getBaseContext(),"Not Got Response From Server.",Toast.LENGTH_SHORT).show();
					}

				}
			};

		});
		// Start Thread
		background.start();  //After call start method thread called run Method
	}
	private void bckMthd2(){
		//ALERT MESSAGE
		Toast.makeText(getBaseContext(),"Please wait, connecting to server bckMthd2.",Toast.LENGTH_SHORT).show();
		// Create Inner Thread Class
		Thread background = new Thread(new Runnable() {

			private final HttpClient Client = new DefaultHttpClient();
			//private String URL = "http://androidexample.com/media/webservice/getPage.php";
			private String URL = "http://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json";

			// After call for background.start this run method call
			public void run() {
				try {
					long startTime = System.currentTimeMillis();
					String SetServerString = "";
					HttpGet httpget = new HttpGet(URL);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					SetServerString = Client.execute(httpget, responseHandler);
					if(!SetServerString.equals("")) {
						long elapsedTime = System.currentTimeMillis() - startTime;
						System.out.println("bckMthd2 Total elapsed http request/response time in milliseconds: " + elapsedTime);
					}
					threadMsg(SetServerString);
				} catch (Throwable t) {
					// just end the background thread
					Log.i("Animation", "Thread  exception " + t);
				}
			}

			private void threadMsg(String msg) {
				if (!msg.equals(null) && !msg.equals("")) {
					Message msgObj = handler.obtainMessage();
					Bundle b = new Bundle();
					b.putString("message", msg);
					msgObj.setData(b);
					handler.sendMessage(msgObj);
				}
			}

			// Define the Handler that receives messages from the
			// thread and update the
			// progress
			private final Handler handler = new Handler() {
				public void handleMessage(Message msg) {

					String aResponse = msg.getData().getString("message");

					if ((null != aResponse)) {
						//ALERT MESSAGE
						Toast.makeText(getBaseContext(),"Server Response bckMthd2: "+aResponse,Toast.LENGTH_SHORT).show();
						tV_json_result_2.setText(aResponse);
						tV_json_result_2.setTextSize(10);

					}
					else
					{
						//ALERT MESSAGE
						Toast.makeText(getBaseContext(),"Not Got Response From Server.",Toast.LENGTH_SHORT).show();
					}

				}
			};

		});
		// Start Thread
		background.start();  //After call start method thread called run Method
	}
	private void bckMthd3(){
		//ALERT MESSAGE
		Toast.makeText(getBaseContext(),"Please wait, connecting to server bckMthd3.",Toast.LENGTH_SHORT).show();
		// Create Inner Thread Class
		Thread background = new Thread(new Runnable() {

			private final HttpClient Client = new DefaultHttpClient();
			//private String URL = "http://androidexample.com/media/webservice/getPage.php";
			private String URL = "http://soundcloud.com/oembed?url=http%3A//soundcloud.com/forss/flickermood&format=json";

			// After call for background.start this run method call
			public void run() {
				try {
					long startTime = System.currentTimeMillis();
					String SetServerString = "";
					HttpGet httpget = new HttpGet(URL);
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
					SetServerString = Client.execute(httpget, responseHandler);
					if(!SetServerString.equals("")) {
						long elapsedTime = System.currentTimeMillis() - startTime;
						System.out.println("bckMthd3 Total elapsed http request/response time in milliseconds: " + elapsedTime);
					}
					threadMsg(SetServerString);
				} catch (Throwable t) {
					// just end the background thread
					Log.i("Animation", "Thread  exception " + t);
				}
			}

			private void threadMsg(String msg) {
				if (!msg.equals(null) && !msg.equals("")) {
					Message msgObj = handler.obtainMessage();
					Bundle b = new Bundle();
					b.putString("message", msg);
					msgObj.setData(b);
					handler.sendMessage(msgObj);
				}
			}

			// Define the Handler that receives messages from the
			// thread and update the
			// progress
			private final Handler handler = new Handler() {
				public void handleMessage(Message msg) {

					String aResponse = msg.getData().getString("message");

					if ((null != aResponse)) {
						//ALERT MESSAGE
						Toast.makeText(getBaseContext(),"Server Response bckMthd3: "+aResponse,Toast.LENGTH_SHORT).show();
						tV_json_result_3.setText(aResponse);
						tV_json_result_3.setTextSize(10);

					}
					else
					{
						//ALERT MESSAGE
						Toast.makeText(getBaseContext(),"Not Got Response From Server.",Toast.LENGTH_SHORT).show();
					}

				}
			};

		});
		// Start Thread
		background.start();  //After call start method thread called run Method
	}

	private void pingWithExecutorService(){
		final int MYTHREADS = 30;
		ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);
		String[] hostList = {"http://crunchify.com", "http://yahoo.com",
				"http://www.ebay.com", "http://google.com",
				"http://www.example.co", "https://paypal.com",
				"http://bing.com/", "http://techcrunch.com/",
				"http://mashable.com/", "http://thenextweb.com/",
				"http://wordpress.com/", "http://wordpress.org/",
				"http://example.com/", "http://sjsu.edu/",
				"http://ebay.co.uk/", "http://google.co.uk/",
				"http://www.wikipedia.org/",
				"http://en.wikipedia.org/wiki/Main_Page"};
		//String myHostList= {};
		//for (int i = 0; i < hostList.length; i++) {
		for (int i = 0; i < 30; i++) {

			//String url = hostList[i];
			String url = myHostUrl;
			//Runnable worker = new MyRunnable(url);
			Runnable worker = new ExecutorRunnable(url);
			executor.execute(worker);
		}
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
			//System.out.println("\nExecutor is Still Working");
		}
		System.out.println("\nFinished all threads");

	}

	public static class MyRunnable implements Runnable {
		private final String url;

		MyRunnable(String url) {
			this.url = url;
		}

		@Override
		public void run() {

			String result = "";
			int code = 200;
			try {
				URL siteURL = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();

				code = connection.getResponseCode();
				if (code == 200) {
					result = "Green\t";
				}
			} catch (Exception e) {
				result = "->Red<-\t";
			}
			System.out.println(url + "\t\tStatus:" + result);
		}
	}


	public  class ExecutorRunnable implements Runnable{
		private final String url;

		private final HttpClient Client = new DefaultHttpClient();

		ExecutorRunnable(String url) {
			this.url = url;
		}

		@Override
		public void run() {

			try {
				long startTime = System.currentTimeMillis();
				String SetServerString = "";
				HttpGet httpget = new HttpGet(url);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				SetServerString = Client.execute(httpget, responseHandler);
				if(!SetServerString.equals("")) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					System.out.println("bckMthd3 Total elapsed http request/response time in milliseconds: " + elapsedTime);
				}
				threadMsg(SetServerString);
			} catch (Throwable t) {
				// just end the background thread
				Log.i("Animation", "Thread  exception " + t);
			}

		}

		private void threadMsg(String msg) {
			if (!msg.equals(null) && !msg.equals("")) {
				Message msgObj = handler.obtainMessage();
				Bundle b = new Bundle();
				b.putString("message", msg);
				msgObj.setData(b);
				handler.sendMessage(msgObj);
			}
		}

		// Define the Handler that receives messages from the
		// thread and update the
		// progress
		private final Handler handler = new Handler() {
			public void handleMessage(Message msg) {

				String aResponse = msg.getData().getString("message");

				if ((null != aResponse)) {
					//ALERT MESSAGE
					/*Toast.makeText(getBaseContext(),"Server Response bckMthd3: "+aResponse,Toast.LENGTH_SHORT).show();
					tV_json_result_3.setText(aResponse);
					tV_json_result_3.setTextSize(10);*/
					System.out.println(url + "\t\tStatus:" + aResponse);

				}
				else
				{
					//ALERT MESSAGE
					Toast.makeText(getBaseContext(),"Not Got Response From Server.",Toast.LENGTH_SHORT).show();
				}

			}
		};


	}



}




	


