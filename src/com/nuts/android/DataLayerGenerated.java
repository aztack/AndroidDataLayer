// Auto Generated, DO NOT Modify
// Extends this class if needed
package com.nuts.android;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.nuts.android.Callback;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import com.nuts.android.item.*;

public class DataLayerGenerated extends DataLayerBase {
	// 获取某天问题列表和黄历
	protected final static String GET_QUESTION_LIST = "/api/getQuestionList/?day=%d";
	protected final static int _GET_QUESTION_LIST = 0;
	// 根据给定id数组获取问题列表
	protected final static String GET_QUESTION_BY_QIDS = "/api/getQuestionByQids/?qids=%s";
	protected final static int _GET_QUESTION_BY_QIDS = 1;
	// 顶踩操作
	protected final static String POST_ACT = "/api/postAct?qid=%s&act=%d&mark=%s";
	protected final static int _POST_ACT = 2;

	@Override
	protected boolean initialize() {
		return true;
	}

	@Override
	boolean beforeRequest(Object obj) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	void onReceiveResponse(int urlId, String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getAbsoluteUrl(String relativeUrl) {
		// TODO: modify the base url
		return String.format("http://jiecao.kaixin001.com%s", relativeUrl);
	}
	 
	// 获取某天问题列表和黄历
	public void getQuestionList(int day,final Callback.Callback1<QuestionAndAlmanacOfDay> callback){
		String url = String.format(GET_QUESTION_LIST,day);
		get(url, new AsyncHttpResponseHandler(){
			public void onSuccess(String response){
				onReceiveResponse(_GET_QUESTION_LIST,response);
				JSONObject json;
				try {
					json = (JSONObject) parser.parse(response);
					QuestionAndAlmanacOfDay x = new QuestionAndAlmanacOfDay(json);
					if (callback != null)
						callback.invoke(x);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 根据给定id数组获取问题列表
	public void getQuestionByQids(String qids,final Callback.Callback1<QuestionList> callback){
		String url = String.format(GET_QUESTION_BY_QIDS,qids);
		get(url, new AsyncHttpResponseHandler(){
			public void onSuccess(String response){
				onReceiveResponse(_GET_QUESTION_BY_QIDS,response);
				JSONObject json;
				try {
					json = (JSONObject) parser.parse(response);
					QuestionList x = new QuestionList(json);
					if (callback != null)
						callback.invoke(x);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 顶踩操作
	public void postAct(String qid,int act,String mark,
			final Callback.Callback1<JSONObject> callback) {
		RequestParams params = new RequestParams();
		params.put("qid", qid);
		params.put("act", String.valueOf(act));
		params.put("mark", mark);
		post(POST_ACT, params, callback);
	}

}
