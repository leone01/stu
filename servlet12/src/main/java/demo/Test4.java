package demo;

import java.lang.reflect.Method;
import java.util.Scanner;

import mvc.RequestMapping;

public class Test4 {

	public static void main(String[] args) throws Exception {
		Scanner in=new Scanner(System.in);
		System.out.println("请输入包名.类名：");
		String className=in.nextLine();
		System.out.println("请输入url：");
		String url=in.nextLine();
		
		//System.out.println("className="+className+",url="+url);
		
		Class cz=Class.forName(className);
		
		Object obj=cz.newInstance();
		
		Method[] ms=cz.getDeclaredMethods();
		for(Method method:ms) {
			RequestMapping rm =
					method.getAnnotation(RequestMapping.class);
			if(url.equals(rm.value())) {
				Object result=method.invoke(obj);
				System.out.println("result="+result);
			}
		}
	}

}
