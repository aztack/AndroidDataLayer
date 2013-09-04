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

public class PostResponse extends ItemBase {
	public int succ;// 
	public String error;// 

	public PostResponse(){}
	public PostResponse(Object obj){
		if(obj instanceof JSONObject)
			initialize((JSONObject)obj);
	}

	@Override
	public void initialize(JSONObject obj){
		Object o = null;

		o = obj.get("succ");
		succ = cast(o, o.getClass(), int.class);

		o = obj.get("error");
		error = cast(o, o.getClass(), String.class);
	}

	public static List<PostResponse> fromList(Object o) {
		JSONArray list = (JSONArray)o;
		List<PostResponse> l = new LinkedList<PostResponse>();
		if(!(o instanceof JSONArray)) return l;
		for(Object x: list){
			JSONObject j = (JSONObject)x;
			l.add(new PostResponse(j));
		}
		return l;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K> Map<K,PostResponse> fromMap(Object o){
		JSONObject map = (JSONObject)o;
		Map<K,PostResponse> m = new HashMap<K, PostResponse>();
		if(!(o instanceof JSONObject)) return m;
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			K key = (K) entry.getKey();
			JSONObject obj = (JSONObject)entry.getValue();
			PostResponse value = new PostResponse(obj);
			m.put(key, value);
		}
		return m;
	}

}
