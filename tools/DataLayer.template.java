<%#encoding:utf-8 %>
// Auto Generated, DO NOT Modify
// Extends this class if needed
package <%= pkg %>;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.nuts.android.Callback;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import <%= type_pkg %>.*;

public class <%=name%> extends DataLayerBase {
	<% protocols.each_with_index do |(name,p),i| %>
	// <%=p.desc%>
	protected final static String <%=name.underscore.upcase%> = "<%=p.url%>?<%=p.fmt_string%>";
	protected final static int _<%=name.underscore.upcase%> = <%=i%>;
	<% end %>

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
		return String.format("<%=base%>%s", relativeUrl);
	}
	 
	<% 
	protocols.each_with_index do |(name,p), i|
	if p.type == "GET"
	otype = p.output['type']
	%>
	// <%=p.desc%>
	public void <%= name %>(<%= p.java_signature %>,final Callback.Callback1<<%=otype%>> callback){
		String url = String.format(<%=name.underscore.upcase%>,<%=p.input_names%>);
		<%=p.type.downcase%>(url, new AsyncHttpResponseHandler(){
			public void onSuccess(String response){
				onReceiveResponse(_<%=name.underscore.upcase%>,response);
				JSONObject json;
				try {
					json = (JSONObject) parser.parse(response);
					<%=otype%> x = new <%=otype%>(json);
					if (callback != null)
						callback.invoke(x);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
	}

	<% elsif p.type == "POST" %>
	// <%= p.desc %>
	public void <%= name %>(<%= p.java_signature %>,
			final Callback.Callback1<JSONObject> callback) {
		RequestParams params = new RequestParams();
		<% p.inputs.each do |(name,input)| 
			t = input.type.downcase
		%>
		<% if t == "integer" or t == "int" %>
		params.put("<%=name%>", String.valueOf(<%= name %>));
		<% else %>
		params.put("<%=name%>", <%=name%>);
		<% end %>
		<% end %>
		<%=p.type.downcase%>(<%=name.underscore.upcase%>, params, callback);
	}

	<% end%>
	<% end %>
}