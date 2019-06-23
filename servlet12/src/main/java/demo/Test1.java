package demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test1 {

	public static void main(String[] args) {
		
		Class cz=String.class;
		
		// 获取类中声明的所有属性
		Field[] fs=cz.getDeclaredFields();
		
		for(Field field:fs) {
			System.out.println(field);
		}
		
		// 获取类中声明的所有方法
		Method[] ms=cz.getDeclaredMethods();
		
		for(Method method:ms) {
			System.out.println(method);
		}


	}

}
