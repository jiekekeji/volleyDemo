package com.jk.volleydemo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

/** .客户端以参数的post方式进行提交,服务端返回json串 **/
public class NormalPostRequest extends Request<JSONObject> {
	private Map<String, String> mMap;
	private Listener<JSONObject> mListener;

	public NormalPostRequest(String url, Listener<JSONObject> listener,
			ErrorListener errorListener, Map<String, String> map) {
		super(Request.Method.POST, url, errorListener);

		mListener = listener;
		mMap = map;
	}

	// mMap是已经按照前面的方式,设置了参数的实例
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return mMap;
	}

	// 此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		try {
			// solution 1:
			String jsonString = new String(response.data, "UTF-8");
			Log.i("jsonString", jsonString);
			jsonString = URLDecoder.decode(jsonString, "utf-8");
			Log.i("res", jsonString);
			// solution 2:
			// response.headers.put(HTTP.CONTENT_TYPE,
			// response.headers.get("content-type"));
			//
			// String jsonString = new String(response.data,
			// HttpHeaderParser.parseCharset(response.headers));
			// Log.i("jsonString", jsonString);
			return Response.success(new JSONObject(jsonString),
					HttpHeaderParser.parseCacheHeaders(response));

		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		mListener.onResponse(response);
	}
}