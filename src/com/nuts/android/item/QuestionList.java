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

public class QuestionList extends ItemBase {
	public List<Question> list;// 

	public QuestionList(){}
	public QuestionList(Object obj){
		if(obj instanceof JSONObject)
			initialize((JSONObject)obj);
	}

	@Override
	public void initialize(JSONObject obj){
		Object o = null;

		o = obj.get("list");
		list = Question.fromList(o);
	}

	public static List<QuestionList> fromList(Object o) {
		JSONArray list = (JSONArray)o;
		List<QuestionList> l = new LinkedList<QuestionList>();
		if(!(o instanceof JSONArray)) return l;
		for(Object x: list){
			JSONObject j = (JSONObject)x;
			l.add(new QuestionList(j));
		}
		return l;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K> Map<K,QuestionList> fromMap(Object o){
		JSONObject map = (JSONObject)o;
		Map<K,QuestionList> m = new HashMap<K, QuestionList>();
		if(!(o instanceof JSONObject)) return m;
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			K key = (K) entry.getKey();
			JSONObject obj = (JSONObject)entry.getValue();
			QuestionList value = new QuestionList(obj);
			m.put(key, value);
		}
		return m;
	}

}
