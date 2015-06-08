package com.jk.my.volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestData {

	public static final String URL = "http://110.5.27.4:8080/D001/servlet/TestServlet";
	protected static final String TAG = RequestData.class.getSimpleName();

	/**
	 * 客户端以普通的post方式进行提交, 服务端返回字符串
	 * 
	 * @param ct
	 */
	public static void getData1(Context ct) {
		RequestQueue requestQueue = Volley.newRequestQueue(ct);

		StringRequest stringRequest = new StringRequest(Request.Method.POST,
				URL, new Response.Listener<String>() {
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
				map.put("page", "1");
				map.put("limit", "5");
				map.put("type", "id");
				return map;
			}
		};
		requestQueue.add(stringRequest);
	}

	/**
	 * 以json串的方式提交请求 获取jsonObject
	 * 
	 * @param ct
	 */
	public static void getData2(Context ct) {
		RequestQueue requestQueue = Volley.newRequestQueue(ct);
		Map<String, String> map = new HashMap<String, String>();

		map.put("page", "1");
		map.put("limit", "5");
		map.put("type", "id");

		JSONObject jsonObject = new JSONObject(map);
		JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(
				Method.POST, URL, jsonObject,
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

	/**
	 * 以參數的方式提交請求 获取jsonobejct
	 * 
	 * @param ct
	 */
	public static void getData3(Context ct) {
		RequestQueue requestQueue = Volley.newRequestQueue(ct);

		Map<String, String> map = new HashMap<String, String>();
		map.put("page", "1");
		map.put("limit", "5");
		map.put("type", "id");

		// 自定义的request
		Request<JSONObject> request = new PostRequestJson(URL,
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
}
