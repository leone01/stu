package demo;

import mvc.Handler;
import mvc.HandlerMapping;

public class Test6 {

	public static void main(String[] args) throws Exception {
		HandlerMapping hm=new HandlerMapping();
		
		hm.parseController("demo.Demo");
		
		Handler handler=hm.getHandler("/listUser.do");
		
		String path=handler.execute();
		
		System.out.println("path="+path);
		
	}

}
