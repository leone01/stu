package mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 维护了用户请求url和Handler
 * 的映射关系
 * 
 * 提供了根据url查询Handler的方法
 * 
 */
public class HandlerMapping {
	
	private Map<String, Handler> map =
			 new HashMap<String, Handler>();
	
	/**
	 * 封装了Map集合的初始化逻辑
	 * 向Map中添加键值对
	 * key:   url -> RequestMapping注解的值
	 * value: Handler
	 * @throws Exception 
	 */
	public void parseController(String className) throws Exception {
		Class cz=Class.forName(className);
		
		Object obj=cz.newInstance(); // Controller的实例
		
		Method[] ms=cz.getDeclaredMethods();
		
		for(Method method:ms) {
			RequestMapping rm =
					method.getAnnotation(RequestMapping.class);
			
			if(rm!=null) {
				// RequestMapping注解的值，即用户请求的url
				String url=rm.value();
				
				// 保存method与controller实例的对应关系
				Handler handler=new Handler(obj, method);
				
				map.put(url, handler);
			}
		}
	}
	
	
	/**
	 * 基于url查询对应handler的方法
	 * @param url
	 * @return
	 */
	public Handler getHandler(String url) {
		return map.get(url);
	}
	

}









