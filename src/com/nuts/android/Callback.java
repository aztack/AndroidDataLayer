package com.nuts.android;

public class Callback {
	/**
	 * naming convention:
	 * CallbackN[R][E]
	 * N= parameter number
	 * R= with return type R
	 */
	
	public interface Callback0{
		public void invoke();
	}
	
	public interface Callback0R<R>{
		public R invoke();
	}
	
	public interface Callback1<T>{
		public void invoke(T obj);
	}
	
	public interface Callback1R<T,R>{
		public R invoke(T obj);
	}
	
	public interface Callback2<T1,T2>{
		public void invoke(T1 obj1, T2 obj2);
	}
	
	public interface Callback2R<T1,T2,R>{
		public R invoke(T1 obj1,T2 obj2);
	}
	
	public interface Callback3<T1,T2,T3>{
		public void invoke(T1 obj1, T2 obj2, T3 obj3);
	}
	
	public interface Callback3R<T1,T2,T3,R>{
		public R invoke(T1 obj1, T2 obj2, T3 obj3);
	}
}