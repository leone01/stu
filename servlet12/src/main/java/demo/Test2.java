package demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import mvc.RequestMapping;

public class Test2 {

	public static void main(String[] args) throws ClassNotFoundException {
		// 动态加载 demo.Demo类
		Class cz=Class.forName("demo.Demo");
		
		Method[] ms=cz.getDeclaredMethods();
		
		for(Method method:ms) {
			// 返回该方法上声明的所有注解
			Annotation[] as=method.getAnnotations();
			
			// 找到RequestMapping类型的注解
			if(as!=null) {
				for(Annotation an:as) {
					// 判断是否是RequestMapping类型
					if(an instanceof RequestMapping) {
						
						RequestMapping rm=(RequestMapping)an;
						String value=rm.value();
						System.out.println("method="+method+",an="+value);
					}
				}
			}
		}
	}
}







