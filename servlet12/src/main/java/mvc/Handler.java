package mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

/**
 * 执行器
 * 
 * 封装了一个Method和
 * 与它对应的Object实例的对应关系
 * 
 * 提供了反射调用该Method的方法
 * 
 */
public class Handler {
	
	private Object obj; // 实例
	
	private Method method;
	
	
	public String execute(HttpServletRequest request) throws Exception {
		
		Object result=method.invoke(obj, request);
		
		return (String)result;
	}
	
	public String execute() throws Exception {
		
		Object result=method.invoke(obj);
		
		return (String)result;
	}


	public Handler() {
		
	}

	public Handler(Object obj, Method method) {
		this.obj = obj;
		this.method = method;
	}

	@Override
	public String toString() {
		return "Handler [obj=" + obj + ", method=" + method + "]";
	}

}












