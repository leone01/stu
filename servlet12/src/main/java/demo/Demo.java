package demo;

import mvc.RequestMapping;

public class Demo {
	
	@RequestMapping("/listUser.do")
	public String listUser() {
		
		return "/listUsers.jsp";
	}

	
	@RequestMapping("/addUser.do")
	public String addUser() {
		
		return "/addUser.jsp";
	}
	
	
	
}
