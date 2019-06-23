package mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.UserDAO;
import domain.User;

/**
 * 子控制器
 * 
 * 封装每个请求具体的处理逻辑
 */
public class Controller {
	
	@RequestMapping("/listUser.do")
	public String listUser(HttpServletRequest request) throws Exception {
		
		UserDAO dao=new UserDAO();
		List<User> list=dao.listAllUser();

		request.setAttribute("list", list);
		
		return "listUsers";
	}
	
	
	@RequestMapping("/addUser-form.do")
	public String addUserForm(HttpServletRequest request) {
		
		return "addUser";
	}

	@RequestMapping("/addUser.do")
	public String addUser(HttpServletRequest request) {
		//1. 接收请求参数
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		
		//2. 表单验证
		if(username==null || "".equals(username)) {
			request.setAttribute("msg_usr", "用户名不能为空");
			return "addUser";
		}
		
		//3. 用户名不重复验证
		UserDAO dao=new UserDAO();
		// 如果当前能处理，就尽量处理
		// 如果当前处理不了，就抛出去
		
		try {
			boolean flag=dao.getUserByUsername(username);
			if(flag) {
				request.setAttribute("msg_usr", "该用户名已存在");
				return "addUser";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg_usr", e.getMessage());
			return "addUser";
		}
		
		
		//4. 调用DAO保存数据
		User user=new User(-1, username, password, email);
		boolean flag=dao.saveUser(user);
		
		if(flag) {
			// 重定向到用户列表
			return "redirect:/listUser.do";
		}
		
		//5. 返回页面的名称
		request.setAttribute("msg_usr", "添加失败");
		return "addUser";
	}

	

}





