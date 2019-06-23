package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 1. 获取ServletContext对象
		// 事件对象会包含对 事件源 的引用
		ServletContext sc=sce.getServletContext();
		
		// 2. 获取当前项目映射的路径
		String path=sc.getContextPath();
		
		// 3. 将path存入ServletContext作用域
		sc.setAttribute("app", path);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
