package com.tencent.tvassistant.model;

/**
 * 对外开放接口定义
 * 
 * 不提倡使用这种方式
 * 
 * @author shadywang
 */

public class IntentD {

	/**
	 * 第三方使用：供Home使用的Intent字段，在AndroidManifest.xml注册
	 */
	final static public class Ext_HOME {
		/**
		 * 主页的Intent action
		 */
		public final static String GOTO_HOME = "QQLIVETV.OPEN.INTENT.CHANNEL.GOTO_HOME";
	}

	/**
	 * 第三方使用：供Channel使用的Intent字段，在AndroidManifest.xml注册
	 */
	final static public class Ext_Channel {
		/** 
		 * 频道页的Intent
		 */
		public final static String GOTO_CHANNEL = "QQLIVETV.OPEN.INTENT.CHANNEL.GOTO_CHANNEL";
		/** 
		 * 名字字段
		 */
		public final static String KEY_CHANNEL_NAME = "QQLIVETV.OPEN.INTENT.CHANNEL.KEY_CHANNEL_NAME";
	}

	/**
	 * 第三方使用：供Search使用的Intent字段，在AndroidManifest.xml注册
	 */
	final static public class Ext_Search {
		/** 
		 * 搜索的Intent
		 */
		public final static String SEARCH = "QQLIVETV.OPEN.INTENT.SEARCH.SEARCH";
		/** 
		 * 名字字段
		 */
		public final static String KEY_NAME = "QQLIVETV.OPEN.INTENT.SEARCH.KEY_NAME";
	}

	/**
	 * 第三方使用：由外部播放指定视频，在AndroidManifest.xml注册
	 */
	final static public class Ext_VOD {
		/** 
		 * 请求播放记录
		 */
		public final static String OPEN_VIDEO = "QQLIVETV.OPEN.INTENT.VOD.OPEN_VIDEO";
		/** 
		 * 请求打开详情页
		 */
		public final static String OPEN_DETAIL = "QQLIVETV.OPEN.INTENT.VOD.OPEN_DETAIL";
		/** 
		 * 指定历史记录的字段
		 */
		public final static String KEY_HISTORY_ITEM = "QQLIVETV.OPEN.INTENT.VOD.KEY_HISTORY_ITEM";
		/** 
		 * 指定Cover ID的字段
		 */
		public final static String KEY_COVER_ID = "QQLIVETV.OPEN.INTENT.VOD.KEY_COVER_ID";
		/** 
		 * 指定剧集序号的字段,-1表示从历史记录中寻找续播
		 */
		public final static String KEY_COVER_INDEX = "QQLIVETV.OPEN.INTENT.VOD.KEY_COVER_INDEX";
		/** 
		 * 指定Video ID的字段
		 */
		public final static String KEY_VIDEO_ID = "QQLIVETV.OPEN.INTENT.VOD.KEY_VIDEO_ID";
	}
	
	final static public class Ext_type{
		public final static int OPEN_DETAIL=1;
		public final static int OPEN_CHANNEL=3;
		public final static int OPEN_HOME=4;
		public final static int OPEN_TOPIC=6;
		public final static int OPEN_PLAYER=7;
		public final static int OPEN_SEARCH=9;
	}
	
}
