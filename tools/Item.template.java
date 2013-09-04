<%#encoding:utf-8 %>
// Auto Generated, DO NOT Modify
// Extends this class if needed
package <%= pkg %>;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.nuts.android.ItemBase;

public class <%= name %> extends ItemBase {
	<% each do |n,t|%>
	public <%=t['type']%> <%= n %>;// <%=t['desc']%>
	<% end %>

	public <%= name %>(){}
	public <%= name %>(Object obj){
		if(obj instanceof JSONObject)
			initialize((JSONObject)obj);
	}

	@Override
	public void initialize(JSONObject obj){
		Object o = null;
	<% each do |n,t| %>

		<% type,downcase = t['type'],t['type'].downcase %>
		<%if downcase == 'int' or downcase =='integer' or downcase == 'string' %>
		o = obj.get("<%= n %>");
		<%= n %> = cast(o, o.getClass(), <%=type%>.class);
		<% elsif type['<'].nil? %>
		o = obj.get("<%= n %>");
		<%= n %> = new <%=type%>(o);
		<% elsif type.scan /^(.*?)<(.*)>$/%>
			<% if type[','] and type['Map'] %>
			o = obj.get("<%=n%>");
		<%= n %> = <%=$2.split(',').last%>.fromMap(o);
			<% else %>
		o = obj.get("<%=n%>");
		<%= n %> = <%=$2%>.fromList(o);
			<% end %>			
		<% else %>
		//ERROR: Don't known type '<%=type%>' of <%=n%>
		<% end %>
	<% end %>
	}

	public static List<<%=name%>> fromList(Object o) {
		JSONArray list = (JSONArray)o;
		List<<%=name%>> l = new LinkedList<<%=name%>>();
		if(!(o instanceof JSONArray)) return l;
		for(Object x: list){
			JSONObject j = (JSONObject)x;
			l.add(new <%=name%>(j));
		}
		return l;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <K> Map<K,<%=name%>> fromMap(Object o){
		JSONObject map = (JSONObject)o;
		Map<K,<%=name%>> m = new HashMap<K, <%=name%>>();
		if(!(o instanceof JSONObject)) return m;
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			K key = (K) entry.getKey();
			JSONObject obj = (JSONObject)entry.getValue();
			<%=name%> value = new <%=name%>(obj);
			m.put(key, value);
		}
		return m;
	}

}