package com.jk.volleydemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	protected static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getData1();

		getData2();

		getData3();
	}

	/** 客户端以普通的post方式进行提交,服务端返回字符串 **/
	private void getData1() {
		RequestQueue requestQueue = Volley
				.newRequestQueue(getApplicationContext());

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				HOST + REQ_URL.USER_LOGIN, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.i(TAG, "response -> " + response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.i(TAG, error.getMessage());
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				// 在这里设置需要post的参数
				Map<String, String> map = new HashMap<String, String>();
				map.put("account", "luzong");
				map.put("password", "12345");
				return map;
			}
		};
		requestQueue.add(stringRequest);
	}

	/** 以json串的方式提交请求获取jsonObject **/
	private void getData2() {
		RequestQueue requestQueue = Volley
				.newRequestQueue(getApplicationContext());
		Map<String, String> map = new HashMap<String, String>();
		map.put("account", "luzong");
		map.put("password", "12345");
		JSONObject jsonObject = new JSONObject(map);
		JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(
				Method.POST, HOST + REQ_URL.USER_LOGIN, jsonObject,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, "response -> " + response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e(TAG, error.getMessage(), error);
					}
				}) {
			// 注意此处override的getParams()方法,在此处设置post需要提交的参数根本不起作用
			// 必须象上面那样,构成JSONObject当做实参传入JsonObjectRequest对象里
			// 所以这个方法在此处是不需要的
			// @Override
			// protected Map<String, String> getParams() {
			// Map<String, String> map = new HashMap<String, String>();
			// map.put("name1", "value1");
			// map.put("name2", "value2");

			// return params;
			// }

			@Override
			public Map<String, String> getHeaders() {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Accept", "application/json");
				headers.put("Content-Type", "application/json; charset=UTF-8");

				return headers;
			}
		};
		requestQueue.add(jsonRequest);
	}

	/** 以參數的方式提交請求获取jsonobejct **/
	private void getData3() {
		RequestQueue requestQueue = Volley
				.newRequestQueue(getApplicationContext());

		Map<String, String> map = new HashMap<String, String>();
		map.put("account", "luzong");

		// 自定义的request
		Request<JSONObject> request = new NormalPostRequest(HOST
				+ REQ_URL.VISIT_CARD_EDIT_REQUEST,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							Log.i(TAG, response.getString("code"));
							Log.d(TAG, "response -> " + response.toString());
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e(TAG, error.getMessage(), error);
					}
				}, map);
		requestQueue.add(request);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** 主机地址 **/
	public static final String HOST = "http://www.wanhuipay.net/";

	/** 请求地址 **/
	public static final class REQ_URL {
		/** 登录 **/
		public static final String USER_LOGIN = "wh/app.do?method=AppLogin";
		/** 注册 **/
		public static final String USER_REGISTER = "wh/app.do?method=Registered";
		/** 请求编辑名片 **/
		public static final String VISIT_CARD_EDIT_REQUEST = "wh/app.do?method=AppEdit";
		/** 提交同步名片 **/
		public static final String SUBMIT_EDIT = "wh/app.do?method=AppEditSubmit";
	}
}
