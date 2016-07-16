package com.jorge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This says this class is a controller
//@RequestMapping("/user") // This attribute makes a common prefix for the routes of a controller. 
                           // So you can write @RequestMapping("/list") and @RequestMapping("/{id}/{field}") 
						   // instead of @RequestMapping("/user/list") and @RequestMapping("/user/{id}/{field}")
public class UserController {
	
	@RequestMapping("/user/list") // How to call userList() method from URL => http://localhost:8080/spring3_mvc/user/list 
								  // Takes the route as a parameter. A request with the /user/list route will execute the userList() method.
								  // Route in file directory is /src/main/webapp/WEB-INF/jsp/user/list
								  // list refers to a jsp file (list.jsp) with HTML code inside
	//@ResponseBody //If we put this annotation, the result on browser would be return value ('Here we are!'), not the list.jsp file ('There are many users.')
	/*
	public String userList() {
		//return "Here we are!";
		return "user/list"; // Return "/user/list" value without @ResponseBody annotation shows list.jsp
	}
	*/
	
	public void userList(Model model) {
		model.addAttribute("nbUsers", 13); // Passing attribute to jsp result. try it: http://localhost:8080/spring3_mvc/user/list
	}
	
	@RequestMapping("/user/{id}/{field}") //Using dynamic route parameters in a controller method. Try it: http://localhost:8080/spring3_mvc/user/7/email
	@ResponseBody
	public String showUserField(@PathVariable("id") Long userId, @PathVariable("field") String field) {
		return "ID: " + userId + ", Field: " + field;
	}
	
	@RequestMapping("tiles") // Try it: http://localhost:8080/spring3_mvc/tiles
	public String toUseWithTiles() {
		return "home"; // You must create home.jsp file in '/WEB-INF/jsp/' path without headers, footers, etc, just a jsp file body
					   // The content of home.jsp will be included as body of template.jsp
	}
	
	@RequestMapping("tiles2") // Try it: http://localhost:8080/spring3_mvc/tiles2
	public String toUseWithTilesAndPath() {
		return "user/list2"; // You must create list2.jsp file in '/WEB-INF/jsp/user' path without headers, footers, etc, just a jsp file body
					   		 // The content of list2.jsp will be included as body of template.jsp
	}
	
	@RequestMapping("tilesMain") // Try it: http://localhost:8080/spring3_mvc/tilesMain
	public String toUseWithTilesMain() {
		return "main_home";  // You must create main_home.jsp file in '/WEB-INF/jsp' path without headers, footers, etc, just a jsp file body
					   		 // The content of main_home.jsp will be included as body of template1.jsp
	}
	
	@RequestMapping("tilesSec") // Try it: http://localhost:8080/spring3_mvc/tilesSec
	public String toUseWithTilesSec() {
		return "secondary_home"; // You must create secondary_home.jsp file in '/WEB-INF/jsp' path without headers, footers, etc, just a jsp file body
					   		     // The content of secondary_home.jsp will be included as body of template2.jsp
	}
	
	@RequestMapping("tilesSec2") // Try it: http://localhost:8080/spring3_mvc/tilessec2
	public String toUseWithTilesSec2() {
		return "secondary_home2"; // You must create secondary_home2.jsp file in '/WEB-INF/jsp' path without headers, footers, etc, just a jsp file body
					   		      // The content of secondary_home2.jsp will be included as body of template2.jsp
	}
	
	@RequestMapping("i18n") // Internationalization example. Try it: http://localhost:8080/spring3_mvc/i18n
	public String i18n() {
		return "multilang"; 
	}
}
