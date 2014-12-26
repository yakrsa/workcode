package com.tencent.tvassistant.websvr;

import com.tencent.tvassistant.model.CaseData;

import android.R.bool;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WebService extends Service {

	public static  int PORT = 9898;
	public static final String WEBROOT = "/";
	public boolean isStop=false;
	private WebServer webServer;
	public static boolean isTODO=false;
	public static String ACTION="nothing";
	public static String EXTRA="";
	private int loop=0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		webServer = new WebServer(PORT, WEBROOT);
		
		//新建县城，每隔1s检查一次 是否需要做什么
		new Thread(){
			
			public void run(){
				while(!isStop){
					Log.i("httpservice", "ACTION:"+ACTION+ "\t EXTRA: "+EXTRA+" isTODO:"+isTODO);
					if(isTODO && ACTION.equals("push")){
						loop++;
						Log.i("httpservice", "loop:"+loop);
						Intent intent = new Intent();
						intent.setAction("com.tencent.wseals.ACTION_PUSH_MSG_RECEIVE");
						String data="";
						if(EXTRA.equals("video"))
							data=CaseData.videomsg[loop % 3];
						else if(EXTRA.equals("photo"))
							data=CaseData.albummsg[loop % 3];
						Log.i("httpservice", "data:"+data);
						intent.putExtra("push_data",data );
						
						sendBroadcast(intent);
						isTODO=false;
					}
					else{
					//do nothing	
						Log.i("httpservice", "do nothing");
					}					
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Log.i("httpservice", "thread stop  isStop is true");
			}
			
		}.start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		webServer.setDaemon(true);
		webServer.start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		isStop=true;//即使service销毁县城也不会停止，所以需要设置isStop来停止线程
		webServer.close();
		Log.i("httpservice", "webservier destroy ");
		super.onDestroy();
	}

}
