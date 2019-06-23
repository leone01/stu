package demo;

import static org.hamcrest.CoreMatchers.containsString;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mvc.RequestMapping;

public class Test3 {

	public static void main(String[] args) throws Exception {
		
		String url="/listUser.do";
		
		Class cz=Class.forName("demo.Demo");
		
		// 反射创建一个实例
		Object obj=cz.newInstance();
		
		Method[] ms=cz.getDeclaredMethods();
		
		for(Method method:ms) {
			// 获取method上添加的RequestMapping类型的注解
			RequestMapping rm=
					method.getAnnotation(RequestMapping.class);
			if(rm!=null) {
				String value=rm.value();
				if(url.equals(value)) {
					// 当前方法是与url对应的方法
					// 动态调用该方法
					Object result=method.invoke(obj);
					System.out.println(result);
				}
			}
			
		}
		
		
	}

}



