package demo;

import mvc.RequestMapping;

public class Demo2 {
	
	@RequestMapping("/delUser.do")
	public String delUser() {
		
		return "/delUser.jsp";
	}

}
