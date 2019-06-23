package demo;

import java.lang.reflect.Field;

public class Test5 {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		Class cz=Demo.class;
		
		Field f1=cz.getDeclaredField("username");
		
		Object obj=cz.newInstance();
		
		// 修改该属性的访问权限
		f1.setAccessible(true);

		System.out.println("f1="+f1.get(obj));
	}

}






