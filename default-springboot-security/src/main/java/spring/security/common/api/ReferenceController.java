package spring.security.common.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.security.common.bean.User;

@RestController
@RequestMapping("/home")
public class ReferenceController {
	
	@Autowired
	private User user;
	
	@RequestMapping("/hello")
	String home() {
		  return "Hello World!";
	}
	
	@RequestMapping("/user")
	public User user() {
		
		if(null==user) {
			user = new User();
		}
		
		user.setName("sheikh");
		user.setPassword("*****");
		
		return user;
	}
	
}

/**		 @RestController and @RequestMapping Annotations
 *  
 *  They are known as stereotype annotations
 *  It provides hints to spring boot that the class plays a specific role
 *  
 *  In this case, our class is a web @Controller,
 *  So Spring considers it when handling incoming web requests
 *  
 *  The @RequestMapping annotation provides “routing” information
 *  It tells Spring that any HTTP request with the '/hello' path
 *  Should be mapped to the home() method
 *  
 *  The @RestController annotation tells Spring to render
 *  the resulting string directly back to the caller
 *  
 *  The @RestController and @RequestMapping annotations are Spring MVC annotations
 *  (they are not specific to Spring Boot)
 */