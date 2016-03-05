package com.example.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

public class HttpThread extends Thread {
	// 配置您申请的KEY
	public static final String APPKEY = "4e3ac1b60dda9ad991e0f0605dd671ed";
	private static ListView listView;
	private static EditText input;
	private static Context context;
	private static Handler handler;
	private static MyAdapter adapter;
	final static List<Ben> list = new ArrayList<>();
	public HttpThread() {

	}

	public HttpThread(ListView listView, EditText input, Context context,
			Handler handler, MyAdapter adapter) {
		super();
		this.listView = listView;
		this.input = input;
		this.context = context;
		this.handler = handler;
		this.adapter = adapter;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		resluts();
	}

	private static void resluts() {
		String result = null;
		String url = "http://op.juhe.cn/robot/index";// 请求接口地址
		Map params = new HashMap();// 请求参数
		params.put("key", APPKEY);// 您申请到的本接口专用的APPKEY
		params.put("info", input.getText().toString());// 要发送给机器人的内容，不要超过30个字符
		params.put("dtype", "json");// 返回的数据的格式，json或xml，默认为json
		result = net(url, params);
	}

	public static String net(String strUrl, Map params) {
		strUrl = strUrl + "?" + urlencode(params);
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String len;
			while ((len = br.readLine()) != null) {
				sb.append(len);
			}

			// Log.v("As", sb.toString());
			final JsonInfo info = getjson(sb.toString());
			// Log.v("As", info.toString());
		
			handler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					String conetnt = input.getText().toString();
					if (!TextUtils.isEmpty(conetnt)) {
						Ben ben2 = new Ben(conetnt, Ben.TYPE_SEND);
						list.add(ben2);
						Ben.TYPE_RESULT = info.getCode();
						Ben ben = new Ben(info.getContent(), Ben.TYPE_RESULT);
						Log.v("As", ben.toString());
						list.add(ben);
						adapter.getlist(list);
						listView.setAdapter(adapter);
						input.setText("");
						adapter.notifyDataSetChanged();
						listView.setSelection(list.size());
					}
				}
			});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=")
						.append(URLEncoder.encode(i.getValue() + "", "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static JsonInfo getjson(String json) {
		try {
			JsonInfo info;
			JSONObject object = new JSONObject(json);
			JSONObject bens = object.getJSONObject("result");
			int code = bens.getInt("code");
			String content = bens.getString("text");
			info = new JsonInfo();
			info.setContent(content);
			info.setCode(code);
			return info;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
