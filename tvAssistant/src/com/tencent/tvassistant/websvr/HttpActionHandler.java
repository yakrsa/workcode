package com.tencent.tvassistant.websvr;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import android.content.Intent;
import android.content.ContextWrapper;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.util.Log;

public class HttpActionHandler implements HttpRequestHandler {

	private String webRoot;
	public String TAG="httpservice";
	public HttpActionHandler(String webRoot) {
		// TODO Auto-generated constructor stub
		this.webRoot=webRoot;
	}

	public void handle(HttpRequest request, HttpResponse response,
			HttpContext context) throws HttpException, IOException {
		// TODO Auto-generated method stub
		
		String uri=URLDecoder.decode(request.getRequestLine().getUri(),"UTF-8");
		StringEntity entity=null;
		Log.i(TAG, "uri:"+uri);
		if (uri.startsWith("/Action?")){
			entity=new StringEntity("{\"code\":0,\"msg\":\"success\"}", "utf-8");
			uri=uri.substring(8);
			Log.i(TAG, "uri2:"+uri);
			String[] params=uri.split("&");
			Log.i(TAG, "params 0:"+params[0]);
			Log.i(TAG, "params 1:"+params[1]);
			Map<String, String> pmap = new HashMap<String, String>();
			String[] kv;
			for(int i=0;i<params.length;i++){
				kv=params[i].split("=");
				Log.i(TAG, "kv 0:"+kv[0]);
				Log.i(TAG, "kv 1:"+kv[1]);
				pmap.put(kv[0], kv[1]);
				
				Log.i(TAG, "i:"+i);
			}
			String cmd=pmap.get("cmd");
			String extra=pmap.get("extra");
			Log.i(TAG, "cmd:"+cmd+"\nextra:"+extra);
			if(cmd.equals("push")){
				WebService.ACTION="push";
				WebService.isTODO=true;
				if (extra.equals("video"))
					WebService.EXTRA="video";
				else if (extra.equals("photo"))
					WebService.EXTRA="photo";
				else
					WebService.EXTRA="";
			}
			else{
				WebService.ACTION="nothing";
			}
			
		}
		else{
			entity=new StringEntity("{\"code\":-1,\"msg\":\"您所调用的方法暂不支持，请检查所用方法是否正确\"}", "utf-8");
		}
		
		response.setStatusCode(HttpStatus.SC_OK);
		response.setHeader("Content-Type", "text/plain; charset=utf-8");
		response.setEntity(entity);
	}
	


}
