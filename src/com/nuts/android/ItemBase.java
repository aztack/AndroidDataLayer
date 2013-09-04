package com.nuts.android;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;

/**
 * ���ݽṹ���� ��JSON����ת���ɶ�Ӧ��Java��ʵ�� Ҫ��JSON�е�key����Java���е�������һ�¡������շ���������û��ǰ��׺
 * 
 * @author aztack
 * 
 */
public abstract class ItemBase implements Serializable {

	private static final long serialVersionUID = 1L;

	abstract public void initialize(JSONObject obj);

	@SuppressWarnings("unchecked")
	protected <T> T cast(Object value, Class<?> from, Class<?> to) {
		if(from == to) return (T)value;
		if (from == String.class
				&& (to == int.class || to == Integer.class)) {
			return (T) Integer.valueOf((String) value);
		} else if (from != String.class && to == String.class) {
			return (T) String.valueOf(value);
		} else if ((from == Long.class || from == long.class)
				&& (to == int.class || to == Integer.class)) {
			return (T)Integer.valueOf(((Long)value).intValue());
		}
		return null;
	}
}
