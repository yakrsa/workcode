package com.tencent.tvassistant.activity;


import com.tencent.tvassistant.R;
import com.tencent.tvassistant.websvr.WebService;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;;
public class MainActivity extends Activity implements OnClickListener {
	public static final String TAG="tvAssistant";
	
	private Button bt_testqqlivehd,bt_installqqlivehd,bt_pulltest,bt_startservice,bt_stopservice;
	private TextView txt_boxinfo;
	private String ip;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        bt_testqqlivehd=(Button) findViewById(R.id.bt_testqqlivehd);
        bt_installqqlivehd=(Button) findViewById(R.id.bt_installqqlivehd);
        bt_pulltest=(Button) findViewById(R.id.bt_pulltest);
        bt_startservice=(Button) findViewById(R.id.start_service);
        bt_stopservice=(Button) findViewById(R.id.stop_service);
        txt_boxinfo= (TextView) findViewById(R.id.txt_boxinfo);
        
        bt_startservice.setOnClickListener(this);
        bt_stopservice.setOnClickListener(this);
        
        //测试腾讯视频
        bt_testqqlivehd.setOnClickListener(this);

        //安装测试包
        bt_installqqlivehd.setOnClickListener(this);			

        //外部拉起测试
        bt_pulltest.setOnClickListener(this);
        ip=getIp();
        txt_boxinfo.setText("device id:\t"+getDeviceId()
        		+"\nguid:\t"+getGUID(getApplicationContext())
        		+"\ndevice name:\t"+getDeviceName()
        		+"\nwidthPixels:\t"+this.getResources().getDisplayMetrics().widthPixels
        		+"\nheightPixels:\t"+this.getResources().getDisplayMetrics().heightPixels
        		+"\ndensity:\t"+this.getResources().getDisplayMetrics().density
        		+"\ndensityDpi:\t"+this.getResources().getDisplayMetrics().densityDpi
        		+"\nAndroid sdk version:\t"+android.os.Build.VERSION.SDK_INT );
        		
			
    }

	/**
	 * 获取设备唯一标识
	 * 
	 * @param context
	 * @return
	 */
	public static String getGUID(Context context)
	{
		String guidString = android.provider.Settings.System.getString(
				context.getContentResolver(), "android_id");
		return guidString;
	}

	public static String getDeviceName()
	{
		return urlToFileName(android.os.Build.MODEL);
	}

	/**
	 * 去掉文件名非法字符
	 * 
	 * @param fileName
	 * @return
	 */
	public static String urlToFileName(String fileName)
	{
		String str = fileName;
		str = str.replace("\\", "");
		str = str.replace("/", "");
		str = str.replace(":", "");
		str = str.replace("*", "");
		str = str.replace("?", "");
		str = str.replace("\"", "");
		str = str.replace("<", "");
		str = str.replace(">", "");
		str = str.replace("|", "");
		str = str.replace("&", "_");
		str = str.replace(" ", "_"); // 前面的替换会产生空格,最后将其一并替换掉
		return str;
	}
    
    public String  getDeviceId(){
    	TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
    	return tm.getDeviceId();
    }
    
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	switch (v.getId()){
    	case R.id.bt_testqqlivehd:
				// TODO Auto-generated method stub
				Bundle args=new Bundle();
				args.putString("e", "class com.tencent.qqlivetest.FirstTest");
				Toast.makeText(MainActivity.this.getBaseContext(), "start instrument", Toast.LENGTH_LONG).show();
				startInstrumentation(new ComponentName("com.tencent.qqlivetest","android.test.InstrumentationTestRunner"), null, args);
		case R.id.bt_installqqlivehd:
			Toast.makeText(MainActivity.this.getBaseContext(), "ip is:"+ip, Toast.LENGTH_LONG).show();
			break;
		case R.id.bt_pulltest:
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, PulltestActivity.class);
			startActivity(intent);
			break;
		case R.id.start_service:
			Log.i(TAG,"start service click");
			if (ip == null) {
				Toast.makeText(this, "ip is null,can't start service", Toast.LENGTH_SHORT)
						.show();
			} else {
				Intent startIntent=new Intent(this,WebService.class);
				stopService(startIntent);
				startService(startIntent);
				txt_boxinfo.append("\nhttp://" + ip + ":" + WebService.PORT + "/");
			}
			break;
		case R.id.stop_service:
			Log.i(TAG,"stop service click");
			Intent stopIntent=new Intent(this,WebService.class);
			stopService(stopIntent);
			break;
		default:
			break;
		}
    	
    }

    
	public String getIp() {
		// TODO Auto-generated method stub
		
		//获取wifi服务  
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);  
        //判断wifi是否开启  
        if (!wifiManager.isWifiEnabled()) {  
        wifiManager.setWifiEnabled(true);    
        }  
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();       
        int ipAddress = wifiInfo.getIpAddress();   
        String ip = intToIp(ipAddress);   
		return ip;
	}
    private String intToIp(int i) {       
        
        return (i & 0xFF ) + "." +       
      ((i >> 8 ) & 0xFF) + "." +       
      ((i >> 16 ) & 0xFF) + "." +       
      ( i >> 24 & 0xFF) ;  
   }  
    
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	Intent stopIntent=new Intent(this,WebService.class);
    	super.onDestroy();
    }

}
