package mvc;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {

	/**
	 * 返回该注解的值的方法
	 */
	String value();

	
	
	
	
}
