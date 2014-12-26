package com.tencent.tvassistant.activity;

import java.util.List;

import com.tencent.tvassistant.model.*;
import com.tencent.tvassistant.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class PulltestActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pulltest);
	}

	/**
	 * 广播action方式拉起腾讯视频
	 * 
	 * @param v
	 */
	public void onBroadcastButtonClick(View v) {

		switch (v.getId()) {
		case R.id.button_broadcast_action_0:
			// 打开详情页
			openWithBroadcastAction(IntentD.Ext_VOD.OPEN_DETAIL,IntentD.Ext_VOD.KEY_COVER_ID,"86jvlgtu3ygueg2");
			break;
		case R.id.button_broadcast_action_1:
			// 打开频道页
			openWithBroadcastAction(IntentD.Ext_Channel.GOTO_CHANNEL,IntentD.Ext_Channel.KEY_CHANNEL_NAME,"tv");
			break;
		case R.id.button_broadcast_action_2:
			// 打开主页
			openWithBroadcastAction(IntentD.Ext_HOME.GOTO_HOME,"","");
			break;
		case R.id.button_broadcast_action_3:
			// 打开播放器
			openWithBroadcastAction(IntentD.Ext_VOD.OPEN_VIDEO,IntentD.Ext_VOD.KEY_VIDEO_ID,"c0015a2c0pr");
			break;
		case R.id.button_broadcast_action_4:
			// 打开搜索页
			openWithBroadcastAction(IntentD.Ext_Search.SEARCH,IntentD.Ext_Search.KEY_NAME,"中国");
			break;
		}
	}
	public boolean isBroadcast;
	public void onImplicitStartButtonClick(View v) {
		isBroadcast=false;
		switch (v.getId()) {
		case R.id.button_implicit_intent_0:
			// 打开详情页面
			openWithUri(IntentD.Ext_type.OPEN_DETAIL ,"m2jw8ioluuwgpdt");
			
			break;
		case R.id.button_implicit_intent_1:
			// 打开频道页面
			openWithUri(IntentD.Ext_type.OPEN_CHANNEL,"tv");
			break;
		case R.id.button_implicit_intent_2:
			// 打开主页面
			openWithUri(IntentD.Ext_type.OPEN_HOME,"");
			break;
		case R.id.button_implicit_intent_3:
			// 打开播放器
			openWithUri(IntentD.Ext_type.OPEN_PLAYER,"c0015a2c0pr");
			break;
		case R.id.button_implicit_intent_4:
			// 打开搜索页面
			openWithUri(IntentD.Ext_type.OPEN_SEARCH,"ZG");
			break;
		case R.id.button_implicit_intent_5:
			// 打开专题页面
			openWithUri(IntentD.Ext_type.OPEN_TOPIC,"1149");
			break;
		}
	}

	
	public void onBroadcastSchemeButtonClick(View v) {
		isBroadcast=true;
		switch (v.getId()) {
		case R.id.button_broadcast_implicit_intent_0:
			// 打开综艺详情页面
			openWithUri(IntentD.Ext_type.OPEN_DETAIL ,"m2jw8ioluuwgpdt");
			break;
		case R.id.button_broadcast_implicit_intent_1:
			// 打开频道页面
			openWithUri(IntentD.Ext_type.OPEN_CHANNEL,"tv");
			break;
		case R.id.button_broadcast_implicit_intent_2:
			// 打开主页面
			openWithUri(IntentD.Ext_type.OPEN_HOME,"");
			break;
		case R.id.button_broadcast_implicit_intent_3:
			// 打开播放器
			openWithUri(IntentD.Ext_type.OPEN_PLAYER,"c0015a2c0pr");
			break;
		case R.id.button_broadcast_implicit_intent_4:
			// 打开搜索页面
			openWithUri(IntentD.Ext_type.OPEN_SEARCH,"ZG");
			break;
		case R.id.button_broadcast_implicit_intent_5:
			// 打开专题页面
			openWithUri(IntentD.Ext_type.OPEN_TOPIC,"1149");
			break;
		case R.id.button_broadcast_implicit_intent_6:
			//打开电影详情页
			openWithUri(IntentD.Ext_type.OPEN_DETAIL ,"ujnamwpqg1xg8qm");
			break;
		case R.id.button_broadcast_implicit_intent_7:
			//打开电视详情页
			openWithUri(IntentD.Ext_type.OPEN_DETAIL ,"zrxyhghf3n8xhxl&episode_idx=3");
			break;
		case R.id.button_broadcast_implicit_intent_8:
			//打开纪录片详情页
			openWithUri(IntentD.Ext_type.OPEN_DETAIL ,"2rxhob03hrjg6k8");
			break;
		case R.id.button_broadcast_implicit_intent_9:
			//打开好莱坞付费视频详情页
			openWithUri(IntentD.Ext_type.OPEN_DETAIL ,"ht4z382bhs7h94u");
			break;
		default:
			break;
		}

	}

	private void openWithUri(int openType,String values) {
		String scheme="";
		
		switch (openType) {
		case IntentD.Ext_type.OPEN_DETAIL:
			// 打开详情页面
			scheme="action=1&cover_id="+values;
			break;
		case IntentD.Ext_type.OPEN_CHANNEL:
			// 打开频道页面
			scheme="action=3&channel_code="+values;
			break;
		case IntentD.Ext_type.OPEN_HOME:
			// 打开主页面
			scheme="action=4";
			break;
		case IntentD.Ext_type.OPEN_PLAYER:
			// 打开播放器
			scheme="action=7&video_id="+values;
			break;
		case IntentD.Ext_type.OPEN_SEARCH:
			// 打开搜索页面
			scheme="action=9&search_key="+values;
			break;
		case IntentD.Ext_type.OPEN_TOPIC:
			// 打开专题页面
			scheme="action=6&topic_id="+values;
			break;
		default:
			break;
		}
		
		Intent intent = new Intent();
		StringBuilder uriString = new StringBuilder("tenvideo2://?").append(scheme);
		intent.setData(Uri.parse(uriString.toString()));
		Log.i("apptest", uriString.toString());
		handleIntent(isBroadcast, intent);
	}

	/**
	 * 提倡使用这两种做法来完成跳转
	 * 
	 * @param isBroadcast
	 * @param intent
	 */
	private void handleIntent(boolean isBroadcast, Intent intent) {
		if (isBroadcast) {
			// 广播scheme模式拉起应用
			sendBroadcast(intent);
		} else {
			// 隐式调用的方式startActivity
			intent.setAction("com.tencent.qqlivetv.open");
			PackageManager packageManager = getPackageManager();
			List<ResolveInfo> activities = packageManager
					.queryIntentActivities(intent, 0);
			boolean isIntentSafe = activities.size() > 0;

			if (isIntentSafe) {
				startActivity(intent);
			} else {
				Toast.makeText(this, "未安装腾讯视频 ， 无法跳转", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * 这种方式不提倡使用 之后也不会继续支持下去
	 * 
	 * @param action
	 */
	@Deprecated
	private void openWithBroadcastAction(String action,String key ,String value) {

		if (TextUtils.isEmpty(action)) {
			return;
		}
		Intent intent = new Intent(action);
		putIntentExtra(key, value,	intent);
		sendBroadcast(intent);
	}

	private void putIntentExtra(String key, String value, final Intent intent) {
		if (intent == null || TextUtils.isEmpty(value)
				|| TextUtils.isEmpty(key)) {
			return;
		}
		intent.putExtra(key, value);
	}
}
