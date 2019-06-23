package mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HandlerMapping hm;

	@Override
	public void init() throws ServletException {
		// 获取配置文件的名称
		String configFileName=getServletConfig()
				.getInitParameter("config");
		
		// 初始化HandlerMapping
		hm=new HandlerMapping();
		
		// 使用dom4j读取mvc.xml文件中的配置
		SAXReader reader=new SAXReader();
		
		InputStream is=this.getClass().getClassLoader()
				.getResourceAsStream(configFileName);
		
		try {
			Document document=reader.read(is);
			Element rootEle=document.getRootElement();
			// 获取根标签下所有叫 bean的子标签
			List<Element> list=rootEle.elements("bean");
			for(Element ele:list) {
				String className=ele.attributeValue("class");
				if(className!=null) {
					hm.parseController(className);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取项目通用的编码规则
		String encode=getServletContext().getInitParameter("encode");
		// 解决乱码
		request.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);
		
		// 1. 获取用户请求的url
		// /servlet11/user/listUser.do
		String url=request.getRequestURI();
		
		// 将项目路径从url中截取出去
		url=url.substring(request.getContextPath().length());
		
		try {
			// 获取url对应的Handler
			Handler handler=hm.getHandler(url);
			
			if(handler==null) {
				// 说明url不存在
				// 服务器主动返回404响应状态码
				response.setStatus(404);
				return;
			}
			
			
			// 反射执行该方法
			String path=handler.execute(request);
			
			if(path.startsWith("redirect:")) {
				// 重定向  redirect:/listUser.do
				path=path.substring("redirect:".length());
				
				// 项目外，直接重定向
				if(path.startsWith("http:")) {
					response.sendRedirect(path);
				}else {
					// 拼接项目路径
					path=request.getContextPath()+path;
					response.sendRedirect(path);
				}
			}else {
				path="/WEB-INF/jsp/"+path+".jsp";
				
				// 实现转发
				request.getRequestDispatcher(path)
				            .forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter()
			   .write("服务器出现异常，请稍后重试");
			return;
		}
	}

}
