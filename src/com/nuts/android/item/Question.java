// Auto Generated, DO NOT Modify
// Extends this class if needed
package com.nuts.android.item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.nuts.android.ItemBase;

public class Question extends ItemBase {
	public int acttype;// 动作类型
	public String bigpic;// 大图URL
	public int cai;// 踩数量
	public int ding;// 顶数量
	public String ctime;// 创建日期
	public String date;// 日期
	public int ishot;// 是否热门
	public String pic;// 小图URL
	public String qid;// ID
	public String question;// 内容文字

	public Question(){}
	public Question(Object obj){
		if(obj instanceof JSONObject)
			initialize((JSONObject)obj);
	}

	@Override
	public void initialize(JSONObject obj){
		Object o = null;

		o = obj.get("acttype");
		acttype = cast(o, o.getClass(), int.class);

		o = obj.get("bigpic");
		bigpic = cast(o, o.getClass(), String.class);

		o = obj.get("cai");
		cai = cast(o, o.getClass(), int.class);

		o = obj.get("ding");
		ding = cast(o, o.getClass(), int.class);

		o = obj.get("ctime");
		ctime = cast(o, o.getClass(), String.class);

		o = obj.get("date");
		date = cast(o, o.getClass(), String.class);

		o = obj.get("ishot");
		ishot = cast(o, o.getClass(), int.class);

		o = obj.get("pic");
		pic = cast(o, o.getClass(), String.class);

		o = obj.get("qid");
		qid = cast(o, o.getClass(), String.class);

		o = obj.get("question");
		question = cast(o, o.getClass(), String.class);
	}

	public static List<Question> fromList(Object o) {
		JSONArray list = (JSONArray)o;
		List<Question> l = new LinkedList<Question>();
		if(!(o instanceof JSONArray)) return l;
		for(Object x: list){
			JSONObject j = (JSONObject)x;
			l.add(new Question(j));
		}
		return l;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K> Map<K,Question> fromMap(Object o){
		JSONObject map = (JSONObject)o;
		Map<K,Question> m = new HashMap<K, Question>();
		if(!(o instanceof JSONObject)) return m;
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			K key = (K) entry.getKey();
			JSONObject obj = (JSONObject)entry.getValue();
			Question value = new Question(obj);
			m.put(key, value);
		}
		return m;
	}

}
