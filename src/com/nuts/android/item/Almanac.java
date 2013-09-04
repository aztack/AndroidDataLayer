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

public class Almanac extends ItemBase {
	public String day;// 日期
	public String suit;// 宜
	public String taboos;// 忌

	public Almanac(){}
	public Almanac(Object obj){
		if(obj instanceof JSONObject)
			initialize((JSONObject)obj);
	}

	@Override
	public void initialize(JSONObject obj){
		Object o = null;

		o = obj.get("day");
		day = cast(o, o.getClass(), String.class);

		o = obj.get("suit");
		suit = cast(o, o.getClass(), String.class);

		o = obj.get("taboos");
		taboos = cast(o, o.getClass(), String.class);
	}

	public static List<Almanac> fromList(Object o) {
		JSONArray list = (JSONArray)o;
		List<Almanac> l = new LinkedList<Almanac>();
		if(!(o instanceof JSONArray)) return l;
		for(Object x: list){
			JSONObject j = (JSONObject)x;
			l.add(new Almanac(j));
		}
		return l;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K> Map<K,Almanac> fromMap(Object o){
		JSONObject map = (JSONObject)o;
		Map<K,Almanac> m = new HashMap<K, Almanac>();
		if(!(o instanceof JSONObject)) return m;
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			K key = (K) entry.getKey();
			JSONObject obj = (JSONObject)entry.getValue();
			Almanac value = new Almanac(obj);
			m.put(key, value);
		}
		return m;
	}

}
