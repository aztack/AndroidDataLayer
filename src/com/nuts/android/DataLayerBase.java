package com.nuts.android;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.nuts.android.Callback.Callback1;

public abstract class DataLayerBase {
	public interface Handler<T> {
		public void onSuccess(T data);
	}

	static JSONParser parser = new JSONParser();
	private static String baseUrl = null;
	
	abstract boolean initialize();
	abstract String getAbsoluteUrl(String relativeUrl);
	abstract boolean beforeRequest(Object obj);
	abstract void onReceiveResponse(int urlId, String response);

	protected Object getParameterForBeforeRequest() {
		return null;
	}

	/**
	 * HTTP GET
	 * 
	 * @param url
	 * @param handler
	 */
	void get(String url, final AsyncHttpResponseHandler handler) {
		if (!beforeRequest(getParameterForBeforeRequest())) {
			return;
		}
		AsyncHttpClient client = new AsyncHttpClient();
		String absUrl = getAbsoluteUrl(url);
		client.get(absUrl, handler);
	}

	/**
	 * HTTP POST
	 * 
	 * @param url
	 * @param params
	 * @param callback
	 */
	void post(String url, RequestParams params,
			final Callback1<JSONObject> callback) {
		if (!beforeRequest(getParameterForBeforeRequest())) {
			return;
		}
		AsyncHttpClient client = new AsyncHttpClient();
		client.post(getAbsoluteUrl(url), params,
				new AsyncHttpResponseHandler() {
					public void onSuccess(String response) {
						JSONObject json;
						try {
							json = (JSONObject) parser.parse(response);
							if (callback != null)
								callback.invoke(json);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				});
	}
}
